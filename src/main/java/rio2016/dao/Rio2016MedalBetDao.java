package rio2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;
import rio2016.bean.Rio2016MedalBet;

public class Rio2016MedalBetDao extends CommonDao<Rio2016MedalBet> {

	private static volatile Rio2016MedalBetDao instance = null;

	private Rio2016MedalBetDao() {
	}

	public static Rio2016MedalBetDao getInstance() {
		if (instance == null) {
			synchronized (Rio2016MedalBetDao.class) {
				if (instance == null) {
					instance = new Rio2016MedalBetDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "RIO2016_MEDAL_BET", "" }, { "COMPETITOR_ID", DataTypesEnum.NUMBER.get() },
				{ "SPORT_ID", DataTypesEnum.NUMBER.get() }, { "USER_ID", DataTypesEnum.NUMBER.get() },
				{ "BET", DataTypesEnum.NUMBER.get() }, { "SCORE", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Rio2016MedalBet createBeanFromRS(ResultSet rs) throws SQLException {
		Rio2016MedalBet rec = new Rio2016MedalBet();
		rec.setCompetitorId(rs.getInt(1));
		rec.setSportId(rs.getInt(2));
		rec.setUserId(rs.getInt(3));
		rec.setBet(rs.getInt(4));
		rec.setScore(rs.getInt(5));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Rio2016MedalBet bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getCompetitorId());
		ps.setInt(2, bean.getSportId());
		ps.setInt(3, bean.getUserId());
		ps.setInt(4, bean.getBet());
		ps.setInt(5, bean.getScore());
		ps.setInt(6, bean.getCompetitorId());
		ps.setInt(7, bean.getSportId());
		ps.setInt(8, bean.getUserId());
	}

	@Override
	public int insert(Rio2016MedalBet bean) {
		return createEntry(new Integer(bean.getCompetitorId()), new Integer(bean.getSportId()),
				new Integer(bean.getUserId()), new Integer(bean.getBet()), new Integer(bean.getScore()));
	}

	@Override
	protected String getKeyWhereClause() {
		return " WHERE COMPETITOR_ID = ? AND SPORT_ID = ? AND USER_ID = ? ";
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY USER_ID, SPORT_ID, BET ASC ";
	}

	@Override
	protected void setKeyParameters(PreparedStatement ps, Object... keys) throws SQLException {
		ps.setInt(1, ((Integer) keys[0]).intValue());
		ps.setInt(2, ((Integer) keys[1]).intValue());
		ps.setInt(3, ((Integer) keys[2]).intValue());
	}

}
