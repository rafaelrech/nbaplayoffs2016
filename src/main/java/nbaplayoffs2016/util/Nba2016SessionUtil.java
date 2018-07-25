package nbaplayoffs2016.util;

import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.http.HttpSession;

import nbaplayoffs2016.bean.Nba2016Game;
import nbaplayoffs2016.bean.Nba2016GameBet;
import nbaplayoffs2016.bean.Nba2016User;
import nbaplayoffs2016.bean.Nba2016Series;
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
import rech.bolao.bean.User;
import rech.bolao.util.BaseSessionUtil;

public class Nba2016SessionUtil {

	public static final String ALL_USERS = "allNba2016Users";
	public static final String ALL_SERIES = "allNba2016Series";
	public static final String ALL_SERIES_BETS = "allNba2016SeriesBets";
	public static final String ALL_GAMES = "allNba2016Games";
	public static final String ALL_GAME_BETS = "allNba2016GameBets";
	public static final String ALL_BONUS = "allNba2016Bonus";
	public static final String ALL_BONUS_BETS = "allNba2016BonusBets";

	public static void udpateSessionInfo(HttpSession session, User u) {

		clearSessionInfo(session);

		// FULL INFO
		ArrayList<Nba2016User> allNba2016Users;
		try {
			allNba2016Users = Nba2016UserDao.getInstance().listAll();
		} catch (SQLException e5) {
			allNba2016Users = new ArrayList<Nba2016User>();
		}
		ArrayList<Nba2016Series> allSeries;
		try {
			allSeries = Nba2016SeriesDao.getInstance().listAll();
		} catch (SQLException e4) {
			allSeries = new ArrayList<Nba2016Series>();
		}
		ArrayList<Nba2016SeriesBet> allSeriesBets;
		try {
			allSeriesBets = Nba2016SeriesBetDao.getInstance().listAll();
		} catch (SQLException e3) {
			allSeriesBets = new ArrayList<Nba2016SeriesBet>();
		}
		ArrayList<Nba2016Game> allGames;
		try {
			allGames = Nba2016GameDao.getInstance().listAll();
		} catch (SQLException e2) {
			allGames = new ArrayList<Nba2016Game>();
		}
		ArrayList<Nba2016GameBet> allGameBets;
		try {
			allGameBets = Nba2016GameBetDao.getInstance().listAll();
		} catch (SQLException e1) {
			allGameBets = new ArrayList<Nba2016GameBet>();
		}
		ArrayList<Nba2016TieBreaker> allBonus;
		try {
			allBonus = Nba2016TieBreakerDao.getInstance().listAll();
		} catch (SQLException e) {
			allBonus = new ArrayList<Nba2016TieBreaker>();
		}
		ArrayList<Nba2016TieBreakerBet> allBonusBets;
		try {
			allBonusBets = Nba2016TieBreakerBetDao.getInstance().listAll();
		} catch (SQLException e) {
			allBonusBets = new ArrayList<Nba2016TieBreakerBet>();
		}

		ArrayList<User> baseUsers = (ArrayList<User>) session.getAttribute(BaseSessionUtil.ALL_USERS);
		for (User baseUser : baseUsers) {
			for (Nba2016User user : allNba2016Users) {
				if (user.getUserId() == baseUser.getId()) {
					user.setUser(baseUser);
				}
				for (Nba2016GameBet gameBet : allGameBets) {
					if (gameBet.getUserId() == user.getUserId()) {
						gameBet.setUser(user);
						user.addBet(gameBet);
					}
				}
				for (Nba2016SeriesBet seriesBet : allSeriesBets) {
					if (seriesBet.getUserId() == user.getUserId()) {
						seriesBet.setUser(user);
						user.addBet(seriesBet);
					}
				}
				for (Nba2016TieBreakerBet bonusBet : allBonusBets) {
					if (bonusBet.getUserId() == user.getUserId()) {
						bonusBet.setUser(user);
						user.addBet(bonusBet);
					}
				}
			}
		}
		for (Nba2016Game game : allGames) {
			for (Nba2016GameBet gameBet : allGameBets) {
				if (gameBet.getGameId() == game.getId()) {
					gameBet.setGame(game);
					game.addBet(gameBet);
				}
			}
			for (Nba2016Series series : allSeries) {
				if (series.getId() == game.getBracketId()) {
					game.setSeries(series);
					series.addGame(game);
				}
			}
		}
		for (Nba2016TieBreaker tieBreaker : allBonus) {
			for (Nba2016TieBreakerBet tieBreakerBet : allBonusBets) {
				if (tieBreakerBet.getTieBrackerId() == tieBreaker.getId()) {
					tieBreakerBet.setTieBreaker(tieBreaker);
					tieBreaker.addBet(tieBreakerBet);
				}
			}
		}
		for (Nba2016Series series : allSeries) {
			for (Nba2016SeriesBet seriesBet : allSeriesBets) {
				if (seriesBet.getBracketId() == series.getId()) {
					seriesBet.setSeries(series);
					series.addBet(seriesBet);
				}
			}
		}
		session.setAttribute(ALL_SERIES, allSeries);
		session.setAttribute(ALL_SERIES_BETS, allSeriesBets);
		session.setAttribute(ALL_GAMES, allGames);
		session.setAttribute(ALL_GAME_BETS, allGameBets);
		session.setAttribute(ALL_BONUS, allBonus);
		session.setAttribute(ALL_BONUS_BETS, allBonusBets);
	}

	public static void clearSessionInfo(HttpSession session) {
		session.setAttribute(ALL_SERIES, null);
		session.setAttribute(ALL_SERIES_BETS, null);
		session.setAttribute(ALL_GAMES, null);
		session.setAttribute(ALL_GAME_BETS, null);
		session.setAttribute(ALL_BONUS, null);
		session.setAttribute(ALL_BONUS_BETS, null);
	}

}
