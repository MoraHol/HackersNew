package controllers;

import models.Notice;
import models.User;

public class NoticeController {
	public Notice searchNotice(int id) {
		return null;
	}
	public static boolean createNotice(User user,String title, String url) {
		try {
		Notice notice =  new Notice(user,title,url);
		user.getNotices().add(notice);
		return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public static boolean deleteNotice(Notice notice) {
		try {
		notice.getUser().getNotices().remove(notice);
		return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public static boolean editNotice(Notice notice, String title) {
		try {
			notice.setTitle(title);
			return true;
		}catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
}
