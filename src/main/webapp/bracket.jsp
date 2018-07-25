<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@page import="rech.bolao.bean.User"%>
<%@ page import="java.util.*" %>
<%@ page import="nbaplayoffs2016.bean.*" %>
<%@ page import="nbaplayoffs2016.util.*" %>
<%@ page session="true" %>
 
				<h2>BRACKET</h2>
				<div class="tournament-wrap">
					<div class="round4-wrap">
						<div class="round-sep-block" style='height: 264px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b15_1" class="round-bet">Top Bet</div>
								<div id="b15_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b15_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b15_2_entry" class="round-entry">Top Winner</div>
								<div id="b15_2" class="round-bet">Top Bet</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div class="round-sep" style='height: 146px;'></div>
								<div class="round-sep" style='height: 21px;'>
									<b>CHAMPION</b>
								</div>
								<div class="champion" id='champion'></div>
								<div class="round-sep" style='height: 21px; line-height: 2;'>
									<b>MVP</b>
								</div>
								<div class="champion" id='MVP'></div>
							</div>
						</div>
						<!-- 
							 -->
					</div>
					<div class="round3-wrap">
						<div class="round-sep-block" style='height: 110px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b13_1" class="round-bet">Top Bet</div>
								<div id="b13_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b13_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b13_2_entry" class="round-entry">Top Winner</div>
								<div id="b13_2" class="round-bet">Top Bet</div>
							</div>
						</div>
						<div class="round-sep-block" style='height: 214px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b14_1" class="round-bet">Top Bet</div>
								<div id="b14_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b14_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b14_2_entry" class="round-entry">Top Winner</div>
								<div id="b14_2" class="round-bet">Top Bet</div>
							</div>
						</div>
					</div>
					<div class="round2-wrap">
						<div class="round-sep-block" style='height: 33px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b9_1" class="round-bet">Top Bet</div>
								<div id="b9_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b9_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b9_2_entry" class="round-entry">Top Winner</div>
								<div id="b9_2" class="round-bet">Top Bet</div>
							</div>
						</div>
						<div class="round-sep-block" style='height: 60px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b10_1" class="round-bet">Top Bet</div>
								<div id="b10_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b10_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b10_2_entry" class="round-entry">Top Winner</div>
								<div id="b10_2" class="round-bet">Top Bet</div>
							</div>
						</div>
						<div class="round-sep-block" style='height: 60px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b11_1" class="round-bet">Top Bet</div>
								<div id="b11_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b11_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b11_2_entry" class="round-entry">Top Winner</div>
								<div id="b11_2" class="round-bet">Top Bet</div>
							</div>
						</div>
						<div class="round-sep-block" style='height: 61px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b12_1" class="round-bet">Top Bet</div>
								<div id="b12_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b12_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b12_2_entry" class="round-entry">Top Winner</div>
								<div id="b12_2" class="round-bet">Top Bet</div>
							</div>
						</div>
					</div>
					<div class="round1-wrap">
						<div class="round-sep-block" style='height: 10px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<!-- div id="b1_1" class="round-bet">Top Bet</div  -->
								<div id="b1_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b1_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b1_2_entry" class="round-entry">Top Winner</div>
								<!--  div id="b1_2" class="round-bet">Top Bet</div-->
							</div>
						</div>
						<div class="round-sep-block" style='height: 15px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<!-- div id="b2_1" class="round-bet">Top Bet</div  -->
								<div id="b2_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b2_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b2_2_entry" class="round-entry">Top Winner</div>
								<!--  div id="b2_2" class="round-bet">Top Bet</div-->
							</div>
						</div>
						<div class="round-sep-block" style='height: 15px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<!-- div id="b3_1" class="round-bet">Top Bet</div  -->
								<div id="b3_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b3_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b3_2_entry" class="round-entry">Top Winner</div>
								<!--  div id="b3_2" class="round-bet">Top Bet</div-->
							</div>
						</div>
						<div class="round-sep-block" style='height: 15px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<!-- div id="b4_1" class="round-bet">Top Bet</div  -->
								<div id="b4_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b4_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b4_2_entry" class="round-entry">Top Winner</div>
								<!--  div id="b4_2" class="round-bet">Top Bet</div-->
							</div>
						</div>
						<div class="round-sep-block" style='height: 15px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<!-- div id="b5_1" class="round-bet">Top Bet</div  -->
								<div id="b5_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b5_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b5_2_entry" class="round-entry">Top Winner</div>
								<!--  div id="b5_2" class="round-bet">Top Bet</div-->
							</div>
						</div>
						<div class="round-sep-block" style='height: 15px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<!-- div id="b6_1" class="round-bet">Top Bet</div  -->
								<div id="b6_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b6_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b6_2_entry" class="round-entry">Top Winner</div>
								<!--  div id="b6_2" class="round-bet">Top Bet</div-->
							</div>
						</div>
						<div class="round-sep-block" style='height: 15px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<!-- div id="b7_1" class="round-bet">Top Bet</div  -->
								<div id="b7_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b7_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b7_2_entry" class="round-entry">Top Winner</div>
								<!--  div id="b7_2" class="round-bet">Top Bet</div-->
							</div>
						</div>
						<div class="round-sep-block" style='height: 15px'></div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<!-- div id="b8_1" class="round-bet">Top Bet</div  -->
								<div id="b8_1_entry" class="round-entry">Top Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-right">
								<div id="b8_winner" class="round-winner">Winner</div>
							</div>
						</div>
						<div class="round-sep-block">
							<div class="round-sep-left">
								<div id="b8_2_entry" class="round-entry">Top Winner</div>
								<!--  div id="b8_2" class="round-bet">Top Bet</div-->
							</div>
						</div>
						<div class="round-sep-block" style='height: 10px'></div>
					</div>
				</div>


