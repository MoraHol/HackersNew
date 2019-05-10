package com.hackersnews.dao;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import com.hackersnews.idao.INoticeDao;
import com.hackersnews.idao.IUserDao;
import com.hackersnews.model.Notice;
import com.hackersnews.model.User;

public class NoticeDaoImpl extends ConnectionSQL implements INoticeDao {
	private IUserDao userDao;
	
	public NoticeDaoImpl() {
		userDao = new UserDaoImpl();
	}
	
	public Notice findNoticeById(int id) throws SQLException {
		Notice notice = null;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement("SELECT * FROM `notices` WHERE notices.id = ?");
			ps.setInt(1, id);
			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				notice = new Notice(userDao.findUserById(rs.getInt("user_id")), rs.getInt("id"),
						rs.getString("title"), rs.getString("url"), rs.getDate("created_at"));
			}
		} catch (Exception e) {
			// TODO: handle exception
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return notice;
	}

	public  int save(Notice notice) {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("INSERT INTO `notices` (`title`, `url`, `user_id`) VALUES (?, ?, ?)");
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getUrl());
			ps.setInt(3, notice.getUser().getId());
			status = ps.executeUpdate();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return status;
	}

	public  int delete(int id) {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement("DELETE FROM notices WHERE notices.id = ?");
			ps.setInt(1, id);
			status = ps.executeUpdate();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return status;
	}

	public  ArrayList<Notice> findNoticesByUser(User user) {
		ArrayList<Notice> notices = new ArrayList<Notice>();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement(
					"SELECT notices.id,title,url,notices.created_at,type FROM `notices` INNER JOIN users ON user_id = users.id WHERE users.id = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notices.add(new Notice(user, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
			}
			ps.close();
			rs.close();
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return notices;
	}


	public  int update(Notice notice) {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("UPDATE `notices` SET `title` = ?, `url` = ? WHERE `notices`.`id` = ?");
			ps.setString(1, notice.getTitle());
			ps.setString(2, notice.getUrl());
			ps.setInt(3, notice.getId());
			status = ps.executeUpdate();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return status;
	}

	public  ArrayList<Notice> findAllNotices() throws SQLException {
		ArrayList<Notice> notices = new ArrayList<Notice>();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement("select * from notices");
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				Notice notice = findNoticeById(rs.getInt("id"));
				notices.add(notice);
			}
			
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}finally {
			this.disconnect();
		}
		return notices;
	}
	public  int findPointsByNotice(Notice notice) {
		int points = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("SELECT * FROM rating_users WHERE notice_id = ?");
			ps.setInt(1, notice.getId());
			ResultSet rs = ps.executeQuery();
			while(rs.next()) {
				points++;
			}
			rs.close();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return points;
	}

	public  int rateNotice(User user, Notice notice) {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("INSERT INTO `rating_users` (`user_id`, `notice_id`) VALUES (?, ?)");
			ps.setInt(1, user.getId());
			ps.setInt(2, notice.getId());
			status = ps.executeUpdate();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return status;
	}
	public  int removePoint(User user, Notice notice) {
		int status = 0;
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection()
					.prepareStatement("DELETE FROM `rating_users` WHERE `rating_comments`.`user_id` = ? AND `rating_comments`.`comment_id` = ?");
			ps.setInt(1, user.getId());
			ps.setInt(2, notice.getId());
			status = ps.executeUpdate();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return status;
	}
	public  ArrayList<Notice> findNoticesRatedByUser(User user) {
		ArrayList<Notice> notices = new ArrayList<Notice>();
		try {
			this.connect();
			PreparedStatement ps = this.getJdbcConnection().prepareStatement(
					"SELECT notices.id,notices.url,notices.title, notices.created_at FROM `rating_users` JOIN notices ON `notice_id` = notices.id WHERE rating_users.`user_id` = ?");
			ps.setInt(1, user.getId());
			ResultSet rs = ps.executeQuery();
			while (rs.next()) {
				notices.add(new Notice(user,rs.getString("title"),rs.getString("url"),rs.getDate("created_at")));
			}
			rs.close();
			ps.close();
			this.disconnect();
		} catch (Exception e) {
			System.out.println("NoticeDaoImpl: " + e.getMessage());
		}
		return notices;
	}
}
