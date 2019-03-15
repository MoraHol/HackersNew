<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<!DOCTYPE html>
<html>
<head>
<meta name="referrer" content="origin">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="shortcut icon" href="favicon.ico">
</head>
<body>
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
			<input type="text" value="false" style="display:none" name="register">
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
			<input type="text" value="true" style="display:none" name="register">
		</table>
		<br> <input type="submit" value="create account">
	</form>
</body>
</html>