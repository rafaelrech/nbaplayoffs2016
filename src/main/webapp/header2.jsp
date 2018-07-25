<%@page import="rech.bolao.bean.User"%>
<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@page import="java.util.*"%>
<%@page import="nbaplayoffs2016.bean.*"%>
<%@page import="nbaplayoffs2016.util.*"%>
<%@page session="true"%>

<hgroup class="row header-row">
	<img width=38 height=63
		src="http://i.cdn.turner.com/nba/tmpl_asset/static/nba-cms3-homepage/1577/elements/global/nba-logo.png">
	<font size=+3><B>BOL&Atilde;O NBA PLAYOFFS 2016</B> </font>
</hgroup>
AA
<%=request.getParameter("currpage") %>
BB
<form method="post" action="ChangeUserInfo">

	<div class="row header-row">
		<section>
			<%
				if (((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)) != null) {
			%>
			Welcome <b><%=((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getUsername()%></b>
			!&nbsp;&nbsp;&nbsp; <a href="home.jsp">Minhas Apostas</a>&nbsp;&nbsp;||&nbsp;&nbsp;
			<!-- <a href="help.jsp" >Regras</a>&nbsp;&nbsp;||&nbsp;&nbsp; -->
			<a href="leaderboard.jsp">Leaderboard and Schedule</a>&nbsp;&nbsp;||&nbsp;&nbsp;
			<%
				if (((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getRole().equalsIgnoreCase("admin")) {
			%>
			<a href="home.jsp">New home</a>&nbsp;&nbsp;||&nbsp;&nbsp; <a
			<a href="dashboard.jsp">Dashboard</a>&nbsp;&nbsp;||&nbsp;&nbsp; <a
				href="db-admin.jsp">DB Admin</a>&nbsp;&nbsp;||&nbsp;&nbsp;
			<%
				}
			%>
			<a href="LogoutServlet">Logout</a>&nbsp;&nbsp;||&nbsp;&nbsp;
			&nbsp;&nbsp; New username: <input type='text' name='username'
				id='username' size='10'
				value='<%=((User) session.getAttribute(BaseSessionUtil.LOGGED_USER)).getUsername()%>'>
			&nbsp;&nbsp;New password: <input type='password' name='password'
				id='password' size='10' value=''> &nbsp;
			<button type="submit" class="change-button">Login</button>
			<%
				}
			%>
		</section>
	</div>
</form>
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

