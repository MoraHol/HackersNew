package com.hackersnews.idao;

import java.util.List;

import com.hackersnews.model.Comment;
import com.hackersnews.model.Notice;
import com.hackersnews.model.User;
/**
 * @author Alexis Holguin github:MoraHol
 *
 */
public interface ICommentDao {
	public int save(Comment comment);
	public int update(Comment comment);
	public int delete(int id);
	public List<Comment> findCommentsByUser(User user);
	public Comment CommentById(int id);
	public List<Comment> findCommentsByNotice(Notice notice);
	public int findPointsByComment(Comment comment);
	public int rateComment(User user,Comment comment);
	public List<Comment> findCommentsRatedByUser(User user);
	public int removePoint(User user, Comment comment);
}
