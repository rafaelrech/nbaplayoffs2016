package rech.bolao.bean;

import java.util.Calendar;

import rech.bolao.dao.LoggingDao;

public class LogEntry extends CommonBean {

	public int id;
	public Calendar data;
	public String username;
	public String entry;

	public LogEntry() {
		super();
	}

	public LogEntry(int id, Calendar data, String username, String entry) {
		super();
		this.id = id;
		this.data = data;
		this.username = username;
		this.entry = entry;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public Calendar getData() {
		return data;
	}

	public void setData(Calendar data) {
		this.data = data;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public String getEntry() {
		return entry;
	}

	public void setEntry(String entry) {
		this.entry = entry;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(LogEntry.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(LogEntry.class, LoggingDao.getInstance().generateInsertStatement());
	}

}
