package com.nagarro.imagemanagement.controllers;

import com.nagarro.imagemanagement.models.Image;
import com.nagarro.imagemanagement.services.ImageImplementation;
import com.nagarro.imagemanagement.utils.Constants;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author vishalchaudhary01
 *
 */
public class ImageRetreiver extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageRetreiver() {
		super();
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect(Constants.indexPage);
		} else {
			ImageImplementation imageManagement = new ImageImplementation();
			String imageId = request.getParameter("imageId");
			Image image = imageManagement.getImage(imageId);
			System.out.println(image);
			if (image != null) {
				response.setContentType("image/jpeg, image/jpg, image/png, image/gif");
				try {
					response.getOutputStream().flush();
					response.getOutputStream().write(image.getPhoto());
					response.getOutputStream().close();
				} catch (IOException e) {
					e.printStackTrace();
				}
			}
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