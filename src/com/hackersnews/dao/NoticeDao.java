package com.hackersnews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hackersnews.model.Notice;
import com.hackersnews.model.User;

public class NoticeDao {

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

	public static Notice getNoticeById(int id) throws SQLException {
		Notice notice = null;
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM `notices` WHERE notices.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				notice = new Notice(UserDao.getUserById(rs.getInt("user_id"), con), rs.getInt("id"),
						rs.getString("title"), rs.getString("url"), rs.getDate("created_at"));
			}
			con.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return notice;
	}

	public static Notice getNoticeById(int id, Connection con) throws SQLException {
		Notice notice = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM `notices` WHERE notices.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				notice = new Notice(UserDao.getUserById(rs.getInt("user_id"), con), rs.getInt("id"),
						rs.getString("title"), rs.getString("url"), rs.getDate("created_at"));
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		return notice;
	}

	public static int save(Notice notice) {
		int status = 0;
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO `notices` (`title`, `url`, `user_id`) VALUES (?, ?, ?)");
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getUrl());
			ps.setInt(3, notice.getUser().getId());
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static int delete(int id) {
		int status = 0;
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM notices WHERE notices.id = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static ArrayList<Notice> getNoticesByUser(User user) {
		ArrayList<Notice> notices = new ArrayList<Notice>();
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT notices.id,title,url,notices.created_at,type FROM `notices` INNER JOIN users ON user_id = users.id WHERE users.id = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notices.add(new Notice(user, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notices;
	}

	public static ArrayList<Notice> getNoticesByUser(User user, Connection con) {
		ArrayList<Notice> notices = new ArrayList<Notice>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT notices.id,title,url,notices.created_at,type FROM `notices` INNER JOIN users ON user_id = users.id WHERE users.id = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notices.add(new Notice(user, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notices;
	}

	public static int update(Notice notice) {
		int status = 0;
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE `notices` SET `title` = ?, `url` = ? WHERE `notices`.`id` = ?");
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getUrl());
			ps.setInt(3, notice.getId());
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ArrayList<Notice> findAllNotices() {
		ArrayList<Notice> notices = new ArrayList<Notice>();
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con.prepareStatement("select * from notices");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Notice notice = getNoticeById(rs.getInt("id"), con);
				notices.add(notice);
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return notices;
	}
	public static int findPointsByNotice(Notice notice) {
		int points = 0;
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM rating_users WHERE notice_id = ?");
			ps.setInt(1, notice.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				points++;
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return points;
	}

	public static int rateNotice(User user, Notice notice) {
		int status = 0;
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO `rating_users` (`user_id`, `notice_id`) VALUES (?, ?)");
			ps.setInt(1, user.getId());
			ps.setInt(2, notice.getId());
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
	public static int removePoint(User user, Notice notice) {
		int status = 0;
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("DELETE FROM `rating_users` WHERE `rating_comments`.`user_id` = ? AND `rating_comments`.`comment_id` = ?");
			ps.setInt(1, user.getId());
			ps.setInt(2, notice.getId());
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
	public static ArrayList<Notice> NoticesRatedByUser(User user) {
		ArrayList<Notice> notices = new ArrayList<Notice>();
		try {
			Connection con = NoticeDao.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT notices.id,notices.url,notices.title, notices.created_at FROM `rating_users` JOIN notices ON `notice_id` = notices.id WHERE rating_users.`user_id` = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notices.add(new Notice(user,rs.getString("title"),rs.getString("url"),rs.getDate("created_at")));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return notices;
	}
}
