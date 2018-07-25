<%@page import="java.util.Calendar"%>
<%@page import="java.util.HashMap"%>
<%@page import="rio2016.bean.Rio2016MedalBet"%>
<%@page import="rech.bolao.bean.User"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="rio2016.util.Rio2016WebUtil"%>
<%@page import="rech.bolao.web.WebUtils"%>
<%@page import="rio2016.bean.Rio2016Participation"%>
<%@page import="rio2016.util.Rio2016SessionUtil"%>
<%@page import="rio2016.bean.Rio2016Sport"%>
<%@page import="rech.bolao.bean.CommonBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rio2016.util.Rio2016DBUtil"%>
<%@ page session="true"%>
<%@ page import="rech.bolao.util.BaseSessionUtil"%>
<link rel="apple-touch-icon" sizes="57x57" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-57x57.png">
<link rel="apple-touch-icon" sizes="60x60" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-60x60.png">
<link rel="apple-touch-icon" sizes="72x72" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-72x72.png">
<link rel="apple-touch-icon" sizes="76x76" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-76x76.png">
<link rel="apple-touch-icon" sizes="114x114" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-114x114.png">
<link rel="apple-touch-icon" sizes="120x120" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-120x120.png">
<link rel="apple-touch-icon" sizes="144x144" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-144x144.png">
<link rel="apple-touch-icon" sizes="152x152" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-152x152.png">
<link rel="apple-touch-icon" sizes="180x180" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/apple-icon-180x180.png">
<link rel="icon" type="image/png" sizes="192x192" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/android-icon-192x192.png">
<link rel="icon" type="image/png" sizes="32x32" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/favicon-32x32.png">
<link rel="icon" type="image/png" sizes="96x96" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/favicon-96x96.png">
<link rel="icon" type="image/png" sizes="16x16" href="https://smsprio2016-a.akamaihd.net/data/content/1.65/images/favicon.ico/favicon-16x16.png">


<!DOCTYPE HTML>
<html>
<head>
	<title>RIO 2016</title>
	<meta charset="utf-8" />
	<meta name="viewport" content="width=device-width, initial-scale=1" />
	<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
	<link rel="stylesheet" href="assets/css/main.css" />
	<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
<style>
td {
    vertical-align: middle;
    font-size: larger;
}
textarea {
    /*
    height: 150px;
    resize: none;
    background-color: #f8f8f8;
    */
    width: 100%;
    padding: 6px 10px;
    box-sizing: border-box;
    border: 2px solid #ccc;
    border-radius: 20px;
    font-size: large;
}

</style>
</head>

