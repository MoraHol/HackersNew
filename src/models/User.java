package models;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class User{
	private int id;
	private String userName;
	private String password;
	private int karma;
	private String email;
	private Date createdAt;
	private String aboutme;

	public User(String userName, String password) {
		this.userName = userName;
		this.password = password;
		this.karma = 0;
		this.email = "";
		this.createdAt = new Date();
		this.aboutme = "";
	}

	public User() {

	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public int getKarma() {
		return karma;
	}

	public void setKarma(int karma) {
		this.karma = karma;
	}

	/**
	 * @return the userName
	 */
	public String getUserName() {
		return userName;
	}

	/**
	 * @param userName the userName to set
	 */
	public void setUserName(String userName) {
		this.userName = userName;
	}

	/**
	 * @return the password
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * @param password the password to set
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	public void addKarma() {
		this.karma++;
	}

	public String getAboutme() {
		return aboutme;
	}

	public void setAboutme(String aboutme) {
		this.aboutme = aboutme;
	}

	public String birth() {
		SimpleDateFormat sdf = new SimpleDateFormat("MMMM dd,yyyy");
		return sdf.format(this.createdAt);
	}

	public int getId() {
		return id;
	}

	public void setId(int i) {
		this.id = i;
	}
}
