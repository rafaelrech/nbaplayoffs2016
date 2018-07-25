<%@page import="rech.bolao.bean.User"%>
<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@page import="java.util.*"%>
<%@page import="nbaplayoffs2016.bean.*"%>
<%@page import="nbaplayoffs2016.util.*"%>
<%@page session="true"%>



<%
String currpage = request.getParameter("currpage") != null ? request.getParameter("currpage") : "XOXOXOXOXOXOX";
%>
<div id="header-wrapper">
	<div class="container">
		<div class="row">
			<div class="12u">
				<header id="header">
					<h1><a href="#" id="logo"><img width=38 height=63 src="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png">RRBOL</a></h1>
					<nav id="nav">
						<a href="index.html" <%=(currpage.equals("home")?"class='current-page-item'":"") %>>Home</a>
						<a href="twocolumn2.html">Main-Bolao</a>
						<a href="threecolumn.html">Apostas</a>
						<a href="onecolumn.html" <%=(currpage.equals("leaderboard")?"class='current-page-item'":"") %>>Leaderboard</a>
						<a href="threecolumn.html">Dashboard</a>
						<a href="threecolumn.html">DBAdimn</a>
						<a href="twocolumn1.html">Perfil</a>
					</nav>
				</header>

			</div>
		</div>
	</div>
</div>


<%
	String msg = (String) session.getAttribute(BaseSessionUtil.MESSAGE);
	if (msg != null) {
%>
<div class="row">
	<section style='background-color: #edf5fa;'>
		<font color="#333" size="+1"><b><%=session.getAttribute(BaseSessionUtil.MESSAGE)%></b></font>
		<%
		BaseSessionUtil.clearSessionMessage(session);
		%>
	</section>
</div>
<%
	}
%>

