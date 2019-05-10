package com.hackersnews.controller;

import java.util.ArrayList;

import com.hackersnews.dao.CommentDaoImpl;
import com.hackersnews.model.Comment;
import com.hackersnews.model.Notice;
import com.hackersnews.model.User;

public class CommentController {
	public static boolean newComment(User user, Notice notice, Comment parent, String text) {
		try {
			Comment comment = new Comment(user, text, notice, parent);
			CommentDaoImpl.save(comment);
			return true;
		} catch (Exception e) {
			// TODO: handle exception
			return false;
		}
	}

	public static Comment searchComment(int id){
		return CommentDaoImpl.getCommentById(id);
	}

	public boolean editComment(Comment comment, String text) {
		comment.setText(text);
		CommentDaoImpl.update(comment);
		return false;
	}

	public boolean rateCommment(Comment comment, User user) {
		return false;
	}

	private static ArrayList<Comment> quickSortId(ArrayList<Comment> array) {
		ArrayList<Comment> array1 = new ArrayList<Comment>();
		ArrayList<Comment> less_subarray = new ArrayList<Comment>();
		ArrayList<Comment> greater_subarray = new ArrayList<Comment>();

		if (array.size() == 0) {
			// el arreglo esta ordenado
			return array;
		} else {
			// tomar el primer elemento como pivote
			Comment pivot = array.get(0);
			for (int i = 1; i < array.size(); i++) {
				if (array.get(i).getId() < pivot.getId()) {
					less_subarray.add(array.get(i));
				} else {
					greater_subarray.add(array.get(i));
				}
			}

			less_subarray = quickSortId(less_subarray);
			greater_subarray = quickSortId(greater_subarray);

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

	private static Comment binarySearch(ArrayList<Comment> comments, int id) {
		int n = comments.size();
		int centro, inf = 0, sup = n - 1;
		while (inf <= sup) {
			centro = (sup + inf) / 2;
			if (comments.get(centro).getId() == id)
				return comments.get(centro);
			else if (id < comments.get(centro).getId()) {
				sup = centro - 1;
			} else {
				inf = centro + 1;
			}
		}
		return null;
	}
}