</head>
<%
	if (session.getAttribute(BaseSessionUtil.LOGGED_USER) == null){
	    response.sendRedirect("../login.jsp");
	}
	else 
	{
%>
<script>
	function reloadPage (pValue) {
		if(pValue == 'order-by-date') { document.getElementById('orderBy').value = 'date'; }
		if(pValue == 'order-by-name') { document.getElementById('orderBy').value = 'name'; }
		if(pValue == 'show-today') { document.getElementById('showDates').value = 'today'; }
		if(pValue == 'show-tomorrow') { document.getElementById('showDates').value = 'tomorrow'; }
		if(pValue == 'show-all-dates') { document.getElementById('showDates').value = 'all-dates'; }
		if(pValue == 'show-completed') { document.getElementById('showCompleted').value = 'true'; }
		if(pValue == 'dont-show-completed') { document.getElementById('showCompleted').value = 'false'; }
		if(pValue == 'show-pending') { document.getElementById('onlyPending').value = 'true'; }
		if(pValue == 'show-all-states') { document.getElementById('onlyPending').value = 'false'; }
		document.getElementById('form_reload').submit();
	}
</script>

<body>
	<%
		String orderBy = request.getParameter("orderBy") == null ? "date" : request.getParameter("orderBy");  
		String showDates = request.getParameter("showDates") == null ? "all-dates" : request.getParameter("showDates");  
		Boolean showCompleted = request.getParameter("showCompleted") == null ? Boolean.TRUE : new Boolean(request.getParameter("showCompleted"));  
		Boolean onlyPending = request.getParameter("onlyPending") == null ? Boolean.FALSE : new Boolean(request.getParameter("onlyPending"));  
	%>
	<form method="post" id="form_reload" action="bets.jsp">
		<input type="hidden" id="orderBy" name="orderBy" value="<%=orderBy%>">
		<input type="hidden" id="showDates" name="showDates" value="<%=showDates%>">
		<input type="hidden" id="showCompleted" name="showCompleted" value="<%=showCompleted%>">
		<input type="hidden" id="onlyPending" name="onlyPending" value="<%=onlyPending%>">
	</form>
	
	<script>
	function bet(pAction, pSport, pCompetitor, pBetValue, pAnchor) {
		document.getElementById('action').value = pAction;
		document.getElementById('sportId').value = pSport;
		document.getElementById('competitorId').value = pCompetitor;
		document.getElementById('betValue').value = pBetValue;
		document.getElementById('target').value = 'bets.jsp#' + pAnchor;
		document.getElementById('formbet').submit();
	}	</script>

	<form method="post" id="formbet" action="BetServlet">
		<input type="hidden" id="target" name="target" value="bets.jsp">
		<input type="hidden" id="action" name="action" value="BET">
		<input type="hidden" id="competitorId" name="competitorId" value="<%=Rio2016DBUtil.SPORT_BEAN%>"> 
		<input type="hidden" id="sportId" name="sportId" value="<%=Rio2016DBUtil.SPORT_BEAN%>"> 
		<input type="hidden" id="betValue" name="betValue" value="<%=Rio2016DBUtil.SPORT_BEAN%>">
		<input type="hidden" id="orderBy" name="orderBy" value="<%=orderBy%>">
		<input type="hidden" id="showDates" name="showDates" value="<%=showDates%>">
		<input type="hidden" id="showCompleted" name="showCompleted" value="<%=showCompleted%>">
		<input type="hidden" id="onlyPending" name="onlyPending" value="<%=onlyPending%>">
	</form>


	<div id="page-wrapper">
		<jsp:include page="header.jsp">
			<jsp:param name="currpage" value="bets" />
		</jsp:include>

		<div id="main">
			<div class="container">
				<div class="row main-row">
					<div class="12u">
						<section>
							<h2>Modalidades</h2>
							<p>
								<!-- 
								 Ordenar por: <a href="#" onclick="reloadPage ('order-by-date')">Data</a>&nbsp;<a href="#" onclick="reloadPage ('order-by-name')">Nome</a>&nbsp;<br>
								 -->
								<%
								if(!showDates.equals("today")) {
								%>
								<a href="#" onclick="reloadPage('show-today')" class="button">Mostrar iniciando hoje</a>&nbsp;
								<% 
								} 
								%>
								<%
								if(!showDates.equals("tomorrow")) {
								%>
								<a href="#" onclick="reloadPage('show-tomorrow')" class="button">Mostrar inicando amanhã</a>&nbsp;
								<% 
								} 
								%>
								<%
								if(!showDates.equals("all-dates")) {
								%>
								<a href="#" onclick="reloadPage('show-all-dates')" class="button">Mostrar todas datas</a>&nbsp;
								<% 
								} 
								%>
								&nbsp;||&nbsp;
								<% 
								if(!showCompleted) {
								%>
								<a href="#" onclick="reloadPage('show-completed')" class="button">Mostrar finalizadas</a>&nbsp;
								<% 
								} else {
								%>
								<a href="#" onclick="reloadPage('dont-show-completed')" class="button">Esconder finalizadas</a>&nbsp;
								<% 
								} 
								%>
								&nbsp;||&nbsp;
								<% 
								if(!onlyPending) {
								%>
								<a href="#" onclick="reloadPage('show-pending')" class="button">Mostrar apenas pendentes</a>&nbsp;
								<% 
								} else {
								%>
								<a href="#" onclick="reloadPage('show-all-states')" class="button">Mostrar pendentes e não pendentes</a>&nbsp;
								<% 
								} 
								%>
							</p>
							<div id="breadcrumbs"></div>
							<br><br>
							<% 
								StringBuffer bcrumbs = new StringBuffer("");
								Calendar today = Calendar.getInstance();
								today = WebUtils.truncate(today);
								Calendar tomorrow = Calendar.getInstance();
								tomorrow.add(Calendar.DATE, 1);
								tomorrow = WebUtils.truncate(tomorrow);
								ArrayList<Rio2016Sport> sports = (ArrayList<Rio2016Sport>) session.getAttribute(Rio2016SessionUtil.ALL_SPORTS);
								int counter = 0;
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
								for (Rio2016Sport sport : sports) {
									Calendar startDate = sport.getStartDate();
									startDate = WebUtils.truncate(startDate);
									if(showDates.equals("today") && (startDate.getTimeInMillis() != today.getTimeInMillis())) { continue; }
									if(showDates.equals("tomorrow") && (startDate.getTimeInMillis() != tomorrow.getTimeInMillis())) { continue; }
									if(sport.getCompleted()==1 && !showCompleted) { continue; }
									if(sport.getPaticipations().size()<1) {continue;}
									User u = (User) session.getAttribute(BaseSessionUtil.LOGGED_USER);
									ArrayList<Rio2016MedalBet> allBets = (ArrayList<Rio2016MedalBet>) session.getAttribute(Rio2016SessionUtil.ALL_MEDAL_BETS);
									int golds = 0;
									int silvers = 0;
									int bronzes = 0;
									HashMap<Integer, Integer> otherBets = new HashMap<Integer, Integer>();
									HashMap<Integer, Integer> userBets = new HashMap<Integer, Integer>();
									for (Rio2016MedalBet bet : allBets) {
										if(sport.getId() != bet.getSportId()) {continue;}
										if(bet.getUserId() == u.getId()) {
											if(bet.getBet()==1) {golds ++;}
											if(bet.getBet()==2) {silvers ++;}
											if(bet.getBet()==3) {bronzes ++;}
											userBets.put(bet.getCompetitorId(), bet.getBet());
										} else {
											int qtyBets = 0;
											if(otherBets.containsKey(bet.getCompetitorId())) {
												qtyBets = otherBets.get(bet.getCompetitorId());
											}
											otherBets.put(bet.getCompetitorId(), qtyBets+1);
										}
									}
									boolean goldPending = !(sport.getQtyGold()==golds);
									boolean silverPending = !(sport.getQtySilver()==silvers);
									boolean bronzePending = !(sport.getQtyBronze()==bronzes);
									if(onlyPending && (!goldPending) && (!silverPending) && (!bronzePending) ) { continue; }
									String medals = String.format("%d Ouro%s, %d Prata%s, %d Bronze%s", sport.getQtyGold(),
											sport.getQtyGold() <= 1 ? "" : "s", sport.getQtySilver(),
											sport.getQtySilver() <= 1 ? "" : "s", sport.getQtyBronze(),
											sport.getQtyBronze() <= 1 ? "" : "s");
									String pendingBets = String.format("%d Ouro%s, %d Prata%s, %d Bronze%s", (sport.getQtyGold()-golds),
											(sport.getQtyGold()-golds) <= 1 ? "" : "s", (sport.getQtySilver()-silvers),
													(sport.getQtySilver()-silvers) <= 1 ? "" : "s", (sport.getQtyBronze()-bronzes),
															(sport.getQtyBronze()-bronzes) <= 1 ? "" : "s");
									boolean alreadyStarted = (startDate.getTimeInMillis() <= today.getTimeInMillis());
									if(bcrumbs.length() > 1) {
										bcrumbs.append("&nbsp;&nbsp;|&nbsp;&nbsp;");
									}
									bcrumbs.append(String.format("<a href='#sport%d'>%s</a>", sport.getId(), sport.getName().toLowerCase()));
							%>
							<a name="sport<%=sport.getId()%>"></a>
							<div>
								<div class="row">
									<div class="8u 12u(mobile)">
										<h4><%=sport.getName() %></h4>
										<p>
											Início: <%= sdf.format(sport.getStartDate().getTime()) %>&nbsp;&nbsp;Finais: <%= sdf.format(sport.getEndDate().getTime()) %><br>
											Medalhas distribuídas: <%=medals %> <br>
											<% if (!alreadyStarted) { %>
											Apostas pendentes: <%=pendingBets %><br>
											<% } %>
											<%= (alreadyStarted && sport.getCompleted()!=1?"<h4>Competição já iniciada!</h4>":"") %>
											<%= (sport.getCompleted()==1?"<h4>Competição já finalizada!</h4>":"") %>
										</p>
										<table id="table-1">
											<tbody>
										<%
										for (Rio2016Participation part : sport.getPaticipations()) {
										%>
												<tr>
													<td><%=Rio2016WebUtil.getImageString(part.getCompetitor().getCountry()) %></td>
													<td><%=part.getCompetitor().getName()%>&nbsp;&nbsp;</td>
													<td><a href='#sport<%=sport.getId()%>' <%= WebUtils.getBetJSLink(sport.getId(), part.getCompetitorId(), 1, goldPending, alreadyStarted) %>><img width=32 height=36 src='images/medals/gold.png'></a></td>
													<td><a href='#sport<%=sport.getId()%>' <%= WebUtils.getBetJSLink(sport.getId(), part.getCompetitorId(), 2, silverPending, alreadyStarted) %>><img width=32 height=36 src='images/medals/silver.png'></a></td>
													<td><a href='#sport<%=sport.getId()%>' <%= WebUtils.getBetJSLink(sport.getId(), part.getCompetitorId(), 3, bronzePending, alreadyStarted) %>><img width=32 height=36 src='images/medals/bronze.png'></a></td>
													<td><a href='#sport<%=sport.getId()%>' <%= WebUtils.getBetJSLink(sport.getId(), part.getCompetitorId(), 0, true, alreadyStarted) %>><img width=32 height=36 src='images/medals/none.png'></a></td>
													<td>&nbsp;</td>
													<td>
														<%
															if(userBets.containsKey(part.getCompetitorId())) {
																switch(userBets.get(part.getCompetitorId())) {
																	case 1 : {
																		%>Aposta: <img width=32 height=36 src='images/medals/gold.png'>&nbsp;<%
																		break;
																	}
																	case 2 : {
																		%>Aposta: <img width=32 height=36 src='images/medals/silver.png'>&nbsp;<%
																		break;
																	}
																	case 3 : {
																		%>Aposta: <img width=32 height=36 src='images/medals/bronze.png'>&nbsp;<%
																		break;
																	}
																}
															}
														if(otherBets.containsKey(part.getCompetitorId())) {
															%><i><%=otherBets.get(part.getCompetitorId()) %> apostas registradas por outros usuários</i><%
														}
														%>
													</td>
													<td>
													<%
														switch(part.getResult()) {
															case 1 : {
																%>Resultado: <img width=32 height=36 src='images/medals/gold.png'>&nbsp;<%
																break;
															}
															case 2 : {
																%>Resultado: <img width=32 height=36 src='images/medals/silver.png'>&nbsp;<%
																break;
															}
															case 3 : {
																%>Resultado: <img width=32 height=36 src='images/medals/bronze.png'>&nbsp;<%
																break;
															}
														}
													%>
													</td>
												</tr>
										<%
										}	
										%>
											</tbody>
										</table>
									</div>
								</div>
							<p>&nbsp;</p>
							<p>&nbsp;</p>
							</div>
							<% } %>
							<script>document.getElementById('breadcrumbs').innerHTML = "<%=bcrumbs.toString()%>";</script>
						</section>
					</div>


				</div>
			</div>
		</div>
	</div>
<% } %>

	<!-- Scripts -->
	<script src="assets/js/jquery.min.js"></script>
	<script src="assets/js/skel.min.js"></script>
	<script src="assets/js/skel-viewport.min.js"></script>
	<script src="assets/js/util.js"></script>
	<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
	<script src="assets/js/main.js"></script>

</body>
</html>

