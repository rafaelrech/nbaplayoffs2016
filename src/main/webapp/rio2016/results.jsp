<%@page import="rio2016.bean.Rio2016User"%>
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
    padding: 6px 10px;
}
th {
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

<body>

	<div id="page-wrapper">
		<jsp:include page="header.jsp">
			<jsp:param name="currpage" value="results" />
		</jsp:include>

		<div id="main">
			<div class="container">
				<div class="row main-row">
					<div class="12u">
						<section>
							<h2>Modalidades</h2>
							<div id="breadcrumbs"></div>
							<br><br>
							<% 
								StringBuffer bcrumbs = new StringBuffer("");
								Calendar today = Calendar.getInstance();
								today = WebUtils.truncate(today);
								ArrayList<Rio2016Sport> sports = (ArrayList<Rio2016Sport>) session.getAttribute(Rio2016SessionUtil.ALL_SPORTS);
								int counter = 0;
								SimpleDateFormat sdf = new SimpleDateFormat("dd/MM");
								for (Rio2016Sport sport : sports) {
									boolean alreadyStarted = (sport.getStartDate().getTimeInMillis() <= today.getTimeInMillis());
//									if(!alreadyStarted) { continue; }
									if(sport.getPaticipations().size()<1) {continue;}
									User u = (User) session.getAttribute(BaseSessionUtil.LOGGED_USER);
									ArrayList<Rio2016MedalBet> allBets = (ArrayList<Rio2016MedalBet>) session.getAttribute(Rio2016SessionUtil.ALL_MEDAL_BETS);
									HashMap<Integer, Integer> otherBets = new HashMap<Integer, Integer>();
									HashMap<Integer, Integer> userBets = new HashMap<Integer, Integer>();
									for (Rio2016MedalBet bet : allBets) {
										if(sport.getId() != bet.getSportId()) {continue;}
										if(bet.getUserId() == u.getId()) {
											userBets.put(bet.getCompetitorId(), bet.getBet());
										} else {
											int qtyBets = 0;
											if(otherBets.containsKey(bet.getCompetitorId())) {
												qtyBets = otherBets.get(bet.getCompetitorId());
											}
											otherBets.put(bet.getCompetitorId(), qtyBets+1);
										}
									}
									String medals = String.format("%d Ouro%s, %d Prata%s, %d Bronze%s", sport.getQtyGold(),
											sport.getQtyGold() <= 1 ? "" : "s", sport.getQtySilver(),
											sport.getQtySilver() <= 1 ? "" : "s", sport.getQtyBronze(),
											sport.getQtyBronze() <= 1 ? "" : "s");
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
											<%= (sport.getCompleted()==1?"<h4>Competição já finalizada!</h4>":"") %>
										</p>
										<table id="table-1">
											<tbody>
												<tr>
													<td colspan=2>Participante</td>
													<td>Resultado</td>
										<%
												ArrayList<Rio2016User> users = (ArrayList<Rio2016User>) session.getAttribute(Rio2016SessionUtil.ALL_USERS);
												int[] userIds;
												if(users== null || users.size()==0) {
													userIds = new int[0];
												} else {
													userIds = new int[users.size()]; 
													int count = 0;
													for (Rio2016User user : users) {
														userIds[count ++] = user.getUserId();
														%><td><%=user.getUser().getUsername() %></td><% 
													}
												}
										%>
												</tr>
										<%
											for (Rio2016Participation part : sport.getPaticipations()) {
												if ((part.getResult() < 1 || part.getResult() > 3) && 
														(!userBets.containsKey(part.getCompetitorId())) && 
														(!otherBets.containsKey(part.getCompetitorId()))
														) {
													continue;
												}
										%>
												<tr>
													<td><%=Rio2016WebUtil.getImageString(part.getCompetitor().getCountry()) %></td>
													<td><%=part.getCompetitor().getName()%>&nbsp;&nbsp;</td>
													<td>
													<% 
														String medalImgStr = "<img width=32 height=36 src='images/medals/%s.png'>";
														switch(part.getResult()) {
															case 1:
																out.println(String.format(medalImgStr, "gold"));
																break;
															case 2:
																out.println(String.format(medalImgStr, "silver"));
																break;
															case 3:
																out.println(String.format(medalImgStr, "bronze"));
																break;
															default:
																out.println(String.format(medalImgStr, "blank"));
														}
													%>
													</td>
													<%
														for(int idx=0; idx<userIds.length; idx++) {
															%><td><%
															ArrayList<Rio2016MedalBet> sportBets = sport.getMedalBets();
															for (Rio2016MedalBet bet : sportBets) {
																if((bet.getUserId() == userIds[idx]) && (bet.getCompetitorId() == part.getCompetitorId())) {
																	switch(bet.getBet()) {
																		case 1:
																			out.println(String.format(medalImgStr, "gold"));
																			break;
																		case 2:
																			out.println(String.format(medalImgStr, "silver"));
																			break;
																		case 3:
																			out.println(String.format(medalImgStr, "bronze"));
																			break;
																		default:
																			out.println(String.format(medalImgStr, "blank"));
																	}
																}
															}
															%></td><%
														}
													%>
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

