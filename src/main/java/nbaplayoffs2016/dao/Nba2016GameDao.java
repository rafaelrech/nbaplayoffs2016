package nbaplayoffs2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import nbaplayoffs2016.bean.Nba2016Game;
import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;

public class Nba2016GameDao extends CommonDao<Nba2016Game> {

	private static volatile Nba2016GameDao instance;

	private Nba2016GameDao() {
	}

	public static Nba2016GameDao getInstance() {
		if (instance == null) {
			synchronized (Nba2016GameDao.class) {
				if (instance == null) {
					instance = new Nba2016GameDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "GAMES", "" }, { "ID", DataTypesEnum.NUMBER.get() },
				{ "EXTERNAL_ID", DataTypesEnum.BIGTEXT.get() }, { "FASE", DataTypesEnum.NUMBER.get() },
				{ "BRACKET_ID", DataTypesEnum.NUMBER.get() }, { "GAME_DATE", DataTypesEnum.DATE.get() },
				{ "HOME_TEAM", DataTypesEnum.TEXT.get() }, { "HOME_SCORE", DataTypesEnum.NUMBER.get() },
				{ "AWAY_TTEAM", DataTypesEnum.TEXT.get() }, { "AWAY_SCORE", DataTypesEnum.NUMBER.get() },
				{ "OT", DataTypesEnum.NUMBER.get() }, { "COMPLETED", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Nba2016Game createBeanFromRS(ResultSet rs) throws SQLException {
		Nba2016Game rec = new Nba2016Game();
		rec.setId(rs.getInt(1));
		rec.setExternalId(rs.getString(2));
		rec.setFase(rs.getInt(3));
		rec.setBracketId(rs.getInt(4));
		rec.setData(timestampToCalendar(rs.getTimestamp(5)));
		rec.setHomeTeam(rs.getString(6));
		rec.setHomeScore(rs.getInt(7));
		rec.setVisitTeam(rs.getString(8));
		rec.setVisitScore(rs.getInt(9));
		rec.setOverTime(rs.getInt(10));
		rec.setCompleted(rs.getInt(11));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Nba2016Game bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setString(2, bean.getExternalId());
		ps.setInt(3, bean.getFase());
		ps.setInt(4, bean.getBracketId());
		ps.setTimestamp(5, (bean.getData() == null) ? null : new Timestamp(bean.getData().getTimeInMillis()));
		ps.setString(6, bean.getHomeTeam());
		ps.setInt(7, bean.getHomeScore());
		ps.setString(8, bean.getVisitTeam());
		ps.setInt(9, bean.getVisitScore());
		ps.setInt(10, bean.getOverTime());
		ps.setInt(11, bean.getCompleted());
		ps.setInt(12, bean.getId());
	}

	@Override
	public int insert(Nba2016Game bean) {
		ArrayList<Nba2016Game> all = new ArrayList<Nba2016Game>();
		try {
			all = listAll();
		} catch (SQLException e) {
		}
		int maxId = 0;
		for (Nba2016Game rec : all) {
			maxId = Math.max(maxId, rec.getId());
		}
		maxId++;
		return createEntry(new Integer(maxId), bean.getExternalId(), bean.getFase(), bean.getBracketId(),
				bean.getData(), bean.getHomeTeam(), bean.getHomeScore(), bean.getVisitTeam(), bean.getVisitScore(),
				bean.getOverTime(), bean.getCompleted());
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY GAME_DATE ";
	}

}
