<%@ page session="true"%>

<!doctype html>
<html lang="en">
<head>
<meta charset="utf-8">
<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
<title>Login do Bolao</title>
<!-- link rel="stylesheet" href="css/style.css"> -->
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
	<section class='container'>

		<%@ include file="header.jsp"%>

		<div class="row">
			<section class='scol-sm-6 col-md-6'>
			<BR>
				<form method="post" action="LoginServlet" class="login">
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
