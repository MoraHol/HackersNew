package controllers;

import java.util.ArrayList;

import models.User;
import models.UserDao;

public class UserController {
	public static ArrayList<User> getUsers() {
		return UserDao.getAllUsers();
	}

	public static User validateUser(String username, String password) {
		User user = UserDao.getUserByUserName(username);
		if (user.getUserName() != null) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				return user;
			} else {
				return null;
			}
		}else {
			return null;
		}

	}

	public static User searchUser(String username) {
		User user = UserDao.getUserByUserName(username);
		if (user.getUserName().equals(username)) {
			return user;
		} else {
			return null;
		}
	}

	public static User createAccount(String username, String password) {
		try {
			User user = validateUser(username, password);
			if (user != null) {
				return null;
			} else {
				user = new User(username, password);
				UserDao.save(user);
				return user;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
