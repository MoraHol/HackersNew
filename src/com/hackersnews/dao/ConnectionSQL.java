/**
 * 
 */
package com.hackersnews.dao;

import java.sql.DriverManager;
import java.sql.SQLException;

import com.mysql.jdbc.Connection;

/**
 * @author Alexis Holguin github:MoraHol
 *
 */
public class ConnectionSQL {
	private Connection jdbcConnection;
	private String jdbcURL;
	private String jdbcDataBase;
	private String jdbcUsername;
	private String jdbcPassword;

	public ConnectionSQL() {
		this.jdbcURL = "jdbc:mysql://localhost:3306/";
		this.jdbcDataBase = "hackersnews";
		this.jdbcUsername = "root";
		this.jdbcPassword = "";
	}

	public void connect() throws SQLException {
		if (jdbcConnection == null || jdbcConnection.isClosed()) {
			try {
				Class.forName("com.mysql.jdbc.Driver");
			} catch (ClassNotFoundException e) {
				throw new SQLException(e);
			}
			jdbcConnection = (Connection) DriverManager.getConnection(jdbcURL + jdbcDataBase, jdbcUsername, jdbcPassword);
		}
	}

	public void disconect() throws SQLException {
		if (jdbcConnection != null && !jdbcConnection.isClosed()) {
			jdbcConnection.close();
		}
	}

	public Connection getJdbcConnection() {
		return jdbcConnection;
	}

}
