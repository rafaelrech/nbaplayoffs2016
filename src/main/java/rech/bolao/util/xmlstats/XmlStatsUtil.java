package rech.bolao.util.xmlstats;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Map;

import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.StatusLine;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.client.entity.GzipDecompressingEntity;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;

import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import rech.bolao.bean.LogEntry;
import rech.bolao.bean.xmlstats.Events;
import rech.bolao.bean.xmlstats.XmlstatsError;
import rech.bolao.dao.LoggingDao;

public class XmlStatsUtil {

	// static final String ACCESS_TOKEN =
	// "1f7277c6-4717-4f88-8b00-4f461ca97a7d";
	static final String ACCESS_TOKEN = "5788f47d-eb8f-4db7-a9f2-81bb4dccb010";
	static final String USER_AGENT_CONTACT = "rafael.rech@gmail.com";
	static final String TIME_ZONE = "America/New_York";
	static final String VERSION = "1.0";

	static final String USER_AGENT = "xmlstats-exjavahc/%s (%s)";
	static final String AUTHORIZATION = "Authorization";
	static final String BEARER_AUTH_TOKEN = "Bearer %s";
	static final String ACCEPT_ENCODING = "Accept-encoding";
	static final String GZIP = "gzip";

	private static String accessToken = String.format(BEARER_AUTH_TOKEN, ACCESS_TOKEN);
	private static String userAgent = String.format(USER_AGENT, VERSION, USER_AGENT_CONTACT);

	private static Map<String, String> teams = new HashMap<String, String>();

	private static volatile XmlStatsUtil instance;

	private XmlStatsUtil() {
	}

	public static XmlStatsUtil getInstance() {
		if (instance == null) {
			synchronized (XmlStatsUtil.class) {
				if (instance == null) {
					instance = new XmlStatsUtil();
				}
			}
		}
		return instance;
	}

	public static String calendar2String(Calendar c) {
		String formattedDate = "" + c.get(Calendar.YEAR) + ""
				+ ((c.get(Calendar.MONTH) + 1) > 10 ? (c.get(Calendar.MONTH) + 1) : "0" + (c.get(Calendar.MONTH) + 1))
				+ (c.get(Calendar.DATE) > 10 ? c.get(Calendar.DATE) : "0" + c.get(Calendar.DATE));
		return formattedDate;
	}

	public static Events queryTodayGames() {
		return queryGames(0);
	}

