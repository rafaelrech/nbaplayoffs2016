package rio2016.bean;

import rech.bolao.bean.CommonBean;
import rio2016.dao.Rio2016MedalBetDao;

public class Rio2016MedalBet extends CommonBean {

	public int competitorId;
	public int sportId;
	public int userId;
	public int bet;
	public int score;

	private Rio2016Competitor competitor;
	private Rio2016Sport sport;
	private Rio2016User user;

	public Rio2016MedalBet() {
		super();
	}

	public Rio2016MedalBet(int competitorId, int sportId, int userId, int bet, int score) {
		super();
		this.competitorId = competitorId;
		this.sportId = sportId;
		this.userId = userId;
		this.bet = bet;
		this.score = score;
	}

	public int getCompetitorId() {
		return competitorId;
	}

	public void setCompetitorId(int competitorId) {
		this.competitorId = competitorId;
	}

	public int getSportId() {
		return sportId;
	}

	public void setSportId(int sportId) {
		this.sportId = sportId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getBet() {
		return bet;
	}

	public void setBet(int bet) {
		this.bet = bet;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public Rio2016Sport getSport() {
		return sport;
	}

	public void setSport(Rio2016Sport sport) {
		this.sport = sport;
	}

	public Rio2016Competitor getCompetitor() {
		return competitor;
	}

	public void setCompetitor(Rio2016Competitor competitor) {
		this.competitor = competitor;
	}

	public Rio2016User getUser() {
		return user;
	}

	public void setUser(Rio2016User user) {
		this.user = user;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Rio2016MedalBet.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Rio2016MedalBet.class, Rio2016MedalBetDao.getInstance().generateInsertStatement());
	}

}
