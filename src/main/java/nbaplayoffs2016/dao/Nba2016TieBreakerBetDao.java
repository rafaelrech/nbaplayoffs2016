package nbaplayoffs2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import nbaplayoffs2016.bean.Nba2016TieBreakerBet;
import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;

public class Nba2016TieBreakerBetDao extends CommonDao<Nba2016TieBreakerBet> {

	private static volatile Nba2016TieBreakerBetDao instance;

	private Nba2016TieBreakerBetDao() {
	}

	public static Nba2016TieBreakerBetDao getInstance() {
		if (instance == null) {
			synchronized (Nba2016TieBreakerBetDao.class) {
				if (instance == null) {
					instance = new Nba2016TieBreakerBetDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "TIE_BREAKER_BETS", "" }, { "TB_ID", DataTypesEnum.NUMBER.get() },
				{ "USER_ID", DataTypesEnum.NUMBER.get() }, { "VALUE", DataTypesEnum.TEXT.get() },
				{ "PONTUACAO", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Nba2016TieBreakerBet createBeanFromRS(ResultSet rs) throws SQLException {
		Nba2016TieBreakerBet rec = new Nba2016TieBreakerBet();
		rec.setTieBrackerId(rs.getInt(1));
		rec.setUserId(rs.getInt(2));
		rec.setValue(rs.getString(3));
		rec.setUserScore(rs.getInt(4));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Nba2016TieBreakerBet bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getTieBrackerId());
		ps.setInt(2, bean.getUserId());
		ps.setString(3, bean.getValue());
		ps.setInt(4, bean.getUserScore());
		ps.setInt(5, bean.getTieBrackerId());
		ps.setInt(6, bean.getUserId());
	}

	@Override
	public int insert(Nba2016TieBreakerBet bean) {
		return createEntry(bean.getTieBrackerId(), bean.getUserId(), bean.getValue(), bean.getUserScore());
	}

	@Override
	protected String getKeyWhereClause() {
		return " WHERE TB_ID = ? AND USER_ID = ? ";

	}

	@Override
	protected void setKeyParameters(PreparedStatement ps, Object... keys) throws SQLException {
		ps.setInt(1, ((Integer) keys[0]).intValue());
		ps.setInt(2, ((Integer) keys[1]).intValue());
	}

}
