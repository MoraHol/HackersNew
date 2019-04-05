package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.sql.Types;

import models.Comment;
import models.User;

public class CommentDao {
	private static Connection getConnection() throws SQLException, ClassNotFoundException {
		// Initialize all the information regarding
		// Database Connection
		String dbDriver = "com.mysql.jdbc.Driver";
		String dbURL = "jdbc:mysql://localhost:3306/";
		// Database name to access
		String dbName = "hackersnews";
		String dbUsername = "root";
		String dbPassword = "";
		Class.forName(dbDriver);
		Connection con = DriverManager.getConnection(dbURL + dbName, dbUsername, dbPassword);
		return con;
	}

	public static int save(Comment comment) {
		int status = 0;
		try {
			Connection con = CommentDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO comments(text, user_id, parent_id, notice_id) values (?,?,?,?)");
			ps.setString(1, comment.getText());
			ps.setInt(2, comment.getUser().getId());
			if (comment.getParentComment() == null) {
				ps.setNull(3, Types.INTEGER);
			} else {
				ps.setInt(3, comment.getParentComment().getId());
			}
			ps.setInt(4, comment.getParentNotice().getId());
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
			Connection con = CommentDao.getConnection();
			PreparedStatement ps = con.prepareStatement("DELETE FROM comments WHERE comments.id = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			con.close();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public static int update(Comment comment) {
		int status = 0;
		try {
			Connection con = CommentDao.getConnection();
			PreparedStatement ps = con
					.prepareStatement("UPDATE `comments` SET  `text` = ?, `active` = ?, `parent_id` = ? WHERE `comments`.`id` = ?");
			ps.setString(1, comment.getText());
			ps.setBoolean(2, comment.isActive());
			if (comment.getParentComment() == null) {
				ps.setNull(3, Types.INTEGER);
			} else {
				ps.setInt(3, comment.getParentComment().getId());
			}
			ps.setInt(4, comment.getId());
			status = ps.executeUpdate();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ArrayList<Comment> getCommentsByUser(User user) {
		return null;
	}

	public static Comment getCommentById(int id) {
		return null;
	}

	public static ArrayList<Comment> getCommentsByNotice(int noticeId) {
		return null;
	}

}
