package nbaplayoffs2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import nbaplayoffs2016.bean.Nba2016TieBreaker;
import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;

public class Nba2016TieBreakerDao extends CommonDao<Nba2016TieBreaker> {

	private static volatile Nba2016TieBreakerDao instance;

	private Nba2016TieBreakerDao() {
	}

	public static Nba2016TieBreakerDao getInstance() {
		if (instance == null) {
			synchronized (Nba2016TieBreakerDao.class) {
				if (instance == null) {
					instance = new Nba2016TieBreakerDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "TIE_BREAKERS", "" }, { "ID", DataTypesEnum.NUMBER.get() },
				{ "DESCRIPTION", DataTypesEnum.TEXT.get() }, { "VALUE", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Nba2016TieBreaker createBeanFromRS(ResultSet rs) throws SQLException {
		Nba2016TieBreaker rec = new Nba2016TieBreaker();
		rec.setId(rs.getInt(1));
		rec.setDescription(rs.getString(2));
		rec.setValue(rs.getString(3));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Nba2016TieBreaker bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setString(2, bean.getDescription());
		ps.setString(3, bean.getValue());
		ps.setInt(4, bean.getId());
	}

	@Override
	public int insert(Nba2016TieBreaker bean) {
		ArrayList<Nba2016TieBreaker> allUsers = new ArrayList<Nba2016TieBreaker>();
		try {
			allUsers = listAll();
		} catch (SQLException e) {
		}
		int maxId = 0;
		for (Nba2016TieBreaker rec : allUsers) {
			maxId = Math.max(maxId, rec.getId());
		}
		maxId++;
		return createEntry(new Integer(maxId), bean.getDescription(), bean.getValue());
	}

}
