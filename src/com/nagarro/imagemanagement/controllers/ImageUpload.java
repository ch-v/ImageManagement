package com.nagarro.imagemanagement.controllers;

import com.nagarro.imagemanagement.models.Image;
import com.nagarro.imagemanagement.models.User;
import com.nagarro.imagemanagement.services.ImageImplementation;
import com.nagarro.imagemanagement.services.LoginImplementation;
import com.nagarro.imagemanagement.utils.Constants;

import org.apache.commons.fileupload.FileItem;
import org.apache.commons.fileupload.disk.DiskFileItemFactory;
import org.apache.commons.fileupload.servlet.ServletFileUpload;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * @author vishalchaudhary01
 *
 */
public class ImageUpload extends HttpServlet {
	private static final long serialVersionUID = 1L;

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public ImageUpload() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		if (request.getSession().getAttribute("user") == null) {
			response.sendRedirect(Constants.indexPage);
		} else {
			String imageName = null;
			byte bytes[] = null;
			double imageSize = 0;
			if (ServletFileUpload.isMultipartContent(request)) {
				try {
					List<FileItem> multiparts = new ServletFileUpload(new DiskFileItemFactory()).parseRequest(request);

					for (FileItem item : multiparts) {
						if (item.isFormField()) {
							imageName = item.getString();
						} else {
							imageSize = item.getSize() / Constants.IMAGE_SIZE;
							bytes = item.get();
						}
					}

					request.setAttribute("message", "File Uploaded Successfully");
					User user = (User) request.getSession().getAttribute("user");
					Image image = new Image(imageName, imageSize, bytes);
					image.setUser(user);

					if (user != null) {
						if (image.getImageSize() < Constants.IMAGE_SIZE) {
							if (GetImagesSize.getImagesSize(user.getUsername())
									+ image.getImageSize() < Constants.TOTAL_IMAGE_SIZE) {
								ImageImplementation imageManagement = new ImageImplementation();
								imageManagement.addImage(image);
								try {
									LoginImplementation login = new LoginImplementation();

									User userUpdated = login.getUserDetails(
											((User) request.getSession().getAttribute("user")).getUsername());
									request.getSession().setAttribute("user", userUpdated);
									response.sendRedirect(Constants.userPage);
								} catch (Exception e) {
									e.printStackTrace();
								}
							} else {
								System.out.println("Images size exceeded > 10 MB");
								response.sendRedirect(Constants.userPage);
							}
						} else {
							System.out.println("Image size exceeded");
							response.sendRedirect(Constants.userPage);
						}
					}
				} catch (Exception ex) {
					request.setAttribute("message", "File Upload Failed due to " + ex);
				}

			} else {
				request.setAttribute("message", "Sorry image could not be uploaded");
			}
		}
	}
}