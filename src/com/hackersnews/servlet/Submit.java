package com.hackersnews.servlet;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import controllers.NoticeController;
import models.Session;

/**
 * Servlet implementation class Submit
 */
@WebServlet("/Submit")
public class Submit extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		if (sessionUser != null) {
			if (sessionUser.isSession()) {
				getServletContext().getRequestDispatcher("/submit.jsp").forward(request, response);
			}
		} else {
			getServletContext().getRequestDispatcher("/login.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		String url = request.getParameter("url");
		String title = request.getParameter("title");
		String text = request.getParameter("text");
		Session sessionUser = (Session) request.getSession().getAttribute("sessionUser");
		NoticeController.createNotice(sessionUser.getUser(), title, url);
		response.getWriter().println("registrado correctamente "+ sessionUser.getUser().getNotices().toString());
	}

}
