package com.hackersnews.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hackersnews.idao.ICommentDao;
import com.hackersnews.model.Comment;
import com.hackersnews.model.User;

import java.sql.Types;

public class CommentDaoImpl extends Conexion implements ICommentDao{
	@Override
	public int save(Comment comment) throws SQLException {
		int status = 0;
		try {
			this.con
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
			Connection con = CommentDaoImpl.getConnection();
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
			Connection con = CommentDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"UPDATE `comments` SET  `text` = ?, `active` = ?, `parent_id` = ? WHERE `comments`.`id` = ?");
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

	public static ArrayList<Comment> getCommentsByUser(User user) throws SQLException {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			Connection con = CommentDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT comments.id,comments.text,comments.active,comments.created_at,comments.user_id,comments.parent_id,comments.notice_id FROM `comments` INNER JOIN users ON comments.user_id = users.id WHERE users.id = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(
						new Comment(user, rs.getString("text"), NoticeDaoImpl.getNoticeById(rs.getInt("notice_id"), con),
								CommentDaoImpl.getCommentById(rs.getInt("parent_id"), con)));
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	public static ArrayList<Comment> getCommentsByUser(User user, Connection con) throws SQLException {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT comments.id,comments.text,comments.active,comments.created_at,comments.user_id,comments.parent_id,comments.notice_id FROM `comments` INNER JOIN users ON comments.user_id = users.id WHERE users.id = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(new Comment(user, rs.getInt("id"), rs.getString("text"),
						NoticeDaoImpl.getNoticeById(rs.getInt("notice_id"), con),
						CommentDaoImpl.getCommentById(rs.getInt("parent_id"), con)));
			}
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	public static Comment getCommentById(int id, Connection con) throws SQLException {
		Comment comment = null;
		try {
			PreparedStatement ps = con.prepareStatement("SELECT * FROM comments WHERE comments.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				comment = new Comment(UserDaoImpl.getUserById(rs.getInt("user_id"), con), rs.getInt("id"),
						rs.getString("text"), NoticeDaoImpl.getNoticeById(rs.getInt("notice_id"), con),
						CommentDaoImpl.getCommentById(rs.getInt("parent_id"), con));
			}
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comment;
	}

	public static Comment getCommentById(int id){
		Comment comment = null;
		try {
			Connection con = CommentDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement("SELECT * FROM comments WHERE comments.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				comment = new Comment(UserDaoImpl.getUserById(rs.getInt("user_id"), con), rs.getInt("id"),
						rs.getString("text"), NoticeDaoImpl.getNoticeById(rs.getInt("notice_id"), con),
						CommentDaoImpl.getCommentById(rs.getInt("parent_id"), con));
			}
			ps.close();
			rs.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comment;
	}

	public static ArrayList<Comment> getCommentsByNotice(int noticeId, Connection con) throws SQLException {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			PreparedStatement ps = con.prepareStatement(
					"SELECT comments.id,comments.text,comments.active,comments.created_at, comments.parent_id FROM comments INNER JOIN notices ON comments.notice_id = notices.id WHERE notices.id = ?");
			ps.setInt(1, noticeId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(CommentDaoImpl.getCommentById(rs.getInt("id"), con));
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	public static ArrayList<Comment> getCommentsByNotice(int noticeId) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			Connection con = CommentDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT comments.id,comments.text,comments.active,comments.created_at, comments.parent_id FROM comments INNER JOIN notices ON comments.notice_id = notices.id WHERE notices.id = ?");
			ps.setInt(1, noticeId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(CommentDaoImpl.getCommentById(rs.getInt("id"), con));
			}
			ps.close();
			rs.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}

	public static int findPointsByComment(Comment comment) {
		int points = 0;
		try {
			Connection con = CommentDaoImpl.getConnection();
			PreparedStatement ps = con
					.prepareStatement("SELECT * FROM rating_comments WHERE comment_id = ?");
			ps.setInt(1, comment.getId());
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

	public static int rateComment(User user, Comment comment) {
		int status = 0;
		try {
			Connection con = CommentDaoImpl.getConnection();
			PreparedStatement ps = con
					.prepareStatement("INSERT INTO `rating_comments` (`user_id`, `comment_id`) VALUES (?, ?)");
			ps.setInt(1, user.getId());
			ps.setInt(2, comment.getId());
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}

	public static ArrayList<Comment> commentsRatedByUser(User user) {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			Connection con = CommentDaoImpl.getConnection();
			PreparedStatement ps = con.prepareStatement(
					"SELECT comments.id,comments.text,comments.active, comments.created_at, comments.parent_id, comments.notice_id FROM `rating_comments` JOIN comments ON `comment_id` = comments.id WHERE rating_comments.`user_id` = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(new Comment(user, rs.getString("text"), NoticeDaoImpl.getNoticeById(rs.getInt("notice_id")),
						CommentDaoImpl.getCommentById(rs.getInt("parent_id"))));
			}
			rs.close();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return comments;
	}
	public static int removePoint(User user, Comment comment) {
		int status = 0;
		try {
			Connection con = NoticeDaoImpl.getConnection();
			PreparedStatement ps = con
					.prepareStatement("DELETE FROM `rating_comments` WHERE `rating_comments`.`user_id` = ? AND `rating_comments`.`comment_id` = ?");
			ps.setInt(1, user.getId());
			ps.setInt(2, comment.getId());
			status = ps.executeUpdate();
			ps.close();
			con.close();
		} catch (Exception ex) {
			ex.printStackTrace();
		}
		return status;
	}
}
