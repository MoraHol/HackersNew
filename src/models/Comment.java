package models;

public class Comment extends Item {
	private String text;
	private Notice parentNotice;
	private Comment parentComment;
	
	public Comment(User user, int id, String text, Notice parentNotice, Comment parentComment) {
		super(user,id);
		this.text = text;
		this.parentNotice = parentNotice;
		this.parentComment = parentComment;
	}
	public Comment(User user, String text, Notice parentNotice, Comment parentComment) {
		super(user);
		this.text = text;
		this.parentNotice = parentNotice;
		this.parentComment = parentComment;
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
