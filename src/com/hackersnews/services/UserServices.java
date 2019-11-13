package com.hackersnews.services;

import java.util.ArrayList;

import javax.ws.rs.DELETE;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;

import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;


import com.hackersnews.controller.UserController;
import com.hackersnews.dao.UserDaoImpl;
import com.hackersnews.idao.IUserDao;
import com.hackersnews.model.User;

/**
 * 
 * @author Alexis Holguin github:MoraHol
 *
 */
@Path("/users")
public class UserServices {
	private IUserDao userDao = new UserDaoImpl();
	
	@GET
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUsers() {
		ArrayList<User> users = new ArrayList<>();
		try {
			users = userDao.findAll();
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error api users:" + e.getMessage());
		}
		
		return Response.ok(users).build();
	}
	
	@GET
	@Path("/{username}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response getUserByUserName(@PathParam("username") String username) {
		User user = new User();
		System.out.println();
		try {
			user = userDao.findUserByUserName(username);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error api users:" + e.getMessage());
		}
		return Response.ok(user).build();
	}
	@POST
	@Path("/register/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response registerUser(@PathParam("username") String username, @PathParam("password") String password) {
		User user = new User();
		UserController userController = new UserController();
		try {
			user = userController.createAccount(username, password);
		}catch (Exception e) {
			// TODO: handle exception
			System.out.println("Error api users:" + e.getMessage());
			return Response.status(Response.Status.NOT_FOUND).build();
		}
		return Response.ok(user).build();
	}
	
	@PUT
	@Path("/update/{username}/{password}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response updateUser(@PathParam("username") String username, @PathParam("password") String password) {
		try {
		User user = userDao.findUserByUserName(username);
		user.setPassword(password);
		userDao.update(user);
		return Response.ok().build();
		}catch (Exception e) {
			// TODO: handle exception
			return Response.status(Response.Status.NOT_FOUND).build();
		}
	}
	@DELETE
	@Path("/delete/{id}")
	@Produces(MediaType.APPLICATION_JSON)
	public Response deleteUser(@PathParam("id") int id) {
		try {
			int status = userDao.delete(id);
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
