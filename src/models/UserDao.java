package models;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

public class UserDao {

	public static Connection getConnection() throws SQLException, ClassNotFoundException {
		// Initialize all the information regarding
		// Database Connection
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/";
		// Database name to access
		String dbName = "hackersnews";
		String dbUsername = "root";
		String dbPassword = "";
		Class.forName(dbDriver);
		return DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
	}

	public static int save(User user) {
		int status = 0;
		try {
			Connection con = UserDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO users (user_name, password, email) values (?,?,?)");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = UserDao.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM users WHERE users.id = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static User getUserById(int id,  Connection con) {
		User user = new User();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE users.id = ?");
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
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public static User getUserByUserName(String userName) {
		User user = new User();
		try {
			Connection con = UserDao.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE users.user_name = ?");
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
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}

	public static User getUserByUserName(String userName, Connection con) {
		User user = new User();
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM users WHERE users.user_name = ?");
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
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return user;
	}
	
	public static ArrayList<User> getAllUsers() {
		ArrayList<User> list = new ArrayList<User>();
		try {
			Connection con = UserDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from users");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				User user = getUserByUserName(rs.getString("user_name"),con);
				list.add(user);
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return list;
	}

	public static int update(User user) {
		int status = 0;
		try {
			Connection con = UserDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"UPDATE `users` SET `user_name` = ?, `password` = ?, `email` = ?, `karma` = ? WHERE `users`.`id` = ?");
			ps.setString(1, user.getUserName());
			ps.setString(2, user.getPassword());
			ps.setString(3, user.getEmail());
			ps.setInt(4, user.getKarma());
			ps.setInt(5, user.getId());
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
}
