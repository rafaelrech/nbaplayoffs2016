<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@page import="rech.bolao.bean.User"%>
<%@ page import="java.util.*"%>
<%@ page import="nbaplayoffs2016.bean.*"%>
<%@ page import="nbaplayoffs2016.util.*"%>
<%@ page session="true"%>

<h2>OTHER BRACKETS</h2>

<%
ArrayList<User> users = (ArrayList<User>) session.getAttribute(BaseSessionUtil.ALL_USERS);
for (User entry : users) {
	if(!entry.getRole().equalsIgnoreCase("admin")) {
		out.println("<a href='/nbaplayoffs2016/main.jsp?user=" + entry.getId() + "'>" + entry.getUsername() + "</a>&nbsp;&nbsp;||&nbsp;");
	}
}
%>
<br>

<h2>BRACKET</h2>

<div class="bracket-layer">
	<div class="sep" style="height: 43px; width: 792px;"></div>
	<!-- FIRST ROUND WEST -->
	<div class="block-first">
		<div class="sep" style="width: 20px;"></div>
		<div id="b5_1_entry" class="team-entry"></div>
		<div class="sep" style="height: 40px; width: 120px;"></div>
		<div class="sep" style="width: 60px;"></div>
		<div id="b11_1" class="bet-entry"></div>
		<div class="sep" style="height: 25px; width: 120px;"></div>
		<div class="sep" style="width: 20px;"></div>
		<div id="b5_2_entry" class="team-entry"></div>
		<div class="sep" style="height: 10px; width: 120px;"></div>
		<div class="sep" style="width: 20px;"></div>
		<div id="b6_1_entry" class="team-entry"></div>
		<div class="sep" style="height: 40px; width: 120px;"></div>
		<div class="sep" style="width: 60px;"></div>
		<div id="b11_2" class="bet-entry"></div>
		<div class="sep" style="height: 25px; width: 120px;"></div>
		<div class="sep" style="width: 20px;"></div>
		<div id="b6_2_entry" class="team-entry"></div>
		<div class="sep" style="height: 10px; width: 120px;"></div>
		<div class="sep" style="width: 20px;"></div>
		<div id="b7_1_entry" class="team-entry"></div>
		<div class="sep" style="height: 38px; width: 120px;"></div>
		<div class="sep" style="width: 60px;"></div>
		<div id="b12_1" class="bet-entry"></div>
		<div class="sep" style="height: 25px; width: 120px;"></div>
		<div class="sep" style="width: 20px;"></div>
		<div id="b7_2_entry" class="team-entry"></div>
		<div class="sep" style="height: 9px; width: 120px;"></div>
		<div class="sep" style="width: 20px;"></div>
		<div id="b8_1_entry" class="team-entry"></div>
		<div class="sep" style="height: 40px; width: 120px;"></div>
		<div class="sep" style="width: 60px;"></div>
		<div id="b12_2" class="bet-entry"></div>
		<div class="sep" style="height: 23px; width: 120px;"></div>
		<div class="sep" style="width: 20px;"></div>
		<div id="b8_2_entry" class="team-entry"></div>
		<div class="sep" style="height: 10px; width: 120px;"></div>
		<div class="sep" style="width: 20px;"></div>
	</div>

	<!-- CONF SEMIS WEST -->
	<div class="block-semis">
		<div class="sep" style="height: 48px; width: 110px;"></div>
		<div class="sep" style="width: 5px;"></div>
		<div id="b11_1_entry" class="team-entry"></div>
		<div class="sep" style="height: 60px; width: 110px;"></div>
		<div class="sep" style="width: 50px;"></div>
		<div id="b14_1" class="bet-entry"></div>
		<div class="sep" style="height: 35px; width: 110px;"></div>
		<div class="sep" style="width: 5px;"></div>
		<div id="b11_2_entry" class="team-entry"></div>
		<div class="sep" style="height: 110px; width: 110px;"></div>
		<div class="sep" style="width: 5px;"></div>
		<div id="b12_1_entry" class="team-entry"></div>
		<div class="sep" style="height: 60px; width: 110px;"></div>
		<div class="sep" style="width: 50px;"></div>
		<div id="b14_2" class="bet-entry"></div>
		<div class="sep" style="height: 35px; width: 110px;"></div>
		<div class="sep" style="width: 5px;"></div>
		<div id="b12_2_entry" class="team-entry"></div>
	</div>

	<!-- CONF FINALS WEST -->
	<div class="block-finals">
		<div class="sep" style="height: 118px; width: 105px;"></div>
		<div class="sep" style="width: 5px;"></div>
		<div id="b14_1_entry" class="team-entry"></div>
		<div class="sep" style="height: 112px; width: 105px;"></div>
		<div class="sep" style="width: 43px;"></div>
		<div id="b15_2" class="bet-entry"></div>
		<div class="sep" style="height: 110px; width: 105px;"></div>
		<div class="sep" style="width: 5px;"></div>
		<div id="b14_2_entry" class="team-entry"></div>
	</div>

	<!-- NBA FINALS  -->
	<div class="block-nba-finals">
		<div class="sep" style="height: 190px; width: 123px;"></div>
		<div class="sep" style="height: 25px; width: 12px;"></div>
		<div class="champion-entry" id="champion"></div>
		<div class="sep" style="height: 25px; width: 123px;"></div>
		<div class="sep" style="height: 20px; width: 10px;"></div>
		<div id="b15_2_entry" class="team-entry"></div>
		<div class="sep" style="height: 102px; width: 123px;"></div>
		<div class="sep" style="height: 20px; width: 5px;"></div>
		<div id="b15_1_entry" class="team-entry-right"></div>
		<div class="sep" style="height: 57px; width: 123px;"></div>
		<div class="sep" style="height: 25px; width: 15px;"></div>
		<div class="champion-entry" id='MVP'></div>
	</div>

	<!-- CONF FINALS EAST -->
	<div class="block-finals">
		<div class="sep" style="height: 118px; width: 105px;"></div>
		<div id="b13_1_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 5px;"></div>
		<div class="sep" style="height: 112px; width: 105px;"></div>
		<div id="b15_1" class="bet-entry"></div>
		<div class="sep" style="width: 45px;"></div>
		<div class="sep" style="height: 110px; width: 105px;"></div>
		<div id="b13_2_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 5px;"></div>
	</div>

	<!-- CONF SEMIS EAST -->
	<div class="block-semis">
		<div class="sep" style="height: 48px; width: 110px;"></div>
		<div id="b9_1_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 5px;"></div>
		<div class="sep" style="height: 60px; width: 110px;"></div>
		<div id="b13_1" class="bet-entry"></div>
		<div class="sep" style="width: 50px;"></div>
		<div class="sep" style="height: 35px; width: 110px;"></div>
		<div id="b9_2_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 5px;"></div>
		<div class="sep" style="height: 110px; width: 110px;"></div>
		<div id="b10_1_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 5px;"></div>
		<div class="sep" style="height: 60px; width: 110px;"></div>
		<div id="b13_2" class="bet-entry"></div>
		<div class="sep" style="width: 50px;"></div>
		<div class="sep" style="height: 35px; width: 110px;"></div>
		<div id="b10_2_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 5px;"></div>
	</div>

	<!-- FIRST ROUND EAST -->
	<div class="block-nba-finals" style="width: 120px;">
		<div id="b1_1_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 20px;"></div>
		<div class="sep" style="height: 40px; width: 120px;"></div>
		<div id="b9_1" class="bet-entry"></div>
		<div class="sep" style="width: 60px;"></div>
		<div class="sep" style="height: 25px; width: 120px;"></div>
		<div id="b1_2_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 20px;"></div>
		<div class="sep" style="height: 10px; width: 120px;"></div>
		<div id="b2_1_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 20px;"></div>
		<div class="sep" style="height: 40px; width: 120px;"></div>
		<div id="b9_2" class="bet-entry"></div>
		<div class="sep" style="width: 60px;"></div>
		<div class="sep" style="height: 25px; width: 120px;"></div>
		<div id="b2_2_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 20px;"></div>
		<div class="sep" style="height: 10px; width: 120px;"></div>
		<div id="b3_1_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 20px;"></div>
		<div class="sep" style="height: 38px; width: 120px;"></div>
		<div id="b10_1" class="bet-entry"></div>
		<div class="sep" style="width: 60px;"></div>
		<div class="sep" style="height: 25px; width: 120px;"></div>
		<div id="b3_2_entry" class="team-entry-right">]</div>
		<div class="sep" style="width: 20px;"></div>
		<div class="sep" style="height: 9px; width: 120px;"></div>
		<div id="b4_1_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 20px;"></div>
		<div class="sep" style="height: 40px; width: 120px;"></div>
		<div id="b10_2" class="bet-entry"></div>
		<div class="sep" style="width: 60px;"></div>
		<div class="sep" style="height: 23px; width: 120px;"></div>
		<div id="b4_2_entry" class="team-entry-right"></div>
		<div class="sep" style="width: 20px;"></div>
		<div class="sep" style="height: 10px; width: 120px;"></div>
	</div>
