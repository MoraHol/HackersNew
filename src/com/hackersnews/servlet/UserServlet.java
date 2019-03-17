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

/**
 * Servlet implementation class UserServlet
 */
@WebServlet("/user")
public class UserServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		PrintWriter out = response.getWriter();
		User user = UserController.searchUser(request.getParameter("id"));
		if (sessionUser != null) {
			if (request.getParameter("id")
					.equals(((Session) request.getSession().getAttribute("sessionUser")).getUser().getUserName())) {
				try {
					getServletContext().getRequestDispatcher("/user.jsp").forward(request, response);
				} catch (Exception e) {
					response.sendRedirect(request.getContextPath() + "/index.jsp");
				}
			} else {
				// writing head
				out.println(
						"<html op=\"user\"><head><meta name=\"referrer\" content=\"origin\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link rel=\"stylesheet\" type=\"text/css\" href=\"css/index.css\">\n"
								+ "            <link rel=\"shortcut icon\" href=\"favicon.ico\">\n"
								+ "        <title>Profile: " + request.getParameter("id")
								+ " | Hacker News</title></head><body><center>");
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
			}
		} else {
			out.println(
					"<table id=\"hnmain\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"85%\" bgcolor=\"#f6f6ef\">\n"
							+ "        <tr><td bgcolor=\"#ff6600\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"padding:2px\"><tr><td style=\"width:18px;padding-right:4px\"><a href=\"https://news.ycombinator.com\"><img src=\"y18.gif\" width=\"18\" height=\"18\" style=\"border:1px white solid;\"></a></td>\n"
							+ "                  <td style=\"line-height:12pt; height:10px;\"><span class=\"pagetop\"><b class=\"hnname\"><a href=\"news\">Hacker News</a></b>\n"
							+ "              <span class=\"topsel\"><a href=\"newest\">new</a></span> | <a href=\"front\">past</a> | <a href=\"newcomments\">comments</a> | <a href=\"ask\">ask</a> | <a href=\"show\">show</a> | <a href=\"jobs\">jobs</a> | <a href=\"Submit\">submit</a>            </span></td><td style=\"text-align:right;padding-right:4px;\"><span class=\"pagetop\">\n"
							+ "                              <a href=\"Login\">login</a>\n"
							+ "                          </span></td>\n" + "              </tr></table></td></tr>");
		}
		out.println("<tr id=\"pagespace\" title=\"Profile: " + user.getUserName()
				+ "\" style=\"height:10px\"></tr><tr><td><table border=\"0\"><tr class='athing'><td valign=\"top\">user:</td><td timestamp=\"1515962159\"><a href=\"user?id="
				+ user.getUserName() + "\" class=\"hnuser\">" + user.getUserName() + "</a></td></tr>\n"
				+ "    <tr><td valign=\"top\">created:</td><td><a href=\"#\">" + user.birth() + "</a></td></tr>\n"
				+ "    <tr><td valign=\"top\">karma:</td><td>\n" + "              " + user.getKarma()
				+ "         </td></tr>\n" + "    <tr><td valign=\"top\">about:</td><td>\n" + "          </td></tr>\n"
				+ "              <tr><td></td><td><a href=\"submitted?id=" + user.getUserName()
				+ "\"><u>submissions</u></a></td></tr><tr><td></td><td><a href=\"threads?id=" + user.getUserName()
				+ "\"><u>comments</u></a></td></tr>\n" + "      <tr><td></td><td>\n"
				+ "              <a href=\"favorites?id=" + user.getUserName() + "\"><u>favorites</u></a>\n"
				+ "          </td></tr>\n" + "  </table>\n" + "<br><br>\n" + "</td></tr>\n"
				+ "      </table></center></body></html>");

		// TODO Auto-generated method stub

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
