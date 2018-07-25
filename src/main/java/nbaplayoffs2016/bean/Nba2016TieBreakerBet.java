package nbaplayoffs2016.bean;

import nbaplayoffs2016.dao.Nba2016TieBreakerBetDao;
import rech.bolao.bean.CommonBean;

public class Nba2016TieBreakerBet extends CommonBean {

	public int tieBrackerId;
	public int userId;
	public String value;
	public int userScore;

	private Nba2016TieBreaker tieBreaker;
	private Nba2016User user;

	public Nba2016TieBreakerBet() {
		super();
	}

	public Nba2016TieBreakerBet(int tieBrackerId, int userId, String value, int userScore) {
		super();
		this.tieBrackerId = tieBrackerId;
		this.userId = userId;
		this.value = value;
		this.userScore = userScore;
	}

	public int getTieBrackerId() {
		return tieBrackerId;
	}

	public void setTieBrackerId(int tieBrackerId) {
		this.tieBrackerId = tieBrackerId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Nba2016TieBreakerBet.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Nba2016TieBreakerBet.class,
				Nba2016TieBreakerBetDao.getInstance().generateInsertStatement());
	}

	public Nba2016TieBreaker getTieBreaker() {
		return tieBreaker;
	}

	public void setTieBreaker(Nba2016TieBreaker tieBreaker) {
		this.tieBreaker = tieBreaker;
	}

	public Nba2016User getUser() {
		return user;
	}

	public void setUser(Nba2016User user) {
		this.user = user;
	}

}
