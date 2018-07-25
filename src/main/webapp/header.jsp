<%@page import="rech.bolao.bean.Bolao"%>
<%@page import="rech.bolao.bean.User"%>
<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@page import="java.util.*"%>
<%@page import="nbaplayoffs2016.bean.*"%>
<%@page import="nbaplayoffs2016.util.*"%>
<%@page session="true"%>



<%
String currpage = request.getParameter("currpage") != null ? request.getParameter("currpage") : "XOXOXOXOXOXOX";
ArrayList<Bolao> entries = (ArrayList<Bolao>) session.getAttribute(BaseSessionUtil.ALL_BALLOTS);
if(entries == null || entries.size() == 0) {
	BaseSessionUtil.udpateSessionInfo(session, null);
}
%>
<div id="header-wrapper">
	<div class="container">
		<div class="row">
			<div class="12u">
				<header id="header">
					<h1><a href="home.jsp" id="logo">BOLOES</a></h1>
					<nav id="nav">
				<%
				if (session.getAttribute(BaseSessionUtil.LOGGED_USER) == null) {
				%>
					<a href="login.jsp">Entrar / Registrar</a>
				<%
				} else {		
					
					
					
					
					
					
					if (((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole().equalsIgnoreCase("admin")) {
				%>
					<a href="dashboard.jsp" <%=(currpage.equals("dashboard")?"class='current-page-item'":"") %>>Dashboard</a>
					<a href="db-admin.jsp" <%=(currpage.equals("dbadm")?"class='current-page-item'":"") %>>DB Adm</a>
				<%
					}
				%>
					<a href="profile.jsp">Perfil [<%= ((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getUsername().toLowerCase() %>]</a>
					
					<a href="LogoutServlet">Desconectar</a>
				<%
				}
				%>
					</nav>
				</header>
			</div>
		</div>
		
		<%
			String msg = (String) session.getAttribute(BaseSessionUtil.MESSAGE);
			if (msg != null) {
		%>
		<div class="row">
			<div class="12u">
					<h4><%=msg%></h4>
			</div>
		</div>
		<%
				BaseSessionUtil.clearSessionMessage(session);
			}
		%>

	</div>
</div>

