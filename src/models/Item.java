package models;

import java.util.ArrayList;
import java.util.Date;

public class Item {
	private Date createdAt;
	private int id;
	private ArrayList<String> points;
	private User user;
	private boolean active;

	public Item(User user,int id) {
		this.id = id;
		this.user = user;
		this.createdAt = new Date();
		this.points = new ArrayList<String>();
	}
	public Item(User user,int id,Date createdAt) {
		this.id = id;
		this.user = user;
		this.createdAt = createdAt;
		this.points = new ArrayList<String>();
	}
	public Item(User user,int id,Date createdAt, ArrayList<String> points) {
		this.id = id;
		this.user = user;
		this.createdAt = createdAt;
		this.points = points;
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getPoints() {
		return this.points.size();
	}

	public void setPoints(ArrayList<String> points) {
		this.points = points;
	}

	public boolean userVoted(String userId) {
		if (this.points.contains(userId)) {
			System.out.println(this.points.contains(userId));
			return true;
		} else {
			return false;
		}
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public void addPoint(String userId) {
		this.points.add(userId);
	}

	public void removePoint(String userId) {
		this.points.remove(userId);
	}

	public String getAge() {
		int difference = (int) ((new Date().getTime() - this.createdAt.getTime()) / 1000);
		int days = 0;
		int hours = 0;
		int minutes = 0;
		if (difference > 86400) {
			days = (int) Math.floor(difference / 86400);
			difference = difference - (days * 86400);
			return days + " days ago";
		}
		if (difference > 3600) {
			hours = (int) Math.floor(difference / 3600);
			difference = difference - (hours * 3600);
			return hours + " hours ago";
		}
		if (difference > 60) {
			minutes = (int) Math.floor(difference / 60);
			difference = difference - (minutes * 60);
			return minutes + " minutes ago";
		} else {
			return difference + " seconds ago";
		}
	}
	public boolean isActive() {
		return active;
	}
	public void setActive(boolean active) {
		this.active = active;
	}
}
