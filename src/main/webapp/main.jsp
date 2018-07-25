<%@ page session="true" %>
 
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

	 	<%--@ include file="header.jsp" --%>
	 	
	 	
 		<div class="row">
 				<%@ include file="bracket2.jsp" %>
 				<HR>
 			 	<%@ include file="games.jsp" %>
<%--
			<section class='scol-sm-6 col-md-6'>
			</section>
			<section class='col-xs-12 col-sm-6 col-md-6'>
			</section>
 --%> 				
		</div>
	
	</section>
<%
} 
%>
</body>
</html>