package com.nagarro.imagemanagement.services;

import com.nagarro.imagemanagement.models.User;
import com.nagarro.imagemanagement.utils.HibernateUtil;

import org.hibernate.Session;


/**
 * @author vishalchaudhary01
 *
 */
public class LoginImplementation {

	/**
	 * returns true if user with the given name and password exists
	 *
	 * @param username
	 * @param password
	 * @return
	 */
	public Boolean userAuthentication(String username, String password) {
		try (Session session = HibernateUtil.getSessionInstance()) {
			session.getTransaction().begin();
			User user = session.get(User.class, username);
			if (user.getUsername() != null && user.getPassword().equals(password))
				return true;
			else
				return false;
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return false;
	}

	/**
	 * Returns user with given username
	 *
	 * @param username
	 * @return
	 */
	public User getUserDetails(String username) {
		User user = null;
		try (Session session = HibernateUtil.getSessionInstance();) {
			session.getTransaction().begin();
			user = session.get(User.class, username);

		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
		return user;
	}

	/**
	 * Updates the password for a given username
	 *
	 * @param username
	 * @param password
	 */
	public void updatePassword(String username, String password) {
		User user = null;
		try (Session session = HibernateUtil.getSessionInstance();) {
			session.getTransaction().begin();
			user = session.get(User.class, username);
			user.setPassword(password);
			session.update(user);
			session.getTransaction().commit();
		} catch (Exception e) {
			System.out.println(e.getMessage());
		}
	}

}
