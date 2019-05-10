package com.hackersnews.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hackersnews.controller.UserController;
import com.hackersnews.dao.NoticeDaoImpl;
import com.hackersnews.model.Notice;
import com.hackersnews.model.Session;
import com.hackersnews.model.User;

/**
 * Servlet implementation class Submitted
 */
@WebServlet("/submitted")
public class Submitted extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		User user = UserController.searchUser(request.getParameter("id"));
		PrintWriter out = response.getWriter();
		// writing head
		out.println("<!DOCTYPE html>\n" + "<html op=\"submitted\">\n" + "<head>\n"
				+ "<meta name=\"referrer\" content=\"origin\">\n"
				+ "<meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\">\n"
				+ "<link rel=\"stylesheet\" type=\"text/css\"\n" + "	href=\"css/index.css\">\n"
				+ "<link rel=\"shortcut icon\" href=\"favicon.ico\">\n" + "<title>" + user.getUserName()
				+ "&#x27;s submissions | Hacker News</title>\n" + "</head>");
		// writing menu

		out.println("<body>\n" + "	<center>\n"
				+ "		<table id=\"hnmain\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
				+ "			width=\"85%\" bgcolor=\"#f6f6ef\">\n" + "			<tr>\n"
				+ "				<td bgcolor=\"#ff6600\"><table border=\"0\" cellpadding=\"0\"\n"
				+ "						cellspacing=\"0\" width=\"100%\" style=\"padding: 2px\">\n"
				+ "						<tr>\n"
				+ "							<td style=\"width: 18px; padding-right: 4px\"><a\n"
				+ "								href=\"https://news.ycombinator.com\"><img src=\"y18.gif\"\n"
				+ "									width=\"18\" height=\"18\" style=\"border: 1px white solid;\"></a></td>\n"
				+ "							<td style=\"line-height: 12pt; height: 10px;\"><span\n"
				+ "								class=\"pagetop\"><b class=\"hnname\"><a href=\"news\">Hacker\n"
				+ "											News</a></b> <a href=\"newest\">new</a> | <a\n"
				+ "									href=\"threads?id=" + sessionUser.getUser().getUserName()
				+ "\">threads</a> | <a href=\"front\">past</a>\n"
				+ "									| <a href=\"newcomments\">comments</a> | <a href=\"ask\">ask</a> |\n"
				+ "									<a href=\"show\">show</a> | <a href=\"jobs\">jobs</a> | <a\n"
				+ "									href=\"Submit\">submit</a> | <font color=\"#ffffff\">"
				+ user.getUserName() + "'s\n"
				+ "										submissions</font> </span></td>\n"
				+ "							<td style=\"text-align: right; padding-right: 4px;\"><span\n"
				+ "								class=\"pagetop\"> <a id='me' href=\"user?id="
				+ sessionUser.getUser().getUserName() + "\">" + sessionUser.getUser().getUserName() + "</a>\n"
				+ "									(" + sessionUser.getUser().getKarma() + ") | <a id='logout'\n"
				+ "									href=\"/HackersNew\">logout</a>\n"
				+ "							</span></td>\n" + "						</tr>\n"
				+ "					</table></td>\n" + "			</tr>");
		out.println("<tr id=\"pagespace\" title=\"" + sessionUser.getUser().getUserName() + "&#x27;s submissions\"\n"
				+ "				style=\"height: 10px\"></tr>");
		out.println("<tr>\n" + "				<td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\"\n"
				+ "						class=\"itemlist\">");
		// noticia
		int count = 1;
		for (Notice notice : NoticeDaoImpl.getNoticesByUser(user)) {
			out.println("<tr class='athing' id='" + notice.getId() + "'>\n"
					+ "							<td align=\"right\" valign=\"top\" class=\"title\"><span\n"
					+ "								class=\"rank\">" + count + ".</span></td>\n"
					+ "							<td valign=\"top\" class=\"votelinks\"><center>\n"
					+ "									<font color=\"#ff6600\">*</font><br> <img src=\"https://news.ycombinator.com/s.gif\"\n"
					+ "										height=\"1\" width=\"14\">\n"
					+ "								</center></td>\n"
					+ "							<td class=\"title\"><a\n" + "								href=\""
					+ notice.getUrl() + "\"\n" + "								class=\"storylink\" rel=\"nofollow\">"
					+ notice.getTitle() + "</a><span class=\"sitebit comhead\"> (<a href=\"from?site="
					+ notice.getDomainUrl() + "\"><span class=\"sitestr\">" + notice.getDomainUrl()
					+ "</span></a>)</span></td>\n" + "						</tr>\n" + "						<tr>\n"
					+ "							<td colspan=\"2\"></td>\n"
					+ "							<td class=\"subtext\"><span class=\"score\" id=\"score_"
					+ notice.getId() + "\">" + NoticeDaoImpl.findPointsByNotice(notice) + "\n"
					+ "									points</span> by <a href=\"user?id="
					+ sessionUser.getUser().getUserName() + "\" class=\"hnuser\">" + sessionUser.getUser().getUserName()
					+ "</a>\n" + "								<span class=\"age\"><a href=\"item?id=" + notice.getId()
					+ "\">" + notice.getAge() + "</a></span> <span id=\"unv_" + notice.getId() + "\"></span> | <a\n"
					+ "								href=\"https://hn.algolia.com/?query="
					+ URLEncoder.encode(notice.getTitle(), "utf-8")
					+ "&sort=byDate&dateRange=all&type=story&storyText=false&prefix&page=0\"\n"
					+ "								class=\"hnpast\">past</a> | <a\n"
					+ "								href=\"https://www.google.com/search?q="
					+ URLEncoder.encode(notice.getTitle(), "utf-8") + "\">web</a> | <a\n"
					+ "								href=\"edit?id=" + notice.getId() + "\">edit</a> | <a\n"
					+ "								href=\"delete-confirm?id=19404484&amp;goto=submitted%3Fid%3Dalexis302000\">delete</a>\n"
					+ "							</td>\n" + "						</tr>");
			count++;
		}
		// cierre de historias
		out.println("</td>\n" + "						</tr>\n"
				+ "						<tr class=\"spacer\" style=\"height: 5px\"></tr>\n"
				+ "					</table></td></tr>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
