package nbaplayoffs2016.bean;

import nbaplayoffs2016.dao.Nba2016SeriesBetDao;
import rech.bolao.bean.CommonBean;

public class Nba2016SeriesBet extends CommonBean {

	public int bracketId;
	public int userId;
	public String winner;
	public int games;
	public int userScore;
	public Nba2016Series series;
	public Nba2016User user;

	public Nba2016SeriesBet() {
		super();
	}

	public Nba2016SeriesBet(int bracketId, int userId, String winner, int games, int userScore) {
		super();
		this.bracketId = bracketId;
		this.userId = userId;
		this.winner = winner;
		this.games = games;
		this.userScore = userScore;
	}

	public int getBracketId() {
		return bracketId;
	}

	public void setBracketId(int bracketId) {
		this.bracketId = bracketId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getGames() {
		return games;
	}

	public void setGames(int games) {
		this.games = games;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Nba2016SeriesBet.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Nba2016SeriesBet.class,
				Nba2016SeriesBetDao.getInstance().generateInsertStatement());
	}

	public Nba2016Series getSeries() {
		return series;
	}

	public void setSeries(Nba2016Series series) {
		this.series = series;
	}

	public Nba2016User getUser() {
		return user;
	}

	public void setUser(Nba2016User user) {
		this.user = user;
	}

}
