package rio2016.bean;

import java.util.ArrayList;

import rech.bolao.bean.CommonBean;
import rio2016.dao.Rio2016CompetitorDao;

public class Rio2016Competitor extends CommonBean {

	public int id;
	public String name;
	public String country;
	public String picture;

	private ArrayList<Rio2016Participation> paticipations = new ArrayList<Rio2016Participation>();
	private ArrayList<Rio2016MedalBet> medalBets = new ArrayList<Rio2016MedalBet>();

	public Rio2016Competitor() {
		super();
	}

	public Rio2016Competitor(int id, String name, String country, String picture) {
		super();
		this.id = id;
		this.name = name;
		this.country = country;
		this.picture = picture;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getCountry() {
		return country;
	}

	public void setCountry(String country) {
		this.country = country;
	}

	public String getPicture() {
		return picture;
	}

	public void setPicture(String picture) {
		this.picture = picture;
	}

	public ArrayList<Rio2016Participation> getPaticipations() {
		return paticipations;
	}

	public void setPaticipations(ArrayList<Rio2016Participation> paticipations) {
		this.paticipations = paticipations;
	}

	public void addParticipation(Rio2016Participation part) {
		if (this.paticipations == null) {
			this.paticipations = new ArrayList<Rio2016Participation>();
		}
		this.paticipations.add(part);
	}

	public ArrayList<Rio2016MedalBet> getMedalBets() {
		return medalBets;
	}

	public void setMedalBets(ArrayList<Rio2016MedalBet> medalBets) {
		this.medalBets = medalBets;
	}

	public void addBet(Rio2016MedalBet bet) {
		if (medalBets == null) {
			medalBets = new ArrayList<Rio2016MedalBet>();
		}
		medalBets.add(bet);
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Rio2016Competitor.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Rio2016Competitor.class,
				Rio2016CompetitorDao.getInstance().generateInsertStatement());
	}

}
