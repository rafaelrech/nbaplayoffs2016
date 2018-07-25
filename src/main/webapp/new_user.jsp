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
			<jsp:param name="currpage" value="new_user" />
		</jsp:include>

		<div id="main">
			<div class="container">
				<div class="row main-row">
					<div class="12u">
						<section>
							<h2>Novo usuário?</h2>
							<div>
								<div class="row">
									<p>Utiize os botões abaixo para <b>confirmar o registro de um novo usuário</b>, ou para voltar a tela de login</p>
								</div>
								<div class="row">
									<form method="post" action="LoginServlet" class="login">
										<input type="hidden" name="target" value="new_home">
										<input type="hidden" id="action" name="action" value="register">
										
										<p>
											<button type="submit" >Registrar</button>&nbsp;
											<button type="submit" onclick="javascript:document.getElementById('action').value = 'go-back'; return true;">Retornar</button>&nbsp;
										</p>
									</form>
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
