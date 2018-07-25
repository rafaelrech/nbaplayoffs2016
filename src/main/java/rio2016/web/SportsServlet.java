package rio2016.web;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.StringTokenizer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import rech.bolao.bean.User;
import rech.bolao.util.BaseSessionUtil;
import rech.bolao.web.WebUtils;
import rio2016.bean.Rio2016Competitor;
import rio2016.bean.Rio2016Participation;
import rio2016.bean.Rio2016Sport;
import rio2016.dao.Rio2016CompetitorDao;
import rio2016.dao.Rio2016ParticipationDao;
import rio2016.dao.Rio2016SportDao;
import rio2016.util.Rio2016SessionUtil;
import rio2016.util.Rio2016WebUtil;

@WebServlet("/rio2016/SportsServlet")
public class SportsServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	public SportsServlet() {
		super();
	}

	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// String source = request.getParameter("source");
		String target = request.getParameter("target");
		String action = request.getParameter("action");
		String pSportId = request.getParameter("sportId");
		String pCompetitorId = request.getParameter("competitorId");
		String pNewValue = request.getParameter("newvalue");

		HttpSession session = request.getSession();
		User u = (User) session.getAttribute("loggedUser");

		if (!u.getRole().equalsIgnoreCase("admin")) {
			fail(response, session, "You are not an admin", getRedirectInfo(target));
			return;
		}
		if (action == null || action.isEmpty()) {
			fail(response, session, "'action' is null or empty", getRedirectInfo(target));
			return;
		}
		if (pSportId == null || pSportId.isEmpty()) {
			fail(response, session, "'sportId' is null or empty", getRedirectInfo(target));
			return;
		}
		int sportId = new Integer(pSportId).intValue();

		if (action.equalsIgnoreCase("UPDATE-RESULT")) {
			if (pCompetitorId == null || pCompetitorId.isEmpty()) {
				fail(response, session, "'competitorId' is null or empty", getRedirectInfo(target));
				return;
			}
			if (pNewValue == null || pNewValue.isEmpty()) {
				fail(response, session, "'newvalue' is null or empty", getRedirectInfo(target));
				return;
			}
			int competitorId = new Integer(pCompetitorId).intValue();
			int newValue = new Integer(pNewValue).intValue();

			int goldCount = 0;
			int silverCount = 0;
			int bronzeCount = 0;
			Rio2016Sport sport = Rio2016SportDao.getInstance().get(sportId);
			try {
				ArrayList<Rio2016Participation> results = Rio2016ParticipationDao.getInstance()
						.listAll(String.format(" SPORT_ID = %d AND RESULT >=1 AND RESULT <=3", sportId));
				for (Rio2016Participation part : results) {
					if (part.getCompetitorId() == competitorId) {
						continue;
					}
					switch (part.getResult()) {
					case 1:
						goldCount++;
						if (newValue == 1 && goldCount >= sport.getQtyGold()) {
							fail(response, session, "Número de ouros ultrapassado", getRedirectInfo(target));
							return;
						}
						break;
					case 2:
						silverCount++;
						if (newValue == 2 && silverCount >= sport.getQtySilver()) {
							fail(response, session, "Número de pratas ultrapassado", getRedirectInfo(target));
							return;
						}
						break;
					case 3:
						bronzeCount++;
						if (newValue == 3 && bronzeCount >= sport.getQtyBronze()) {
							fail(response, session, "Número de bronzes ultrapassado", getRedirectInfo(target));
							return;
						}
						break;
					}
				}
			} catch (SQLException e) {
			}
			Rio2016Participation part = Rio2016ParticipationDao.getInstance().get(competitorId, sportId);
			part.setResult(newValue);
			Rio2016ParticipationDao.getInstance().update(part);
		}

		if (action.equalsIgnoreCase("UPDATE-SPORT-STATUS")) {
			Rio2016Sport sport = Rio2016SportDao.getInstance().get(sportId);
			sport.setCompleted(new Integer(request.getParameter("newvalue")).intValue());
			Rio2016SportDao.getInstance().update(sport);
		}

		if (action.equalsIgnoreCase("UPDATE-SPORT-GOLD")) {
			Rio2016Sport sport = Rio2016SportDao.getInstance().get(sportId);
			sport.setQtyGold(new Integer(request.getParameter("newvalue")).intValue());
			Rio2016SportDao.getInstance().update(sport);
		}

		if (action.equalsIgnoreCase("UPDATE-SPORT-SILVER")) {
			Rio2016Sport sport = Rio2016SportDao.getInstance().get(sportId);
			sport.setQtySilver(new Integer(request.getParameter("newvalue")).intValue());
			Rio2016SportDao.getInstance().update(sport);
		}

		if (action.equalsIgnoreCase("UPDATE-SPORT-BRONZE")) {
			Rio2016Sport sport = Rio2016SportDao.getInstance().get(sportId);
			sport.setQtyBronze(new Integer(request.getParameter("newvalue")).intValue());
			Rio2016SportDao.getInstance().update(sport);
		}

		if (action.equalsIgnoreCase("PARTICIPANTS")) {
			StringBuffer text = new StringBuffer(request.getParameter("participants"));
			StringTokenizer st = new StringTokenizer(text.toString(), "\r\n");
			@SuppressWarnings("unchecked")
			ArrayList<Rio2016Sport> sports = (ArrayList<Rio2016Sport>) session
					.getAttribute(Rio2016SessionUtil.ALL_SPORTS);
			Rio2016Sport sport = null;
			for (Rio2016Sport rio2016Sport : sports) {
				if (rio2016Sport.getId() == sportId) {
					sport = rio2016Sport;
					break;
				}
			}
			ArrayList<Integer> saved = new ArrayList<Integer>();
			while (st.hasMoreElements()) {
				String participant = (String) st.nextElement();
				StringTokenizer st2 = new StringTokenizer(participant, "-");
				int competitorId = -1;
				String competitorName = "";
				String country = "";
				if (st2.countTokens() == 3) {
					// tem id (pode ser "-1")
					competitorId = new Integer(st2.nextToken()).intValue();
					competitorName = st2.nextToken();
					country = st2.nextToken();
				} else {
					// nao tem id
					competitorName = st2.nextToken();
					country = st2.nextToken();
				}

				if (competitorId > 0) {
					boolean found = false;
					for (Rio2016Participation participation : sport.getPaticipations()) {
						if (competitorId == participation.getCompetitorId()) {
							found = true;
							break;
						}
					}
					if (!found) {
						Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(competitorId, sportId));
					}
					saved.add(new Integer(competitorId));
				} else {
					Rio2016Competitor competitor = Rio2016CompetitorDao.getInstance()
							.getBy(String.format("UPPER(NAME)='%s' AND UPPER(COUNTRY)='%s' ", competitorName.toString(),
									country.toString()));
					if (competitor != null) {
						competitorId = competitor.getId();
						Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(competitorId, sportId));
						saved.add(new Integer(competitorId));
					} else {
						Rio2016Competitor newCompetitor = new Rio2016Competitor(-1, competitorName, country, "");
						Rio2016CompetitorDao.getInstance().insert(newCompetitor);
						competitorId = newCompetitor.getId();
						Rio2016ParticipationDao.getInstance().insert(new Rio2016Participation(competitorId, sportId));
						saved.add(new Integer(competitorId));
					}
				}

			}
			// remover os que nao foram salvos
			for (Rio2016Participation participation : sport.getPaticipations()) {
				boolean found = false;
				for (Integer savedCompetitorId : saved) {
					if (savedCompetitorId == participation.getCompetitorId()) {
						found = true;
						break;
					}
				}
				if (!found) {
					Rio2016ParticipationDao.getInstance().delete(participation.getCompetitorId(),
							participation.getSportId());
				}
			}
		}

		BaseSessionUtil.udpateSessionInfo(session, u);
		response.sendRedirect(getRedirectInfo(target));
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}

	private void fail(HttpServletResponse response, HttpSession session, String msg, String targetPage)
			throws IOException {
		System.out.println(msg);
		BaseSessionUtil.setSessionMessage(session, "FAIL! " + msg);
		response.sendRedirect(targetPage);
	}

	private String getRedirectInfo(String page) {
		return WebUtils.getRedirectInfo(page, Rio2016WebUtil.DBUTIL_JSP);
	}

}
