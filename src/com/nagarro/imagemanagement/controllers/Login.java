package com.nagarro.imagemanagement.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.imagemanagement.models.User;
import com.nagarro.imagemanagement.services.LoginImplementation;
import com.nagarro.imagemanagement.utils.Constants;

/**
 * Servlet implementation class Login
 */
/**
 * @author vishalchaudhary01
 *
 */
public class Login extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Login() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// response.getWriter().append("Served at: ").append(request.getContextPath());
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("authorized");
		request.getSession().invalidate();
		response.sendRedirect(Constants.indexPage);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		// doGet(request, response);
		LoginImplementation login = new LoginImplementation();
		String username = request.getParameter("username");
		String password = request.getParameter("password");
		System.out.println(username + password);
		if (login.userAuthentication(username, password)) {
			User user = login.getUserDetails(username);
			request.getSession().setAttribute("authorized", "true");
			request.getSession().setAttribute("user", user);
			response.sendRedirect(Constants.redirectPage);
		} else {
			request.getSession().setAttribute("authorized", "false");
			response.sendRedirect(Constants.indexPage);
		}
	}

}
