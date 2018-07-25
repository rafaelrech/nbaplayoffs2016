package rio2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;
import rio2016.bean.Rio2016Competitor;

public class Rio2016CompetitorDao extends CommonDao<Rio2016Competitor> {

	private static volatile Rio2016CompetitorDao instance = null;

	private Rio2016CompetitorDao() {
	}

	public static Rio2016CompetitorDao getInstance() {
		if (instance == null) {
			synchronized (Rio2016CompetitorDao.class) {
				if (instance == null) {
					instance = new Rio2016CompetitorDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "RIO2016_COMPETITOR", "" }, { "ID", DataTypesEnum.NUMBER.get() },
				{ "NAME", DataTypesEnum.TEXT.get() }, { "COUNTRY", DataTypesEnum.TEXT.get() },
				{ "PICTURE", DataTypesEnum.TEXT.get() } };
		return def;
	}

	@Override
	protected Rio2016Competitor createBeanFromRS(ResultSet rs) throws SQLException {
		Rio2016Competitor rec = new Rio2016Competitor();
		rec.setId(rs.getInt(1));
		rec.setName(rs.getString(2));
		rec.setCountry(rs.getString(3));
		rec.setPicture(rs.getString(4));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Rio2016Competitor bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setString(2, bean.getName());
		ps.setString(3, bean.getCountry());
		ps.setString(4, bean.getPicture());
		ps.setInt(5, bean.getId());
	}

	@Override
	public int insert(Rio2016Competitor bean) {
		if (bean.getId() == -1) {
			ArrayList<Rio2016Competitor> all = new ArrayList<Rio2016Competitor>();
			try {
				all = listAll();
			} catch (SQLException e) {
			}
			int maxId = 0;
			for (Rio2016Competitor rec : all) {
				maxId = Math.max(maxId, rec.getId());
			}
			maxId++;
			bean.setId(maxId);
		}
		return createEntry(bean.getId(), bean.getName(), bean.getCountry(), bean.getPicture());
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY COUNTRY, NAME ";
	}

}
