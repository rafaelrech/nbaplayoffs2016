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
						<%-- 
						<a href="../main.jsp" <%=(currpage.equals("home")?"class='current-page-item'":"") %>>Home</a>
						--%>
						<a href="main.jsp" <%=(currpage.equals("home")?"class='current-page-item'":"") %>>NBA16 Playoffs</a>
						<a href="bets.jsp"  <%=(currpage.equals("bets")?"class='current-page-item'":"") %>>Apostas</a>
						<a href="leader.jsp" <%=(currpage.equals("leaderboard")?"class='current-page-item'":"") %>>Leaderboard</a>
			<%
				if (((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole().equalsIgnoreCase("admin")) {
			%>
						<a href="dashboard.jsp" <%=(currpage.equals("dashboard")?"class='current-page-item'":"") %>>Dashboard</a>
						<a href="db-admin" <%=(currpage.equals("dbadm")?"class='current-page-item'":"") %>>DB Adm</a>
			<%
				}
			%>
						<a href="../profile.jsp">Perfil</a>
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

