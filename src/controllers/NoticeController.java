package controllers;

import java.util.ArrayList;

import models.Notice;
import models.User;

public class NoticeController {
	private static int idSerial = 1;

	public Notice searchNotice(int id) {
		return null;
	}

	public static boolean createNotice(User user, String title, String url) {
		try {
			Notice notice = new Notice(user, title, url, idSerial);
			user.getNotices().add(notice);
			idSerial++;
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static boolean deleteNotice(Notice notice) {
		try {
			notice.getUser().getNotices().remove(notice);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static boolean editNotice(Notice notice, String title) {
		try {
			notice.setTitle(title);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}
	public static ArrayList<Notice> getNoticesNewest(){
		ArrayList<Notice> noticeNewest = new ArrayList<Notice>();
		for (User user : UserController.getUsers()) {
			noticeNewest.addAll(user.getNotices());
		}
		noticeNewest = quickSort(noticeNewest);
		try {
		noticeNewest = (ArrayList<Notice>) noticeNewest.subList(0,19);
		}catch (Exception e) {
			// TODO: handle exception
		}
		return noticeNewest;
	}
	private static ArrayList<Notice> quickSort(ArrayList<Notice> array) {
		ArrayList<Notice> array1 = new ArrayList<Notice>();
		ArrayList<Notice> less_subarray = new ArrayList<Notice>();
		ArrayList<Notice> greater_subarray = new ArrayList<Notice>();

		if (array.size() == 0) {
			// el arreglo esta ordenado
			return array;
		} else {
			// tomar el primer elemento como pivote
			Notice pivot = array.get(0);
			for (int i = 1; i < array.size(); i++) {
				if (array.get(i).getCreatedAt().after(pivot.getCreatedAt())) {
					less_subarray.add(array.get(i));
				} else {
					greater_subarray.add(array.get(i));
				}
			}

			less_subarray = quickSort(less_subarray);
			greater_subarray = quickSort(greater_subarray);

			if (less_subarray.size() < 1 && greater_subarray.size() < 1) {
				array1.add(pivot);
			} else {
				if (less_subarray.size() < 1) {
					array1.add(pivot);
					for (int i = 0; i < greater_subarray.size(); i++) {
						array1.add(greater_subarray.get(i));
					}
				} else {
					if (greater_subarray.size() < 1) {
						for (int i = 0; i < less_subarray.size(); i++) {
							array1.add(less_subarray.get(i));
						}
						array1.add(pivot);
					} else {
						for (int i = 0; i < less_subarray.size(); i++) {
							array1.add(less_subarray.get(i));
						}
						array1.add(pivot);
						for (int i = 0; i < greater_subarray.size(); i++) {
							array1.add(greater_subarray.get(i));
						}
					}
				}
			}

		}
		return array1;
	}

}
