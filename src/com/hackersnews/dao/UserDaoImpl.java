package com.hackersnews.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;

import com.hackersnews.idao.IUserDao;
import com.hackersnews.model.User;

public class UserDaoImpl extends ConnectionSQL implements IUserDao{
	@Override
	public int save(User user) throws Exception {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("INSERT INTO users (user_name, password, email) values (?,?,?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			status = ps.executeUpdate();
			this.disconnect();
		} catch (Exception e) {
            System.out.println(" UserDaoImpl: " + e.getMessage());
            throw e;

		}
		return status;
	}
	@Override
	public int delete(int id) throws Exception {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement("DELETE FROM users WHERE users.id = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" UserDaoImpl: " + e.getMessage());
            throw e;
		}
		return status;
	}

	public User findUserById(int id) throws Exception {
		User user = new User();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement("SELECT * FROM users WHERE users.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setKarma(rs.getInt(4));
				user.setCreatedAt(rs.getDate("created_at"));
			}
			ps.close();
			rs.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" UserDaoImpl: " + e.getMessage());
            throw e;
		}
		return user;
	}

	public User findUserByUserName(String userName) throws Exception {
		User user = new User();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement("SELECT * FROM users WHERE users.user_name = ?");
			ps.setString(1, userName);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				user.setId(rs.getInt(1));
				user.setUserName(rs.getString(2));
				user.setPassword(rs.getString(3));
				user.setEmail(rs.getString(4));
				user.setKarma(rs.getInt(4));
				user.setCreatedAt(rs.getDate("created_at"));
			}
			ps.close();
			rs.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" UserDaoImpl: " + e.getMessage());
            throw e;
		}
		return user;
	}
	
	public ArrayList<User> findAll() throws Exception {
		ArrayList<User> list = new ArrayList<User>();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement("select * from users");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = findUserByUserName(rs.getString("user_name"));
				list.add(user);
			}
			ps.close();
			rs.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" UserDaoImpl: " + e.getMessage());
            throw e;
		}
		return list;
	}

	public int update(User user) throws Exception {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement(
					"UPDATE `users` SET `user_name` = ?, `password` = ?, `email` = ?, `karma` = ? WHERE `users`.`id` = ?");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getKarma());
			ps.setInt(5, user.getId());
			status = ps.executeUpdate();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" UserDaoImpl: " + e.getMessage());
            throw e;
		}
		return status;
	}
}
