package com.hackersnews.idao;

import java.util.ArrayList;

import com.hackersnews.model.Notice;
import com.hackersnews.model.User;

/**
 * @author Alexis Holguin github:MoraHol
 *
 */
public interface INoticeDao {
	public int save(Notice notice)throws Exception;

	public int update(Notice notice)throws Exception;

	public int delete(int id)throws Exception;

	public Notice findNoticeById(int id)throws Exception;

	public ArrayList<Notice> findNoticesByUser(User user)throws Exception;

	public ArrayList<Notice> findAllNotices()throws Exception;

	public int findPointsByNotice(Notice notice)throws Exception;

	public int rateNotice(User user,Notice notice);

	public int removePoint(User user, Notice notice);

	public ArrayList<Notice> findNoticesRatedByUser(User user);
}
