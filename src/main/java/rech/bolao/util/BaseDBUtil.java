package rech.bolao.util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.Properties;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.bean.Bolao;
import rech.bolao.bean.User;
import rech.bolao.bean.UserBolao;
import rech.bolao.dao.BolaoDao;
import rech.bolao.dao.CommonDao;
import rech.bolao.dao.LoggingDao;
import rech.bolao.dao.UserBolaoDao;
import rech.bolao.dao.UserDao;

public class BaseDBUtil {

	public static final String LOG_BEAN = "LOG";
	public static final String BOLAO_BEAN = "BOLAO";
	public static final String USER_BOLAO_BEAN = "USER_BOLAO";
	public static final String USER_BEAN = "USER";

	public static final String[] beans = { LOG_BEAN, USER_BEAN, BOLAO_BEAN, USER_BOLAO_BEAN };

	private static Connection conn = null;
	private static Boolean RUNNING_LOCAL = null;

	public static Connection getConnection() {

		if (conn != null) {
			try {
				if (!conn.isClosed()) {
					return conn;
				}
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		String APP_NAME = "playoffs2016";
		String DB_HOST = "localhost";
		String DB_PORT = "3306";
		String DB_USER = "rafael";
		String DB_PASSWD = "rafael";

		if (RUNNING_LOCAL == null) {
			Properties props = new Properties();
			String resourceName = "db.properties"; // could also be a constant
			ClassLoader loader = Thread.currentThread().getContextClassLoader();
			try(InputStream resourceStream = loader.getResourceAsStream(resourceName)) {
			    props.load(resourceStream);
			    RUNNING_LOCAL = new Boolean(props.getProperty("running-local"));
			    System.out.println("CONSEGUIU");
			} catch (IOException e) {
				System.err.println(e.getMessage());
				// e.printStackTrace();
				RUNNING_LOCAL = true;
			}
		}

		if (!RUNNING_LOCAL) {
			APP_NAME = System.getenv("OPENSHIFT_APP_NAME");
			DB_HOST = System.getenv("OPENSHIFT_MYSQL_DB_HOST");
			DB_PORT = System.getenv("OPENSHIFT_MYSQL_DB_PORT");
			DB_USER = System.getenv("OPENSHIFT_MYSQL_DB_USERNAME");
			DB_PASSWD = System.getenv("OPENSHIFT_MYSQL_DB_PASSWORD");
		}
		// So in the example above your connection string would be something
		// like:
		String DB_CONN = "jdbc:mysql://" + DB_HOST + ":" + DB_PORT + "/" + APP_NAME;

		try {
			String jdbcString = DB_CONN + "?user=" + DB_USER + "&password=" + DB_PASSWD;
			System.out.println(jdbcString);
			Class.forName("com.mysql.jdbc.Driver");
			System.out.println("JDBC Driver loaded");

			conn = DriverManager.getConnection(DB_CONN, DB_USER, DB_PASSWD);
			System.out.println("CONNECTED");

		} catch (SQLException e) {
			e.printStackTrace();
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		return conn;
	}

	public static int executeDML(String sql) {
		try {
			PreparedStatement stmt = (PreparedStatement) getConnection().prepareStatement(sql);
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static int executeDML(String sql, Object... values) {
		try {
			PreparedStatement stmt = (PreparedStatement) getConnection().prepareStatement(sql);
			for (int idx = 0; idx < values.length; idx++) {
				if (values[idx] == null) {
					stmt.setNull(idx + 1, java.sql.Types.CHAR);
				} else if (values[idx] instanceof String) {
					stmt.setString(idx + 1, (String) values[idx]);
				} else if (values[idx] instanceof Integer) {
					stmt.setInt(idx + 1, ((Integer) values[idx]).intValue());
				} else if (values[idx] instanceof Boolean) {
					stmt.setBoolean(idx + 1, ((Boolean) values[idx]).booleanValue());
				} else if (values[idx] instanceof Calendar) {
					stmt.setTimestamp(idx + 1, new Timestamp(((Calendar) values[idx]).getTimeInMillis()));
				} else if (values[idx] instanceof Float) {
					stmt.setFloat(idx + 1, ((Float) values[idx]).floatValue());
				} else {
				}
			}
			return stmt.executeUpdate();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		return 0;
	}

	public static CommonDao<?> getDaoFromBean(String bean) {
		if (bean.equals(LOG_BEAN)) {
			return LoggingDao.getInstance();
		}
		if (bean.equals(USER_BEAN)) {
			return UserDao.getInstance();
		}
		if (bean.equals(USER_BOLAO_BEAN)) {
			return BolaoDao.getInstance();
		}
		if (bean.equals(BOLAO_BEAN)) {
			return UserBolaoDao.getInstance();
		}
		return null;
	}

	public static void createTables() {
		for (String bean : beans) {
			getDaoFromBean(bean).createTable();
		}
	}

	public static void dropTables() {
		for (String bean : beans) {
			getDaoFromBean(bean).dropTable();
		}
	}

	public static void createTable(String bean) {
		getDaoFromBean(bean).createTable();
	}

	public static void dropTable(String bean) {
		getDaoFromBean(bean).dropTable();
	}

	public static void loadUsers() {
		UserDao.getInstance().insert(new User(-1, "rafael.g.rech@adp.com", "admin", "admin", "admin", 1, ""));

		UserDao.getInstance().insert(new User(-1, "rafael.rech@gmail.com", "rech", "rech", "user", 1, "")); // 2
		UserDao.getInstance().insert(new User(-1, "jean.espindola@gmail.com", "jean", "jaca", "user", 1, "")); // 3
		UserDao.getInstance().insert(new User(-1, "rscortegagna@gmail.com", "rafael", "rato", "user", 1, ""));// 4
		UserDao.getInstance()
				.insert(new User(-1, "guilherme.guimaraes1991@gmail.com", "guilherme", "gema", "user", 1, "")); // 5
		UserDao.getInstance().insert(new User(-1, "scholles@gmail.com", "scholles", "sapo", "user", 1, "")); // 6
		UserDao.getInstance().insert(new User(-1, "limabrasil@gmail.com", "lima", "lima", "user", 1, "")); // 7
		UserDao.getInstance().insert(new User(-1, "szsch.vinicius@gmail.com", "szsch", "sombra", "user", 1, "")); // 8
	}

	public static void loadBallots() {
		Calendar c = Calendar.getInstance();
		Calendar d = Calendar.getInstance();
		Calendar e = Calendar.getInstance();

		c.set(2015, 3, 20);
		c.set(2015, 3, 25);
		c.set(2015, 7, 10);
		BolaoDao.getInstance().insert(new Bolao(-1, "NBA 2015", "Playoffs NBA 2015", "nba2015", c, d, e, 0, 1));

		c.set(2016, 3, 20);
		c.set(2016, 3, 25);
		c.set(2016, 7, 10);
		BolaoDao.getInstance().insert(new Bolao(-1, "NBA 2016", "Playoffs NBA 2016", "nba2016-mm", c, d, e, 0, 0));

		c.set(2016, 3, 20);
		c.set(2016, 3, 25);
		c.set(2016, 7, 10);
		BolaoDao.getInstance()
				.insert(new Bolao(-1, "Rio 2016", "Olimpiadas do Rio de Janeiro - 2016", "rio2016", c, d, e, 1, 0));
	}

	public static void loadUserBallots() {
		UserBolaoDao.getInstance().insert(new UserBolao(1, 2, 800));
		UserBolaoDao.getInstance().insert(new UserBolao(1, 3, 840));
		UserBolaoDao.getInstance().insert(new UserBolao(1, 4, 640));
		UserBolaoDao.getInstance().insert(new UserBolao(1, 5, 660));
		UserBolaoDao.getInstance().insert(new UserBolao(1, 6, 720));
		UserBolaoDao.getInstance().insert(new UserBolao(1, 7, 700));

		UserBolaoDao.getInstance().insert(new UserBolao(2, 2, 800));
		UserBolaoDao.getInstance().insert(new UserBolao(2, 3, 840));
		UserBolaoDao.getInstance().insert(new UserBolao(2, 4, 640));
		UserBolaoDao.getInstance().insert(new UserBolao(2, 5, 660));
		UserBolaoDao.getInstance().insert(new UserBolao(2, 6, 720));
		UserBolaoDao.getInstance().insert(new UserBolao(2, 8, 120));

		UserBolaoDao.getInstance().insert(new UserBolao(3, 2));
	}

}
