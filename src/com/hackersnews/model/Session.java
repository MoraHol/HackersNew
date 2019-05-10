package com.hackersnews.model;

import com.hackersnews.controller.UserController;

public class Session {
	private User user;
	private UserController userConstroller;
	public Session() {
		userConstroller = new UserController();
	}
	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public boolean isSession() {
		if (this.user != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean login(String username, String password) throws Exception {
		User user = userConstroller.validateUser(username, password);
		if (user != null) {
			this.user = user;
			return true;
		}
		return false;
	}

	public boolean createAccount(String username, String password) {
		if (userConstroller.createAccount(username, password) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyUser(String username) throws Exception {
		for (User user : userConstroller.getUsers()) {
			if (user.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}
}
