package rio2016.bean;

import java.util.ArrayList;
import java.util.Calendar;

import rech.bolao.bean.CommonBean;
import rio2016.dao.Rio2016SportDao;

public class Rio2016Sport extends CommonBean {

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

	private ArrayList<Rio2016Participation> paticipations = new ArrayList<Rio2016Participation>();
	private ArrayList<Rio2016MedalBet> medalBets = new ArrayList<Rio2016MedalBet>();

	public Rio2016Sport() {
		super();
	}

	public Rio2016Sport(int id, String name, int gender, Calendar startDate, Calendar endDate, int sportType,
			int completed, int qtyGold, int qtySilver, int qtyBronze) {
		super();
		this.id = id;
		this.name = name;
		this.gender = gender;
		this.startDate = startDate;
		this.endDate = endDate;
		this.sportType = sportType;
		this.completed = completed;
		this.qtyGold = qtyGold;
		this.qtySilver = qtySilver;
		this.qtyBronze = qtyBronze;
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

	public int getGender() {
		return gender;
	}

	public void setGender(int gender) {
		this.gender = gender;
	}

	public Calendar getStartDate() {
		return startDate;
	}

	public void setStartDate(Calendar startDate) {
		this.startDate = startDate;
	}

	public Calendar getEndDate() {
		return endDate;
	}

	public void setEndDate(Calendar endDate) {
		this.endDate = endDate;
	}

	public int getSportType() {
		return sportType;
	}

	public void setSportType(int sportType) {
		this.sportType = sportType;
	}

	public int getCompleted() {
		return completed;
	}

	public void setCompleted(int completed) {
		this.completed = completed;
	}

	public int getQtyGold() {
		return qtyGold;
	}

	public void setQtyGold(int qtyGold) {
		this.qtyGold = qtyGold;
	}

	public int getQtySilver() {
		return qtySilver;
	}

	public void setQtySilver(int qtySilver) {
		this.qtySilver = qtySilver;
	}

	public int getQtyBronze() {
		return qtyBronze;
	}

	public void setQtyBronze(int qtyBronze) {
		this.qtyBronze = qtyBronze;
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
		return super.toJson(Rio2016Sport.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Rio2016Sport.class, Rio2016SportDao.getInstance().generateInsertStatement());
	}
}
