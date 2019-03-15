package models;

import controllers.UserController;

public class Session {
	private User user;

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

	public boolean login(String username, String password) {
		User user = UserController.searchUser(username, password);
		if (user != null) {
			this.user = user;
			return true;
		}
		return false;
	}

	public boolean createAccount(String username, String password) {
		if (UserController.createAccount(username, password) != null) {
			return true;
		} else {
			return false;
		}
	}

	public boolean verifyUser(String username) {
		for (User user : UserController.getUsers()) {
			if (user.getUserName().equals(username)) {
				return true;
			}
		}
		return false;
	}
}
