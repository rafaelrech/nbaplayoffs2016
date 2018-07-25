package nbaplayoffs2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.mysql.jdbc.PreparedStatement;

import nbaplayoffs2016.bean.Nba2016SeriesBet;
import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;

public class Nba2016SeriesBetDao extends CommonDao<Nba2016SeriesBet> {

	private static volatile Nba2016SeriesBetDao instance;

	private Nba2016SeriesBetDao() {
	}

	public static Nba2016SeriesBetDao getInstance() {
		if (instance == null) {
			synchronized (Nba2016SeriesBetDao.class) {
				if (instance == null) {
					instance = new Nba2016SeriesBetDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "SERIES_BETS", "" }, { "BRACKET_ID", DataTypesEnum.NUMBER.get() },
				{ "USER_ID", DataTypesEnum.NUMBER.get() }, { "WINNER", DataTypesEnum.TEXT.get() },
				{ "GAMES", DataTypesEnum.NUMBER.get() }, { "PONTUACAO", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Nba2016SeriesBet createBeanFromRS(ResultSet rs) throws SQLException {
		Nba2016SeriesBet rec = new Nba2016SeriesBet();
		rec.setBracketId(rs.getInt(1));
		rec.setUserId(rs.getInt(2));
		rec.setWinner(rs.getString(3));
		rec.setGames(rs.getInt(4));
		rec.setUserScore(rs.getInt(5));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Nba2016SeriesBet bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getBracketId());
		ps.setInt(2, bean.getUserId());
		ps.setString(3, bean.getWinner());
		ps.setInt(4, bean.getGames());
		ps.setInt(5, bean.getUserScore());
		ps.setInt(6, bean.getBracketId());
		ps.setInt(7, bean.getUserId());
	}

	@Override
	public int insert(Nba2016SeriesBet bean) {
		return createEntry(bean.getBracketId(), bean.getUserId(), bean.getWinner(), bean.getGames(),
				bean.getUserScore());
	}

	@Override
	protected String getKeyWhereClause() {
		return " WHERE BRACKET_ID = ? AND USER_ID = ? ";

	}

	@Override
	protected void setKeyParameters(PreparedStatement ps, Object... keys) throws SQLException {
		ps.setInt(1, ((Integer) keys[0]).intValue());
		ps.setInt(2, ((Integer) keys[1]).intValue());
	}

	public List<Nba2016SeriesBet> listUserBets(int userId) {
		ArrayList<Nba2016SeriesBet> all = new ArrayList<Nba2016SeriesBet>();
		try {
			all = listAll();
		} catch (SQLException e) {
		}
		ArrayList<Nba2016SeriesBet> ret = new ArrayList<Nba2016SeriesBet>();
		for (Nba2016SeriesBet bet : all) {
			if (bet.getUserId() == userId) {
				ret.add(bet);
			}
		}
		return ret;
	}
}
