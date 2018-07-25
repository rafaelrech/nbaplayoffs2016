package rech.bolao.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.bean.Bolao;
import rech.bolao.util.DataTypesEnum;

public class BolaoDao extends CommonDao<Bolao> {

	private static volatile BolaoDao instance;

	private BolaoDao() {
	}

	public static BolaoDao getInstance() {
		if (instance == null) {
			synchronized (BolaoDao.class) {
				if (instance == null) {
					instance = new BolaoDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "BOLAO", "" }, { "ID", DataTypesEnum.NUMBER.get() }, { "NAME", DataTypesEnum.TEXT.get() },
				{ "DESCRIPTION", DataTypesEnum.BIGTEXT.get() }, { "FOLDER_NAME", DataTypesEnum.BIGTEXT.get() },
				{ "REGISTRATION_DEADLINE", DataTypesEnum.DATE.get() }, { "INIT_DATE", DataTypesEnum.DATE.get() },
				{ "COMPLETE_DATE", DataTypesEnum.DATE.get() }, { "AVAILABLE", DataTypesEnum.NUMBER.get() },
				{ "COMPLETED", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Bolao createBeanFromRS(ResultSet rs) throws SQLException {
		Bolao rec = new Bolao();
		rec.setId(rs.getInt(1));
		rec.setName(rs.getString(2));
		rec.setDescription(rs.getString(3));
		rec.setFolderName(rs.getString(4));
		rec.setRegisterDeadlineDate(timestampToCalendar(rs.getTimestamp(5)));
		rec.setInitDate(timestampToCalendar(rs.getTimestamp(6)));
		rec.setCompleteDate(timestampToCalendar(rs.getTimestamp(7)));
		rec.setAvailable(rs.getInt(8));
		rec.setCompleted(rs.getInt(9));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Bolao bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setString(2, bean.getName());
		ps.setString(3, bean.getDescription());
		ps.setString(4, bean.getFolderName());
		ps.setTimestamp(5, (bean.getRegisterDeadlineDate() == null) ? null
				: new Timestamp(bean.getRegisterDeadlineDate().getTimeInMillis()));
		ps.setTimestamp(6, (bean.getInitDate() == null) ? null : new Timestamp(bean.getInitDate().getTimeInMillis()));
		ps.setTimestamp(7,
				(bean.getCompleteDate() == null) ? null : new Timestamp(bean.getCompleteDate().getTimeInMillis()));
		ps.setInt(8, bean.getAvailable());
		ps.setInt(9, bean.getCompleted());
		ps.setInt(10, bean.getId());
	}

	@Override
	public int insert(Bolao bean) {
		ArrayList<Bolao> all = new ArrayList<Bolao>();
		try {
			all = listAll();
		} catch (SQLException e) {
		}
		int maxId = 0;
		for (Bolao rec : all) {
			maxId = Math.max(maxId, rec.getId());
		}
		maxId++;
		return createEntry(new Integer(maxId), bean.getName(), bean.getDescription(), bean.getFolderName(),
				bean.getRegisterDeadlineDate(), bean.getInitDate(), bean.getCompleteDate(), bean.getAvailable(),
				bean.getCompleted());
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY REGISTRATION_DEADLINE ";
	}

}
