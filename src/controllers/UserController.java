package controllers;

import java.util.ArrayList;
import java.util.List;

import models.User;

public class UserController {
	private static List<User> users = new ArrayList<User>();

	public static void setUsers(List<User> users) {
		UserController.users = users;
	}

	public static List<User> getUsers() {
		return users;
	}

	public static User validateUser(String username, String password) {
		for (User user : users) {
			if (user.getUserName().equals(username) && user.getPassword().equals(password)) {
				return user;
			}
		}
		return null;
	}

	public static User searchUser(String username) {
		for (User user : users) {
			if (user.getUserName().equals(username)) {
				return user;
			}
		}
		return null;
	}

	public static User createAccount(String username, String password) {
		try {
			User user = validateUser(username, password);
			if (user != null) {
				return null;
			} else {
				user = new User(username, password);
				users.add(user);
				return user;
			}
		} catch (Exception e) {
			return null;
		}
	}
}
