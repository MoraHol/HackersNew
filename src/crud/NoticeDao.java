package crud;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;

import models.Notice;
import models.User;

public class NoticeDao {
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

	public static Notice getNoticeById(int id) {
		return null;
	}

	public static int save(Notice notice) {
		return 0;
	}

	public static int delete(int id) {
		return 0;
	}

	public static ArrayList<Notice> getNoticesByUser(User user) throws ClassNotFoundException, SQLException {
		Connection con = NoticeDao.getConnection();
		PreparedStatement ps = con.prepareStatement(
				"SELECT notices.id,title,url,notices.created_at,type FROM `notices` INNER JOIN users ON users_id = users.id WHERE users.id = ?");
		ps.setInt(1, user.getId());
		ResultSet rs = ps.executeQuery();
		ArrayList<Notice> notices = new ArrayList<Notice>();
		while (rs.next()) {
			notices.add(new Notice(user, rs.getInt(1), rs.getString(2), rs.getString(3), rs.getDate(4)));
		}
		return notices;
	}

	public static int update(Notice notice) {
		return 0;
	}
}
