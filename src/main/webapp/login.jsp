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
<style>
.label {
	font-size: larger;
}
input[type=password], input[type=text] {
    width: 100%;
    padding: 12px 20px;
    margin: 8px 0;
   	box-sizing: border-box;
	font-size: larger;
}
input[type=button], input[type=submit], input[type=reset] {

}
.lala {
	font-size: larger;
    border: none;
    color: white;
    padding: 16px 32px;
    text-decoration: none;
    margin: 4px 2px;
    cursor: pointer;
    background-color: #4CAF50;
}
</style>
</head>
<body>
	<div id="page-wrapper">

		<jsp:include page="header.jsp">
			<jsp:param name="currpage" value="login" />
		</jsp:include>

		<div id="main">
			<div class="container">
				<div class="row main-row">
					<div class="12u">
						<section>
							<h2>Credenciais</h2>
							<div>
								<div class="row">
									<p>Para <b>registrar um novo usuário</b>, utilize os campos abaixo
										para criar seu usernmae e senha</p>
								</div>
								<div class="row">
									<form method="post" action="LoginServlet" class="login">
										<input type="hidden" name="target" value="new_home">
										<input type="hidden" name="action" value="login">
										<p>
											<label for="login" class="label">User:</label> 
											<input type="text" name="login" id="login">
										</p>

										<p>
											<label for="password"" class="label">Password:</label> 
											<input type="password" name="password" id="password" >
										</p>

										<p>
											<input type="submit" value="Login" class="button"></input>
										</p>
									</form>
								</div>
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
