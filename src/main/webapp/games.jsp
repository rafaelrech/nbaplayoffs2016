<%@page import="rech.bolao.bean.User"%>
<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@ page import="java.util.*"%>
<%@ page import="nbaplayoffs2016.bean.*"%>
<%@ page import="nbaplayoffs2016.util.*"%>
<%@ page session="true"%>

<h2>TODAY'S GAMES</h2>
<%
	{
		int loggedId = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getId();
		boolean isAdmin = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole()
				.equalsIgnoreCase("admin");
		int id2use = loggedId;
		String reqestUserId = request.getParameter("user");
		if (reqestUserId != null && !reqestUserId.isEmpty()) {
			id2use = Integer.valueOf(reqestUserId);
		}

	ArrayList<Nba2016Game> todayGames = (ArrayList<Nba2016Game>) session.getAttribute(Nba2016SessionUtil.ALL_GAMES);
		for (Nba2016Game game : todayGames) {
			if (!Nba2016WebUtil.isGameToday(game.getData())) {
				continue;
			}
			ArrayList<Nba2016GameBet> bets = game.getBets();
			String winner = new String("");
			String overTime = new String("-1");
			String userScore = new String("-1");
			for (Nba2016GameBet rec : bets) {
				if (rec.getUserId() != id2use) {
					continue;
				}
				winner = rec.getWinner();
				overTime = String.valueOf(rec.getOverTime());
				userScore = String.valueOf(rec.getUserScore());
			}
%>
<%=Nba2016WebUtil.getGameEntryLine(game, winner, overTime, userScore, (loggedId == id2use))%><br>
<%
	}
%>
<hr>
<h2>SERIES</h2>
<%
	StringBuffer currentSeries = new StringBuffer();
	StringBuffer completedSeries = new StringBuffer();

	ArrayList<Nba2016Series> series = (ArrayList<Nba2016Series>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES);
	for (Nba2016Series serie : series) {
		ArrayList<Nba2016Game> games = serie.getGames();
		if (serie.getTeam1().isEmpty() || serie.getTeam2().isEmpty() || serie.getTeam1().startsWith("W") || serie.getTeam2().startsWith("W")) {
			continue;
		}
		StringBuffer gameEntries = new StringBuffer();
		int team1Wins = 0;
		int team2Wins = 0;
		for (Nba2016Game game : games) {
			ArrayList<Nba2016GameBet> bets = game.getBets();
			String winner = new String("");
			String overTime = new String("-1");
			String userScore = new String("-1");
			for (Nba2016GameBet rec : bets) {
				if (rec.getUserId() != id2use) {
					continue;
				}
				winner = rec.getWinner();
				overTime = String.valueOf(rec.getOverTime());
				userScore = String.valueOf(rec.getUserScore());
			}
			if (game.getHomeScore() > game.getVisitScore()) {
				if (serie.getTeam1().equals(game.getHomeTeam())) {
					team1Wins++;
				} else {
					team2Wins++;
				}
			}
			if (game.getHomeScore() < game.getVisitScore()) {
				if (serie.getTeam1().equals(game.getHomeTeam())) {
					team2Wins++;
				} else {
					team1Wins++;
				}
			}
			gameEntries.append(
					Nba2016WebUtil.getGameEntryLine(game, winner, overTime, userScore, (loggedId == id2use)))
					.append("<br>");
		}
		String seriesStatus = new String();
		if (team1Wins != team2Wins) {
			seriesStatus = String.format("%s %s %d-%d",
					((team1Wins > team2Wins) ? Nba2016WebUtil.getAcronym(serie.getTeam1())
							: Nba2016WebUtil.getAcronym(serie.getTeam2())),
					(Math.max(team1Wins, team2Wins) == 4 ? "won" : "leads"), Math.max(team1Wins, team2Wins),
					Math.min(team1Wins, team2Wins));
		} else {
			seriesStatus = String.format("Nba2016Series tied %d-%d", team1Wins, team2Wins);
		}
		String betLinks = "";
		if(loggedId == id2use) {
			if ((team1Wins + team2Wins) == 0) {
				ArrayList<Nba2016SeriesBet> bracketBets = (ArrayList<Nba2016SeriesBet>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES_BETS);
				String prev1BetWinner = new String();
				String prev2BetWinner = new String();
				String currentBet = "";
				int currentBetGames = -1;
				for (Nba2016SeriesBet bet : bracketBets) {
					if (bet.getUserId() != id2use) {
						continue;
					}
					if (bet.getBracketId() == serie.getId()) {
						//betLinks += String.format("&nbsp;&nbsp;Current bet: <b>%s in %d</b>.", bet.getWinner(), bet.getGames()) ;
						currentBet = bet.getWinner();
						currentBetGames = bet.getGames();
					}
					if (bet.getBracketId() == serie.getPrevId1()) {
						prev1BetWinner = bet.getWinner();
					}
					if (bet.getBracketId() == serie.getPrevId2()) {
						prev2BetWinner = bet.getWinner();
					}
				}
				betLinks += "Bet on: ";
				if(serie.getTeam1().equals(prev1BetWinner) || serie.getTeam1().equals(prev2BetWinner)) {
					for(int x=4; x<=7; x++) {
						if(currentBet.equals(serie.getTeam1()) && currentBetGames==x) {
							betLinks += String.format("<font color='blue'><b>%s in %d</b></font>&nbsp;||&nbsp;", serie.getTeam1(), x);					
						} else {
							betLinks += String.format("<a href='SeriesBetServlet?seriesId=%d&winner=%s&games=%d'>%s in %d</a>&nbsp;||&nbsp;", serie.getId(), serie.getTeam1(), x, serie.getTeam1(), x);
						}
					}
				}
				if(serie.getTeam2().equals(prev1BetWinner) || serie.getTeam2().equals(prev2BetWinner)) {
					for(int x=4; x<=7; x++) {
						if(currentBet.equals(serie.getTeam2()) && currentBetGames==x) {
							betLinks += String.format("<font color='blue'><b>%s in %d</b></font>&nbsp;||&nbsp;", serie.getTeam2(), x);					
						} else {
							betLinks += String.format("<a href='SeriesBetServlet?seriesId=%d&winner=%s&games=%d'>%s in %d</a>&nbsp;||&nbsp;", serie.getId(), serie.getTeam2(), x, serie.getTeam2(), x);
						}
					}
				}
			}
		}
		if (Math.max(team1Wins, team2Wins) == 4) {
			completedSeries.append(String.format(
					"<h2>%s&nbsp;%s vs %s&nbsp;%s&nbsp;&nbsp;&nbsp;<font size='-1'>(%s) %s</font></h2>%s<hr>",
					Nba2016WebUtil.getImage(serie.getTeam1(), 32, 40), serie.getTeam1(), serie.getTeam2(),
					Nba2016WebUtil.getImage(serie.getTeam2(), 32, 40), seriesStatus, betLinks, gameEntries.toString()

			));
		} else {
			currentSeries.append(String.format(
					"<h2>%s&nbsp;%s vs %s&nbsp;%s&nbsp;&nbsp;&nbsp;<font size='-1'>(%s) %s</font></h2>%s<hr>",
					Nba2016WebUtil.getImage(serie.getTeam1(), 32, 40), serie.getTeam1(), serie.getTeam2(),
					Nba2016WebUtil.getImage(serie.getTeam2(), 32, 40), seriesStatus, betLinks, gameEntries.toString()

			));
		}
%>
<%-- 
		<h2><%=WebUtil.getImage(serie.getTeam1(), 32, 40)%>&nbsp;<%=serie.getTeam1()%> vs <%=serie.getTeam2()%>&nbsp;<%=WebUtil.getImage(serie.getTeam2(), 32, 40)%>&nbsp;&nbsp;&nbsp;<font size='-1'>(<%=seriesStatus%>)</font></h2>
		<%=gameEntries.toString()%>
		<hr>
		--%>
<%
	}
	out.println (currentSeries);
%>
<%--
<hr>
<h2>COMPLETED SERIES</h2>
<hr>
<%
out.println (completedSeries);
%>
 --%>

<% 
	}
%>
