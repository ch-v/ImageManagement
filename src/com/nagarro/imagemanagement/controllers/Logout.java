package com.nagarro.imagemanagement.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.imagemanagement.utils.Constants;

/**
 * Servlet implementation class Logout
 */
/**
 * @author vishalchaudhary01
 *
 */
public class Logout extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public Logout() {
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
		request.getSession().removeAttribute("user");
		request.getSession().removeAttribute("authorized");
		request.getSession().invalidate();
		response.sendRedirect(Constants.indexPage);
	}

}
