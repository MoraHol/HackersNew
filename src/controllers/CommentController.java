package controllers;

import javax.xml.stream.events.Comment;

import models.Notice;
import models.User;

public class CommentController {
	public boolean newComment(User user, Notice notice, int parent) {
		return false;	
	}
	public Comment searchComment(int id) {
		return null;
	}
	public boolean editComment(Comment comment,String text) {
		return false;
	}
	public boolean rateCommment(Comment comment) {
		return false;
	}
}
