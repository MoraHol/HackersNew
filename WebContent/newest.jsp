<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
	<%@ page import="models.Session" %>
	<%Session sessionUser = (Session) request.getAttribute("session");
	session.setAttribute("sessionUser",sessionUser);%>
<!DOCTYPE html>
<html op="newest">
<head>
<meta name="referrer" content="origin">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="shortcut icon" href="favicon.ico">
<title>New Links | Hacker News</title>
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
								href="https://news.ycombinator.com"><img src="https://news.ycombinator.com/y18.gif"
									width="18" height="18" style="border: 1px white solid;"></a></td>
							<td style="line-height: 12pt; height: 10px;"><span
								class="pagetop"><b class="hnname"><a href="news">Hacker
											News</a></b> <span class="topsel"><a href="newest">new</a></span> | <a
									href="threads?id=<%=sessionUser.getUser().getUserName()%>">threads</a> | <a href="front">past</a>
									| <a href="newcomments">comments</a> | <a href="ask">ask</a> |
									<a href="show">show</a> | <a href="jobs">jobs</a> | <a
									href="Submit">submit</a> </span></td>
							<td style="text-align: right; padding-right: 4px;"><span
								class="pagetop"> <a id='me'
									href="user?id=<%=sessionUser.getUser().getUserName()%>"> <%=sessionUser.getUser().getUserName()%>
								</a> (<%= sessionUser.getUser().getKarma() %>) | <a id='logout' href="/HackersNew">logout</a>
							</span></td>
						</tr>
					</table></td>
			</tr>