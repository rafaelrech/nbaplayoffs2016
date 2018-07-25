package nbaplayoffs2016.util;

import java.sql.Connection;
import java.util.Calendar;

import nbaplayoffs2016.bean.Nba2016Game;
import nbaplayoffs2016.bean.Nba2016GameBet;
import nbaplayoffs2016.bean.Nba2016User;
import nbaplayoffs2016.bean.Nba2016SeriesBet;
import nbaplayoffs2016.bean.Nba2016TieBreaker;
import nbaplayoffs2016.bean.Nba2016TieBreakerBet;
import nbaplayoffs2016.dao.Nba2016GameBetDao;
import nbaplayoffs2016.dao.Nba2016GameDao;
import nbaplayoffs2016.dao.Nba2016UserDao;
import nbaplayoffs2016.dao.Nba2016SeriesBetDao;
import nbaplayoffs2016.dao.Nba2016SeriesDao;
import nbaplayoffs2016.dao.Nba2016TieBreakerBetDao;
import nbaplayoffs2016.dao.Nba2016TieBreakerDao;
import rech.bolao.util.BaseDBUtil;

public class Nba2016DBUtil {

	public static Connection getConnection() {
		return BaseDBUtil.getConnection();
	}

	public static void initDatabase() {
		initDatabase(false);
	}

	public static void initDatabase(boolean dropFirst) {
		// if (true) {
		// return;
		// }
		if (dropFirst) {
			dropTables();
		}
		createTables();

		// USER
		loadUsers();

		loadBrackets();

		loadBracketBets();

		// GAMES
		loadGames();

		// GAME BETS
		loadGameBets();

		loadTieBreakers();
		loadTieBreakerBets();
	}

	public static void createTables() {
		createTable(Nba2016UserDao.getInstance().getTableDefinition());
		createTable(Nba2016GameDao.getInstance().getTableDefinition());
		createTable(Nba2016SeriesDao.getInstance().getTableDefinition());
		createTable(Nba2016TieBreakerDao.getInstance().getTableDefinition());
		createTable(Nba2016GameBetDao.getInstance().getTableDefinition());
		createTable(Nba2016SeriesBetDao.getInstance().getTableDefinition());
		createTable(Nba2016TieBreakerBetDao.getInstance().getTableDefinition());
	}