</div>


<script>
<%{
				int loggedId = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getId();
				boolean isAdmin = ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole()
						.equalsIgnoreCase("admin");
				int id2use = loggedId;
				String reqestUserId = request.getParameter("user");
				if (reqestUserId != null && !reqestUserId.isEmpty()) {
					id2use = Integer.valueOf(reqestUserId);
				}

				ArrayList<Nba2016Series> brackets = (ArrayList<Nba2016Series>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES);
				ArrayList<Nba2016SeriesBet> bracketBets = (ArrayList<Nba2016SeriesBet>) session.getAttribute(Nba2016SessionUtil.ALL_SERIES_BETS);
				Map<Integer, String> prevSeriesWinners = new HashMap<Integer, String>(); 
				Map<Integer, Integer> prevSeriesGames = new HashMap<Integer, Integer>(); 
				for (Nba2016Series series : brackets) {
					String currWinner = new String();
					String prev1BetWinner = new String();
					String prev2BetWinner = new String();
					String currGames = new String("-1");
					String prev1BetGames = new String("-1");
					String prev2BetGames = new String("-1");
					if (series.getWinner() != null && !series.getWinner().isEmpty() && series.getWinner().trim().equalsIgnoreCase(series.getTeam1())) {
						out.println("addEliminated('" + series.getTeam2() + "');");
						prevSeriesWinners.put(new Integer(series.getId()), series.getWinner());
						prevSeriesGames.put(new Integer(series.getId()), series.getNbrGames());
					}
					if (series.getWinner() != null && !series.getWinner().isEmpty() && series.getWinner().trim().equalsIgnoreCase(series.getTeam2())) {
						out.println("addEliminated('" + series.getTeam1() + "');");
						prevSeriesWinners.put(new Integer(series.getId()), series.getWinner());
						prevSeriesGames.put(new Integer(series.getId()), series.getNbrGames());
					}
					for (Nba2016SeriesBet bet : bracketBets) {
						if (bet.getUserId() != id2use) {
							continue;
						}
						if (bet.getBracketId() == series.getId()) {
							currWinner = bet.getWinner();
							currGames = String.valueOf(bet.getGames());
						}
						if (bet.getBracketId() == series.getPrevId1()) {
							prev1BetWinner = bet.getWinner();
							prev1BetGames = String.valueOf(bet.getGames());
						}
						if (bet.getBracketId() == series.getPrevId2()) {
							prev2BetWinner = bet.getWinner();
							prev2BetGames = String.valueOf(bet.getGames());
						}
					}
					String prevSeries1Winner = new String("");
					String prevSeries1NbrGames = new String("-1");
					String prevSeries2Winner = new String("");
					String prevSeries2NbrGames = new String("-1");
					if(prevSeriesWinners.containsKey(new Integer(series.getPrevId1()))) {
						prevSeries1Winner = prevSeriesWinners.get(new Integer(series.getPrevId1()));
						prevSeries1NbrGames = String.valueOf(prevSeriesGames.get(new Integer(series.getPrevId1())));
					}
					if(prevSeriesWinners.containsKey(new Integer(series.getPrevId2()))) {
						prevSeries2Winner = prevSeriesWinners.get(new Integer(series.getPrevId2()));
						prevSeries2NbrGames = String.valueOf(prevSeriesGames.get(new Integer(series.getPrevId2())));
					}
					%>
					changeBracketInfo('<%=series.getId()%>', <%=series.getFase()%>, '<%=series.getTeam1()%>', '<%=series.getTeam2()%>', 
							'<%=series.getWinner()%>', '<%=series.getNbrGames()%>', '<%=currWinner%>', <%=currGames%>, '<%=prev1BetWinner%>', <%=prev1BetGames%>, 
							'<%=prev2BetWinner%>', <%=prev2BetGames%>, 
							'<%=prevSeries1Winner%>', <%=prevSeries1NbrGames%>, 
							'<%=prevSeries2Winner%>', <%=prevSeries2NbrGames%>);<%}

				ArrayList<Nba2016TieBreakerBet> bonusBets = (ArrayList<Nba2016TieBreakerBet>) session
						.getAttribute(Nba2016SessionUtil.ALL_BONUS_BETS);
				for (Nba2016TieBreakerBet bonusBet : bonusBets) {
					if (bonusBet.getUserId() != id2use) {
						continue;
					}%>changeDivText('MVP', '<%=bonusBet.getValue()%>');
<%}

			}%>
	
</script>
