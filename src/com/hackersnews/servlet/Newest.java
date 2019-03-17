package com.hackersnews.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.net.URLEncoder;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.NoticeController;
import models.Notice;
import models.Session;

/**
 * Servlet implementation class Newest
 */
@WebServlet("/newest")
public class Newest extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		out.println(
				"<html op=\"newest\"><head><meta name=\"referrer\" content=\"origin\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link rel=\"stylesheet\" type=\"text/css\" href=\"css/index.css\">\n"
						+ "            <link rel=\"shortcut icon\" href=\"favicon.ico\">\n"
						+ "        <title>New Links | Hacker News</title></head><body><center>");
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		if (sessionUser != null) {
			out.println(
					"<table id=\"hnmain\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"85%\" bgcolor=\"#f6f6ef\">\n"
							+ "        <tr><td bgcolor=\"#ff6600\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"padding:2px\"><tr><td style=\"width:18px;padding-right:4px\"><a href=\"/HackersNew\"><img src=\"y18.gif\" width=\"18\" height=\"18\" style=\"border:1px white solid;\"></a></td>\n"
							+ "                  <td style=\"line-height:12pt; height:10px;\"><span class=\"pagetop\"><b class=\"hnname\"><a href=\"news\">Hacker News</a></b>\n"
							+ "              <span class=\"topsel\"><a href=\"newest\">new</a></span> | <a href=\"threads?id="
							+ sessionUser.getUser().getUserName()
							+ "\">threads</a> | <a href=\"front\">past</a> | <a href=\"newcomments\">comments</a> | <a href=\"ask\">ask</a> | <a href=\"show\">show</a> | <a href=\"jobs\">jobs</a> | <a href=\"Submit\">submit</a>            </span></td><td style=\"text-align:right;padding-right:4px;\"><span class=\"pagetop\">\n"
							+ "                              <a id='me' href=\"user?id="
							+ sessionUser.getUser().getUserName() + "\">" + sessionUser.getUser().getUserName()
							+ "</a>                (" + sessionUser.getUser().getKarma() + ") |\n"
							+ "                <a id='logout' href=\"/HackersNew\">logout</a>                          </span></td>\n"
							+ "              </tr></table></td></tr>");
		} else {
			out.println(
					"<table id=\"hnmain\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"85%\" bgcolor=\"#f6f6ef\">\n"
							+ "        <tr><td bgcolor=\"#ff6600\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"padding:2px\"><tr><td style=\"width:18px;padding-right:4px\"><a href=\"https://news.ycombinator.com\"><img src=\"y18.gif\" width=\"18\" height=\"18\" style=\"border:1px white solid;\"></a></td>\n"
							+ "                  <td style=\"line-height:12pt; height:10px;\"><span class=\"pagetop\"><b class=\"hnname\"><a href=\"news\">Hacker News</a></b>\n"
							+ "              <span class=\"topsel\"><a href=\"newest\">new</a></span> | <a href=\"front\">past</a> | <a href=\"newcomments\">comments</a> | <a href=\"ask\">ask</a> | <a href=\"show\">show</a> | <a href=\"jobs\">jobs</a> | <a href=\"Submit\">submit</a>            </span></td><td style=\"text-align:right;padding-right:4px;\"><span class=\"pagetop\">\n"
							+ "                              <a href=\"Login\">login</a>\n"
							+ "                          </span></td>\n" + "              </tr></table></td></tr>");
		}
		try {
			ArrayList<Notice> noticeNewest = NoticeController.getNoticesNewest();
			out.println(
					"<tr id=\"pagespace\" title=\"New Links\" style=\"height:10px\"></tr><tr><td><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" class=\"itemlist\">\n"
							+ "              <tr class='athing' id='" + noticeNewest.get(0).getId() + "'>");
			int counter = 1;
			for (Notice notice : noticeNewest) {
				out.println("<td align=\"right\" valign=\"top\" class=\"title\"><span class=\"rank\">" + counter
						+ ".</span></td>      <td valign=\"top\" class=\"votelinks\"><center><a id='up_"
						+ notice.getId() + "' href='vote?id=" + notice.getId()
						+ "&amp;how=up&amp;goto=newest'><div class='votearrow' title='upvote'></div></a></center></td><td class=\"title\"><a href=\""
						+ notice.getUrl() + "\" class=\"storylink\" rel=\"nofollow\">" + notice.getTitle()
						+ "</a><span class=\"sitebit comhead\"> (<a href=\"from?site=" + notice.getDomainUrl()
						+ "\"><span class=\"sitestr\">" + notice.getDomainUrl()
						+ "</span></a>)</span></td></tr><tr><td colspan=\"2\"></td><td class=\"subtext\">\n"
						+ "        <span class=\"score\" id=\"score_" + notice.getId() + "\">" + notice.getPoints()
						+ " point</span> by <a href=\"user?id=" + notice.getUser().getUserName()
						+ "\" class=\"hnuser\">" + notice.getUser().getUserName()
						+ "</a> <span class=\"age\"><a href=\"item?id=" + notice.getId()
						+ "\">"+notice.getAge()+"</a></span> <span id=\"unv_" + notice.getId()
						+ "\"></span> | <a href=\"hide?id=" + notice.getId()
						+ "&amp;goto=newest\">hide</a> | <a href=\"https://hn.algolia.com/?query="
						+ URLEncoder.encode(notice.getTitle(), "utf-8")
						+ "&sort=byDate&dateRange=all&type=story&storyText=false&prefix&page=0\" class=\"hnpast\">past</a> | <a href=\"https://www.google.com/search?q="
						+ URLEncoder.encode(notice.getTitle(), "utf-8") + "\">web</a> | <a href=\"item?id="
						+ notice.getId() + "\">discuss</a>              </td></tr>");
				counter++;
			}
		} catch (Exception e) {

		}
		out.println(
				"</table></tr><tr><td><img src=\"s.gif\" height=\"10\" width=\"0\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"1\"><tbody><tr><td bgcolor=\"#ff6600\"></td></tr></tbody></table><br><center><a href=\"https://www.ycombinator.com/apply/\">\n"
						+ "        Applications are open for YC Summer 2019\n"
						+ "      </a></center><br><center><span class=\"yclinks\"><a href=\"newsguidelines.html\">Guidelines</a>\n"
						+ "        | <a href=\"newsfaq.html\">FAQ</a>\n"
						+ "        | <a href=\"mailto:hn@ycombinator.com\">Support</a>\n"
						+ "        | <a href=\"https://github.com/HackerNews/API\">API</a>\n"
						+ "        | <a href=\"security.html\">Security</a>\n"
						+ "        | <a href=\"lists\">Lists</a>\n"
						+ "        | <a href=\"bookmarklet.html\" rel=\"nofollow\">Bookmarklet</a>\n"
						+ "        | <a href=\"http://www.ycombinator.com/legal/\">Legal</a>\n"
						+ "        | <a href=\"http://www.ycombinator.com/apply/\">Apply to YC</a>\n"
						+ "        | <a href=\"mailto:hn@ycombinator.com\">Contact</a></span><br><br><form method=\"get\" action=\"//hn.algolia.com/\">Search:\n"
						+ "          <input type=\"text\" name=\"q\" value=\"\" size=\"17\" autocorrect=\"off\" spellcheck=\"false\" autocapitalize=\"off\" autocomplete=\"false\"></form>\n"
						+ "            </center></td></tr>");
		out.println("</center></body></html>");
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
