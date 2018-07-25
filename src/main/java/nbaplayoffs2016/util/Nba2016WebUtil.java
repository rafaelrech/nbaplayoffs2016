package nbaplayoffs2016.util;

import java.util.Calendar;

import nbaplayoffs2016.bean.Nba2016Game;

public class Nba2016WebUtil {

	public static final String GAMES_JSP = "games.jsp";
	public static final String DASHBOARD_JSP = "dashboard.jsp";
	public static final String DBUTIL_JSP = "db-admin.jsp";


	public static boolean isGameInSpecificDay(Calendar gameDate, String date) {
		Calendar c = Calendar.getInstance();
		int sepPos = date.indexOf("/", 0);
		int year = Integer.valueOf(date.substring(0, sepPos));
		int sepPos2 = date.indexOf("/", sepPos + 1);
		int month = Integer.valueOf(date.substring(sepPos + 1, sepPos2));
		int day = Integer.valueOf(date.substring(sepPos2 + 1));
		c.set(year < 100 ? (year + 2000) : year, month - 1, day);
		return isGameInSpecificDay(gameDate, c);
	}

	public static boolean isGameToday(Calendar gameDate) {
		Calendar c = Calendar.getInstance();
		c.setTimeInMillis(c.getTimeInMillis() - (4 * 60 * 60 * 1000));
		return isGameInSpecificDay(gameDate, c);
	}

	public static boolean isGameInSpecificDay(Calendar gameDate, Calendar specificDay) {
		return (gameDate.get(Calendar.YEAR) == specificDay.get(Calendar.YEAR))
				&& (gameDate.get(Calendar.MONTH) == specificDay.get(Calendar.MONTH))
				&& (gameDate.get(Calendar.DAY_OF_MONTH) == specificDay.get(Calendar.DAY_OF_MONTH));
	}

	public static String getImage(String team, int h, int w) {
		String mapped = getAcronym(team).toLowerCase();
		return "<img src='http://i.cdn.turner.com/nba/nba/.element/img/1.0/teamsites/logos/teamlogos_80x64/" + mapped
				+ ".gif' height=" + h + " width=" + w + ">";
	}

	public static String getGameEntryLine(Nba2016Game game, String winnerBet, String otBet, String userScore, boolean ownPage) {
		boolean existBet = winnerBet != null && !winnerBet.isEmpty();
		boolean homeWins = false, visitWins = false, gameFinished = false;
		int ot = Integer.valueOf(otBet).intValue();

		Calendar c = game.getData();

		String formattedDate = "" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DATE) + " "
				+ c.get(Calendar.HOUR_OF_DAY) + ":"
				+ (c.get(Calendar.MINUTE) > 10 ? c.get(Calendar.MINUTE) : "0" + c.get(Calendar.MINUTE));

