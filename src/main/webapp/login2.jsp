<%@page import="rech.bolao.util.BaseSessionUtil"%>
<%@ page session="true"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Login do Bolão</title>
<!-- link rel="stylesheet" href="css/style.css"> -->
<link rel="stylesheet" href="css/bolao.css">
<link href='https://fonts.googleapis.com/css?family=Open+Sans' rel='stylesheet' type='text/css'>
<script src="http://code.jquery.com/jquery-1.8.3.min.js"></script>
<script src="http://underscorejs.org/underscore-min.js"></script>
</head>
<body>
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
	<section class='container'>

		<%--@ include file="header.jsp"--%>

		<div class="row">
			<section class='scol-sm-6 col-md-6'>
			<BR>
				<form method="post" action="LoginServlet" class="login">
				<input type="hidden" name="target" value="new_home">
					<p>
						<label for="login">Email/User:</label> <input type="text"
							name="login" id="login" value="name@example.com">
					</p>

					<p>
						<label for="password">Password:</label> <input type="password"
							name="password" id="password" value="jajaja">
					</p>

					<p class="login-submit">
						<button type="submit" class="login-button">Login</button>
					</p>
				</form>

<BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR><BR>
			</section>
		</div>

	</section>

</body>
</html>
