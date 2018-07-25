package nbaplayoffs2016.bean;

import java.util.ArrayList;

import nbaplayoffs2016.dao.Nba2016TieBreakerDao;
import rech.bolao.bean.CommonBean;

public class Nba2016TieBreaker extends CommonBean {

	public int id;
	public String description;
	public String value;

	private ArrayList<Nba2016TieBreakerBet> bets = new ArrayList<Nba2016TieBreakerBet>();

	public Nba2016TieBreaker() {
		super();
	}

	public Nba2016TieBreaker(int id, String description, String value) {
		super();
		this.id = id;
		this.description = description;
		this.value = value;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public String getValue() {
		return value;
	}

	public void setValue(String value) {
		this.value = value;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Nba2016TieBreaker.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Nba2016TieBreaker.class,
				Nba2016TieBreakerDao.getInstance().generateInsertStatement());
	}

	public ArrayList<Nba2016TieBreakerBet> getBets() {
		return bets;
	}

	public void setBets(ArrayList<Nba2016TieBreakerBet> bets) {
		this.bets = bets;
	}

	public void addBet(Nba2016TieBreakerBet bet) {
		if (bets == null) {
			bets = new ArrayList<Nba2016TieBreakerBet>();
		}
		bets.add(bet);
	}

}
