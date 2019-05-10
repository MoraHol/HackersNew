package com.hackersnews.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Types;
import java.util.ArrayList;

import com.hackersnews.idao.ICommentDao;
import com.hackersnews.idao.INoticeDao;
import com.hackersnews.idao.IUserDao;
import com.hackersnews.model.Comment;
import com.hackersnews.model.User;

public class CommentDaoImpl extends ConnectionSQL implements ICommentDao {
	private INoticeDao noticeDao;
	private IUserDao userDao;

	public CommentDaoImpl() {
		// TODO Auto-generated constructor stub
		noticeDao = new NoticeDaoImpl();
		userDao = new UserDaoImpl();
	}

	@Override
	public int save(Comment comment) throws SQLException {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
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
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" CommentDaoImpl: " + e.getMessage());
			throw e;
		}
		return status;
	}

	public int delete(int id) {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("DELETE FROM comments WHERE comments.id = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			this.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public int update(Comment comment) {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement(
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
			this.disconnect();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return status;
	}

	public ArrayList<Comment> findCommentsByUser(User user) throws Exception {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement(
					"SELECT comments.id,comments.text,comments.active,comments.created_at,comments.user_id,comments.parent_id,comments.notice_id FROM `comments` INNER JOIN users ON comments.user_id = users.id WHERE users.id = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(new Comment(user, rs.getString("text"), noticeDao.findNoticeById(rs.getInt("notice_id")),
						findCommentById(rs.getInt("parent_id"))));
			}
			ps.close();
			rs.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" CommentDaoImpl: " + e.getMessage());
			throw e;
		}
		return comments;
	}

	public Comment findCommentById(int id) throws Exception {
		Comment comment = null;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("SELECT * FROM comments WHERE comments.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				comment = new Comment(userDao.findUserById(rs.getInt("user_id")), rs.getInt("id"), rs.getString("text"),
						noticeDao.findNoticeById(rs.getInt("notice_id")), findCommentById(rs.getInt("parent_id")));
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println(" CommentDaoImpl: " + e.getMessage());
			throw e;
		}
		return comment;
	}

	public ArrayList<Comment> findCommentsByNotice(int noticeId) throws Exception {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement(
					"SELECT comments.id,comments.text,comments.active,comments.created_at, comments.parent_id FROM comments INNER JOIN notices ON comments.notice_id = notices.id WHERE notices.id = ?");
			ps.setInt(1, noticeId);
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(findCommentById(rs.getInt("id")));
			}
			ps.close();
			rs.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" CommentDaoImpl: " + e.getMessage());
			throw e;
		}
		return comments;
	}

	public int findPointsByComment(Comment comment) throws Exception {
		int points = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("SELECT * FROM rating_comments WHERE comment_id = ?");
			ps.setInt(1, comment.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				points++;
			}
			rs.close();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" CommentDaoImpl: " + e.getMessage());
			throw e;
		}
		return points;
	}

	public int rateComment(User user, Comment comment) throws Exception {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("INSERT INTO `rating_comments` (`user_id`, `comment_id`) VALUES (?, ?)");
			ps.setInt(1, user.getId());
			ps.setInt(2, comment.getId());
			status = ps.executeUpdate();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" CommentDaoImpl: " + e.getMessage());
			throw e;
		}
		return status;
	}

	public ArrayList<Comment> findCommentsRatedByUser(User user) throws Exception {
		ArrayList<Comment> comments = new ArrayList<Comment>();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement(
					"SELECT comments.id,comments.text,comments.active, comments.created_at, comments.parent_id, comments.notice_id FROM `rating_comments` JOIN comments ON `comment_id` = comments.id WHERE rating_comments.`user_id` = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				comments.add(new Comment(user, rs.getString("text"),
						noticeDao.findNoticeById(rs.getInt("notice_id")), findCommentById(rs.getInt("parent_id"))));
			}
			rs.close();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" CommentDaoImpl: " + e.getMessage());
			throw e;
		}
		return comments;
	}

	public int removePoint(User user, Comment comment) throws Exception {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement(
					"DELETE FROM `rating_comments` WHERE `rating_comments`.`user_id` = ? AND `rating_comments`.`comment_id` = ?");
			ps.setInt(1, user.getId());
			ps.setInt(2, comment.getId());
			status = ps.executeUpdate();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println(" CommentDaoImpl: " + e.getMessage());
			throw e;
		}
		return status;
	}
}
