package rech.bolao.bean.xmlstats;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@JsonIgnoreProperties(ignoreUnknown = true)
public class Event {

	@JsonProperty("event_id")
	private String eventId;

	@JsonProperty("event_status")
	private String eventStatus;

	@JsonProperty("season_type")
	private String seasonType;

	@JsonProperty("start_date_time")
	private String startDateTime;

	@JsonProperty("site")
	private Site site;

	@JsonProperty("away_team")
	private Team awayTeam;

	@JsonProperty("home_team")
	private Team homeTeam;

	@JsonProperty("away_points_scored")
	private int awayPointsScored;

	@JsonProperty("home_points_scored")
	private int homePointsScored;

	@JsonProperty("away_period_scores")
	private int[] awayPeriodsScored;

	@JsonProperty("home_period_scores")
	private int[] homePeriodsScored;

	public Event() {
	}

	public String getEventId() {
		return eventId;
	}

	public void setEventId(String eventId) {
		this.eventId = eventId;
	}

	public int getAwayPointsScored() {
		return awayPointsScored;
	}

	public void setAwayPointsScored(int awayPointsScored) {
		this.awayPointsScored = awayPointsScored;
	}

	public int getHomePointsScored() {
		return homePointsScored;
	}

	public void setHomePointsScored(int homePointsScored) {
		this.homePointsScored = homePointsScored;
	}

	public String getEventStatus() {
		return eventStatus;
	}

	public void setEventStatus(String eventStatus) {
		this.eventStatus = eventStatus;
	}

	public String getStartDateTime() {
		return startDateTime;
	}

	public void setStartDateTime(String startDateTime) {
		this.startDateTime = startDateTime;
	}

	public Team getAwayTeam() {
		return awayTeam;
	}

	public void setAwayTeam(Team awayTeam) {
		this.awayTeam = awayTeam;
	}

	public Team getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(Team homeTeam) {
		this.homeTeam = homeTeam;
	}

	public String getSeasonType() {
		return seasonType;
	}

	public void setSeasonType(String seasonType) {
		this.seasonType = seasonType;
	}

	public Site getSite() {
		return site;
	}

	public void setSite(Site site) {
		this.site = site;
	}

	public int[] getAwayPeriodsScored() {
		return awayPeriodsScored;
	}

	public void setAwayPeriodsScored(int[] awayPeriodsScored) {
		this.awayPeriodsScored = awayPeriodsScored;
	}

	public int[] getHomePeriodsScored() {
		return homePeriodsScored;
	}

	public void setHomePeriodsScored(int[] homePeriodsScored) {
		this.homePeriodsScored = homePeriodsScored;
	}
}
