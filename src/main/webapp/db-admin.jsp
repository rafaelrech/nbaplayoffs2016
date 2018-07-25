<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rech.bolao.bean.CommonBean"%>
<%@page import="rech.bolao.util.BaseDBUtil"%>
<%@ page session="true"%>



<!DOCTYPE HTML>
<html>
	<head>
		<title>BOLOES</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="rio2016/assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="rio2016/assets/css/main.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="rio2016/assets/css/ie9.css" /><![endif]-->
	</head>
<%
	if (session.getAttribute(BaseSessionUtil.LOGGED_USER) == null){
	    response.sendRedirect("../login.jsp");
	}
	else 
	{
%>
<script>
	function updateAndSubmit(pAction, pObject, pAnchor) {
		document.getElementById('object').value = pObject;
		document.getElementById('action').value = pAction;
		document.getElementById('target').value = 'db-admin.jsp#' + pAnchor;
		document.getElementById('formus').submit();
	}
</script>

	<body>
		<form method="post" id="formus" action="DBServlet">
			<input type="hidden" name="source" value="db-admin.jsp"> 
			<input type="hidden" id="target" name="target" value="db-admin.jsp"> 
			<input type="hidden" id="action" name="action" value="CREATE"> 
			<input type="hidden" id="object" name="object" value="CREATE">
		</form>
		<div id="page-wrapper">
			
			<jsp:include page="header.jsp">
				<jsp:param name="currpage" value="dbadm" />
			</jsp:include>

			<div id="main">
				<div class="container">
					<div class="row main-row">
						<div class="12u">
							<section>
								<h2>FREE TEXT SQL STATEMENT</h2>
								<div>
									<form method="post" action="DBServlet">
										<input type="hidden" name="action" value="EXECUTE">
										<textarea id="sql" name="sql" rows="8" cols="80"></textarea>
										<br>
										<button type="submit" >submit</button>
									</form>
								</div>
							</section>
						</div>
						<div class="12u">
							<section>
								<h2>Manage Tables</h2>
								<div>
								<% 
								String[] beans = BaseDBUtil.beans;
								int counter = 0;
								for (String bean : beans) { counter++; %>
									<a name="bean<%=counter%>"></a>
									<div class="row">
										<p>
										<b><%= bean %></b> 
										<a href="#" onclick="javascript: updateAndSubmit('CREATE', '<%=bean%>', 'bean<%=counter%>')">CREATE</a> 
										<a href="#" onclick="javascript: updateAndSubmit('DROP', '<%=bean%>', 'bean<%=counter%>')">DROP</a> 
										<a href="#" onclick="javascript: updateAndSubmit('LOAD', '<%=bean%>', 'bean<%=counter%>')">LOAD</a>
										<a href="#" onclick="javascript: updateAndSubmit('CLEAN', '<%=bean%>', 'bean<%=counter%>')">CLEAN</a><br>
									<%
										try {
											ArrayList<CommonBean> entries = (ArrayList<CommonBean>) BaseDBUtil.getDaoFromBean(bean).listAll();
											if(entries.size()==0) {
											out.println ("No rows returned");	
											} else {
											for (CommonBean entry : entries) {
												out.println(entry.toInsertString() + "<br>");
											}
											}
										} catch (Exception e) {
											out.println ("<b>Exceção : </b>" + e.getMessage());	
										}
									%>
										</p>
										<p>&nbsp;</p>
									</div>
									<%
									} 
									%>
								</div>
							</section>
						</div>
						
					</div>
				</div>
			</div>
		</div>

		<!-- Scripts -->
			<script src="assets/js/jquery.min.js"></script>
			<script src="assets/js/skel.min.js"></script>
			<script src="assets/js/skel-viewport.min.js"></script>
			<script src="assets/js/util.js"></script>
			<!--[if lte IE 8]><script src="assets/js/ie/respond.min.js"></script><![endif]-->
			<script src="assets/js/main.js"></script>

	</body>
</html>
<%
} 
%>






<%--
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<link rel="apple-touch-icon"
	href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link rel="shortcut icon" type="image/x-icon"
	href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<title>Bolão NBA - DASHBOARD</title>
