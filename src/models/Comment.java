package models;

import java.util.ArrayList;

public class Comment extends Item {
	private String text;
	private Notice parentNotice;
	private Comment parentComment;
	private ArrayList<Comment> childs;
	
	public Comment(User user, String text, Notice parentNotice, Comment parentComment) {
		super(user);
		this.text = text;
		this.parentNotice = parentNotice;
		this.parentComment = parentComment;
		setChilds(new ArrayList<Comment>());
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

	public ArrayList<Comment> getChilds() {
		return childs;
	}

	public void setChilds(ArrayList<Comment> childs) {
		this.childs = childs;
	}

}
