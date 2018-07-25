package rio2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;
import rio2016.bean.Rio2016User;

public class Rio2016UserDao extends CommonDao<Rio2016User> {

	private static volatile Rio2016UserDao instance = null;

	private Rio2016UserDao() {
	}

	public static Rio2016UserDao getInstance() {
		if (instance == null) {
			synchronized (Rio2016UserDao.class) {
				if (instance == null) {
					instance = new Rio2016UserDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "RIO2016_USER", "" }, { "USER_ID", DataTypesEnum.NUMBER.get() },
				{ "SCORE", DataTypesEnum.NUMBER.get() }, { "MEDAL_SCORE", DataTypesEnum.NUMBER.get() },
				{ "BOARD_SCORE", DataTypesEnum.NUMBER.get() }, { "BRAZIL_SCORE", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Rio2016User createBeanFromRS(ResultSet rs) throws SQLException {
		Rio2016User rec = new Rio2016User();
		rec.setUserId(rs.getInt(1));
		rec.setScore(rs.getInt(2));
		rec.setMedalBetsScore(rs.getInt(3));
		rec.setBoardBetsScore(rs.getInt(4));
		rec.setBrBoardBetScore(rs.getInt(5));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Rio2016User bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getUserId());
		ps.setInt(2, bean.getScore());
		ps.setInt(3, bean.getMedalBetsScore());
		ps.setInt(4, bean.getBoardBetsScore());
		ps.setInt(5, bean.getBrBoardBetScore());
		ps.setInt(6, bean.getUserId());
	}

	@Override
	public int insert(Rio2016User bean) {
		if (bean.getUserId() == -1) {
			ArrayList<Rio2016User> allUsers = new ArrayList<Rio2016User>();
			try {
				allUsers = listAll();
			} catch (SQLException e) {
			}
			int maxId = 0;
			for (Rio2016User rec : allUsers) {
				maxId = Math.max(maxId, rec.getUserId());
			}
			maxId++;
			bean.setUserId(maxId);
		}
		return createEntry(bean.getUserId(), new Integer(bean.getScore()), new Integer(bean.getMedalBetsScore()),
				new Integer(bean.getBoardBetsScore()), new Integer(bean.getBrBoardBetScore()));
	}

	@Override
	protected String getKeyWhereClause() {
		return " WHERE USER_ID = ?";
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY SCORE DESC ";
	}

}
