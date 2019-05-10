package com.hackersnews.model;

import java.util.Date;

public class Item {
	private Date createdAt;
	private int id;
	private User user;
	private boolean active;
	
	public Item(User user) {
		this.user = user;
		this.createdAt = new Date();
	}
	
	public Item(User user,int id) {
		this.id = id;
		this.user = user;
		this.createdAt = new Date();
	}
	public Item(User user,int id,Date createdAt) {
		this.id = id;
		this.user = user;
		this.createdAt = createdAt;
	}
	public Item(User user,Date createdAt) {
		this.user = user;
		this.createdAt = createdAt;
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

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
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
