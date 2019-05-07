<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="referrer" content="origin">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico">
<link
	href="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/css/bootstrap.min.css"
	rel="stylesheet" id="bootstrap-css">
<script
	src="//maxcdn.bootstrapcdn.com/bootstrap/3.3.0/js/bootstrap.min.js"></script>
<script src="//code.jquery.com/jquery-1.11.1.min.js"></script>
<link href="css/login.css" rel="stylesheet">
</head>
<body>
	<div class="container">
		<div class="row">
			<div class="col-md-6 col-md-offset-3">
				<div class="panel panel-login">
					<div class="panel-heading">
						<div class="row">
							<div class="col-xs-6">
								<a href="#" class="active" id="login-form-link">Login</a>
							</div>
							<div class="col-xs-6">
								<a href="#" id="register-form-link">Register</a>
							</div>
						</div>
						<hr>
					</div>
					<div class="panel-body">
						<div class="row">
							<div class="col-lg-12">
								<form id="login-form" action="Login" method="post" role="form"
									style="display: block;">
									<div class="form-group">
										<input type="text" name="acct" id="username" tabindex="1"
											class="form-control" placeholder="Username" value="">
									</div>
									<div class="form-group">
										<input type="password" name="pw" id="password" tabindex="2"
											class="form-control" placeholder="Password">
									</div>
									<input type="text" value="false" style="display: none"
										name="register">
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="login-submit" id="login-submit"
													tabindex="4" class="form-control btn btn-login"
													value="Log In">
											</div>
										</div>
									</div>
								</form>
								<form id="register-form"
									action="Login" method="post"
									role="form" style="display: none;">
									<div class="form-group">
										<input type="text" name="acct" id="username" tabindex="1"
											class="form-control" placeholder="Username" value="">
									</div>
									
									<div class="form-group">
										<input type="password" name="pw" id="password"
											tabindex="2" class="form-control" placeholder="Password">
									</div>
									<input type="text" value="true" style="display: none" name="register">
									<div class="form-group">
										<div class="row">
											<div class="col-sm-6 col-sm-offset-3">
												<input type="submit" name="register-submit"
													id="register-submit" tabindex="4"
													class="form-control btn btn-register" value="Register Now">
											</div>
										</div>
									</div>
								</form>
							</div>
						</div>
					</div>
				</div>
			</div>
		</div>
	</div>
	<!-- 
	<b>Login</b>
	<br>
	<br>
	<form method="post" action="Login">
		<table border="0">
			<tr>
				<td>username:</td>
				<td><input type="text" name="acct" size="20" autocorrect="off"
					spellcheck="false" autocapitalize="off" autofocus="true"></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><input type="password" name="pw" size="20"></td>
			</tr>
			<input type="text" value="false" style="display: none"
				name="register">
		</table>
		<br> <input type="submit" value="login">
	</form>
	<a href="forgot">Forgot your password?</a>
	<br>
	<br>
	<b>Create Account</b>
	<br>
	<br>
	<form method="post" action="Login">
		<table border="0">
			<tr>
				<td>username:</td>
				<td><input type="text" name="acct" size="20" autocorrect="off"
					spellcheck="false" autocapitalize="off"></td>
			</tr>
			<tr>
				<td>password:</td>
				<td><input type="password" name="pw" size="20"></td>
			</tr>
			<input type="text" value="true" style="display: none" name="register">
		</table>
		<br> <input type="submit" value="create account">
	</form>
	S-->
	<script type="text/javascript" src="login.js"></script>
</body>
</html>