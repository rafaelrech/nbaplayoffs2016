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
	function updateAndSubmit(pAction, pObject, pNewValue, pAnchor) {
		document.getElementById('newvalue').value = pNewValue;
		document.getElementById('sportId').value = pObject;
		document.getElementById('action').value = pAction;
		document.getElementById('target').value = 'dashboard.jsp#' + pAnchor;
		document.getElementById('formus').submit();
	}
	function updateResult(pCompetitorId, pSportId, pNewValue, pAnchor) {
		document.getElementById('action').value = 'UPDATE-RESULT';
		document.getElementById('sportId').value = pSportId;
		document.getElementById('competitorId').value = pCompetitorId;
		document.getElementById('newvalue').value = pNewValue;
		document.getElementById('target').value = 'dashboard.jsp#' + pAnchor;
		document.getElementById('formus').submit();
	}
</script>

<body>
	<form method="post" id="formus" action="SportsServlet">
		<input type="hidden" name="source" value="dashboard.jsp"> 
		<input type="hidden" id="target" name="target" value="dashboard.jsp">
		<input type="hidden" id="action" name="action" value="CREATE">
		<input type="hidden" id="sportId" name="sportId" value="<%=Rio2016DBUtil.SPORT_BEAN%>"> 
		<input type="hidden" id="competitorId" name="competitorId" value="<%=Rio2016DBUtil.SPORT_BEAN%>">
		<input type="hidden" id="newvalue" name="newvalue" value="<%=Rio2016DBUtil.SPORT_BEAN%>">
	</form>


	<div id="page-wrapper">
		<jsp:include page="header.jsp">
			<jsp:param name="currpage" value="dashboard" />
		</jsp:include>

		<div id="main">
			<div class="container">
				<div class="row main-row">
					<div class="12u">
						<section>
							<h2>Modalidades</h2>
							<% 
								ArrayList<Rio2016Sport> sports = (ArrayList<Rio2016Sport>) session.getAttribute(Rio2016SessionUtil.ALL_SPORTS);
								int counter = 0;
								for (Rio2016Sport sport : sports) {
									String participants = new String("");
									for (Rio2016Participation part : sport.getPaticipations()) {
										participants += part.getCompetitor().getId() + "-" + part.getCompetitor().getName() + "-" + part.getCompetitor().getCountry() + "\n";
									}

							%>
							<a name="sport<%=sport.getId()%>"></a>
							<div>
								<h4><%=sport.getName() %></h4>
								<p>
									Actions: <br> 
									<%
									if (sport.getCompleted()==0) {
									%>
									<a href="#" onclick="javascript: updateAndSubmit('UPDATE-SPORT-STATUS', <%=sport.getId()%>, '1', 'sport<%=sport.getId()%>')" class="button">Mark as Completed</a><br>
									<% 
									} else {
									%>
									<a href="#" onclick="javascript: updateAndSubmit('UPDATE-SPORT-STATUS', <%=sport.getId()%>, '0', 'sport<%=sport.getId()%>')" class="button">Mark As Not Completed</a><br>
									<% 
									}
									if (sport.getQtyGold()==1) {
									%>
									<img width=32 height=36 src='images/medals/gold.png'> <b>1</b> - <a href="#" onclick="javascript: updateAndSubmit('UPDATE-SPORT-GOLD', <%=sport.getId()%>,  '2', 'sport<%=sport.getId()%>')">2</a>
									<% 
									} else {
									%>
									<img width=32 height=36 src='images/medals/gold.png'> <a href="#" onclick="javascript: updateAndSubmit('UPDATE-SPORT-GOLD',  <%=sport.getId()%>, '1', 'sport<%=sport.getId()%>')">1</a> - <b>2</b>
									<% 
									}
									if (sport.getQtySilver()==1) {
									%>
									- <img width=32 height=36 src='images/medals/silver.png'> <b>1</b> - <a href="#" onclick="javascript: updateAndSubmit('UPDATE-SPORT-SILVER',  <%=sport.getId()%>, '2', 'sport<%=sport.getId()%>')">2</a>
									<% 
									} else {
									%>
									- <img width=32 height=36 src='images/medals/silver.png'> <a href="#" onclick="javascript: updateAndSubmit('UPDATE-SPORT-SILVER',  <%=sport.getId()%>, '1', 'sport<%=sport.getId()%>')">1</a> - <b>2</b>
									<% 
									}
									if (sport.getQtyBronze()==1) {
									%>
									- <img width=32 height=36 src='images/medals/bronze.png'> <b>1</b>
									- <a href="#" onclick="javascript: updateAndSubmit('UPDATE-SPORT-BRONZE',  <%=sport.getId()%>, '2', 'sport<%=sport.getId()%>')">2</a><br>
									<% 
									} else {
									%>
									- <img width=32 height=36 src='images/medals/bronze.png'> <a href="#" onclick="javascript: updateAndSubmit('UPDATE-SPORT-BRONZE',  <%=sport.getId()%>, '1', 'sport<%=sport.getId()%>')">1</a>
									- <b>2</b><br>
									<% 
									}
									%>
									<%--
									 <a href="editSport.jsp?sportId=<%=sport.getId()%>">Editar Modalidade e Participantes</a> <br>
									 --%>
								</p>
								<div class="row">
									<div class="4u 12u(mobile)">
										<form method="post" action="SportsServlet">
											<input type="hidden" name="source" value="dashboard.jsp">
											<input type="hidden" name="target" value="dashboard.jsp#sport<%=sport.getId()%>">
											<input type="hidden" name="action" value="PARTICIPANTS">
											<input type="hidden" name="sportId" value="<%=sport.getId()%>">
											<textarea id="participants" name="participants" rows="15" cols="40"><%=participants %></textarea>
											<br>
											<button type="submit" class="button">submit</button>
										</form>
									</div>
									<div class="8u 12u(mobile)">
											<table id="table-1">
												<tbody>
										<%
										for (Rio2016Participation part : sport.getPaticipations()) {
											%>
												<tr>
													<td><%=Rio2016WebUtil.getImageString(part.getCompetitor().getCountry())%></td>
													<td><%=part.getCompetitor().getName()%>&nbsp;&nbsp;</td>
													<td><a href='#sport<%=sport.getId()%>'
														onclick='updateResult(<%=part.getCompetitorId()%>, <%=sport.getId()%>, 1, "sport<%=sport.getId()%>")'>
															<img width=32 height=36 src='images/medals/gold.png'>
													</a></td>
													<td><a href='#sport<%=sport.getId()%>'
														onclick='updateResult(<%=part.getCompetitorId()%>, <%=sport.getId()%>, 2, "sport<%=sport.getId()%>")'><img
															width=32 height=36 src='images/medals/silver.png'></a></td>
													<td><a href='#sport<%=sport.getId()%>'
														onclick='updateResult(<%=part.getCompetitorId()%>, <%=sport.getId()%>, 3, "sport<%=sport.getId()%>")'><img
															width=32 height=36 src='images/medals/bronze.png'></a></td>
													<td><a href='#sport<%=sport.getId()%>'
														onclick='updateResult(<%=part.getCompetitorId()%>, <%=sport.getId()%>, 99, "sport<%=sport.getId()%>")'><img
															width=32 height=36 src='images/medals/none.png'></a></td>
													<td>&nbsp;&nbsp;Result:&nbsp;<img width=32 height=36
														src='images/medals/none.png'></a></td>
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