<script>
<% 
{
	int loggedId = ((User)session.getAttribute(BaseSessionUtil.LOGGED_USER)).getId();
	boolean isAdmin = ((User)session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole().equalsIgnoreCase("admin");
	int id2use = loggedId;
	String reqestUserId = request.getParameter("user");
	if(reqestUserId != null && !reqestUserId.isEmpty()) {
		id2use = Integer.valueOf(reqestUserId);
	}
	
	ArrayList<Series> brackets = (ArrayList<Series>) session.getAttribute(SessionUtil.ALL_SERIES);
	ArrayList<SeriesBet> bracketBets = (ArrayList<SeriesBet>) session.getAttribute(SessionUtil.ALL_SERIES_BETS);
	for (Series entry : brackets) {
		String currWinner = new String();
		String prevWinner1 = new String();
		String prevWinner2 = new String();
		String currGames = new String("-1");
		String prevGames1 = new String("-1");
		String prevGames2 = new String("-1");
		if (entry.getWinner() != null && !entry.getWinner().isEmpty() && entry.getWinner().trim().equalsIgnoreCase(entry.getTeam1())) {
			%>addEliminated('<%=entry.getTeam2()%>');<%
		}
		if (entry.getWinner() != null && !entry.getWinner().isEmpty() && entry.getWinner().trim().equalsIgnoreCase(entry.getTeam2())) {
			%>addEliminated('<%=entry.getTeam1()%>');<%
		}
		for (SeriesBet rec : bracketBets) {
			if(rec.getUserId() != id2use) { continue; }
			if(rec.getBracketId() == entry.getId()) {
				currWinner = rec.getWinner();
				currGames = String.valueOf(rec.getGames());
			}
			if(rec.getBracketId() == entry.getPrevId1()) {
				prevWinner1 = rec.getWinner();
				prevGames1 = String.valueOf(rec.getGames());
			}
			if(rec.getBracketId() == entry.getPrevId2()) {
				prevWinner2 = rec.getWinner();
				prevGames2 = String.valueOf(rec.getGames());
			}
		}
		%>changeBracketInfo('<%=entry.getId()%>', <%=entry.getFase()%>, '<%=entry.getTeam1()%>', '<%=entry.getTeam2()%>', '<%=entry.getWinner()%>', '<%=entry.getNbrGames()%>', '<%=currWinner%>', <%=currGames%>, '<%=prevWinner1%>', <%=prevGames1%>, '<%=prevWinner2%>', <%=prevGames2%>);<%
	}
	
	ArrayList<TieBreakerBet> bonusBets = (ArrayList<TieBreakerBet>) session.getAttribute(SessionUtil.ALL_BONUS_BETS);
	for (TieBreakerBet bonusBet : bonusBets) {
		if(bonusBet.getUserId() != id2use) { continue; }
		%>changeDivText('MVP', '<%=bonusBet.getValue()%>');<%
	}
	
}
%>
</script> 
