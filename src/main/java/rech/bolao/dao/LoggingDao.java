package rech.bolao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.bean.LogEntry;
import rech.bolao.util.DataTypesEnum;

public class LoggingDao extends CommonDao<LogEntry> {

	private static volatile LoggingDao instance = null;

	private LoggingDao() {
	}

	public static LoggingDao getInstance() {
		if (instance == null) {
			synchronized (LoggingDao.class) {
				if (instance == null) {
					instance = new LoggingDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "LOG_ENTRIES", "" }, { "ID", DataTypesEnum.NUMBER.get() }, { "DATE", DataTypesEnum.DATE.get() },
				{ "USERNAME", DataTypesEnum.TEXT.get() }, { "ENTRY", DataTypesEnum.BIGTEXT.get() } };
		return def;
	}

	@Override
	protected LogEntry createBeanFromRS(ResultSet rs) throws SQLException {
		LogEntry rec = new LogEntry();
		rec.setId(rs.getInt(1));
		rec.setData(timestampToCalendar(rs.getTimestamp(2)));
		rec.setUsername(rs.getString(3));
		rec.setEntry(rs.getString(4));
		return rec;
	}

	@Override
	protected void setUpdateParameters(LogEntry bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setTimestamp(2, (bean.getData() == null) ? null : new Timestamp(bean.getData().getTimeInMillis()));
		ps.setString(3, bean.getUsername());
		ps.setString(4, bean.getEntry());
		ps.setInt(5, bean.getId());
	}

	@Override
	public int insert(LogEntry bean) {
		ArrayList<LogEntry> allEntries = new ArrayList<LogEntry>();
		try {
			allEntries = listAll();
		} catch (SQLException e) {
		}
		int maxId = 0;
		for (LogEntry rec : allEntries) {
			maxId = Math.max(maxId, rec.getId());
		}
		maxId++;
		return createEntry(new Integer(maxId), bean.getData(), bean.getUsername(), bean.getEntry());
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY ID DESC ";
	}

}
