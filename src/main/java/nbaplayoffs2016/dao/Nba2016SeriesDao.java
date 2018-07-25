package nbaplayoffs2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import nbaplayoffs2016.bean.Nba2016Series;
import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;

public class Nba2016SeriesDao extends CommonDao<Nba2016Series> {

	private static volatile Nba2016SeriesDao instance;

	private Nba2016SeriesDao() {
	}

	public static Nba2016SeriesDao getInstance() {
		if (instance == null) {
			synchronized (Nba2016SeriesDao.class) {
				if (instance == null) {
					instance = new Nba2016SeriesDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "SERIES", "" }, { "ID", DataTypesEnum.NUMBER.get() },
				{ "FASE", DataTypesEnum.NUMBER.get() }, { "TEAM1", DataTypesEnum.TEXT.get() },
				{ "TEAM2", DataTypesEnum.TEXT.get() }, { "WINNER", DataTypesEnum.TEXT.get() },
				{ "GAMES", DataTypesEnum.NUMBER.get() }, { "PREV_ID1", DataTypesEnum.NUMBER.get() },
				{ "PREV_ID2", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Nba2016Series createBeanFromRS(ResultSet rs) throws SQLException {
		Nba2016Series rec = new Nba2016Series();
		rec.setId(rs.getInt(1));
		rec.setFase(rs.getInt(2));
		rec.setTeam1(rs.getString(3));
		rec.setTeam2(rs.getString(4));
		rec.setWinner(rs.getString(5));
		rec.setNbrGames(rs.getInt(6));
		rec.setPrevId1(rs.getInt(7));
		rec.setPrevId2(rs.getInt(8));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Nba2016Series bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setInt(2, bean.getFase());
		ps.setString(3, bean.getTeam1());
		ps.setString(4, bean.getTeam2());
		ps.setString(5, bean.getWinner());
		ps.setInt(6, bean.getNbrGames());
		ps.setInt(7, bean.getPrevId1());
		ps.setInt(8, bean.getPrevId2());
		ps.setInt(9, bean.getId());
	}

	@Override
	public int insert(Nba2016Series bean) {
		ArrayList<Nba2016Series> all = new ArrayList<Nba2016Series>();
		try {
			all = listAll();
		} catch (SQLException e) {
		}
		int maxId = 0;
		for (Nba2016Series rec : all) {
			maxId = Math.max(maxId, rec.getId());
		}
		maxId++;
		return createEntry(new Integer(maxId), bean.getFase(), bean.getTeam1(), bean.getTeam2(), bean.getWinner(),
				bean.getNbrGames(), bean.getPrevId1(), bean.getPrevId2());
	}

}
