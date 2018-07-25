<%@page import="rech.bolao.dao.LoggingDao"%>
<%@page import="rech.bolao.bean.LogEntry"%>
<%@ page session="true" %>
<%@ page import="nbaplayoffs2016.dao.*" %>
 
<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Bolão NBA - LOGS</title>
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
	    response.sendRedirect("index.jsp");
	}
	else 
	{
%>

 	<section class='container'>

 	<%@ include file="header.jsp" %>

 		<div class="row">
			<section>
 		

 	<h2>LOGS</h2>			
 	<% 
	ArrayList<LogEntry> logs = (ArrayList<LogEntry>) LoggingDao.getInstance().listAll();
	for (LogEntry entry : logs) {
		%>
		<%=entry.toJson()%>
		<br>
		<%
	}
	%>
 			 				
			</section>
		</div>

	</section>

<%
}
%>
</body>
</html>