<%@ page import="nbaplayoffs2016.bean.*" %>
<%@ page import="nbaplayoffs2016.util.*" %>
<%@ page import="java.text.*" %>
<%@ page import="java.util.*" %>
<%@ page session="true" %>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Bolão NBA - PLAYOFFS 2016</title>
<link rel="stylesheet" href="css/bolao.css">
<link rel="stylesheet" href="css/bracket.css">
<link rel="apple-touch-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link rel="shortcut icon" type="image/x-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://underscorejs.org/underscore-min.js"></script>
<script src="./js/bracket.js"></script>
</head>

<body>
<%
	String data = "";
	if (request.getParameter("data") != null) {
		data = request.getParameter("data");
	}
	User loggedU = (User) session.getAttribute(BaseSessionUtil.LOGGED_USER);
	if (loggedU == null){
	    response.sendRedirect("index.jsp");
	}
	else 
	{
%>
 	<section class='container'>

 	<%@ include file="header.jsp" %>

		<div class="row">
			<section>
				<h2>LEADERBOARD</h2>
				<div style="padding-left: 50px;">

					<div id="nbaStripContainer" class="nbaFsBox">
		<%
		SimpleDateFormat sdf = new SimpleDateFormat("E, dd MMM");
		SimpleDateFormat linkFormatter = new SimpleDateFormat("yyyy/MM/dd");
		Calendar day2use = Calendar.getInstance();
		String pData = request.getParameter("data");
		if (pData == null || pData.isEmpty() || (pData.length() != 10 && pData.length() != 8)) {
			day2use.setTimeInMillis(day2use.getTimeInMillis() - (4 * 60 * 60 * 1000));
		} else {
			int sepPos = pData.indexOf("/", 0);
			int year = Integer.valueOf(pData.substring(0, sepPos));
			int sepPos2 = pData.indexOf("/", sepPos + 1);
			int month = Integer.valueOf(pData.substring(sepPos + 1, sepPos2));
			int day = Integer.valueOf(pData.substring(sepPos2 + 1));
			day2use.set(year < 100 ? (year + 2000) : year, month - 1, day);
		}
		ArrayList<Nba2016Game> games = (ArrayList<Nba2016Game>) session.getAttribute(Nba2016SessionUtil.ALL_GAMES);
		ArrayList<Nba2016Game> todayGames = new ArrayList<Nba2016Game>();
		int[] gameCount = { 0, 0, 0, 0, 0, 0, 0 };
		Calendar[] gameDates = { null, null, null, day2use, null, null, null };
		for (int i = 1; i <= 3; i++) {
			Calendar d1 = Calendar.getInstance();
			d1.setTime(day2use.getTime());
			d1.add(Calendar.DATE, -i);
			gameDates[3 - i] = d1;
			Calendar d2 = Calendar.getInstance();
			d2.setTime(day2use.getTime());
			d2.add(Calendar.DATE, +i);
			gameDates[3 + i] = d2;
		}
		for (Nba2016Game game : games) {
			if (Nba2016WebUtil.isGameInSpecificDay(game.getData(), day2use)) {
				todayGames.add(game);
			}
			for (int i = 0; i < gameDates.length; i++) {
				if (Nba2016WebUtil.isGameInSpecificDay(game.getData(), gameDates[i])) {
					gameCount[i]++;
				}
			}
		}
		for (int i = 0; i < gameDates.length; i++) {
		%>
						<a class="nbaDayAnchor" target="_self"
							href="leaderboard.jsp?data=<%=linkFormatter.format(gameDates[i].getTime())%>">
							<div class="nbaFlStrHeadBox">
								<div id="nbaCalDay2" class="nbaCalDay ltMod">
									<div class="fsDate"><%=sdf.format(gameDates[i].getTime())%></div>
									<div class="fsGame"><%=gameCount[i]%> games</div>
								</div>
							</div>
						</a>
		<%
		}
		%>				
					</div>
				<br><br><br>
					<table class="leagueTable">
						<thead>
							<tr>
								<th class="col-pos">POS</th>
								<th class="col-club">PLAYER</th>
	<%
	for (Nba2016Game game : todayGames) {
		%>
								<th class="col-game"><%=Nba2016WebUtil.getHeaderGameEntryLine(game)%></th>
		<%
	}
	%>
								<th class="col-pts">PTS</th>
								<th class="col-gf" style="width: 10px"></th>
								<th class="col-pts">OTHER GAMES</th>
								<th class="col-pts">SERIES BETS</th>
								<th class="col-pts">MVP BET</th>
								<th class="col-pts">TOTAL</th>
							</tr>
						</thead>

						<tbody>
	<%
	ArrayList<Nba2016User> allUsers = (ArrayList<Nba2016User>) session.getAttribute(BaseSessionUtil.ALL_USERS);
	ArrayList<Nba2016User> todayBets = new ArrayList<Nba2016User>();
	int pos = 1;
	for (Nba2016User user : allUsers) {
		if(!user.getUser().getRole().equalsIgnoreCase("user")) {
			continue;
		}
		if (pos == 1) {
		%>
							<tr class="club-row row1 accent2" accent="accent2">
		<%
		} else {
		%>
							<tr class="club-row row1 accent3" accent="accent3">
		<%
		}
		%>							
								<td class="col-pos"><%=pos%></td>
								<td class="col-club"><%=user.getUser().getUsername()%></td>
		<%
		pos ++;
		int gameBetScores = 0;
		for (Nba2016Game game : todayGames) {
			boolean foundBet = false; 
			ArrayList<Nba2016GameBet> bets = game.getBets();
			for (Nba2016GameBet bet : bets) {
				if(bet.getUserId() == user.getUserId()) {
					foundBet = true;
					if((user.getUserId() != loggedU.getId()) && (game.getData().after(Calendar.getInstance()))) {
						%>
						<td class='col-game' style='color : forestgreen;'>BET IN PLACE</td>
						<%
						continue;
					}
					String betPoints = "";
					if (game.getHomeScore() > 0 && game.getVisitScore() > 0) {
						int gameScore = Nba2016ScoresUtil.calculateGameBetScore(bet, game); 
						gameBetScores += gameScore;
						betPoints = "<br> <font size=-2>" + gameScore + " pts</font>";
					}
					%>
					<td class='col-game'><%=Nba2016WebUtil.getAcronym(bet.getWinner())%><%=(bet.getOverTime()<=0)?" in reg":" in OT"%><%=betPoints%></td>
					<%
				}
			}
			if(!foundBet) {
				%><td class='col-game' style='color : red;'>NO BET YET</td><%
			}
		}		
		%>
								<td class='col-pts'><%=gameBetScores%></td>
								<td class="col-gf"></td>
								<td class='col-pts'><%=(user.getGameBetsScore()-gameBetScores)%></td>
								<td class='col-pts'><%=user.getSeriesBetsScore()%></td>
								<td class='col-pts'><%=user.getMvpBetScore()%></td>
								<td class='col-pts'><%=user.getScore()%></td>
		</tr>
		<%
	}

	%>
			</section>
		</div>
<%
}
%>
</section>

