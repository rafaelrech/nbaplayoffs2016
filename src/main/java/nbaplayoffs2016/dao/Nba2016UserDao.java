package nbaplayoffs2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import nbaplayoffs2016.bean.Nba2016User;
import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;

public class Nba2016UserDao extends CommonDao<Nba2016User> {

	private static volatile Nba2016UserDao instance = null;

	private Nba2016UserDao() {
	}

	public static Nba2016UserDao getInstance() {
		if (instance == null) {
			synchronized (Nba2016UserDao.class) {
				if (instance == null) {
					instance = new Nba2016UserDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "NBA2016_USER", "" }, { "USER_ID", DataTypesEnum.NUMBER.get() },
				{ "SCORE", DataTypesEnum.NUMBER.get() }, { "GAME_SCORE", DataTypesEnum.NUMBER.get() },
				{ "SERIES_SCORE", DataTypesEnum.NUMBER.get() }, { "MVP_SCORE", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Nba2016User createBeanFromRS(ResultSet rs) throws SQLException {
		Nba2016User rec = new Nba2016User();
		rec.setUserId(rs.getInt(1));
		rec.setScore(rs.getInt(2));
		rec.setGameBetsScore(rs.getInt(3));
		rec.setSeriesBetsScore(rs.getInt(4));
		rec.setMvpBetScore(rs.getInt(5));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Nba2016User bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getUserId());
		ps.setInt(2, bean.getScore());
		ps.setInt(3, bean.getGameBetsScore());
		ps.setInt(4, bean.getSeriesBetsScore());
		ps.setInt(5, bean.getMvpBetScore());
		ps.setInt(6, bean.getUserId());
	}

	@Override
	public int insert(Nba2016User bean) {
		if (bean.getUserId() == -1) {
			ArrayList<Nba2016User> allUsers = new ArrayList<Nba2016User>();
			try {
				allUsers = listAll();
			} catch (SQLException e) {
			}
			int maxId = 0;
			for (Nba2016User rec : allUsers) {
				maxId = Math.max(maxId, rec.getUserId());
			}
			maxId++;
			bean.setUserId(maxId);
		}
		return createEntry(bean.getUserId(), new Integer(bean.getScore()), new Integer(bean.getGameBetsScore()),
				new Integer(bean.getSeriesBetsScore()), new Integer(bean.getMvpBetScore()));
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY SCORE DESC ";
	}

}
