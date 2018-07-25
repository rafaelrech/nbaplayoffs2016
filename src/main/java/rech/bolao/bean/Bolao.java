package rech.bolao.bean;

import java.util.ArrayList;
import java.util.Calendar;

import rech.bolao.dao.BolaoDao;

public class Bolao extends CommonBean {

	public int id;
	public String name;
	public String description;
	public String folderName;
	public Calendar registerDeadlineDate;
	public Calendar initDate;
	public Calendar completeDate;
	public int available;
	public int completed;

	private ArrayList<UserBolao> registeredUsers = new ArrayList<UserBolao>();

	public Bolao() {
		super();
	}

	public Bolao(int id, String name, String description, String folderName, Calendar registerDeadlineDate,
			Calendar initDate, Calendar completeDate, int available, int completed) {
		super();
		this.id = id;
		this.name = name;
		this.description = description;
		this.folderName = folderName;
		this.registerDeadlineDate = registerDeadlineDate;
		this.initDate = initDate;
		this.completeDate = completeDate;
		this.available = available;
		this.completed = completed;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getFolderName() {
		return folderName;
	}

	public void setFolderName(String folderName) {
		this.folderName = folderName;
	}

	public Calendar getRegisterDeadlineDate() {
		return registerDeadlineDate;
	}

	public void setRegisterDeadlineDate(Calendar registerDeadlineDate) {
		this.registerDeadlineDate = registerDeadlineDate;
	}

	public Calendar getInitDate() {
		return initDate;
	}

	public void setInitDate(Calendar initDate) {
		this.initDate = initDate;
	}

	public Calendar getCompleteDate() {
		return completeDate;
	}

	public void setCompleteDate(Calendar completeDate) {
		this.completeDate = completeDate;
	}

	public int getAvailable() {
		return available;
	}

	public void setAvailable(int available) {
		this.available = available;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public ArrayList<UserBolao> getRegisteredUsers() {
		return registeredUsers;
	}

	public void setRegisteredUsers(ArrayList<UserBolao> registeredUsers) {
		this.registeredUsers = registeredUsers;
	}

	public void addRegisteredUser(UserBolao userBolao) {
		if (registeredUsers == null) {
			registeredUsers = new ArrayList<UserBolao>();
		}
		registeredUsers.add(userBolao);
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Bolao.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Bolao.class, BolaoDao.getInstance().generateInsertStatement());
	}

}