	public static void createTable(String bean) {
		if (bean.equals("NBA2016-USER")) {
			createTable(Nba2016UserDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-GAME")) {
			createTable(Nba2016GameDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-SERIES")) {
			createTable(Nba2016SeriesDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-TIE-BREAKER")) {
			createTable(Nba2016TieBreakerDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-GAME-BET")) {
			createTable(Nba2016GameBetDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-SERIES-BET")) {
			createTable(Nba2016SeriesBetDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-MVP")) {
			createTable(Nba2016TieBreakerBetDao.getInstance().getTableDefinition());
		}
	}

	private static void createTable(String[][] def) {
		StringBuffer sql = new StringBuffer(String.format("CREATE TABLE %s (", def[0][0]));
		for (int idx = 1; idx < def.length; idx++) {
			sql.append(String.format("%s %s NULL, ", def[idx][0], def[idx][1]));
		}
		sql.deleteCharAt(sql.length() - 1);
		sql.deleteCharAt(sql.length() - 1);
		sql.append(") ENGINE=InnoDB ");
		System.out.println(String.format(" %s ", sql.toString()));
		System.out.println(String.format("CREATING %s : %d changes", def[0][0], BaseDBUtil.executeDML(sql.toString())));
	}

	public static void dropTables() {
		dropTable(Nba2016UserDao.getInstance().getTableDefinition());
		dropTable(Nba2016GameDao.getInstance().getTableDefinition());
		dropTable(Nba2016SeriesDao.getInstance().getTableDefinition());
		dropTable(Nba2016TieBreakerDao.getInstance().getTableDefinition());
		dropTable(Nba2016GameBetDao.getInstance().getTableDefinition());
		dropTable(Nba2016SeriesBetDao.getInstance().getTableDefinition());
		dropTable(Nba2016TieBreakerBetDao.getInstance().getTableDefinition());
	}

	public static void dropTable(String bean) {
		if (bean.equals("NBA2016-USER")) {
			dropTable(Nba2016UserDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-GAME")) {
			dropTable(Nba2016GameDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-SERIES")) {
			dropTable(Nba2016SeriesDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-TIE-BREAKER")) {
			dropTable(Nba2016TieBreakerDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-GAME-BET")) {
			dropTable(Nba2016GameBetDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-SERIES-BET")) {
			dropTable(Nba2016SeriesBetDao.getInstance().getTableDefinition());
		}
		if (bean.equals("NBA2016-MVP")) {
			dropTable(Nba2016TieBreakerBetDao.getInstance().getTableDefinition());
		}
	}

	private static void dropTable(String[][] def) {
		StringBuffer sql = new StringBuffer(String.format("DROP TABLE %s ", def[0][0]));
		System.out
				.println(String.format("DROPPING %s : %d changes ", def[0][0], BaseDBUtil.executeDML(sql.toString())));
	}

	public static void loadUsers() {
		Nba2016UserDao.getInstance().insert(new Nba2016User(-1, 0, null, null, null, 0, 0, 0));
		Nba2016UserDao.getInstance().insert(new Nba2016User(-1, 0, null, null, null, 0, 0, 0));
		Nba2016UserDao.getInstance().insert(new Nba2016User(-1, 0, null, null, null, 0, 0, 0));
		Nba2016UserDao.getInstance().insert(new Nba2016User(-1, 0, null, null, null, 0, 0, 0));
		Nba2016UserDao.getInstance().insert(new Nba2016User(-1, 0, null, null, null, 0, 0, 0));
		Nba2016UserDao.getInstance().insert(new Nba2016User(-1, 0, null, null, null, 0, 0, 0));
		Nba2016UserDao.getInstance().insert(new Nba2016User(-1, 0, null, null, null, 0, 0, 0));
		Nba2016UserDao.getInstance().insert(new Nba2016User(-1, 0, null, null, null, 0, 0, 0));
	}

	public static void loadBrackets() {
	}

	public static void loadBracketBets() {

		// BET - SCHAARSCHMIDT
		Nba2016SeriesBetDao.getInstance().insert(new Nba2016SeriesBet(1, 8, "CLE", 5, 0));
		Nba2016SeriesBetDao.getInstance().insert(new Nba2016SeriesBet(2, 8, "ATL", 6, 0));
		Nba2016SeriesBetDao.getInstance().insert(new Nba2016SeriesBet(3, 8, "IND", 6, 0));
		Nba2016SeriesBetDao.getInstance().insert(new Nba2016SeriesBet(4, 8, "MIA", 7, 0));
		Nba2016SeriesBetDao.getInstance().insert(new Nba2016SeriesBet(5, 8, "GS", 6, 0));
		Nba2016SeriesBetDao.getInstance().insert(new Nba2016SeriesBet(6, 8, "LAC", 5, 0));
		Nba2016SeriesBetDao.getInstance().insert(new Nba2016SeriesBet(7, 8, "SA", 5, 0));
		Nba2016SeriesBetDao.getInstance().insert(new Nba2016SeriesBet(8, 8, "OKC", 7, 0));
		// SeriesBetDao.getInstance().insert(new SeriesBet(9, 8, "HAWKS", 6,
		// 0));
		// SeriesBetDao.getInstance().insert(new SeriesBet(10, 8, "CAVS", 6,
		// 0));
		// SeriesBetDao.getInstance().insert(new SeriesBet(11, 8, "WARRIORS", 5,
		// 0));
		// SeriesBetDao.getInstance().insert(new SeriesBet(17, 8, "ROCKETS", 6,
		// 0));
		// SeriesBetDao.getInstance().insert(new SeriesBet(13, 8, "HAWKS", 7,
		// 0));
		// SeriesBetDao.getInstance().insert(new SeriesBet(14, 8, "WARRIORS", 6,
		// 0));
		// SeriesBetDao.getInstance().insert(new SeriesBet(15, 8, "WARRIORS", 7,
		// 0));

	}

	public static void loadGames() {
		Calendar c = Calendar.getInstance();
		c.set(2015, 3, 19, 17, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 1, c, "HAWKS", 0, "NETS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 22, 19, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 1, c, "HAWKS", 0, "NETS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 25, 15, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 1, c, "NETS", 0, "HAWKS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 27, 19, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 1, c, "NETS", 0, "HAWKS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 19, 15, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 2, c, "CAVS", 0, "CELTICS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 21, 19, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 2, c, "CAVS", 0, "CELTICS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 23, 19, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 2, c, "CELTICS", 0, "CAVS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 26, 13, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 2, c, "CELTICS", 0, "CAVS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 18, 19, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 3, c, "BULLS", 0, "BUCKS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 20, 20, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 3, c, "BULLS", 0, "BUCKS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 23, 20, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 3, c, "BUCKS", 0, "BULLS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 25, 17, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 3, c, "BUCKS", 0, "BULLS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 18, 12, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 4, c, "RAPTORS", 86, "WIZARDS", 93, 1, 1));
		c = Calendar.getInstance();
		c.set(2015, 3, 21, 20, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 4, c, "RAPTORS", 0, "WIZARDS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 24, 20, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 4, c, "WIZARDS", 0, "RAPTORS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 26, 19, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 4, c, "WIZARDS", 0, "RAPTORS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 18, 15, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 5, c, "WARRIORS", 0, "PELICANS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 20, 22, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 5, c, "WARRIORS", 0, "PELICANS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 23, 21, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 5, c, "PELICANS", 0, "WARRIORS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 25, 20, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 5, c, "PELICANS", 0, "WARRIORS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 18, 21, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 6, c, "ROCKETS", 0, "MAVS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 21, 21, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 6, c, "ROCKETS", 0, "MAVS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 24, 19, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 6, c, "MAVS", 0, "ROCKETS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 26, 21, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 6, c, "MAVS", 0, "ROCKETS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 19, 22, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 7, c, "CLIPPERS", 0, "SPURS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 22, 22, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 7, c, "CLIPPERS", 0, "SPURS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 24, 21, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 7, c, "SPURS", 0, "CLIPPERS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 26, 15, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 7, c, "SPURS", 0, "CLIPPERS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 19, 20, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 8, c, "GRIZZLES", 0, "BLAZERS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 22, 20, 0);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 8, c, "GRIZZLES", 0, "BLAZERS", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 25, 22, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 8, c, "BLAZERS", 0, "GRIZZLES", 0, 0, 0));
		c = Calendar.getInstance();
		c.set(2015, 3, 27, 22, 30);
		Nba2016GameDao.getInstance().insert(new Nba2016Game(1, "", 1, 8, c, "BLAZERS", 0, "GRIZZLES", 0, 0, 0));
	}

	public static void loadGameBets() {
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(1, 1, "HAWKS", 1, 1, 0, 100));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(2, 1, "HAWKS", 1, 1, 0, 100));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(3, 1, "HAWKS", 1, 1, 0, 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(4, 1, "HAWKS", 1, 1, 0, 100));
		// GAME BETS - RECH
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(1, 2, "HAWKS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(5, 2, "CAVS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(9, 2, "BULLS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(13, 2, "WIZARDS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(17, 2, "WARRIORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(21, 2, "MAVS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(25, 2, "CLIPPERS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(29, 2, "GRIZZLES", 0));
		// GAME BETS - jean
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(1, 3, "HAWKS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(5, 3, "CAVS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(9, 3, "BULLS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(13, 3, "WIZARDS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(17, 3, "WARRIORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(21, 3, "ROCKETS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(25, 3, "SPURS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(29, 3, "GRIZZLES", 0));
		// GAME BETS - scortegagna
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(1, 4, "HAWKS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(5, 4, "CAVS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(9, 4, "BULLS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(13, 4, "RAPTORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(17, 4, "WARRIORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(21, 4, "ROCKETS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(25, 4, "CLIPPERS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(29, 4, "GRIZZLES", 1));
		// GAME BETS - guilherme
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(1, 5, "HAWKS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(5, 5, "CAVS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(9, 5, "BULLS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(13, 5, "RAPTORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(17, 5, "WARRIORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(21, 5, "ROCKETS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(25, 5, "SPURS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(29, 5, "GRIZZLES", 0));
		// GAME BETS - scholles
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(1, 6, "HAWKS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(5, 6, "CAVS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(9, 6, "BULLS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(13, 6, "RAPTORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(17, 6, "WARRIORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(21, 6, "ROCKETS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(25, 6, "CLIPPERS", 1));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(29, 6, "BLAZERS", 0));
		// GAME BETS - lima
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(1, 7, "HAWKS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(5, 7, "CAVS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(9, 7, "BULLS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(13, 7, "RAPTORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(17, 7, "WARRIORS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(21, 7, "ROCKETS", 0));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(25, 7, "SPURS", 1));
		Nba2016GameBetDao.getInstance().insert(new Nba2016GameBet(29, 7, "BLAZERS", 0));

	}

	public static void loadTieBreakers() {
		Nba2016TieBreakerDao.getInstance().insert(new Nba2016TieBreaker(1, "MVP FINAL", "100"));
	}

	public static void loadTieBreakerBets() {
		Nba2016TieBreakerBetDao.getInstance().insert(new Nba2016TieBreakerBet(1, 2, "CURRY", 0));
		Nba2016TieBreakerBetDao.getInstance().insert(new Nba2016TieBreakerBet(1, 3, "CURRY", 0));
		Nba2016TieBreakerBetDao.getInstance().insert(new Nba2016TieBreakerBet(1, 4, "LEBRON", 0));
		Nba2016TieBreakerBetDao.getInstance().insert(new Nba2016TieBreakerBet(1, 5, "LEBRON", 0));
		Nba2016TieBreakerBetDao.getInstance().insert(new Nba2016TieBreakerBet(1, 6, "HARDEN", 0));
		Nba2016TieBreakerBetDao.getInstance().insert(new Nba2016TieBreakerBet(1, 7, "CURRY", 0));
	}

	public static String DML(String bean) {
		if (bean.equals("GAME")) {
			// Calendar c = Calendar.getInstance();
			// c.set(2015, 3, 29, 19, 30);
			// return String.valueOf(GameDao.getInstance().insert(new Game(1,
			// "", 1,
			// 1, c, "HAWKS", 0, "NETS", 0, 0)));
			String ret = "";
			//
			// Calendar c = Calendar.getInstance();
			// c.set(2015, 3, 27, 20, 00);
			// ret += String.valueOf(GameDao.getInstance().insert(new Game(1,
			// "", 1,
			// 1, c, "BULLS", 0, "BUCKS", 0, 0)));
			//
			// c = Calendar.getInstance();
			// c.set(2015, 3, 27, 20, 00);
			// ret += String.valueOf(GameDao.getInstance().insert(new Game(1,
			// "", 1,
			// 1, c, "BULLS", 0, "BUCKS", 0, 0)));

			return ret;
		}
		if (bean.equals("MVP")) {
			int i = 0;
			// i += TieBreakerBetDao.getInstance().insert(new TieBreakerBet(1,
			// 2, "CURRY", 0));
			// i += TieBreakerBetDao.getInstance().insert(new TieBreakerBet(1,
			// 3, "CURRY", 0));
			// i += TieBreakerBetDao.getInstance().insert(new TieBreakerBet(1,
			// 4, "LEBRON", 0));
			// i += TieBreakerBetDao.getInstance().insert(new TieBreakerBet(1,
			// 5, "LEBRON", 0));
			// i += TieBreakerBetDao.getInstance().insert(new TieBreakerBet(1,
			// 6, "HARDEN", 0));
			// i += TieBreakerBetDao.getInstance().insert(new TieBreakerBet(1,
			// 7, "CURRY", 0));
			return String.valueOf(i);
		} // createTable(UserDao.getInstance().getTableDefinition());
			// createTable(GameDao.getInstance().getTableDefinition());
			// createTable(SeriesDao.getInstance().getTableDefinition());
			// createTable(TieBreakerDao.getInstance().getTableDefinition());
			// createTable(GameBetDao.getInstance().getTableDefinition());
			// createTable(SeriesBetDao.getInstance().getTableDefinition());
			// createTable(TieBreakerBetDao.getInstance().getTableDefinition());
		return "-1";
	}

}