		if (game.getVisitScore() > 0 && game.getHomeScore() > 0) {
			homeWins = game.getHomeScore() > game.getVisitScore();
			visitWins = game.getHomeScore() < game.getVisitScore();
			gameFinished = true;
		}
		String ret = formattedDate + " "
				+ (((existBet) && (winnerBet.equalsIgnoreCase(game.getVisitTeam()))) ? "<font color='blue'>" : "")
				+ (visitWins ? "<b>" : "") + getAcronym(game.getVisitTeam()) + (visitWins ? "</b>" : "")
				+ (((existBet) && (winnerBet.equalsIgnoreCase(game.getVisitTeam()))) ? "</font>" : "") + " @ "
				+ (((existBet) && (winnerBet.equalsIgnoreCase(game.getHomeTeam()))) ? "<font color='blue'>" : "")
				+ (homeWins ? "<b>" : "") + getAcronym(game.getHomeTeam()) + (homeWins ? "</b>" : "")
				+ (((existBet) && (winnerBet.equalsIgnoreCase(game.getHomeTeam()))) ? "</font>" : "");
		if (homeWins || visitWins) {
			ret += " (" + (visitWins ? "<b>" : "") + game.getVisitScore() + (visitWins ? "</b>" : "") + "-"
					+ (homeWins ? "<b>" : "") + game.getHomeScore() + (homeWins ? "</b>" : "")
					+ (game.getOverTime() > 0 ? " in OT" : "") + ")";
		}
		if (existBet) {
			ret += " <font color='blue'>(bet: in " + ((ot <= 0) ? "Reg" : "OT") + "</font>)";
		}
		if (gameFinished) {
			ret += " (" + userScore + " pts)";
		}
		if (ownPage && !gameFinished) {
			if (winnerBet == null || winnerBet.isEmpty() || !winnerBet.equals(game.getHomeTeam()) || (ot > 0))
				ret += "&nbsp; | &nbsp;<a href='/nbaplayoffs2016/BetServlet?gameId=" + game.getId() + "&home=1&ot=0'>"
						+ getAcronym(game.getHomeTeam()) + " in reg</a>";
			if (winnerBet == null || winnerBet.isEmpty() || !winnerBet.equals(game.getVisitTeam()) || (ot > 0))
				ret += "&nbsp; | &nbsp;<a href='/nbaplayoffs2016/BetServlet?gameId=" + game.getId() + "&home=0&ot=0'>"
						+ getAcronym(game.getVisitTeam()) + " in reg</a>";
			if (winnerBet == null || winnerBet.isEmpty() || !winnerBet.equals(game.getHomeTeam()) || (ot <= 0))
				ret += "&nbsp; | &nbsp;<a href='/nbaplayoffs2016/BetServlet?gameId=" + game.getId() + "&home=1&ot=1'>"
						+ getAcronym(game.getHomeTeam()) + " in OT</a>";
			if (winnerBet == null || winnerBet.isEmpty() || !winnerBet.equals(game.getVisitTeam()) || (ot <= 0))
				ret += "&nbsp; | &nbsp;<a href='/nbaplayoffs2016/BetServlet?gameId=" + game.getId() + "&home=0&ot=1'>"
						+ getAcronym(game.getVisitTeam()) + " in OT</a>";
		}
		return ret;
	}

	public static String getHeaderGameEntryLine(Nba2016Game game) {
		boolean homeWins = false, visitWins = false;

		Calendar c = game.getData();
		String formattedDate = "" + c.get(Calendar.HOUR_OF_DAY) + ":"
				+ (c.get(Calendar.MINUTE) > 10 ? c.get(Calendar.MINUTE) : "0" + c.get(Calendar.MINUTE));

		if (game.getVisitScore() > 0 && game.getHomeScore() > 0) {
			homeWins = game.getHomeScore() > game.getVisitScore();
			visitWins = game.getHomeScore() < game.getVisitScore();
		}
		String ret = formattedDate + " " + getAcronym(game.getVisitTeam()) + " @ " + getAcronym(game.getHomeTeam());
		if (homeWins || visitWins) {
			ret += "<br><font color='blue' size=-1>" + game.getVisitScore() + "-" + game.getHomeScore()
					+ (game.getOverTime() > 0 ? " in OT" : "") + "</font>";
		}
		return ret;
	}

	public static String getAcronym(String team) {
		if (team.equalsIgnoreCase("HAWKS"))
			return "ATL";
		if (team.equalsIgnoreCase("NETS"))
			return "BKN";
		if (team.equalsIgnoreCase("RAPTORS"))
			return "TOR";
		if (team.equalsIgnoreCase("WIZARDS"))
			return "WAS";
		if (team.equalsIgnoreCase("CAVS"))
			return "CLE";
		if (team.equalsIgnoreCase("CELTICS"))
			return "BOS";
		if (team.equalsIgnoreCase("BULLS"))
			return "CHI";
		if (team.equalsIgnoreCase("BUCKS"))
			return "MIL";
		if (team.equalsIgnoreCase("WARRIORS"))
			return "GSW";
		if (team.equalsIgnoreCase("GS"))
			return "GSW";
		if (team.equalsIgnoreCase("THUNDER"))
			return "OKC";
		if (team.equalsIgnoreCase("BLAZERS"))
			return "POR";
		if (team.equalsIgnoreCase("GRIZZLES"))
			return "MEM";
		if (team.equalsIgnoreCase("ROCKETS"))
			return "HOU";
		if (team.equalsIgnoreCase("MAVS"))
			return "DAL";
		if (team.equalsIgnoreCase("CLIPPERS"))
			return "LAC";
		if (team.equalsIgnoreCase("SPURS"))
			return "SAS";
		if (team.equalsIgnoreCase("SA"))
			return "SAS";
		return team;
	}

	public static String getImageName(String team) {
		if (team.equalsIgnoreCase("HAWKS"))
			return "ATL";
		if (team.equalsIgnoreCase("NETS"))
			return "BKN";
		if (team.equalsIgnoreCase("RAPTORS"))
			return "TOR";
		if (team.equalsIgnoreCase("WIZARDS"))
			return "WAS";
		if (team.equalsIgnoreCase("CAVS"))
			return "CLE";
		if (team.equalsIgnoreCase("CELTICS"))
			return "BOS";
		if (team.equalsIgnoreCase("BULLS"))
			return "CHI";
		if (team.equalsIgnoreCase("BUCKS"))
			return "MIL";
		if (team.equalsIgnoreCase("WARRIORS"))
			return "GSW";
		if (team.equalsIgnoreCase("PELICANS"))
			return "NOP";
		if (team.equalsIgnoreCase("BLAZERS"))
			return "POR";
		if (team.equalsIgnoreCase("GRIZZLES"))
			return "MEM";
		if (team.equalsIgnoreCase("ROCKETS"))
			return "HOU";
		if (team.equalsIgnoreCase("MAVS"))
			return "DAL";
		if (team.equalsIgnoreCase("CLIPPERS"))
			return "LAC";
		if (team.equalsIgnoreCase("SPURS"))
			return "SAS";
		return team;
	}

}
