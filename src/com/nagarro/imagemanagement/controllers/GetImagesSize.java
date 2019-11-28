package com.nagarro.imagemanagement.controllers;

import java.util.Collection;

import com.nagarro.imagemanagement.models.Image;
import com.nagarro.imagemanagement.models.User;
import com.nagarro.imagemanagement.services.LoginImplementation;

/**
 * @author vishalchaudhary01
 *
 */
public class GetImagesSize {

	private static double totalSize = 0;
	private static LoginImplementation loginImplementation = new LoginImplementation();
	private static User user;
	private static Collection<Image> images;

	public static double getImagesSize(String username) {
		user = loginImplementation.getUserDetails(username);
		images = user.getImages();
		for (Image image : images) {
			totalSize += image.getImageSize();
		}
		return totalSize;
	}
}
