package nbaplayoffs2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import nbaplayoffs2016.bean.Nba2016GameBet;
import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;

public class Nba2016GameBetDao extends CommonDao<Nba2016GameBet> {

	private static volatile Nba2016GameBetDao instance;

	private Nba2016GameBetDao() {
	}

	public static Nba2016GameBetDao getInstance() {
		if (instance == null) {
			synchronized (Nba2016GameBetDao.class) {
				if (instance == null) {
					instance = new Nba2016GameBetDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "APOSTAS_JOGOS", "" }, { "GAME_ID", DataTypesEnum.NUMBER.get() },
				{ "USER_ID", DataTypesEnum.NUMBER.get() }, { "WINNER", DataTypesEnum.TEXT.get() },
				{ "HOME_SCORE", DataTypesEnum.NUMBER.get() }, { "VISITSCORE", DataTypesEnum.NUMBER.get() },
				{ "OT", DataTypesEnum.NUMBER.get() }, { "USERSCORE", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Nba2016GameBet createBeanFromRS(ResultSet rs) throws SQLException {
		Nba2016GameBet rec = new Nba2016GameBet();
		rec.setGameId(rs.getInt(1));
		rec.setUserId(rs.getInt(2));
		rec.setWinner(rs.getString(3));
		rec.setHomeScore(rs.getInt(4));
		rec.setVisitScore(rs.getInt(5));
		rec.setOverTime(rs.getInt(6));
		rec.setUserScore(rs.getInt(7));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Nba2016GameBet bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getGameId());
		ps.setInt(2, bean.getUserId());
		ps.setString(3, bean.getWinner());
		ps.setInt(4, bean.getHomeScore());
		ps.setInt(5, bean.getVisitScore());
		ps.setInt(6, bean.getOverTime());
		ps.setInt(7, bean.getUserScore());
		ps.setInt(8, bean.getGameId());
		ps.setInt(9, bean.getUserId());
	}

	@Override
	public int insert(Nba2016GameBet bean) {
		return createEntry(bean.getGameId(), bean.getUserId(), bean.getWinner(), bean.getHomeScore(),
				bean.getVisitScore(), bean.getOverTime(), bean.getUserScore());
	}

	@Override
	protected String getKeyWhereClause() {
		return " WHERE GAME_ID = ? AND USER_ID = ? ";
	}

	@Override
	protected void setKeyParameters(PreparedStatement ps, Object... keys) throws SQLException {
		ps.setInt(1, ((Integer) keys[0]).intValue());
		ps.setInt(2, ((Integer) keys[1]).intValue());
	}

}
