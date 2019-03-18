package controllers;

import models.Comment;

import models.Notice;
import models.User;

public class CommentController {
	public static boolean newComment(User user, Notice notice, Comment parent, String text) {
		try {
			Comment comment = new Comment(user, text, notice, parent);
			user.getComments().add(comment);
			notice.getComments().add(comment);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public Comment searchComment(int id) {
		return null;
	}

	public boolean editComment(Comment comment, String text) {
		return false;
	}

	public boolean rateCommment(Comment comment) {
		return false;
	}
}
