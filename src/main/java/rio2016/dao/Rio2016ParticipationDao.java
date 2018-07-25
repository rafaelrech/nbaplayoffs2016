package rio2016.dao;

import java.sql.ResultSet;
import java.sql.SQLException;

import com.mysql.jdbc.PreparedStatement;

import rech.bolao.dao.CommonDao;
import rech.bolao.util.DataTypesEnum;
import rio2016.bean.Rio2016Participation;

public class Rio2016ParticipationDao extends CommonDao<Rio2016Participation> {

	private static volatile Rio2016ParticipationDao instance = null;

	private Rio2016ParticipationDao() {
	}

	public static Rio2016ParticipationDao getInstance() {
		if (instance == null) {
			synchronized (Rio2016ParticipationDao.class) {
				if (instance == null) {
					instance = new Rio2016ParticipationDao();
				}
			}
		}
		return instance;
	}

	public String[][] getTableDefinition() {
		String[][] def = { { "RIO2016_PARTICIPATION", "" }, { "COMPETITOR_ID", DataTypesEnum.NUMBER.get() },
				{ "SPORT_ID", DataTypesEnum.NUMBER.get() },
				{ "RESULT", DataTypesEnum.NUMBER.get() } };
		return def;
	}

	@Override
	protected Rio2016Participation createBeanFromRS(ResultSet rs) throws SQLException {
		Rio2016Participation rec = new Rio2016Participation();
		rec.setCompetitorId(rs.getInt(1));
		rec.setSportId(rs.getInt(2));
		rec.setResult(rs.getInt(3));
		return rec;
	}

	@Override
	protected void setUpdateParameters(Rio2016Participation bean, PreparedStatement ps) throws SQLException {
		ps.setInt(1, bean.getCompetitorId());
		ps.setInt(2, bean.getSportId());
		ps.setInt(3, bean.getResult());
		ps.setInt(4, bean.getCompetitorId());
		ps.setInt(5, bean.getSportId());
	}

	@Override
	public int insert(Rio2016Participation bean) {
		return createEntry(new Integer(bean.getCompetitorId()), new Integer(bean.getSportId()), new Integer(bean.getResult()));
	}

	@Override
	protected String getKeyWhereClause() {
		return " WHERE COMPETITOR_ID = ? AND SPORT_ID = ? ";
	}

	@Override
	protected String getDefaultOrderByClause() {
		return " ORDER BY SPORT_ID, RESULT ASC ";
	}

	@Override
	protected void setKeyParameters(PreparedStatement ps, Object... keys) throws SQLException {
		ps.setInt(1, ((Integer) keys[0]).intValue());
		ps.setInt(2, ((Integer) keys[1]).intValue());
	}

}
