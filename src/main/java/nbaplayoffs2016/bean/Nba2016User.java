package nbaplayoffs2016.bean;

import java.util.ArrayList;

import nbaplayoffs2016.dao.Nba2016UserDao;
import rech.bolao.bean.CommonBean;
import rech.bolao.bean.User;

public class Nba2016User extends CommonBean {

	private int userId;
	private int score;
	private User user;

	private ArrayList<Nba2016TieBreakerBet> tbBets = new ArrayList<Nba2016TieBreakerBet>();
	private ArrayList<Nba2016SeriesBet> seriesBets = new ArrayList<Nba2016SeriesBet>();
	private ArrayList<Nba2016GameBet> gameBets = new ArrayList<Nba2016GameBet>();
	private int gameBetsScore = 0;
	private int seriesBetsScore = 0;
	private int mvpBetScore = 0;

	public Nba2016User() {
		super();
	}

	public Nba2016User(int userId, int score, ArrayList<Nba2016TieBreakerBet> tbBets,
			ArrayList<Nba2016SeriesBet> seriesBets, ArrayList<Nba2016GameBet> gameBets, int gameBetsScore,
			int seriesBetsScore, int mvpBetScore) {
		this.userId = userId;
		this.score = score;
		this.tbBets = tbBets;
		this.seriesBets = seriesBets;
		this.gameBets = gameBets;
		this.gameBetsScore = gameBetsScore;
		this.seriesBetsScore = seriesBetsScore;
		this.mvpBetScore = mvpBetScore;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public ArrayList<Nba2016TieBreakerBet> getTbBets() {
		return tbBets;
	}

	public void setTbBets(ArrayList<Nba2016TieBreakerBet> tbBets) {
		this.tbBets = tbBets;
	}

	public void addBet(Nba2016TieBreakerBet bet) {
		if (tbBets == null) {
			tbBets = new ArrayList<Nba2016TieBreakerBet>();
		}
		tbBets.add(bet);
	}

	public ArrayList<Nba2016SeriesBet> getSeriesBets() {
		return seriesBets;
	}

	public void setSeriesBets(ArrayList<Nba2016SeriesBet> seriesBets) {
		this.seriesBets = seriesBets;
	}

	public void addBet(Nba2016SeriesBet bet) {
		if (seriesBets == null) {
			seriesBets = new ArrayList<Nba2016SeriesBet>();
		}
		seriesBets.add(bet);
	}

	public ArrayList<Nba2016GameBet> getGameBets() {
		return gameBets;
	}

	public void setGameBets(ArrayList<Nba2016GameBet> gameBets) {
		this.gameBets = gameBets;
	}

	public void addBet(Nba2016GameBet bet) {
		if (gameBets == null) {
			gameBets = new ArrayList<Nba2016GameBet>();
		}
		gameBets.add(bet);
	}

	public int getGameBetsScore() {
		return gameBetsScore;
	}

	public void setGameBetsScore(int gameBetsScore) {
		this.gameBetsScore = gameBetsScore;
	}

	public int getSeriesBetsScore() {
		return seriesBetsScore;
	}

	public void setSeriesBetsScore(int seriesBetsScore) {
		this.seriesBetsScore = seriesBetsScore;
	}

	public int getMvpBetScore() {
		return mvpBetScore;
	}

	public void setMvpBetScore(int mvpBetScore) {
		this.mvpBetScore = mvpBetScore;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Nba2016User.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Nba2016User.class, Nba2016UserDao.getInstance().generateInsertStatement());
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

}
