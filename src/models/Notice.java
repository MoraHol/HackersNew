package models;

public class Notice {
	private String url;
	private String title;
	private User user;
	private int points;
	private String type;

	public String getUrl() {
		return url;
	}

	public Notice(User user, String title, String url, String type) {
		this.user = user;
		this.title = title;
		this.url = url;
		this.type = type;
		this.points = 0;
	}

	public void setUrl(String url) {
		this.url = url;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public int getPoints() {
		return points;
	}

	public void setPoints(int points) {
		this.points = points;
	}

	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

}
