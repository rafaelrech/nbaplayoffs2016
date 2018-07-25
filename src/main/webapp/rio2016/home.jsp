<%@page import="rech.bolao.bean.User"%>
<%@page import="rio2016.bean.Rio2016User"%>
<%@page import="java.util.ArrayList"%>
<%@page import="rio2016.util.Rio2016SessionUtil"%>
<%@ page session="true" %>
<%@ page import="rech.bolao.util.BaseSessionUtil"%>
<link rel="apple-touch-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
<link rel="shortcut icon" type="image/x-icon" href="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png" />
 
 



<!DOCTYPE HTML>
<html>
	<head>
		<title>RIO 2016</title>
		<meta charset="utf-8" />
		<meta name="viewport" content="width=device-width, initial-scale=1" />
		<!--[if lte IE 8]><script src="assets/js/ie/html5shiv.js"></script><![endif]-->
		<link rel="stylesheet" href="assets/css/main.css" />
		<!--[if lte IE 9]><link rel="stylesheet" href="assets/css/ie9.css" /><![endif]-->
	</head>
	<body>
		<div id="page-wrapper">
			
			<jsp:include page="header.jsp">
				<jsp:param name="currpage" value="home" />
			</jsp:include>

			<div id="banner-wrapper">
				<div class="container">

					<div id="banner">
						<img height="265" width="1160" src="http://www.fina.org/sites/default/files/slider-images/hp_banner_rio2016.jpg">
					</div>
				</div>
			</div>
			<div id="main">
				<div class="container">
					<div class="row main-row">
						<%
						User u = (User) session.getAttribute(BaseSessionUtil.LOGGED_USER);
						if (u != null) {
						%>
						<div class="4u 12u(mobile)">
							<section>
								<%
									if(u.getRole().equalsIgnoreCase("admin") || BaseSessionUtil.isUserEnrolled(session, "Rio 2016")) {
								%>
									<h2>Você já está registrado!</h2>
									<p>Você ainda pode cancelar sua participação até 1 dia antes de começarem as competições através do botão abaixo!</p>
									<p>Utilize o menu no topo para navegar para a página de apostas</p>
									<p><a href="../RegisterServlet?ballot=Rio%202016" class="button">Sair do Bolão</a></p>
								<%
									} else {
								%>
									<h2>Você ainda não está registrado!</h2>
									<p>Não perca mais tempo!</p>
									<p><a href="../RegisterServlet?ballot=Rio%202016" class="button">Registre-se</a></p>
								<%
									}
								%>
							</section>
						</div>
						<%
						}
						%>
						<div class="4u 12u(mobile)">
							<section>
								<h2>Regras</h2>
								<p>
								Os participantes apostarão nos medalhistas (ouro, prata, bronze) de diversas modalidades. 
								As apostas deverão ser realizadas até o dia anterior ao início de cada modalidade.
								</p>
								<h4>Pontuação</h4>
								<ul>
								<li>Acertar o medalhista de ouro: 100 pts</li>
								<li>Acertar o medalhista de prata: 80 pts</li>
								<li>Acertar o medalhista de bronze: 60 pts</li>
								<li>Acertar um medalhista sem acertar a posição correta: 35 pts</li>
								</ul>
								<p>
								Ao final das olimpíadas, o participante com maior pontuação será declarado campeão.
								</p>
								<p>
								O valor para participar do bolão é de R$25,00 não necessitando pagar adiantado. Ao final do bolão o campeão receberá 60% do arrecadado, o vice receberá 30%, e o terceiro colocado, 10%.<br>
								Nesse momento os particpantes irão fazer o pagamento das suas taxas de participação diretamente aos vencedores.
								</p>
							</section>
						</div>
						<div class="4u 12u(mobile)">
							<section>
								<h2>Classificação</h2>
									<%
										ArrayList<Rio2016User> users = (ArrayList<Rio2016User>) session.getAttribute(Rio2016SessionUtil.ALL_USERS);
										if(users!= null && users.size()>0) {
											int count = 0;
											for (Rio2016User user : users) {
												count ++;
												if(count==1) {
												%><span style='font-size:larger'><%=user.getUser().getUsername() %> <%=user.getScore() %> pts</span><br><% 
												} else {
												%><%=user.getUser().getUsername() %> <%=user.getScore() %> pts<br><% 
												}
											}
										} else {
											%>
											<p><h4>Nenhum usuário registrado no momento.</h4></p>
											<p>Seja o primeiro a <a href="../RegisterServlet">registrar-se</a>
											<%
										}
									%>
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
<%--
} 
--%>

