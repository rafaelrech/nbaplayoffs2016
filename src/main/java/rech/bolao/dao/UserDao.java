package rech.bolao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.bean.LogEntry;
import rech.bolao.bean.User;
import rech.bolao.util.DataTypesEnum;

public class UserDao extends CommonDao<User> {

	private static volatile UserDao instance = null;

	private UserDao() {
	}

	public static UserDao getInstance() {
		if (instance == null) {
			synchronized (UserDao.class) {
				if (instance == null) {
					instance = new UserDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "BASIC_USER", "" }, { "ID", DataTypesEnum.NUMBER.get() },
				{ "EMAIL", DataTypesEnum.TEXT.get() }, { "USERNAME", DataTypesEnum.TEXT.get() },
				{ "PASSWORD", DataTypesEnum.PWD.get() }, { "ROLE", DataTypesEnum.TEXT.get() },
				{ "FIRST_NAME", DataTypesEnum.TEXT.get() }, { "LAST_NAME", DataTypesEnum.TEXT.get() },
				{ "SECOND_EMAIL", DataTypesEnum.TEXT.get() }, { "ACTIVE", DataTypesEnum.NUMBER.get() },
				{ "ACTIVATION_KEY", DataTypesEnum.TEXT.get() } };
		return def;
	}

	@Override
	protected User createBeanFromRS(ResultSet rs) throws SQLException {
		User rec = new User();
		rec.setId(rs.getInt(1));
		rec.setEmail(rs.getString(2));
		rec.setUsername(rs.getString(3));
		rec.setPassword(rs.getString(4));
		rec.setRole(rs.getString(5));
		rec.setFirstName(rs.getString(6));
		rec.setLastName(rs.getString(7));
		rec.setSecondEmail(rs.getString(8));
		rec.setActive(rs.getInt(9));
		rec.setKey(rs.getString(10));
		return rec;
	}

	@Override
	protected void setUpdateParameters(User bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setString(2, bean.getEmail());
		ps.setString(3, bean.getUsername());
		ps.setString(4, bean.getPassword());
		ps.setString(5, bean.getRole());
		ps.setString(6, bean.getFirstName());
		ps.setString(7, bean.getLastName());
		ps.setString(8, bean.getSecondEmail());
		ps.setInt(9, bean.getActive());
		ps.setString(10, bean.getKey());
		ps.setInt(11, bean.getId());
	}

	@Override
	public int insert(User bean) {
		ArrayList<User> allUsers = new ArrayList<User>();
		try {
			allUsers = listAll();
		} catch (SQLException e) {
		}
		int maxId = 0;
		for (User rec : allUsers) {
			maxId = Math.max(maxId, rec.getId());
		}
		maxId++;
		bean.setId(maxId);
		return createEntry(bean.getId(), bean.getEmail(), bean.getUsername(), bean.getPassword(), bean.getRole(),
				bean.getFirstName(), bean.getLastName(), bean.getSecondEmail(), bean.getActive(), bean.getKey());
	}

	public User authenticate(String login, String pwd) {
		ArrayList<User> listAll = new ArrayList<User>();
		try {
			listAll = listAll();
		} catch (SQLException e) {
		}
		if (login == null || pwd == null) {
			return null;
		}
		LoggingDao.getInstance().insert(
				new LogEntry(0, Calendar.getInstance(), "", String.format("Authenticating: %s / %s", login, pwd)));
		for (User user : listAll) {
			if (user.getPassword() == null) {
				return null;
			}
			if (((user.getUsername() != null) && (login.trim().equalsIgnoreCase(user.getUsername().trim()))
					|| ((user.getEmail() != null) && login.trim().equalsIgnoreCase(user.getEmail().trim())))) {
				if (pwd.trim().equalsIgnoreCase(user.getPassword())) {
					return user;
				} else {
					return null;
				}
			}
		}
		return new User(-99, "", login, pwd, "user", 0, "");
	}

	public User getUserByUsernameOrEmail(String key) {
		if (key == null) {
			return null;
		}
		key = key.trim();
		ArrayList<User> listAll = new ArrayList<User>();
		try {
			listAll = listAll();
		} catch (SQLException e) {
		}
		for (User user : listAll) {
			String username = user.getUsername();
			String email = user.getEmail();
			if (((username != null) && (key.equalsIgnoreCase(username.trim()))
					|| ((email != null) && key.equalsIgnoreCase(email.trim())))) {
				return user;
			}
		}
		return null;
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY USERNAME DESC ";
	}

}
