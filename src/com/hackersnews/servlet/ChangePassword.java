package com.hackersnews.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.hackersnews.dao.UserDaoImpl;
import com.hackersnews.model.Session;

/**
 * Servlet implementation class ChangePassword
 */
@WebServlet("/changepw")
public class ChangePassword extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		if (sessionUser != null) {
			out.println(
					"<html op=\"changepw\"><head><meta name=\"referrer\" content=\"origin\"><meta name=\"viewport\" content=\"width=device-width, initial-scale=1.0\"><link rel=\"stylesheet\" type=\"text/css\" href=\"css/index.css\"><link rel=\"shortcut icon\" href=\"favicon.ico\"><title>Reset Password for "
							+ sessionUser.getUser().getUserName()
							+ " | Hacker News</title></head><body><center><table id=\"hnmain\" border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"85%\" bgcolor=\"#f6f6ef\"><tbody><tr><td bgcolor=\"#ff6600\"><table border=\"0\" cellpadding=\"0\" cellspacing=\"0\" width=\"100%\" style=\"padding:2px\"><tbody><tr><td style=\"width:18px;padding-right:4px\"><a href=\"https://news.ycombinator.com\"><img src=\"y18.gif\" width=\"18\" height=\"18\" style=\"border:1px #ffffff solid;\"></a></td><td style=\"line-height:12pt; height:10px;\"><span class=\"pagetop\"><b>Reset Password for "
							+ sessionUser.getUser().getUserName()
							+ "</b></span></td></tr></tbody></table></td></tr><tr style=\"height:10px\"></tr><tr><td>");
			try {
				if (request.getParameter("wrongpw").equals("t")) {
					out.println("Current password incorrect. Please try again.<br><br>\n");
				}
			} catch (Exception e) {
				// TODO: handle exception
				out.println("<br><br>\n");
			}

			out.println(
					"<form method=\"post\" action=\"changepw\"><input type=\"hidden\" name=\"fnid\" value=\"GidHiDhSnbDelGC0MDi5Kc\"><input type=\"hidden\" name=\"fnop\" value=\"changepw-page\"><table border=\"0\"><tbody><tr><td>Current Password:</td><td><input type=\"password\" name=\"oldpw\" size=\"20\"></td></tr><tr><td>New Password:</td><td><input type=\"password\" name=\"pw\" size=\"20\"></td></tr><tr><td></td><td><input type=\"submit\" value=\"Change\"></td></tr></tbody></table></form></td></tr></tbody></table></center></body></html>");
		} else {
			response.sendRedirect(request.getContextPath() + "/index.jsp");
		}

	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		String oldPassword = request.getParameter("oldpw");
		String password = request.getParameter("pw");
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		if (oldPassword.equals(sessionUser.getUser().getPassword())) {
			sessionUser.getUser().setPassword(password);
			UserDaoImpl.update(sessionUser.getUser());
			response.sendRedirect(request.getContextPath() + "/newest");
		} else {
			response.sendRedirect(request.getContextPath() + "/changepw?wrongpw=t");
		}
	}

}
