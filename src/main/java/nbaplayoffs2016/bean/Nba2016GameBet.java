package nbaplayoffs2016.bean;

import nbaplayoffs2016.dao.Nba2016GameBetDao;
import rech.bolao.bean.CommonBean;

public class Nba2016GameBet extends CommonBean {

	public int gameId;
	public int userId;
	public String winner;
	public int homeScore;
	public int visitScore;
	public int overTime;
	public int userScore;

	private Nba2016Game game;
	private Nba2016User user;

	public Nba2016GameBet() {
		super();
	}

	public Nba2016GameBet(int gameId, int userId, String winner, int overTime) {
		this(gameId, userId, winner, 0, 0, overTime, 0);
	}

	public Nba2016GameBet(int gameId, int userId, String winner, int homeScore, int visitScore, int overTime,
			int userScore) {
		super();
		this.gameId = gameId;
		this.userId = userId;
		this.winner = winner;
		this.homeScore = homeScore;
		this.visitScore = visitScore;
		this.overTime = overTime;
		this.userScore = userScore;
	}

	public int getGameId() {
		return gameId;
	}

	public void setGameId(int gameId) {
		this.gameId = gameId;
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

	public int getHomeScore() {
		return homeScore;
	}

	public void setHomeScore(int homeScore) {
		this.homeScore = homeScore;
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

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Nba2016GameBet.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Nba2016GameBet.class, Nba2016GameBetDao.getInstance().generateInsertStatement());
	}

	public Nba2016Game getGame() {
		return game;
	}

	public void setGame(Nba2016Game game) {
		this.game = game;
	}

	public Nba2016User getUser() {
		return user;
	}

	public void setUser(Nba2016User user) {
		this.user = user;
	}

}
