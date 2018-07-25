package nbaplayoffs2016.bean;

import java.util.ArrayList;

import nbaplayoffs2016.dao.Nba2016SeriesDao;
import rech.bolao.bean.CommonBean;

public class Nba2016Series extends CommonBean {

	public int id;
	public int fase;
	public String team1;
	public String team2;
	public String winner;
	public int nbrGames;
	public int prevId1;
	public int prevId2;

	private ArrayList<Nba2016SeriesBet> bets = new ArrayList<Nba2016SeriesBet>();
	private ArrayList<Nba2016Game> games = new ArrayList<Nba2016Game>();

	public Nba2016Series() {
		super();
	}

	public Nba2016Series(int id, int fase, String team1, String team2, String winner, int nbrGames, int prevId1,
			int prevId2) {
		super();
		this.id = id;
		this.fase = fase;
		this.team1 = team1;
		this.team2 = team2;
		this.winner = winner;
		this.nbrGames = nbrGames;
		this.prevId1 = prevId1;
		this.prevId2 = prevId2;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getFase() {
		return fase;
	}

	public void setFase(int fase) {
		this.fase = fase;
	}

	public String getTeam1() {
		return team1;
	}

	public void setTeam1(String team1) {
		this.team1 = team1;
	}

	public String getTeam2() {
		return team2;
	}

	public void setTeam2(String team2) {
		this.team2 = team2;
	}

	public String getWinner() {
		return winner;
	}

	public void setWinner(String winner) {
		this.winner = winner;
	}

	public int getNbrGames() {
		return nbrGames;
	}

	public void setNbrGames(int nbrGames) {
		this.nbrGames = nbrGames;
	}

	public int getPrevId1() {
		return prevId1;
	}

	public void setPrevId1(int prevId1) {
		this.prevId1 = prevId1;
	}

	public int getPrevId2() {
		return prevId2;
	}

	public void setPrevId2(int prevId2) {
		this.prevId2 = prevId2;
	}

	@Override
	public StringBuffer toJson() {
		return super.toJson(Nba2016Series.class, null);
	}

	@Override
	public StringBuffer toInsertString() {
		return super.toInsertString(Nba2016Series.class, Nba2016SeriesDao.getInstance().generateInsertStatement());
	}

	public ArrayList<Nba2016SeriesBet> getBets() {
		return bets;
	}

	public void setBets(ArrayList<Nba2016SeriesBet> bets) {
		this.bets = bets;
	}

	public void addBet(Nba2016SeriesBet bet) {
		if (bets == null) {
			bets = new ArrayList<Nba2016SeriesBet>();
		}
		bets.add(bet);
	}

	public ArrayList<Nba2016Game> getGames() {
		return games;
	}

	public void setGames(ArrayList<Nba2016Game> games) {
		this.games = games;
	}

	public void addGame(Nba2016Game game) {
		if (games == null) {
			games = new ArrayList<Nba2016Game>();
		}
		games.add(game);
	}

}
