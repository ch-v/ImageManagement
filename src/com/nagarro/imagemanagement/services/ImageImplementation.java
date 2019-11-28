package com.nagarro.imagemanagement.services;

import com.nagarro.imagemanagement.models.Image;
import com.nagarro.imagemanagement.utils.HibernateUtil;

import org.hibernate.Session;

/**
 * @author vishalchaudhary01
 *
 */
public class ImageImplementation {

	public void addImage(Image image) {
		try (Session session = HibernateUtil.getSessionInstance();) {
			session.getTransaction().begin();
			session.save(image);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * get an image from database
	 */

	public Image getImage(String imageId) {
		Image image = null;
		System.out.println(imageId);
		try (Session session = HibernateUtil.getSessionInstance()) {
			session.getTransaction().begin();
			image = (Image) session.createQuery("from Image where imageId='" + imageId + "'").getSingleResult();
		} catch (Exception e) {
			System.out.println(e.getMessage());
			System.out.println("Unable to connect to database");
		}
		return image;
	}

	/*
	 * method to edit an image
	 */

	public void editImage(Image newImage) {
		try (Session session = HibernateUtil.getSessionInstance();) {
			session.beginTransaction();
			session.update(newImage);
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	/*
	 * method to delete an image present in database using HQL
	 */

	public void deleteImage(String imageId) {
		try (Session session = HibernateUtil.getSessionInstance();) {
			session.getTransaction().begin();
			session.createQuery("delete from Image where imageId='" + imageId + "'").executeUpdate();
			session.getTransaction().commit();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
