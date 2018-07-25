package nbaplayoffs2016.bean;

import java.util.ArrayList;
import java.util.Calendar;

import nbaplayoffs2016.dao.Nba2016GameDao;
import rech.bolao.bean.CommonBean;

public class Nba2016Game extends CommonBean {

	public int id;
	public String externalId;
	public int fase;
	public int bracketId;
	public Calendar data; // eventDate
	public String homeTeam;
	public int homeScore;
	public String visitTeam; // awayTeam
	public int visitScore; // awayScore
	public int overTime;
	public int completed;

	private Nba2016Series series;
	private ArrayList<Nba2016GameBet> bets = new ArrayList<Nba2016GameBet>();

	public Nba2016Game() {
		super();
	}

	public Nba2016Game(int id, String externalId, int fase, int bracketId, Calendar data, String homeTeam,
			int homeScore, String visitTeam, int visitScore, int overTime, int completed) {
		super();
		this.id = id;
		this.externalId = externalId;
		this.fase = fase;
		this.bracketId = bracketId;
		this.data = data;
		this.homeTeam = homeTeam;
		this.homeScore = homeScore;
		this.visitTeam = visitTeam;
		this.visitScore = visitScore;
		this.overTime = overTime;
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getExternalId() {
		return externalId;
	}

	public void setExternalId(String externalId) {
		this.externalId = externalId;
	}

	public int getFase() {
		return fase;
	}

	public int getBracketId() {
		return bracketId;
	}

	public void setBracketId(int bracketId) {
		this.bracketId = bracketId;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getHomeTeam() {
		return homeTeam;
	}

	public void setHomeTeam(String homeTeam) {
		this.homeTeam = homeTeam;
	}

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
	}

	public String getVisitTeam() {
		return visitTeam;
	}

	public void setVisitTeam(String visitTeam) {
		this.visitTeam = visitTeam;
	}

	public int getVisitScore() {
		return visitScore;
	}

	public void setVisitScore(int visitScore) {
		this.visitScore = visitScore;
	}

	public int getOverTime() {
		return overTime;
	}

	public void setOverTime(int overTime) {
		this.overTime = overTime;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Nba2016Game.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Nba2016Game.class, Nba2016GameDao.getInstance().generateInsertStatement());
	}

	public Nba2016Series getSeries() {
		return series;
	}

	public void setSeries(Nba2016Series series) {
		this.series = series;
	}

	public ArrayList<Nba2016GameBet> getBets() {
		return bets;
	}

	public void setBets(ArrayList<Nba2016GameBet> bets) {
		this.bets = bets;
	}

	public void addBet(Nba2016GameBet bet) {
		if (bets == null) {
			bets = new ArrayList<Nba2016GameBet>();
		}
		bets.add(bet);
	}

}
