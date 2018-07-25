package rech.bolao.bean;

import java.util.ArrayList;

import rech.bolao.dao.UserDao;

public class User extends CommonBean {

	protected int id;
	protected String email;
	protected String username;
	protected String password;
	protected String role;
	protected String firstName;
	protected String lastName;
	protected String secondEmail;
	protected int active;
	protected String key;

	private ArrayList<UserBolao> ballots = new ArrayList<UserBolao>();

	public User() {
		super();
	}

	public User(int id, String email, String username, String password, String role, int active, String key) {
		this(id, email, username, password, role, "", "", "", active, key);
	}

	public User(int id, String email, String username, String password, String role, String firstName, String lastName,
			String secondEmail, int active, String key) {
		super();
		this.id = id;
		this.email = email;
		this.username = username;
		this.password = password;
		this.role = role;
		this.firstName = firstName;
		this.lastName = lastName;
		this.secondEmail = secondEmail;
		this.active = active;
		this.key = key;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getSecondEmail() {
		return secondEmail;
	}

	public void setSecondEmail(String secondEmail) {
		this.secondEmail = secondEmail;
	}

	public int getActive() {
		return active;
	}

	public void setActive(int active) {
		this.active = active;
	}

	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}

	public ArrayList<UserBolao> getBallots() {
		return ballots;
	}

	public void setBallots(ArrayList<UserBolao> ballots) {
		this.ballots = ballots;
	}

	public void addBallot(UserBolao userBolao) {
		if (ballots == null) {
			ballots = new ArrayList<UserBolao>();
		}
		ballots.add(userBolao);
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(User.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(User.class, UserDao.getInstance().generateInsertStatement());
	}

}
