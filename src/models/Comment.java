package models;

public class Comment {
	private User user;
	private int points;
	private String text;
	private Notice parentNotice;
	private Comment parentComment;
	
	public Comment(User user, String text, Notice parentNotice, Comment parentComment) {
		this.user = user;
		this.text = text;
		this.parentNotice = parentNotice;
		this.parentComment = parentComment;
		this.points = 0;
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

	public String getText() {
		return text;
	}

	public void setText(String text) {
		this.text = text;
	}

	public Notice getParentNotice() {
		return parentNotice;
	}

	public void setParentNotice(Notice parentNotice) {
		this.parentNotice = parentNotice;
	}

	public Comment getParentComment() {
		return parentComment;
	}

	public void setParentComment(Comment parentComment) {
		this.parentComment = parentComment;
	}

}
