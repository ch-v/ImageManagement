package com.nagarro.imagemanagement.controllers;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.nagarro.imagemanagement.models.User;
import com.nagarro.imagemanagement.services.ImageImplementation;
import com.nagarro.imagemanagement.services.LoginImplementation;
import com.nagarro.imagemanagement.utils.Constants;

/**
 * Servlet implementation class ImageDelete
 */
/**
 * @author vishalchaudhary01
 *
 */
public class ImageDelete extends HttpServlet {

	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageDelete() {
		super();
	}

	/**
	 * Servlet to delete an image
	 *
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect("index.jsp");
		} else {
			LoginImplementation login = new LoginImplementation();
			ImageImplementation imageManagement = new ImageImplementation();
			String imageid = request.getParameter("imageid").toString();
			imageManagement.deleteImage(imageid);
			System.out.println(imageid + "deleted");
			User userUpdated = login.getUserDetails(((User) request.getSession().getAttribute("user")).getUsername());
			request.getSession().setAttribute("user", userUpdated);
			response.sendRedirect(Constants.userPage);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		doGet(request, response);
	}
}
