<%@page import="rech.bolao.bean.UserBolao"%>
<%@page import="rech.bolao.bean.Bolao"%>
<%@page import="java.util.ArrayList"%>
<%@ page session="true"%>
<%@ page import="rech.bolao.util.BaseSessionUtil"%>
<link rel="apple-touch-icon"
	href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link rel="shortcut icon" type="image/x-icon"
	href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />





<!DOCTYPE HTML>
<html>
<head>
<title>BOLAO-RECH</title>
<meta charset="utf-8" />
<meta name="viewport" content="width=device-width, initial-scale=1" />
<!--[if lte IE 8]><script src="nba2016-mm/assets/js/ie/html5shiv.js"></script><![endif]-->
<link rel="stylesheet" href="nba2016-mm/assets/css/main.css" />
<!--[if lte IE 9]><link rel="stylesheet" href="nba2016-mm/assets/css/ie9.css" /><![endif]-->
</head>
<body>
	<div id="page-wrapper">

		<jsp:include page="header.jsp">
			<jsp:param name="currpage" value="home" />
		</jsp:include>

		<div id="banner-wrapper">
			<div class="container">

				<div id="banner">
						<img height="265" width="1160" src="http://www.teachingideas.co.uk/sites/default/files/styles/718w/public/bannerolympics2016.jpg?itok=2IKSWTzP">
				</div>

			</div>
		</div>
		<div id="main">
			<div class="container">
				<div class="row main-row">
					<div class="4u 12u(mobile)">
						<section>
							<h2>Bolões disponíveis</h2>
							<div>
								<div class="row">
									<ul class="link-list">
										<%
											{
													ArrayList<Bolao> entries = (ArrayList<Bolao>) session.getAttribute(BaseSessionUtil.ALL_BALLOTS);
													for (Bolao entry : entries) {
														if (entry.getAvailable() == 1) {
										%>
										<li><a href="./<%=entry.getFolderName()%>/home.jsp" class="button"><%=entry.getDescription()%></a></li>

										<%
											}
													}
												}
										%>

									</ul>
								</div>
							</div>

							<p>
								Clique no link acima para ver a página do bolão. Se ainda não
								houver se registrado, o botão para <strong>participar</strong>,
								estará disponível.
							</p>

						</section>

					</div>
					<div class="4u 12u(mobile)">

							<h2>Bolões em andamento</h2>

										<%
											{
													ArrayList<Bolao> entries = (ArrayList<Bolao>) session.getAttribute(BaseSessionUtil.ALL_BALLOTS);
													for (Bolao entry : entries) {
														if (entry.getAvailable() == 0 && entry.getCompleted() == 0) {
										%>
						<section>
										<li><a href="./<%=entry.getFolderName()%>/home.jsp"><%=entry.getDescription()%></a></li>
										<%
										int count = 0;
										for (UserBolao ub : entry.getRegisteredUsers()) {
											count ++;
											if(count==1) {
											%><p><h4><%=ub.getUser().getUsername() %> <%=ub.getUserScore() %> pts</h4></p><% 
											} else {
											%><%=ub.getUser().getUsername() %> <%=ub.getUserScore() %> pts<br><% 
											}
										}
											}
						%>
						</section>
						<%
													}
												}
										%>

					</div>
					<div class="4u 12u(mobile)">

							<h2>Bolões finalizados</h2>

										<%
											{
													ArrayList<Bolao> entries = (ArrayList<Bolao>) session.getAttribute(BaseSessionUtil.ALL_BALLOTS);
													for (Bolao entry : entries) {
														if (entry.getCompleted() == 1) {
										%>
						<section>
										<h3><%=entry.getDescription()%></h3>
										<%
										int count = 0;
										for (UserBolao ub : entry.getRegisteredUsers()) {
											count ++;
											if(count==1) {
											%><p><h4><%=ub.getUser().getUsername() %> <%=ub.getUserScore() %> pts</h4></p><% 
											} else {
											%><%=ub.getUser().getUsername() %> <%=ub.getUserScore() %> pts<br><% 
											}
										}
											}
						%>
						</section>
						<%
													}
												}
										%>

					</div>
				</div>
			</div>
		</div>
		<%--
		<div id="footer-wrapper">
			<div class="container">
				<div class="row">
					<div class="8u 12u(mobile)">

						<section>
							<h2>How about a truckload of links?</h2>
							<div>
								<div class="row">
									<div class="3u 12u(mobile)">
										<ul class="link-list">
											<li><a href="#">Sed neque nisi consequat</a></li>
											<li><a href="#">Dapibus sed mattis blandit</a></li>
											<li><a href="#">Quis accumsan lorem</a></li>
											<li><a href="#">Suspendisse varius ipsum</a></li>
											<li><a href="#">Eget et amet consequat</a></li>
										</ul>
									</div>
									<div class="3u 12u(mobile)">
										<ul class="link-list">
											<li><a href="#">Quis accumsan lorem</a></li>
											<li><a href="#">Sed neque nisi consequat</a></li>
											<li><a href="#">Eget et amet consequat</a></li>
											<li><a href="#">Dapibus sed mattis blandit</a></li>
											<li><a href="#">Vitae magna sed dolore</a></li>
										</ul>
									</div>
									<div class="3u 12u(mobile)">
										<ul class="link-list">
											<li><a href="#">Sed neque nisi consequat</a></li>
											<li><a href="#">Dapibus sed mattis blandit</a></li>
											<li><a href="#">Quis accumsan lorem</a></li>
											<li><a href="#">Suspendisse varius ipsum</a></li>
											<li><a href="#">Eget et amet consequat</a></li>
										</ul>
									</div>
									<div class="3u 12u(mobile)">
										<ul class="link-list">
											<li><a href="#">Quis accumsan lorem</a></li>
											<li><a href="#">Sed neque nisi consequat</a></li>
											<li><a href="#">Eget et amet consequat</a></li>
											<li><a href="#">Dapibus sed mattis blandit</a></li>
											<li><a href="#">Vitae magna sed dolore</a></li>
										</ul>
									</div>
								</div>
							</div>
						</section>

					</div>
					<div class="4u 12u(mobile)">

						<section>
							<h2>Something of interest</h2>
							<p>Duis neque nisi, dapibus sed mattis quis, rutrum accumsan
								sed. Suspendisse eu varius nibh. Suspendisse vitae magna eget
								odio amet mollis justo facilisis quis. Sed sagittis mauris amet
								tellus gravida lorem ipsum dolor sit amet consequat blandit.</p>
							<footer class="controls">
								<a href="#" class="button">Oh, please continue ....</a>
							</footer>
						</section>

					</div>
				</div>
				<div class="row">
					<div class="12u">

						<div id="copyright">
							&copy; Untitled. All rights reserved. | Design: <a
								href="http://html5up.net">HTML5 UP</a>
						</div>

					</div>
				</div>
			</div>
		</div>
		 --%>
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
<%--
	}
--%>


<%--
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
	if (session.getAttribute(BaseSessionUtil.LOGGED_USER) == null){
	    response.sendRedirect("login.jsp");
	}
	else 
	{
%>
 	<section class='container'>
	<jsp:include page="header.jsp">
		<jsp:param name="currpage" value="home" />
	</jsp:include>
 		<div class="row">
 				<%@ include file="bracket.jsp" %>
 				<HR>
 			 	<%@ include file="games.jsp" %>
		</div>
	</section>
<%
} 
%>
</body>
</html>
  --%>