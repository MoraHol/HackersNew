package com.hackersnews.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.UserController;
import models.Session;
import models.User;
import models.Comment;
import models.CommentDao;

/**
 * Servlet implementation class Threads
 */
@WebServlet("/threads")
public class Threads extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		// wirting head
		out.println("<html op=\"threads\"><head><meta name=\"referrer\" content=\"origin\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link rel=\"stylesheet\" type=\"text/css\" href=\"css/index.css\">\n" + 
				"            <link rel=\"shortcut icon\" href=\"favicon.ico\">\n" + 
				"        <title>"+request.getParameter("id")+"&#x27;s comments | Hacker News</title></head>");
		// writing menu
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		if (sessionUser != null) {
			out.println(
					"<table id=\"hnmain\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"85%\" bgcolor=\"#f6f6ef\">\n"
							+ "        <tr><td bgcolor=\"#ff6600\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"padding:2px\"><tr><td style=\"width:18px;padding-right:4px\"><a href=\"/HackersNew\"><img src=\"y18.gif\" width=\"18\" height=\"18\" style=\"border:1px white solid;\"></a></td>\n"
							+ "                  <td style=\"line-height:12pt; height:10px;\"><span class=\"pagetop\"><b class=\"hnname\"><a href=\"news\">Hacker News</a></b>\n"
							+ "              <a href=\"newest\">new</a> | <span class=\"topsel\"><a href=\"threads?id="
							+ sessionUser.getUser().getUserName()
							+ "\">threads</a></span> | <a href=\"front\">past</a> | <a href=\"newcomments\">comments</a> | <a href=\"ask\">ask</a> | <a href=\"show\">show</a> | <a href=\"jobs\">jobs</a> | <a href=\"Submit\">submit</a>  | <font color=\"#ffffff\">"+request.getParameter("id")+"'s comments</font>          </span></td><td style=\"text-align:right;padding-right:4px;\"><span class=\"pagetop\">\n"
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
							+ "              <a href=\"newest\">new</a> | <a href=\"front\">past</a> | <a href=\"newcomments\">comments</a> | <a href=\"ask\">ask</a> | <a href=\"show\">show</a> | <a href=\"jobs\">jobs</a> | <a href=\"Submit\">submit</a> | <font color=\"#ffffff\">"+request.getParameter("id")+"'s comments</font>             </span></td><td style=\"text-align:right;padding-right:4px;\"><span class=\"pagetop\">\n"
							+ "                              <a href=\"Login\">login</a>\n"
							+ "                          </span></td>\n" + "              </tr></table></td></tr>");
		}
		out.println("<tr id=\"pagespace\" title=\""+request.getParameter("id")+"&#x27;s comments\" style=\"height:10px\"></tr>");
		try {
			User user = UserController.searchUser(request.getParameter("id"));
			for (Comment comment : CommentDao.getCommentsByUser(user)) {
				out.println("<tr class='athing comtr ' id='"+comment.getId()+"'><td>\n" + 
						"            <table border='0'>  <tr>    <td class='ind'><img src=\"s.gif\" height=\"1\" width=\"0\"></td><td valign=\"top\" class=\"votelinks\"><center><a id='up_"+comment.getId()+"' href='vote?id="+comment.getId()+"&amp;how=up&amp;goto=threads%3Fid%3Dwgx'><div class='votearrow' title='upvote'></div></a></center></td><td class=\"default\"><div style=\"margin-top:2px; margin-bottom:-10px;\"><span class=\"comhead\">\n" + 
						"          <a href=\"user?id="+comment.getUser().getUserName()+"\" class=\"hnuser\">"+comment.getUser().getUserName()+"</a> <span class=\"age\"><a href=\"item?id="+comment.getId()+"\">"+comment.getAge()+"</a></span> <span id=\"unv_"+comment.getId()+"\"></span><span class=\"par\"> | <a href=\"item?id="+comment.getId()+"\">parent</a></span> <a class=\"togg\" n=\"6\" href=\"javascript:void(0)\" onclick=\"return toggle(event, "+comment.getId()+")\"></a>          <span class='storyon'> | on: <a href=\"item?id="+comment.getParentNotice().getId()+"\">"+comment.getParentNotice().getTitle()+"</a></span>\n" + 
						"                  </span></div><br><div class=\"comment\">\n" + 
						"                  <span class=\"commtext c00\">"+comment.getText()+"</span>\n" + 
						"              <div class='reply'>        <p><font size=\"1\">\n" + 
						"                  </font>\n" + 
						"      </div></div></td></tr>\n" + 
						"      </table></td></tr>");
			}
		} catch (Exception e) {
			// TODO: handle exception
			out.println("<tr>Este usuario no ha existe</tr>");
		}
		out.println("<tr><td><img src=\"s.gif\" height=\"10\" width=\"0\"><table width=\"100%\" cellspacing=\"0\" cellpadding=\"1\"><tr><td bgcolor=\"#ff6600\"></td></tr></table><br><center><a href=\"https://www.ycombinator.com/apply/\">\n" + 
				"        Applications are open for YC Summer 2019\n" + 
				"      </a></center><br><center><span class=\"yclinks\"><a href=\"newsguidelines.html\">Guidelines</a>\n" + 
				"        | <a href=\"newsfaq.html\">FAQ</a>\n" + 
				"        | <a href=\"mailto:hn@ycombinator.com\">Support</a>\n" + 
				"        | <a href=\"https://github.com/HackerNews/API\">API</a>\n" + 
				"        | <a href=\"security.html\">Security</a>\n" + 
				"        | <a href=\"lists\">Lists</a>\n" + 
				"        | <a href=\"bookmarklet.html\" rel=\"nofollow\">Bookmarklet</a>\n" + 
				"        | <a href=\"http://www.ycombinator.com/legal/\">Legal</a>\n" + 
				"        | <a href=\"http://www.ycombinator.com/apply/\">Apply to YC</a>\n" + 
				"        | <a href=\"mailto:hn@ycombinator.com\">Contact</a></span><br><br><form method=\"get\" action=\"//hn.algolia.com/\">Search:\n" + 
				"          <input type=\"text\" name=\"q\" value=\"\" size=\"17\" autocorrect=\"off\" spellcheck=\"false\" autocapitalize=\"off\" autocomplete=\"false\"></form>\n" + 
				"            </center></td></tr>\n" + 
				"      </table></center></body><script type='text/javascript' src='hn.js'></script>\n" + 
				"  </html>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
