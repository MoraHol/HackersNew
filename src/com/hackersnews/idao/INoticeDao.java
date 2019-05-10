package com.hackersnews.idao;

import java.util.List;

import com.hackersnews.model.Notice;
import com.hackersnews.model.User;

public interface INoticeDao {
	public int save(Notice notice);
	public int update(Notice notice);
	public int delete(int id);
	public Notice findNoticeById(int id);
	public List<Notice> findNoticesByUser(User user);
	public List<Notice> findAllNotices();
	public int findPointByNotice(Notice notice);
	public int rateNotice(Notice notice);
	public int removePoint(User user,Notice notice);
	public List<Notice> findNoticesRatedByUser(User user);	
}
