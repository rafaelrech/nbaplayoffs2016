<%@page import="rech.bolao.bean.User"%>
<%@page import="java.util.ArrayList"%>
<%@ page session="true" %>
<%@ page import="rech.bolao.util.BaseSessionUtil"%>
<link rel="apple-touch-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link rel="shortcut icon" type="image/x-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
 
 

<!DOCTYPE HTML>
<html>
	<head>
		<title>NBA 2016 PLAYOFFS</title>
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
	<body>
		<div id="page-wrapper">
			
			<jsp:include page="header.jsp">
				<jsp:param name="currpage" value="bets" />
			</jsp:include>

			<div id="main">
				<div class="container">
					<div class="row main-row">
						<div class="6u 12u(mobile)">

							<section>
							
							<h2>OTHER BRACKETS</h2>

<%
ArrayList<User> users = (ArrayList<User>) session.getAttribute(BaseSessionUtil.ALL_USERS);
for (User entry : users) {
	if(!entry.getRole().equalsIgnoreCase("admin")) {
		out.println("<a href='/nbaplayoffs2016/main.jsp?user=" + entry.getId() + "'>" + entry.getUsername() + "</a>&nbsp;&nbsp;||&nbsp;");
	}
}
%>
							</section>

						</div>
						<div class="6u 12u(mobile)">


								<h2>An assortment of pictures and text</h2>
								<p>Duis neque nisi, dapibus sed mattis quis, rutrum et accumsan.
								Suspendisse nibh. Suspendisse vitae magna eget odio amet mollis
								justo facilisis quis. Sed sagittis mauris amet tellus gravida
								lorem ipsum dolor sit amet consequat blandit lorem ipsum dolor
								sit amet consequat sed dolore.</p>
								<ul class="big-image-list">
									<li>
										<a href="#"><img src="images/pic3.jpg" alt="" class="left" /></a>
										<h3>Magna Gravida Dolore</h3>
										<p>Varius nibh. Suspendisse vitae magna eget et amet mollis justo
										facilisis amet quis consectetur in, sollicitudin vitae justo. Cras
										Maecenas eu arcu purus, phasellus fermentum elit.</p>
									</li>
									<li>
										<a href="#"><img src="images/pic4.jpg" alt="" class="left" /></a>
										<h3>Magna Gravida Dolore</h3>
										<p>Varius nibh. Suspendisse vitae magna eget et amet mollis justo
										facilisis amet quis consectetur in, sollicitudin vitae justo. Cras
										Maecenas eu arcu purus, phasellus fermentum elit.</p>
									</li>
									<li>
										<a href="#"><img src="images/pic5.jpg" alt="" class="left" /></a>
										<h3>Magna Gravida Dolore</h3>
										<p>Varius nibh. Suspendisse vitae magna eget et amet mollis justo
										facilisis amet quis consectetur in, sollicitudin vitae justo. Cras
										Maecenas eu arcu purus, phasellus fermentum elit.</p>
									</li>
								</ul>
							</section>

						</div>
						<div class="6u 12u(mobile)">

							<article class="blog-post">
								<h2>Just another blog post</h2>
								<a class="comments" href="#">33 comments</a>
								<a href="#"><img src="images/pic6.jpg" alt="" class="top blog-post-image" /></a>
								<h3>Magna Gravida Dolore</h3>
								<p>Aenean non massa sapien. In hac habitasse platea dictumst.
								Maecenas sodales purus et nulla sodales aliquam. Aenean ac
								porttitor metus. In hac habitasse platea dictumst. Phasellus
								blandit turpis in leo scelerisque mollis. Nulla venenatis
								ipsum nec est porta rhoncus. Mauris sodales sed pharetra
								nisi nec consectetur. Cras elit magna, hendrerit nec
								consectetur in, sollicitudin vitae justo. Cras amet aliquet
								Aliquam ligula turpis, feugiat id fermentum malesuada,
								rutrum eget turpis. Mauris sodales sed pharetra nisi nec
								consectetur. Cras elit magna, hendrerit nec consectetur
								in sollicitudin vitae.</p>
								<footer class="controls">
									<a href="#" class="button">Continue Reading</a>
								</footer>
							</article>

						</div>
					</div>
				
					<div class="row main-row">
						<div class="4u 12u(mobile)">

							<section>
								<h2>Welcome to Minimaxing!</h2>
								<p>This is <strong>Minimaxing</strong>, a fully responsive HTML5 site template designed by <a href="http://twitter.com/ajlkn">AJ</a> and released for free by <a href="http://html5up.net">HTML5 UP</a>. It features
								a simple, lightweight design, solid HTML5 and CSS3 code, and full responsive support for desktop, tablet, and mobile displays.</p>
								<footer class="controls">
									<a href="http://html5up.net" class="button">More cool designs ...</a>
								</footer>
							</section>

						</div>
						<div class="4u 12u(mobile)">

							<section>
								<h2>Who are you guys?</h2>
								<ul class="small-image-list">
									<li>
										<a href="#"><img src="images/pic2.jpg" alt="" class="left" /></a>
										<h4>Jane Anderson</h4>
										<p>Varius nibh. Suspendisse vitae magna eget et amet mollis justo facilisis amet quis.</p>
									</li>
									<li>
										<a href="#"><img src="images/pic1.jpg" alt="" class="left" /></a>
										<h4>James Doe</h4>
										<p>Vitae magna eget odio amet mollis justo facilisis amet quis. Sed sagittis consequat.</p>
									</li>
								</ul>
							</section>

						</div>
						<div class="4u 12u(mobile)">

							<section>
								<h2>How about some links?</h2>
								<div>
									<div class="row">
										<div class="6u 12u(mobile)">
											<ul class="link-list">
												<li><a href="#">Sed neque nisi consequat</a></li>
												<li><a href="#">Dapibus sed mattis blandit</a></li>
												<li><a href="#">Quis accumsan lorem</a></li>
												<li><a href="#">Suspendisse varius ipsum</a></li>
												<li><a href="#">Eget et amet consequat</a></li>
											</ul>
										</div>
										<div class="6u 12u(mobile)">
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
					</div>
				</div>
			</div>
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