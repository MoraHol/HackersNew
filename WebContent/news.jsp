<%@page import="jdk.internal.org.objectweb.asm.tree.TryCatchBlockNode"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hackersnews.model.Session"%>
<%@ page import="com.hackersnews.model.Notice"%>
<%@ page import="java.util.ArrayList"%>
<%@ page import="com.hackersnews.controller.NoticeController"%>
<%@ page import="com.hackersnews.dao.NoticeDaoImpl"%>
<%@ page import="com.hackersnews.dao.CommentDaoImpl"%>
<%@ page import="java.net.URLEncoder"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Hackers News</title>
<link rel="shortcut icon" href="favicon.ico">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/index.css">
</head>
<body>
<center>
	<%
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		if (sessionUser != null) {
	%>
	<table id="hnmain" border="0" cellpadding="0" cellspacing="0"
		width="85%" bgcolor="#f6f6ef">
		<tr>
			<td bgcolor="#ff6600"><table border="0" cellpadding="0"
					cellspacing="0" width="100%" style="padding: 2px">
					<tr>
						<td style="width: 18px; padding-right: 4px"><a
							href="/HackersNew"><img src="y18.gif" width="18" height="18"
								style="border: 1pxwhitesolid;"></a></td>
						<td style="line-height: 12pt; height: 10px;"><span
							class="pagetop"><b class="hnname"><a href="news\">Hacker
										News</a></b><span class="topsel"><a href="newest">new</a></span> | <a
								href="threads?id=<%=sessionUser.getUser().getUserName()%>">threads</a>
								| <a href="front">past</a> | <a href="newcomments">comments</a>
								| <a href="ask">ask</a> | <a href="show">show</a> | <a
								href="jobs">jobs</a> | <a href="Submit">submit</a> </span></td>
						<td style="text-align: right; padding-right: 4px;"><span
							class="pagetop"><a id='me'
								href="user?id=<%=sessionUser.getUser().getUserName()%>"> <%=sessionUser.getUser().getUserName()%></a>(<%=sessionUser.getUser().getKarma()%>)
								| <a id='logout' href="/HackersNew">logout</a> </span></td>
					</tr>
				</table></td>
		</tr>
		<%
			} else {
		%>
		<table id="hnmain" border="0" cellpadding="0" cellspacing="0"
			width="85%" bgcolor="#f6f6ef">
			<tr>
				<td bgcolor="#ff6600"><table border="0" cellspacing="0"
						width="100%" style="padding: 2px">
						<tr>
							<td style="width: 18px; padding-right: 4px"><a
								href="https://news.ycombinator.com"><img src="y18.gif"
									width="18" height="18" style="border: 1pxwhitesolid;"></a></td>
							<td style="line-height: 12pt; height: 10px;"><span
								class="pagetop"><b class="hnname"><a href="news">Hacker
											News</a></b><span class="topsel"><a href="newest">new</a></span> | <a
									href="front">past</a> | <a href="newcomments">comments</a> | <a
									href="ask">ask</a> | <a href="show">show</a> | <a href="jobs">jobs</a>
									| <a href="Submit">submit</a> </span></td>
							<td style="text-align: right; padding-right: 4px;"><span
								class="pagetop"><a href="Login">login</a> </span></td>
						</tr>
					</table></td>
			</tr>
			<%
				}
			%>
			<tr id="pagespace" title="New Links" style="height: 10px" />
			<%
				try {
							ArrayList<Notice> noticeNewest = NoticeController.getNoticesNewest();
			%>
			</tr>
			<tr>
				<td><table border="0" cellpadding="0" cellspacing="0"
						class="itemlist">
						<tr class='athing' id="<%=noticeNewest.get(0).getId()%>">
							<%
								int counter = 1;
																			for (Notice notice : noticeNewest) {
							%>
							<td align="right" valign="top" class="title"><span
								class="rank"><%=counter%> </span></td>
							<td valign="top" class="votelinks"><center>
									<a id="up_<%=notice.getId()%>"
										href="vote?id=<%=notice.getId()%>&amp;how=up&amp;goto=newest"><div
											class="votearrow" title="upvote"></div></a>
								</center></td>
							<td class="title"><a href="<%=notice.getUrl()%>"
								class="storylink" rel="nofollow"><%=notice.getTitle()%> </a><span
								class="sitebit comhead"> (<a
									href="from?site=<%=notice.getDomainUrl()%>"><span
										class="sitestr"><%=notice.getDomainUrl()%> </span></a>)
							</span></td>
						</tr>
						<tr>
							<td colspan="2"></td>
							<td class="subtext"><span class="score"
								id="score_<%=notice.getId()%>"><%=NoticeDaoImpl.findPointsByNotice(notice)%>
									point</span> by <a href="user?id=<%=notice.getUser().getUserName()%>"
								class="hnuser"><%=notice.getUser().getUserName()%></a> <span
								class="age"><a href="item?id=<%=notice.getId()%>"> <%=notice.getAge()%></a></span>
								<span id="unv_<%=notice.getId()%>"></span> | <a
								href="hide?id=<%=notice.getId()%>&amp;goto=newest">hide</a> | <a
								href="https://hn.algolia.com/?query=<%=URLEncoder.encode(notice.getTitle(), "utf-8")%>&sort=byDate&dateRange=all&type=story&storyText=false&prefix&page=0"
								class="hnpast">past</a> | <a
								href="https://www.google.com/search?q=
						<%=URLEncoder.encode(notice.getTitle(), "utf-8")%>">web</a>
								| <%
									if (CommentDaoImpl.getCommentsByNotice(notice.getId()).size() == 0) {
								%> <a href="item?id=<%=notice.getId()%>">discuss</a></td>
						</tr>
						<%
							} else {
						%>
						<a href="item?id=<%=notice.getId()%>"><%=CommentDaoImpl.getCommentsByNotice(notice.getId()).size()%>
							comments</a>
						</td>
						</tr>
						<%
							}

									counter++;
								}
						%>
					</table></td>
			</tr>
			<%
				} catch (Exception e) {

				}
			%>
			<tr>
				<td><img src="s.gif" height="10" width="0">
				<table width="100%" cellspacing="0" cellpadding="1">
						<tbody>
							<tr>
								<td bgcolor="#ff660"></td>
							</tr>
						</tbody>
					</table>
			<br>
			<center>
				<a href="https://www.ycombinator.com/apply/">Applications are
					open for YC Summer 2019</a>
			</center>
			<br>
			<center>
				<span class="yclinks"><a href="newsguidelines.html">Guidelines</a>
					| <a href="newsfaq.html">FAQ</a> | <a
					href="mailto:hn@ycombinator.com">Support</a> | <a
					href="https://github.com/HackerNews/API">API</a> | <a
					href="security.html">Security</a> | <a href="lists">Lists</a> | <a
					href="bookmarklet.html" rel="nofollow">Bookmarklet</a> | <a
					href="http://www.ycombinator.com/legal/">Legal</a> | <a
					href="http://www.ycombinator.com/apply/">Apply to YC</a> | <a
					href="mailto:hn@ycombinator.com">Contact</a></span><br>
				<br>
				<form method="get" action="//hn.algolia.com/">
					Search: <input type="text" name="q" value="" size="17" spellcheck="false" 
						autocomplete="false">
				</form>
			</center>
			</td>
			</tr>
</body>
</html>