package rio2016.bean;

import rech.bolao.bean.CommonBean;
import rio2016.dao.Rio2016ParticipationDao;
import rio2016.util.MedalEnum;

public class Rio2016Participation extends CommonBean {

	public int competitorId;
	public int sportId;
	public int result = MedalEnum.NO_MEDAL.getId();
	private Rio2016Competitor competitor;
	private Rio2016Sport sport;

	public Rio2016Participation() {
		super();
	}

	public Rio2016Participation(int competitorId, int sportId) {
		super();
		this.competitorId = competitorId;
		this.sportId = sportId;
	}

	public Rio2016Participation(int competitorId, int sportId, int result) {
		super();
		this.competitorId = competitorId;
		this.sportId = sportId;
		this.result = result;
	}

	public int getCompetitorId() {
		return competitorId;
	}

	public void setCompetitorId(int competitorId) {
		this.competitorId = competitorId;
	}

	public int getSportId() {
		return sportId;
	}

	public void setSportId(int sportId) {
		this.sportId = sportId;
	}

	public int getResult() {
		return result;
	}

	public void setResult(int result) {
		this.result = result;
	}

	public Rio2016Competitor getCompetitor() {
		return competitor;
	}

	public void setCompetitor(Rio2016Competitor competitor) {
		this.competitor = competitor;
	}

	public Rio2016Sport getSport() {
		return sport;
	}

	public void setSport(Rio2016Sport sport) {
		this.sport = sport;
	}

	public Rio2016Participation(int competitorId, int sportId, Rio2016Competitor competitor, Rio2016Sport sport) {
		super();
		this.competitorId = competitorId;
		this.sportId = sportId;
		this.competitor = competitor;
		this.sport = sport;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Rio2016Participation.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Rio2016Participation.class,
				Rio2016ParticipationDao.getInstance().generateInsertStatement());
	}

}
