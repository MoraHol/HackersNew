package com.hackersnews.services;

import java.util.ArrayList;
import java.util.Date;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import com.hackersnews.controller.NoticeController;
import com.hackersnews.dao.NoticeDaoImpl;
import com.hackersnews.dao.UserDaoImpl;
import com.hackersnews.idao.INoticeDao;
import com.hackersnews.idao.IUserDao;
import com.hackersnews.model.Notice;

/**
 * 
 * @author Alexis Holguin github:MoraHol
 *
 */
@Path("/notices")
public class NoticeService {
	INoticeDao noticeDao = new NoticeDaoImpl();
	NoticeController noticeController = new NoticeController();
	IUserDao userDao = new UserDaoImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNotices() {
		try {
			return Response.ok(noticeController.getNoticesNewest()).build();
		}catch (Exception e) {
			// TODO: handle exception
			return Response.serverError().build();
		}
	}
	@GET
	@Path("/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNoticeById(@PathParam("id") int id) {
		Notice notice;
		System.out.println();
		try {
			notice = noticeDao.findNoticeById(id);
			return Response.ok(notice).build();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error api notices:" + e.getMessage());
			return Response.serverError().build();
		}
	}
	@GET
	@Path("/byUser/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getNoticeByUserName(@PathParam("username") String username) {
		ArrayList<Notice> notices;
		try {
			notices = noticeDao.findNoticesByUser(userDao.findUserByUserName(username));
			return Response.ok(notices).build();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error api notices:" + e.getMessage());
			return Response.serverError().build();
		}
	}
	@POST
	@Path("/register/{title}/{url}/user/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerNotice(@PathParam("username") String username,@PathParam("title") String title, @PathParam("url") String url) {
		try {
			Notice notice = new Notice(userDao.findUserByUserName(username), title, url, new Date());
			int status = noticeDao.save(notice);
			if(status > 0) {
				return Response.status(Response.Status.CREATED).build();
			}else {
				return Response.status(Response.Status.BAD_REQUEST).build();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error api notices:" + e.getMessage());
			return Response.serverError().build();
		}
	}
	@PUT
	@Path("/update/{id}/{title}/{url}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateNotice(@PathParam("id") int id, @PathParam("title") String title,@PathParam("url") String url ) {
		try {
			Notice notice = noticeDao.findNoticeById(id);
			notice.setTitle(title);
			notice.setUrl(url);
			int status = noticeDao.save(notice);
			if(status > 0) {
				return Response.status(Response.Status.OK).build();
			}else {
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
			
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error api notices:" + e.getMessage());
			return Response.serverError().build();
		}
	}
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id) {
		try {
			int status = noticeDao.delete(id);
			if(status > 0) {
				return Response.status(Response.Status.OK).build();
			}else {
				return Response.status(Response.Status.NOT_MODIFIED).build();
			}
		} catch (Exception e) {
			// TODO: handle exception
			return Response.serverError().build();
		}
	}
}
