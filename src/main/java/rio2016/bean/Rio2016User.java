package rio2016.bean;

import java.util.ArrayList;

import rech.bolao.bean.CommonBean;
import rech.bolao.bean.User;
import rio2016.dao.Rio2016UserDao;

public class Rio2016User extends CommonBean {

	public int userId;
	public int score;
	public int medalBetsScore = 0;
	public int boardBetsScore = 0;
	public int brBoardBetScore = 0;

	private User user;
	private ArrayList<Rio2016MedalBet> medalBets = new ArrayList<Rio2016MedalBet>();

	public Rio2016User() {
		super();
	}

	public Rio2016User(int userId, int score, int medalBetsScore, int boardBetsScore, int brBoardBetScore) {
		super();
		this.userId = userId;
		this.score = score;
		this.medalBetsScore = medalBetsScore;
		this.boardBetsScore = boardBetsScore;
		this.brBoardBetScore = brBoardBetScore;
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

	public int getMedalBetsScore() {
		return medalBetsScore;
	}

	public void setMedalBetsScore(int medalBetsScore) {
		this.medalBetsScore = medalBetsScore;
	}

	public int getBoardBetsScore() {
		return boardBetsScore;
	}

	public void setBoardBetsScore(int boardBetsScore) {
		this.boardBetsScore = boardBetsScore;
	}

	public int getBrBoardBetScore() {
		return brBoardBetScore;
	}

	public void setBrBoardBetScore(int brBoardBetScore) {
		this.brBoardBetScore = brBoardBetScore;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public ArrayList<Rio2016MedalBet> getMedalBets() {
		return medalBets;
	}

	public void setMedalBets(ArrayList<Rio2016MedalBet> medalBets) {
		this.medalBets = medalBets;
	}

	public void addBet(Rio2016MedalBet bet) {
		if (medalBets == null) {
			medalBets = new ArrayList<Rio2016MedalBet>();
		}
		medalBets.add(bet);
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Rio2016MedalBet.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Rio2016User.class, Rio2016UserDao.getInstance().generateInsertStatement());
	}

}
