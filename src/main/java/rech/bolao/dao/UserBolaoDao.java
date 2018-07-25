package rech.bolao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.bean.UserBolao;
import rech.bolao.util.DataTypesEnum;

public class UserBolaoDao extends CommonDao<UserBolao> {

	private static volatile UserBolaoDao instance;

	private UserBolaoDao() {
	}

	public static UserBolaoDao getInstance() {
		if (instance == null) {
			synchronized (UserBolaoDao.class) {
				if (instance == null) {
					instance = new UserBolaoDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "USER_BOLAO", "" }, { "BOLAO_ID", DataTypesEnum.NUMBER.get() },
				{ "USER_ID", DataTypesEnum.NUMBER.get() }, { "USER_SCORE", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected UserBolao createBeanFromRS(ResultSet rs) throws SQLException {
		UserBolao rec = new UserBolao();
		rec.setBolaoId(rs.getInt(1));
		rec.setUserId(rs.getInt(2));
		rec.setUserScore(rs.getInt(3));
		return rec;
	}

	@Override
	protected void setUpdateParameters(UserBolao bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getBolaoId());
		ps.setInt(2, bean.getUserId());
		ps.setInt(3, bean.getUserScore());
		ps.setInt(4, bean.getBolaoId());
		ps.setInt(5, bean.getUserId());
	}

	@Override
	public int insert(UserBolao bean) {
		return createEntry(bean.getBolaoId(), bean.getUserId(), bean.getUserScore());
	}

	@Override
	protected String getKeyWhereClause() {
		return " WHERE BOLAO_ID = ? AND USER_ID = ? ";

	}

	@Override
	protected void setKeyParameters(PreparedStatement ps, Object... keys) throws SQLException {
		ps.setInt(1, ((Integer) keys[0]).intValue());
		ps.setInt(2, ((Integer) keys[1]).intValue());
	}
	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY BOLAO_ID, USER_SCORE DESC ";
	}

}
