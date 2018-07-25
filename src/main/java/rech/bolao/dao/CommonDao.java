package rech.bolao.dao;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.bean.CommonBean;
import rech.bolao.util.BaseDBUtil;

public abstract class CommonDao<T extends CommonBean> {

	public abstract String[][] getTableDefinition();

	protected String getDefaultOrderByClause() {
		return "";
	}

	protected String getKeyWhereClause() {
		return " WHERE ID = ?";
	}

	protected void setKeyParameters(PreparedStatement ps, Object... keys) throws SQLException {
		ps.setInt(1, ((Integer) keys[0]).intValue());
	}

	protected abstract T createBeanFromRS(ResultSet rs) throws SQLException;

	protected abstract void setUpdateParameters(T bean, PreparedStatement ps) throws SQLException;

	public abstract int insert(T bean);

	public ArrayList<T> listAll() throws SQLException {
		return listAll("", "");
	}

	public ArrayList<T> listAll(String whereClauseArgs) throws SQLException {
		return listAll(whereClauseArgs, "");
	}

	public ArrayList<T> listAll(String whereClauseArgs, String altOrderByArgs) throws SQLException {
		ArrayList<T> all = new ArrayList<T>();
		PreparedStatement strQryStmt = getBaseQueryStatement(whereClauseArgs, altOrderByArgs);
		PreparedStatement baseQueryStatement = strQryStmt;
		try {
			ResultSet rs = baseQueryStatement.executeQuery();
			while (rs.next()) {
				all.add(createBeanFromRS(rs));
			}
		} catch (SQLException exc) {
			exc.printStackTrace();
			throw exc;
		}
		return all;
	}

	public int update(T bean) {
		try {
			PreparedStatement ps = getUpdateQueryStatement();
			setUpdateParameters(bean, ps);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public T get(Object... keys) {
		try {
			StringBuffer sql = new StringBuffer(getBaseQueryString());
			sql.append(getKeyWhereClause());
			PreparedStatement ps = (PreparedStatement) BaseDBUtil.getConnection().prepareStatement(sql.toString());
			for (int i = 0; i < keys.length; i++) {
				ps.setInt(i + 1, ((Integer) keys[i]).intValue());
			}
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return createBeanFromRS(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	public T getBy(String whereClauseArgs) {
		try {
			StringBuffer sql = new StringBuffer(getBaseQueryString());
			sql.append("WHERE " + whereClauseArgs);
			PreparedStatement ps = (PreparedStatement) BaseDBUtil.getConnection().prepareStatement(sql.toString());
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				return createBeanFromRS(rs);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}
	
	public int delete(Object... keys) {
		try {
			PreparedStatement ps = getDeleteStatement();
			setKeyParameters(ps, keys);
			return ps.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	protected String getTableName() {
		return getTableDefinition()[0][0];
	}

	protected int createEntry(Object... values) {
		StringBuffer sql = new StringBuffer(String.format("INSERT INTO %s values (", getTableName()));

		for (int idx = 0; idx < values.length; idx++) {
			sql.append("?,");
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(")");
		return BaseDBUtil.executeDML(sql.toString(), values);
	}

	protected String getFullColumnList() {
		StringBuffer columnList = new StringBuffer();
		for (int idx = 1; idx < getTableDefinition().length; idx++) {
			columnList.append(String.format("%s, ", getTableDefinition()[idx][0]));
		}
		columnList.deleteCharAt(columnList.length() - 2);
		return columnList.toString();
	}

	protected String getBaseQueryString() {
		return String.format("SELECT %s FROM %s ", getFullColumnList(), getTableName());
	}

	protected PreparedStatement getBaseQueryStatement() {
		return getBaseQueryStatement("", "");
	}

	protected PreparedStatement getBaseQueryStatement(String whereClauseArgs) {
		return getBaseQueryStatement(whereClauseArgs, "");
	}

	protected PreparedStatement getBaseQueryStatement(String whereClauseArgs, String altOrderByArgs) {
		try {
			Connection connection = BaseDBUtil.getConnection();
			PreparedStatement prepareStatement = (PreparedStatement) connection.prepareStatement(getBaseQueryString()
					+ (whereClauseArgs.length() > 0 ? " WHERE " + whereClauseArgs : "") + " "
					+ (altOrderByArgs.length() > 0 ? " ORDER BY " + altOrderByArgs : getDefaultOrderByClause()));
			return prepareStatement;
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected String getDeleteString() {
		StringBuffer sb = new StringBuffer(String.format("DELETE FROM %s ", getTableName()));
		sb.append(getKeyWhereClause());
		System.out.println(sb.toString());
		return sb.toString();
	}

	protected String getUpdateQueryString() {
		StringBuffer sql = new StringBuffer(String.format("UPDATE %s SET ", getTableName()));
		for (int idx = 1; idx < getTableDefinition().length; idx++) {
			sql.append(String.format(" %s = ? ,", getTableDefinition()[idx][0]));
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.append(getKeyWhereClause());
		return sql.toString();
	}

	protected PreparedStatement getUpdateQueryStatement() {
		try {
			return (PreparedStatement) BaseDBUtil.getConnection().prepareStatement(getUpdateQueryString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected PreparedStatement getDeleteStatement() {
		try {
			return (PreparedStatement) BaseDBUtil.getConnection().prepareStatement(getDeleteString());
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return null;
	}

	protected static Calendar timestampToCalendar(Timestamp ts) {
		Calendar cal = null;

		if (ts != null) {
			cal = Calendar.getInstance();
			cal.setTimeInMillis(ts.getTime());
		}

		return cal;
	}

	public String generateInsertStatement() {
		return String.format("INSERT INTO %s (%s) VALUES (", getTableName(), getFullColumnList());
	}

	public void createTable() {
		String[][] defs = getTableDefinition();
		StringBuffer sql = new StringBuffer(String.format("CREATE TABLE %s (", defs[0][0]));
		for (int idx = 1; idx < defs.length; idx++) {
			sql.append(String.format("%s %s NULL, ", defs[idx][0], defs[idx][1]));
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") ENGINE=InnoDB ");
		System.out.println(String.format(" %s ", sql.toString()));
		System.out
				.println(String.format("CREATING %s : %d changes", defs[0][0], BaseDBUtil.executeDML(sql.toString())));
	}

	public void dropTable() {
		String[][] defs = getTableDefinition();
		StringBuffer sql = new StringBuffer(String.format("DROP TABLE %s ", defs[0][0]));
		System.out
				.println(String.format("DROPPING %s : %d changes ", defs[0][0], BaseDBUtil.executeDML(sql.toString())));
	}

}
