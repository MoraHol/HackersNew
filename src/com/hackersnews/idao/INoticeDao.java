package com.hackersnews.idao;

import java.util.List;

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

	public List<Notice> findNoticesByUser(User user)throws Exception;

	public List<Notice> findAllNotices()throws Exception;

	public int findPointByNotice(Notice notice)throws Exception;

	public int rateNotice(Notice notice);

	public int removePoint(User user, Notice notice);

	public List<Notice> findNoticesRatedByUser(User user);
}
