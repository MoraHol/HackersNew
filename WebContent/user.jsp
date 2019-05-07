<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="models.Session"%>
<%@ page import="java.util.Date"%>
<%
	Session sessionUser = (Session) session.getAttribute("sessionUser");
%>
<!DOCTYPE html>
<html op="user">
<head>
<meta name="referrer" content="origin">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="icon" href="favicon.ico">
<title>Profile: <%=sessionUser.getUser().getUserName()%> |
	Hacker News
</title>
</head>
<body>
	<center>
		<table id="hnmain" border="0" cellpadding="0" cellspacing="0"
			width="85%" bgcolor="#f6f6ef">
			<tr>
				<td bgcolor="#ff6600"><table border="0" cellpadding="0"
						cellspacing="0" width="100%" style="padding: 2px">
						<tr>
							<td style="width: 18px; padding-right: 4px"><a
								href="https://news.ycombinator.com"><img src="y18.gif"
									width="18" height="18" style="border: 1px white solid;"></a></td>
							<td style="line-height: 12pt; height: 10px;"><span
								class="pagetop"><b class="hnname"><a href="news">Hacker
											News</a></b> <a href="newest">new</a> | <a
									href="threads?id=alexis302000">threads</a> | <a href="front">past</a>
									| <a href="newcomments">comments</a> | <a href="ask">ask</a> |
									<a href="show">show</a> | <a href="jobs">jobs</a> | <a
									href="Submit">submit</a> </span></td>
							<td style="text-align: right; padding-right: 4px;"><span
								class="pagetop"> <a id='me'
									href="user?id= <%=sessionUser.getUser().getUserName()%>">
										<%=sessionUser.getUser().getUserName()%></a> (<%=sessionUser.getUser().getKarma()%>)
									| <a id='logout' href="/HackersNew">logout</a>
							</span></td>
						</tr>
					</table></td>
			</tr>
			<tr id="pagespace"
				title="Profile:  <%=sessionUser.getUser().getUserName()%>"
				style="height: 10px"></tr>
			<tr>
				<td><form class="profileform" method="post" action="/xuser">
						<input type="hidden" name="id" value="alexis302000"><input
							type="hidden" name="hmac"
							value="b41a1c1e3f1080f0a8722d3b6eb447a26c5af20b">
						<table border="0">
							<tr class='athing'>
								<td valign="top">user:</td>
								<td timestamp="1550616503"><a href="user?id=alexis302000"
									class="hnuser"> <%=sessionUser.getUser().getUserName()%></a></td>
							</tr>
							<tr>
								<td valign="top">created:</td>
								<%
									int dias = (int) ((new Date().getTime() - sessionUser.getUser().getCreatedAt().getTime()) / 86400000);
								%>
								<td><a href="front?day=2019-02-19&birth=alexis302000"><%=dias%>
										days ago</a></td>
							</tr>
							<tr>
								<td valign="top">karma:</td>
								<td><%=sessionUser.getUser().getKarma()%></td>
							</tr>
							<tr>
								<td valign="top">about:</td>
								<td><textarea cols="60" rows="5" wrap="virtual"
										name="about">
</textarea> <font size="-2"><a href="formatdoc" tabindex="-1"><font
											color="#afafaf">help</font></a></font></td>
							</tr>
							<tr>
								<td valign="top">email:</td>
								<td><input type="text" name="uemail"
									value="<%=sessionUser.getUser().getEmail()%>" size="60"></td>
							</tr>
							<tr>
								<td valign="top">showdead:</td>
								<td><select name="showd"><option>yes</option>
										<option selected>no</option></select></td>
							</tr>
							<tr>
								<td valign="top">noprocrast:</td>
								<td><select name="nopro"><option>yes</option>
										<option selected>no</option></select></td>
							</tr>
							<tr>
								<td valign="top">maxvisit:</td>
								<td><input type="text" name="maxv" value="20" size="16"></td>
							</tr>
							<tr>
								<td valign="top">minaway:</td>
								<td><input type="text" name="mina" value="180" size="16"></td>
							</tr>
							<tr>
								<td valign="top">delay:</td>
								<td><input type="text" name="delay" value="0" size="16"></td>
							</tr>
							<tr>
								<td></td>
								<td><a href="changepw"><u>change password</u></a></td>
							</tr>
							<tr>
								<td></td>
								<td><a
									href="submitted?id=<%=sessionUser.getUser().getUserName()%>"><u>submissions</u></a></td>
							</tr>
							<tr>
								<td></td>
								<td><a
									href="threads?id=<%=sessionUser.getUser().getUserName()%>"><u>comments</u></a></td>
							</tr>
							<tr>
								<td></td>
								<td><a href="hidden"><u>hidden</u></a></td>
							</tr>
							<tr>
								<td></td>
								<td><a
									href="upvoted?id=<%=sessionUser.getUser().getUserName()%>"><u>upvoted
											submissions</u></a> / <a
									href="upvoted?id=alexis302000&amp;comments=t"><u>comments</u></a>
									&nbsp;<span style='font-style: italic'>(private)</span></td>
							</tr>
							<tr>
								<td></td>
								<td><a
									href="favorites?id=<%=sessionUser.getUser().getUserName()%>"><u>favorite
											submissions</u></a> / <a
									href="favorites?id=alexis302000&amp;comments=t"><u>comments</u></a>
									&nbsp;<span style='font-style: italic'>(shared)</span></td>
							</tr>
						</table>
						<br> <input type="submit" value="update">
					</form> <br> <br></td>
			</tr>
		</table>
	</center>
	<%
		System.out.println(sessionUser.getUser().getCreatedAt());
	%>
</body>
</html>