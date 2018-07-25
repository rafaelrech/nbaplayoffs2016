package rech.bolao.bean;

import rech.bolao.dao.UserBolaoDao;

public class UserBolao extends CommonBean {

	public int bolaoId;
	public int userId;
	public int userScore;

	private Bolao bolao;
	private User user;

	public UserBolao() {
		super();
	}

	public UserBolao(int bolaoId, int userId) {
		this(bolaoId, userId, 0);
	}

	public UserBolao(int bolaoId, int userId, int userScore) {
		super();
		this.bolaoId = bolaoId;
		this.userId = userId;
		this.userScore = userScore;
	}

	public int getBolaoId() {
		return bolaoId;
	}

	public void setBolaoId(int bolaoId) {
		this.bolaoId = bolaoId;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public int getUserScore() {
		return userScore;
	}

	public void setUserScore(int userScore) {
		this.userScore = userScore;
	}

	public Bolao getBolao() {
		return bolao;
	}

	public void setBolao(Bolao bolao) {
		this.bolao = bolao;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(UserBolao.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(UserBolao.class, UserBolaoDao.getInstance().generateInsertStatement());
	}

}
