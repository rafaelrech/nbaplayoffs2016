<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@ page session="true"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Bolão NBA - DASHBOARD</title>
<link rel="apple-touch-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link rel="shortcut icon" type="image/x-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link rel="stylesheet" href="css/bolao.css">
<link rel="stylesheet" href="css/bracket.css">
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://underscorejs.org/underscore-min.js"></script>
<script src="./js/bracket.js"></script>
</head>
<body>
	<%
		if (session.getAttribute(BaseSessionUtil.LOGGED_USER) == null) {
			response.sendRedirect("login.jsp");
		} else {
	%>

	<section class='container'>

		<%@ include file="header2.jsp"%>

		<div class="row">
			<section>

				<form method="post" action="SessionServlet">
					<input type="hidden" name="action" value="FLUSH"> FLUSH
					CACHE: &nbsp;&nbsp;
					<button type="submit" class="change-button">FLUSH</button>
				</form>
				<br>
				<hr>

				<form method="get" action="CalculateServlet">
					RECALCULATE: &nbsp;&nbsp; <button type="submit" class="change-button">RECALCULATE</button>
				</form>
				<br>
				<hr>
				
				<form method="post" action="RunDBServlet">
					LOAD GAMES: &nbsp;&nbsp; RANGE FROM <input type="text"
						name="initial_day" value="-1"> to <input type="text"
						name="final_day" value="1"> <input type="hidden"
						name="action" value="LOAD-GAMES"> &nbsp;&nbsp;
					<button type="submit" class="change-button">LOAD</button>
				</form>
				<br>
				<hr>
<%--
				<form method="post" action="SessionServlet">
					<input type="hidden" name="action" value="XMLSTATS-GAMES">
					TODAY GAMES: &nbsp;&nbsp;
					<button type="submit" class="change-button">XMLSTATS-GAMES</button>
				</form>
				<br>
				<hr>
 --%>

				<form method="post" action="UpdateGameInfoServlet">
					FINISHED GAMES: <select id="gameId" name="gameId">
						<%
							{
									int loggedId = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getId();
									boolean isAdmin = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole()
											.equalsIgnoreCase("admin");
									int id2use = loggedId;

									ArrayList<Nba2016Game> games = (ArrayList<Nba2016Game>) session.getAttribute(Nba2016SessionUtil.ALL_GAMES);
									for (Nba2016Game entry : games) {
										boolean homeWins = false, visitWins = false;
										if (entry.getVisitScore() > 0 && entry.getHomeScore() > 0) {
											homeWins = entry.getHomeScore() > entry.getVisitScore();
											visitWins = entry.getHomeScore() < entry.getVisitScore();
										}
										if (!homeWins && !visitWins) {
											continue;
										}
										Calendar c = entry.getData();
										String formattedDate = "" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DATE) + " "
												+ c.get(Calendar.HOUR_OF_DAY) + ":"
												+ (c.get(Calendar.MINUTE) > 10 ? c.get(Calendar.MINUTE) : "0" + c.get(Calendar.MINUTE));
										String gameEntry = formattedDate + " " + Nba2016WebUtil.getAcronym(entry.getVisitTeam()) + " @ "
												+ Nba2016WebUtil.getAcronym(entry.getHomeTeam());
										gameEntry += " (" + entry.getVisitScore() + ":" + entry.getHomeScore()
												+ (entry.getOverTime() > 0 ? " in OT" : " in reg") + ")";
						%><option value="<%=entry.getId()%>"><%=gameEntry%></option>
						<%
							}
								}
						%>
					</select> DATE: <input type='text' id='dateTime' name='dateTime' size=5>&nbsp;
					VISIT: <input type='text' id='visitScore' name='visitScore' size=5>&nbsp;
					HOME: <input type='text' id='homeScore' name='homeScore' size=5>&nbsp;
					<input type="radio" name="ot" value="0" checked='checked'>Regulation
					<input type="radio" name="ot" value="1">OT &nbsp;&nbsp;
					<button type="submit" class="change-button">U</button>
				</form>
				<br>
				<hr>

				<form method="post" action="UpdateGameInfoServlet">
					UNFINISHED GAMES: <select id="gameId" name="gameId">
						<%
							{
									int loggedId = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getId();
									boolean isAdmin = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole()
											.equalsIgnoreCase("admin");
									int id2use = loggedId;

									ArrayList<Nba2016Game> games = (ArrayList<Nba2016Game>) session.getAttribute(Nba2016SessionUtil.ALL_GAMES);
									for (Nba2016Game entry : games) {
										boolean homeWins = false, visitWins = false;
										if (entry.getVisitScore() > 0 && entry.getHomeScore() > 0) {
											homeWins = entry.getHomeScore() > entry.getVisitScore();
											visitWins = entry.getHomeScore() < entry.getVisitScore();
										}
										if (homeWins || visitWins) {
											continue;
										}
										Calendar c = entry.getData();
										String formattedDate = "" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DATE) + " "
												+ c.get(Calendar.HOUR_OF_DAY) + ":"
												+ (c.get(Calendar.MINUTE) > 10 ? c.get(Calendar.MINUTE) : "0" + c.get(Calendar.MINUTE));
										String gameEntry = formattedDate + " " + Nba2016WebUtil.getAcronym(entry.getVisitTeam()) + " @ "
												+ Nba2016WebUtil.getAcronym(entry.getHomeTeam());
						%><option value="<%=entry.getId()%>"><%=gameEntry%></option>
						<%
							}
								}
						%>
					</select> DATE: <input type='text' id='dateTime' name='dateTime' size=5>&nbsp;
					VISIT: <input type='text' id='visitScore' name='visitScore' size=5>&nbsp;
					HOME: <input type='text' id='homeScore' name='homeScore' size=5>&nbsp;
					<input type="radio" name="ot" value="0" checked='checked'>Regulation
					<input type="radio" name="ot" value="1">OT &nbsp;&nbsp;
					<button type="submit" class="change-button">U</button>
				</form>
				<br>
				<hr>


				<h2>SERIES</h2>
				<%
					ArrayList<Nba2016Series> series = (ArrayList<Nba2016Series>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES);
						for (Nba2016Series serie : series) {
							ArrayList<Nba2016Game> games = serie.getGames();

							if (games.size() == 7 || !serie.getWinner().isEmpty() || serie.getTeam1().startsWith("W")
									|| serie.getTeam2().startsWith("W")) {
								continue;
							}

							SimpleDateFormat sd = new SimpleDateFormat("MM/dd hh:mm");
							out.println(String.format("<h3>%s vs %s</h3>", Nba2016WebUtil.getAcronym(serie.getTeam1()),
									Nba2016WebUtil.getAcronym(serie.getTeam2())));
							Calendar lastGame = Calendar.getInstance();
							for (Nba2016Game game : games) {
								lastGame = (Calendar) game.getData().clone();
								out.println(sd.format(game.getData().getTime()) + " - " + game.getVisitTeam() + " @ "
										+ game.getHomeTeam());
								if (game.getHomeScore() + game.getVisitScore() > 10) {
									out.println(" (" + game.getVisitScore() + ":" + game.getHomeScore() + ")");
								}
								out.println("<br>");
							}
				%>
				<form method="post" action="CreateNewGameServlet">
				<input type="submit" value="Create game <%=(games.size() + 1)%>"></input>

					<input type=hidden name=fase value="<%=serie.getFase()%>">
					<input type=hidden name=bracketId value="<%=serie.getId()%>">
					<%
						if (games.size() == 2 || games.size() == 3 || games.size() == 5) {
					%>
					<input type=text size=3 name="visitTeam"
						value="<%=serie.getTeam1()%>"></input>&nbsp;@&nbsp;<input
						type=text size=3 name="homeTeam" value="<%=serie.getTeam2()%>"></input>
					<%
						} else {
					%>
					<input type=text size=3 name="visitTeam"
						value="<%=serie.getTeam2()%>"></input>&nbsp;@&nbsp;<input
						type=text size=3 name="homeTeam" value="<%=serie.getTeam1()%>"></input>
					<%
						}
					%>

					<select id="gameDate" name="gameDate">
						<%
							SimpleDateFormat shortSd = new SimpleDateFormat("MM/dd");
									for (int idx = 1; idx <= 5; idx++) {
										lastGame.add(Calendar.DAY_OF_MONTH, 1);
										out.println(String.format("<option value=\"%s\">%s</option>",
												shortSd.format(lastGame.getTime()), shortSd.format(lastGame.getTime())));
									}
						%>
					</select>&nbsp; <select id="gameTime" name="gameTime">
						<option value="12:00">12:00</option>
						<option value="12:30">12:30</option>
						<option value="13:00">13:00</option>
						<option value="13:30">13:30</option>
						<option value="14:00">14:00</option>
						<option value="14:30">14:30</option>
						<option value="15:00">15:00</option>
						<option value="15:30">15:30</option>
						<option value="16:00">16:00</option>
						<option value="16:30">16:30</option>
						<option value="17:00">17:00</option>
						<option value="17:30">17:30</option>
						<option value="18:00">18:00</option>
						<option value="18:30">18:30</option>
						<option value="19:00">19:00</option>
						<option value="19:30">19:30</option>
						<option value="20:00">20:00</option>
						<option value="20:30">20:30</option>
						<option value="21:00">21:00</option>
						<option value="21:30">21:30</option>
						<option value="22:00">22:00</option>
						<option value="22:30">22:30</option>
						<option value="23:00">23:00</option>
						<option value="23:30">23:30</option>
					</select>
				</form>
				<%
					}
				%>

				<hr>

				<a href="CalculateServlet">Recalculate</a> <br>
				<hr>
				<br>
				<%-- 
				<h2>USERS</h2>
				<%
					ArrayList<User> users = (ArrayList<User>) session.getAttribute(Nba2016SessionUtil.ALL_USERS);
						for (User entry : users) {
				%>
				<%=entry.toDMLInsertString()%>
				<br>
				<%
					}
				%>
				<hr>

				<h2>BRACKETS</h2>
				<%
					ArrayList<Nba2016Series> brackets = (ArrayList<Nba2016Series>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES);
						for (Nba2016Series entry : brackets) {
				%>
				<%=entry.toDMLInsertString()%>
				<br>
				<%
					}
				%>
				<hr>

				<h2>BRACKET BETS</h2>
				<%
					ArrayList<SeriesBet> bBets = (ArrayList<SeriesBet>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES_BETS);
						for (SeriesBet entry : bBets) {
				%>
				<%=entry.toDMLInsertString()%>
				<br>
				<%
					}
				%>
				<hr>

				<h2>GAMES</h2>
				<%{
					ArrayList<Nba2016Game> games = (ArrayList<Nba2016Game>) session.getAttribute(Nba2016SessionUtil.ALL_GAMES);
						for (Nba2016Game game : games) {
				%>
				<%=Nba2016WebUtil.getHeaderGameEntryLine(game)%>
				<br>
				<%
						}}
				%>
				<hr>
				--%>
				
					<h2>UNFINISHED GAMES:</h2>
					<%
					{
						int loggedId = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getId();
						boolean isAdmin = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole().equalsIgnoreCase("admin");
						int id2use = loggedId;

						ArrayList<Nba2016Game> games = (ArrayList<Nba2016Game>) session.getAttribute(Nba2016SessionUtil.ALL_GAMES);
						for (Nba2016Game entry : games) {
							boolean homeWins = false, visitWins = false;
							if (entry.getVisitScore() > 0 && entry.getHomeScore() > 0) {
								homeWins = entry.getHomeScore() > entry.getVisitScore();
								visitWins = entry.getHomeScore() < entry.getVisitScore();
							}
							if (homeWins || visitWins) {
								continue;
							}
							Calendar c = entry.getData();
							String formattedDate = "" + (c.get(Calendar.MONTH) + 1) + "/" + c.get(Calendar.DATE) + " "
									+ c.get(Calendar.HOUR_OF_DAY) + ":"
									+ (c.get(Calendar.MINUTE) > 10 ? c.get(Calendar.MINUTE) : "0" + c.get(Calendar.MINUTE));
							String gameEntry = formattedDate + " " + Nba2016WebUtil.getAcronym(entry.getVisitTeam()) + " @ "
									+ Nba2016WebUtil.getAcronym(entry.getHomeTeam());
							out.print(String.format("%s - <a href='RunDBServlet?action=DELETE-GAME&game_id=%s'>delete it</a><br>", gameEntry, entry.getId()));
						}
					}
					%>
<%--
				<h2>GAME BETS</h2>
				<%
					ArrayList<GameBet> bets = (ArrayList<GameBet>) session.getAttribute(Nba2016SessionUtil.ALL_GAME_BETS);
						for (GameBet entry : bets) {
				%>
				<%=entry.toDMLInsertString()%>
				<br>
				<%
					}
				%>
				<hr>

				<h2>BONUS</h2>
				<%
					ArrayList<TieBreaker> tbs = (ArrayList<TieBreaker>) session.getAttribute(Nba2016SessionUtil.ALL_BONUS);
						for (TieBreaker entry : tbs) {
				%>
				<%=entry.toDMLInsertString()%>
				<br>
				<%
					}
				%>
				<hr>

				<h2>BONUS BETS</h2>
				<%
					ArrayList<TieBreakerBet> tbBets = (ArrayList<TieBreakerBet>) session
								.getAttribute(Nba2016SessionUtil.ALL_BONUS_BETS);
						for (TieBreakerBet entry : tbBets) {
				%>
				<%=entry.toDMLInsertString()%>
				<br>
				<%
					}
				%>
				<hr>
 --%>
 <p>
 

			</section>
		</div>

	</section>

	<%
		}
	%>
</body>
</html>