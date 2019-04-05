package com.hackersnews.servlet;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.CommentController;
import controllers.NoticeController;
import models.User;
import models.Notice;
import models.Session;
import models.Comment;

/**
 * Servlet implementation class Vote
 */
@WebServlet("/vote")
public class Vote extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		User user = ((Session) request.getSession().getAttribute("sessionUser")).getUser();
		if (NoticeController.searchNotice(Integer.parseInt(request.getParameter("id"))) != null) {
			Notice item = NoticeController.searchNotice(Integer.parseInt(request.getParameter("id")));
			if (request.getParameter("how").equals("up") && item.userVoted(user.getUserName())) {
				item.addPoint(user.getUserName());
			} else {
				item.removePoint(user.getUserName());
			}
			response.sendRedirect(request.getContextPath() + "/newest");
		} else if (CommentController.searchComment(Integer.parseInt(request.getParameter("id"))) != null) {
			Comment item = CommentController.searchComment(Integer.parseInt(request.getParameter("id")));
			if (request.getParameter("how").equals("up")) {
				item.addPoint(user.getUserName());
			} else {
				item.removePoint(user.getUserName());
			}
			response.sendRedirect(request.getContextPath() + "/item?id="+item.getParentNotice().getId());
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
