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
	public int save(Comment comment) throws Exception;

	public int update(Comment comment) throws Exception;

	public int delete(int id) throws Exception;

	public List<Comment> findCommentsByUser(User user) throws Exception;

	public Comment CommentById(int id) throws Exception;

	public List<Comment> findCommentsByNotice(Notice notice) throws Exception;

	public int findPointsByComment(Comment comment) throws Exception;

	public int rateComment(User user, Comment comment) throws Exception;

	public List<Comment> findCommentsRatedByUser(User user) throws Exception;

	public int removePoint(User user, Comment comment) throws Exception;
}
