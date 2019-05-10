package com.hackersnews.controller;

import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;

import com.hackersnews.dao.UserDaoImpl;
import com.hackersnews.idao.IUserDao;
import com.hackersnews.model.User;

public class UserController {
	private IUserDao userDao;

	public UserController() {
		userDao = new UserDaoImpl();
	}

	public ArrayList<User> getUsers() throws Exception {
		return userDao.findAll();
	}

	public User validateUser(String username, String password) throws Exception {
		User user = userDao.findUserByUserName(username);
		if (user.getUserName() != null) {
			if (user.getUserName().equals(username) && user.getPassword().equals(convertSHA256(password))) {
				return user;
			} else {
				return null;
			}
		} else {
			return null;
		}

	}

	public User searchUser(String username) {
		try {
			User user = userDao.findUserByUserName(username);
			if (user.getUserName().equals(username)) {
				return user;
			}
		} catch (Exception e) {
		}
		return null;
	}

	public User createAccount(String username, String password) {
		try {
			User user = validateUser(username, password);
			if (user != null) {
				return null;
			} else {
				user = new User(username, convertSHA256(password));
				userDao.save(user);
				return user;
			}
		} catch (Exception e) {
			return null;
		}
	}

	private String convertSHA256(String password) {
		MessageDigest md;
		try {
			md = MessageDigest.getInstance("SHA-256");
		} catch (NoSuchAlgorithmException e) {
			return null;
		}

		byte[] hash = md.digest(password.getBytes());
		StringBuilder sb = new StringBuilder();

		for (byte b : hash) {
			sb.append(String.format("%02x", b));
		}

		return sb.toString();
	}

}