	public static Events queryGames(int offSet) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(c.getTimeInMillis() - (4 * 60 * 60 * 1000) + (offSet * 24 * 60 * 60 * 1000));
		return queryGamesByDate(c);
	}

	public static Events queryGamesByDate(Calendar c) {
		return queryGamesByDate(c, 1);
	}

	public static Events queryGamesByDate(Calendar c, int tries) {
		String requestDate = calendar2String(c);
		String requestURL = String.format("https://erikberg.com/events.json?sport=nba&date=%s", requestDate);
		// String requestURL =
		// String.format("http://localhost:8080/ROOT/MockStats?sport=nba&date=%s",
		// requestDate);

		try (CloseableHttpClient httpClient = HttpClientBuilder.create().setUserAgent(userAgent).build()) {
			HttpUriRequest request = new HttpGet(requestURL);
			request.addHeader(AUTHORIZATION, accessToken);
			request.addHeader(ACCEPT_ENCODING, GZIP);
			// Response handler deserializes json to Events POJO
			ResponseHandler<Events> responseHandler = new HttpResponseHandler<>(Events.class);
			Events events = httpClient.execute(request, responseHandler);
			LoggingDao.getInstance()
					.insert(new LogEntry(0, Calendar.getInstance(), "",
							String.format("XMLEvents: Call successfully returned %d events for %s",
									events.getEventsCount().intValue(), requestDate)));
			return events;
		} catch (HttpResponseException ex) {
			System.err.printf("Server returned HTTP status: %s. %s%n", ex.getStatusCode(), ex.getMessage());
			LoggingDao.getInstance().insert(new LogEntry(0, Calendar.getInstance(), "",
					String.format("Server returned HTTP status: %s. %s%n", ex.getStatusCode(), ex.getMessage())));
			if (ex.getStatusCode() == 429 && tries < 3) {
				try {
					Thread.sleep(5 * 1000);
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
				return queryGamesByDate(c, ++tries);
			}
		} catch (JsonParseException | JsonMappingException ex) {
			System.err.printf("XMLEvents: Error parsing json at line: %s. column: %s%n", ex.getLocation().getLineNr(),
					ex.getLocation().getColumnNr());
			LoggingDao.getInstance()
					.insert(new LogEntry(0, Calendar.getInstance(), "",
							String.format("XMLEvents: Error parsing json at line: %s. column: %s%n",
									ex.getLocation().getLineNr(), ex.getLocation().getColumnNr())));
			ex.printStackTrace();
		} catch (IOException ex) {
			System.err.println(ex.getMessage());
			LoggingDao.getInstance().insert(new LogEntry(0, Calendar.getInstance(), "",
					String.format("XMLEvents: Call failed. %s", ex.getMessage())));
		}
		return null;
	}

	public static String getGameId(String homeTeam, String awayTeam, Calendar date) {
		if (teams.isEmpty()) {
			teams.put("ATL", "atlanta-hawks");
			teams.put("BOS", "boston-celtics");
			teams.put("BKN", "brooklyn-nets");
			teams.put("CHA", "charlotte-hornets");
			teams.put("CHI", "chicago-bulls");
			teams.put("CLE", "cleveland-cavaliers");
			teams.put("DAL", "dallas-mavericks");
			teams.put("DEN", "denver-nuggets");
			teams.put("DET", "detroit-pistons");
			teams.put("GS", "golden-state-warriors");
			teams.put("HOU", "houston-rockets");
			teams.put("IND", "indiana-pacers");
			teams.put("LAC", "los-angeles-clippers");
			teams.put("LAL", "los-angeles-lakers");
			teams.put("MEM", "memphis-grizzlies");
			teams.put("MIA", "miami-heat");
			teams.put("MIL", "milwaukee-bucks");
			teams.put("MIN", "minnesota-timberwolves");
			teams.put("NO", "new-orleans-pelicans");
			teams.put("NY", "new-york-knicks");
			teams.put("OKC", "oklahoma-city-thunder");
			teams.put("ORL", "orlando-magic");
			teams.put("PHI", "philadelphia-76ers");
			teams.put("PHO", "phoenix-suns");
			teams.put("POR", "portland-trail-blazers");
			teams.put("SAC", "sacramento-kings");
			teams.put("SA", "san-antonio-spurs");
			teams.put("TOR", "toronto-raptors");
			teams.put("UTA", "utah-jazz");
			teams.put("WAS", "washington-wizards");
		}
		String formattedAway = teams.get(awayTeam);
		String formattedHome = teams.get(homeTeam);
		SimpleDateFormat sd = new SimpleDateFormat("yyyyMMdd");
		return String.format("%s-%s-at-%s", sd.format(date.getTime()), formattedAway, formattedHome);
	}

	static class HttpResponseHandler<T> implements ResponseHandler<T> {

		private final Class<T> clazz;

		HttpResponseHandler(Class<T> clazz) {
			this.clazz = clazz;
		}

		public T handleResponse(HttpResponse response) throws ClientProtocolException, IOException {
			ObjectMapper mapper = new ObjectMapper();
			StatusLine status = response.getStatusLine();
			HttpEntity entity = response.getEntity();
			if (entity == null) {
				throw new ClientProtocolException("Response is empty.");
			}

			// Decompress response if it is compressed
			if (GZIP.equals(entity.getContentEncoding())) {
				response.setEntity(new GzipDecompressingEntity(response.getEntity()));
			}

			int statusCode = status.getStatusCode();
			if (statusCode != HttpStatus.SC_OK) {
				String reason = status.getReasonPhrase();
				// If there is an error and the content type is json, it will be
				// an
				// XmlstatsError. See
				// https://erikberg.com/api/objects/xmlstats-error
				if ("application/json".equals(entity.getContentType().getValue())) {
					mapper.enable(DeserializationFeature.UNWRAP_ROOT_VALUE);
					XmlstatsError error = mapper.readValue(entity.getContent(), XmlstatsError.class);
					reason = error.getDescription();
				}
				throw new HttpResponseException(statusCode, reason);
			}
			return mapper.readValue(entity.getContent(), clazz);
		}
	}
}
