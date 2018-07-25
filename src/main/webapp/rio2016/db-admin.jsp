<%@page import="rech.bolao.bean.CommonBean"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rio2016.util.Rio2016DBUtil"%>
<%@ page session="true" %>
<%@ page import="rech.bolao.util.BaseSessionUtil"%>
<link rel="apple-touch-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link rel="shortcut icon" type="image/x-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
 
 



<!DOCTYPE HTML>
<html>
	<head>
		<title>RRBOL</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
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
								String[] beans = Rio2016DBUtil.beans;
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
											ArrayList<CommonBean> entries = (ArrayList<CommonBean>) Rio2016DBUtil.getDaoFromBean(bean).listAll();
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
								<p>Duis neque nisi, dapibus sed mattis quis, rutrum accumsan sed.
								Suspendisse eu varius nibh. Suspendisse vitae magna eget odio amet
								mollis justo facilisis quis. Sed sagittis mauris amet tellus gravida
								lorem ipsum dolor sit amet consequat blandit.</p>
								<footer class="controls">
									<a href="#" class="button">Oh, please continue ....</a>
								</footer>
							</section>

						</div>
					</div>
					<div class="row">
						<div class="12u">

							<div id="copyright">
								&copy; Untitled. All rights reserved. | Design: <a href="http://html5up.net">HTML5 UP</a>
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
<%
} 
%>


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