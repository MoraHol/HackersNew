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
 * Servlet implementation class Login
 */
@WebServlet("/Login")
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		PrintWriter out = response.getWriter();
		try {
			String username = request.getParameter("acct");
			String password = request.getParameter("pw");
			boolean register = Boolean.parseBoolean(request.getParameter("register"));
			System.out.println(username + " " + password + " " + register);
			if (register) {
				Session session = new Session();
				if(session.createAccount(username, password)) {
					session.login(username, password);
					request.setAttribute("session", session);
					getServletContext().getRequestDispatcher("/newest.jsp").forward(request, response);
				}else {
					getServletContext().getRequestDispatcher("/createAccount.jsp").forward(request, response);
				}
			} else {
				Session session = new Session();
				session.login(username, password);
				User usernow = session.getUser();
				System.out.println(usernow);
				if (usernow != null) {
					request.setAttribute("session", session);
					getServletContext().getRequestDispatcher("/newest.jsp").forward(request, response);
				} else {
					out.println("<!DOCTYPE html>\r\n" + "<html>\r\n" + "<head>\r\n" + "    <meta charset=\"utf-8\">\r\n"
							+ "    <meta http-equiv=\"X-UA-Compatible\" content=\"IE=edge\">\r\n"
							+ "    <title>Page Title</title>\r\n"
							+ "    <meta name=\"viewport\" content=\"width=device-width, initial-scale=1\">\r\n"
							+ "</head>\r\n" + "<body>\r\n" + "    <h1>Inicio de sesion erroneo</h1>\r\n"
							+ "    <h3>No esta registrado :(</h3>\r\n" + UserController.getUsers().toString()
							+ "<a href='/HackersNew/login.jsp'>regresar</a>" + "</body>\r\n" + "</html>");
				}
			}
		} finally {
			out.close();
		}
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