<link rel="stylesheet" href="css/bolao.css">
<link rel="stylesheet" href="css/bracket.css">
<link href='https://fonts.googleapis.com/css?family=Open+Sans'
	rel='stylesheet' type='text/css'>
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

				<script>
					function updateAndSubmit0(pAction, pObject) {
						document.getElementById('object0').value = pObject;
						document.getElementById('action0').value = pAction;
						document.getElementById('form0').submit();
					}
					function updateAndSubmit(pAction, pObject) {
						document.getElementById('object1').value = pObject;
						document.getElementById('action1').value = pAction;
						document.getElementById('form1').submit();
					}
				</script>
				<h2>MANAGE DB</h2>
				<br>
				<form method="post" id="form0" action="DBServlet">
					<input type="hidden" name="source" value="db-admin.jsp"> <input
						type="hidden" name="target" value="db-admin.jsp"> <input
						type="hidden" id="action0" name="action" value="CREATE"> <input
						type="hidden" id="object0" name="object" value="CREATE">
					<h4>LOG TABLE</h4>
					<a href="#" onclick="javascript: updateAndSubmit0('CREATE', 'LOG')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('DROP', 'LOG')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('LOAD', 'LOG')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('CLEAN', 'LOG')">CLEAN</a> <br>
					<h4>USER TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit0('CREATE', 'USER')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('DROP', 'USER')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('LOAD', 'USER')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('CLEAN', 'USER')">CLEAN</a>
					<h4>BOLAO TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit0('CREATE', 'BOLAO')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('DROP', 'BOLAO')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('LOAD', 'BOLAO')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('CLEAN', 'BOLAO')">CLEAN</a>
					<h4>USER-BOLAO TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit0('CREATE', 'USER-BOLAO')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('DROP', 'USER-BOLAO')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('LOAD', 'USER-BOLAO')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit0('CLEAN', 'USER-BOLAO')">CLEAN</a>
					<br>
				</form>

				<form method="post" id="form1" action="Nba2016DBServlet">
					<input type="hidden" name="source" value="db-admin.jsp"> <input
						type="hidden" name="target" value="db-admin.jsp"> <input
						type="hidden" id="action1" name="action" value="CREATE"> <input
						type="hidden" id="object1" name="object" value="CREATE">
					<h4>NBA 2016 USER TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit('CREATE', 'NBA2016-USER')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('DROP', 'NBA2016-USER')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('LOAD', 'NBA2016-USER')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('CLEAN', 'NBA2016-USER')">CLEAN</a>
					<br>
					<h4>SERIES TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit('CREATE', 'NBA2016-SERIES')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('DROP', 'NBA2016-SERIES')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('LOAD', 'NBA2016-SERIES')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('CLEAN', 'NBA2016-SERIES')">CLEAN</a>
					<br>
					<h4>SERIES-BET TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit('CREATE', 'NBA2016-SERIES-BET')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('DROP', 'NBA2016-SERIES-BET')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('LOAD', 'NBA2016-SERIES-BET')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('CLEAN', 'NBA2016-SERIES-BET')">CLEAN</a>
					<br>
					<h4>GAME TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit('CREATE', 'NBA2016-GAME')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('DROP', 'NBA2016-GAME')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('LOAD', 'NBA2016-GAME')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('CLEAN', 'NBA2016-GAME')">CLEAN</a>
					<br>
					<h4>GAME-BET TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit('CREATE', 'NBA2016-GAME-BET')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('DROP', 'NBA2016-GAME-BET')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('LOAD', 'NBA2016-GAME-BET')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('CLEAN', 'NBA2016-GAME-BET')">CLEAN</a>
					<br>
					<h4>TIE-BREAKER TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit('CREATE', 'NBA2016-TIE-BREAKER')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('DROP', 'NBA2016-TIE-BREAKER')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('LOAD', 'NBA2016-TIE-BREAKER')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('CLEAN', 'NBA2016-TIE-BREAKER')">CLEAN</a>
					<br>
					<h4>MVP TABLE</h4>
					<a href="#"
						onclick="javascript: updateAndSubmit('CREATE', 'NBA2016-MVP')">CREATE</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('DROP', 'NBA2016-MVP')">DROP</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('LOAD', 'NBA2016-MVP')">LOAD</a>
					&nbsp;&nbsp;||&nbsp;&nbsp;<a href="#"
						onclick="javascript: updateAndSubmit('CLEAN', 'NBA2016-MVP')">CLEAN</a>
					<br>

				</form>
				<br>
				<hr>
				<h2>FREE TEXT SQL STATEMENT</h2>
				<br>
				<form method="post" action="DBServlet">
					<input type="hidden" name="action" value="EXECUTE">
					<textarea id="sql" name="sql" rows="8" cols="200">
</textarea>
					&nbsp;&nbsp;
					<button type="submit" class="change-button">Login</button>
				</form>

				<br>
				<hr>
				<h2>INSERT STATEMENTS</h2>
				<br>
				<h2>USERS</h2>
				<%
					{
							ArrayList<User> users = (ArrayList<User>) session.getAttribute(BaseSessionUtil.ALL_USERS);
							for (User entry : users) {
				%>
				<%=entry.toInsertString()%>
				<br>
				<%
					}
						}
				%>
				<hr>
				<br>
				<h2>BALLOTS</h2>
				<%
					{
							ArrayList<Bolao> users = (ArrayList<Bolao>) session.getAttribute(BaseSessionUtil.ALL_BALLOTS);
							for (Bolao entry : users) {
				%>
				<%=entry.toInsertString()%>
				<br>
				<%
					}
						}
				%>
				<hr>
				<br>
				<h2>REGISTERED USERS (USER-BALLOTS)</h2>
				<%
					{
							ArrayList<UserBolao> users = (ArrayList<UserBolao>) session
									.getAttribute(BaseSessionUtil.ALL_USER_BALLOTS);
							for (UserBolao entry : users) {
				%>
				<%=entry.toInsertString()%>
				<br>
				<%
					}
						}
				%>
				<hr>
				<br>
				<h2>NBA 2016 USERS</h2>
				<%
					{
							//					ArrayList<Nba2016User> users = (ArrayList<Nba2016User>) session.getAttribute(SessionUtil.ALL_USERS);
							//						for (Nba2016User entry : users) {
				%>
				<br>
				<%
					//}
						}
				%>
				<hr>

			</section>
		</div>

	</section>

	<%
		}
	%>
</body>
</html>
				 --%>