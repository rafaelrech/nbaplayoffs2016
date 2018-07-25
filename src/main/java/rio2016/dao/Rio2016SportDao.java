package rio2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Calendar;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;
import rio2016.bean.Rio2016Sport;

public class Rio2016SportDao extends CommonDao<Rio2016Sport> {

	private static volatile Rio2016SportDao instance = null;

	private Rio2016SportDao() {
	}

	public static Rio2016SportDao getInstance() {
		if (instance == null) {
			synchronized (Rio2016SportDao.class) {
				if (instance == null) {
					instance = new Rio2016SportDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "RIO2016_SPORT", "" }, { "ID", DataTypesEnum.NUMBER.get() },
				{ "NAME", DataTypesEnum.TEXT.get() }, { "GENDER", DataTypesEnum.NUMBER.get() },
				{ "START_DT", DataTypesEnum.DATE.get() }, { "END_DT", DataTypesEnum.DATE.get() },
				{ "TYPE", DataTypesEnum.NUMBER.get() }, { "COMPLETED", DataTypesEnum.NUMBER.get() },
				{ "QTY_GOLD", DataTypesEnum.NUMBER.get() }, { "QTY_SILVER", DataTypesEnum.NUMBER.get() },
				{ "QTY_BRONZE", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	public int id;
	public String name;
	public int gender;
	public Calendar startDate;
	public Calendar endDate;
	public int sportType;
	public int completed;
	public int qtyGold = 1;
	public int qtySilver = 1;
	public int qtyBronze = 1;

	@Override
	protected Rio2016Sport createBeanFromRS(ResultSet rs) throws SQLException {
		Rio2016Sport rec = new Rio2016Sport();
		rec.setId(rs.getInt(1));
		rec.setName(rs.getString(2));
		rec.setGender(rs.getInt(3));
		rec.setStartDate(timestampToCalendar(rs.getTimestamp(4)));
		rec.setEndDate(timestampToCalendar(rs.getTimestamp(5)));
		rec.setSportType(rs.getInt(6));
		rec.setCompleted(rs.getInt(7));
		rec.setQtyGold(rs.getInt(8));
		rec.setQtySilver(rs.getInt(9));
		rec.setQtyBronze(rs.getInt(10));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Rio2016Sport bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getId());
		ps.setString(2, bean.getName());
		ps.setInt(3, bean.getGender());
		ps.setTimestamp(4, (bean.getStartDate() == null) ? null : new Timestamp(bean.getStartDate().getTimeInMillis()));
		ps.setTimestamp(5, (bean.getEndDate() == null) ? null : new Timestamp(bean.getEndDate().getTimeInMillis()));
		ps.setInt(6, bean.getSportType());
		ps.setInt(7, bean.getCompleted());
		ps.setInt(8, bean.getQtyGold());
		ps.setInt(9, bean.getQtySilver());
		ps.setInt(10, bean.getQtyBronze());
		ps.setInt(11, bean.getId());
	}

	@Override
	public int insert(Rio2016Sport bean) {
		if (bean.getId() == -1) {
			ArrayList<Rio2016Sport> all = new ArrayList<Rio2016Sport>();
			try {
				all = listAll();
			} catch (SQLException e) {
			}
			int maxId = 0;
			for (Rio2016Sport rec : all) {
				maxId = Math.max(maxId, rec.getId());
			}
			maxId++;
			bean.setId(maxId);
		}
		return createEntry(bean.getId(), bean.getName(), bean.getGender(), bean.getStartDate(), bean.getEndDate(),
				bean.getSportType(), bean.getCompleted(), bean.getQtyGold(), bean.getQtySilver(), bean.getQtyBronze());
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY START_DT, NAME ";
	}

}
