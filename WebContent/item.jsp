<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<%@ page import="com.hackersnews.model.Session"%>
<%@ page import="com.hackersnews.model.Notice"%>
<%@ page import="com.hackersnews.controller.NoticeController"%>
<%@ page import="com.hackersnews.dao.CommentDao"%>
<%@ page import="com.hackersnews.model.Comment"%>
<%@ page import="java.net.URLEncoder"%>
<%@ page import="com.hackersnews.dao.NoticeDao"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<%
		Notice notice = NoticeController.searchNotice(Integer.parseInt(request.getParameter("id")));
		if (notice != null) {
			Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
	%>

<html op="item">
<head>
<meta name="referrer" content="origin">
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<link rel="stylesheet" type="text/css" href="css/index.css">
<link rel="shortcut icon" href="favicon.ico">
<title><%=notice.getTitle()%> | Hacker News</title>
</head>
<body>
	<center>
		<%
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
									style="border: 1px white solid;"></a></td>
							<td style="line-height: 12pt; height: 10px;"><span
								class="pagetop"><b class="hnname"><a href="news">Hacker
											News</a></b> <span class="topsel"><a href="newest">new</a></span> | <a
									href="threads?id=<%=sessionUser.getUser().getUserName()%>">threads</a>
									| <a href="front">past</a> | <a href="newcomments">comments</a>
									| <a href="ask">ask</a> | <a href="show">show</a> | <a
									href="jobs">jobs</a> | <a href="Submit">submit</a> </span></td>
							<td style="text-align: right; padding-right: 4px;"><span
								class="pagetop">n" <a id='me'
									href="user?id=<%=sessionUser.getUser().getUserName()%>"><%=sessionUser.getUser().getUserName()%></a>(<%=sessionUser.getUser().getKarma()%>)
									| <a id='logout' href="/HackersNew">logout</a>
							</span></td>
						</tr>
					</table></td>
			</tr>
			<%
				} else {
			%>
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
				<tr id="pagespace" title="<%=notice.getTitle()%>"
					style="height: 10px"></tr>
				<tr>
					<td><table class="fatitem" border="0">
							<tbody>
								<tr class="athing" id="<%=notice.getId()%>">
									<td align="right" valign="top" class="title"><span
										class="rank"></span></td>
									<td valign="top" class="votelinks"><center>
											<a id="up_" <%=notice.getId()%>" href="vote?id="
												<%=notice.getId()%>&amp;how=up&amp;goto=item%3Fid%3D19417110"><div
													class="votearrow" title="upvote"></div></a>
										</center></td>
									<td class="title"><a href="<%=notice.getUrl()%>"
										class="storylink" rel="nofollow"><%=notice.getTitle()%></a><span
										class="sitebit comhead"> (<a
											href="from?site=<%=notice.getDomainUrl()%>"><span
												class="sitestr"><%=notice.getDomainUrl()%></span></a>)
									</span></td>
								</tr>
								<tr>
									<td colspan="2"></td>
									<td class="subtext"><span class="score"
										id="score_<%=notice.getId()%>"><%=NoticeDao.findPointsByNotice(notice)%>
											point</span> by <a
										href="user?id=<%=notice.getUser().getUserName()%>"
										class="hnuser"><%=notice.getUser().getUserName()%></a> <span
										class="age"><a href="item?id=<%=notice.getId()%>"><%=notice.getAge()%></a></span>
										<span id="unv_"<%=notice.getId()%>"></span> | <a
										href="hide?id=<%=notice.getId()%>&amp;goto=item%3Fid%3D19417110">hide</a>
										| <a
										href="https://hn.algolia.com/?query=
										<%=URLEncoder.encode(notice.getTitle(), "utf-8")%>&amp;sort=byDate&amp;dateRange=all&amp;type=story&amp;storyText=false&amp;prefix&amp;page=0
										class="hnpast">past</a>
										| <a
										href="https://www.google.com/search?q=<%=URLEncoder.encode(notice.getTitle(), "utf-8")%>">web</a>
										| <a
										href="fave?id=<%=notice.getId()%>&amp;auth=e68835b51b5087a0775cfa3ef4287e2914dfaffb">favorite</a>
										| <a href="item?id=<%=notice.getId()%>"><%=CommentDao.getCommentsByNotice(notice.getId()).size()%>&nbsp;comment</a>
									</td>
								</tr>
								<tr style="height: 10px"></tr>
								<tr>
									<td colspan="2"></td>
									<td>
										<form method="post" action="comment">
											<input type="hidden" name="parent"
												value="<%=notice.getId()%>">
											<textarea name="text" rows="6" cols="60"></textarea>
											<br> <br> <input type="submit" value="add comment">
										</form>
									</td>
								</tr>
							</tbody>
						</table> <br> <br>
						<table border="0" class="comment-tree">
							<tbody>
								<%
									for (Comment comment : CommentDao.getCommentsByNotice(notice.getId())) {
								%>
								<tr class="athing comtr" id="<%=comment.getId()%>">
									<td>
										<table border="0">
											<tbody>
												<tr>
													<td class="ind"><img src="s.gif" height="1" width="0"></td>
													<td valign="top" class="votelinks"><center>
															<a id="up_<%=comment.getId()%>"
																href="vote?id=<%=comment.getId()%>&amp;how=up&amp;goto=item%3Fid%3D19417110"><div
																	class="votearrow" title="upvote"></div></a>
														</center></td>
													<td class="default"><div
															style="margin-top: 2px; margin-bottom: -10px;">
															<span class="comhead"> <a
																href="user?id=<%=comment.getUser().getUserName()%>"
																class="hnuser">"<%=comment.getUser().getUserName()%></a>
																<span class="age"><a
																	href="item?id=<%=comment.getId()%>"><%=comment.getAge()%></a></span>
																<span id="unv_<%=comment.getId()%>"></span><span
																class="par"></span> <a class="togg" n="1"
																href="javascript:void(0)"
																onclick="return toggle(event, <%=comment.getId()%>)">[-]</a>
																<span class="storyon"></span>
															</span>
														</div> <br>
														<div class="comment">
															<span class="commtext c00"><%=comment.getText()%></span>
															<div class="reply">
																<p>
																	<font size="1"> <u><a
																			href="reply?id=<%=comment.getId()%>&amp;goto=item%3Fid%3D19417110%2319417238">reply</a></u>
																	</font>
																</p>
															</div>
														</div></td>
												</tr>
											</tbody>
										</table>
									</td>
								</tr>
								<%
									}
								%>
							</tbody>
						</table> <br> <br></td>
				</tr>
				<tr>
					<td><img src="s.gif" height="10" width="0">
						<table width="100%" cellspacing="0" cellpadding="1">
							<tbody>
								<tr>
									<td bgcolor="#ff6600"></td>
								</tr>
							</tbody>
						</table> <br>
						<center>
							<a href="https://www.ycombinator.com/apply/"> Applications
								are open for YC Summer 2019n" </a>
						</center> <br>
						<center>
							<span class="yclinks"><a href="newsguidelines.html">Guidelines</a>
								| <a href="newsfaq.html">FAQ</a> | <a
								href="mailto:hn@ycombinator.com">Support</a> | <a
								href="https://github.com/HackerNews/API">API</a> | <a
								href="security.html">Security</a> | <a href="lists">Lists</a> |
								<a href="bookmarklet.html" rel="nofollow">Bookmarklet</a> | <a
								href="http://www.ycombinator.com/legal/">Legal</a> | <a
								href="http://www.ycombinator.com/apply/">Apply to YC</a> | <a
								href="mailto:hn@ycombinator.com">Contact</a></span><br> <br>
							<form method="get" action="//hn.algolia.com/">
								Search: <input type="text" name="q" value="" size="17"
									autocorrect="off" spellcheck="false" autocapitalize="off"
									autocomplete="false">
							</form>
						</center></td>
				</tr>
				</tbody>
			</table>
			</center>
			<script type="text/javascript" src="hn.js"></script>
			< /body>
</html>
<%
	} else {

	}
%>
</body>
</html>