/**
 * 
 */
package com.hackersnews.idao;

import java.util.ArrayList;
import com.hackersnews.model.User;

/**
 * @author Alexis Holguin github:MoraHol
 *
 */
public interface IUserDao {
	public int save(User user) throws Exception;

	public int update(User user) throws Exception;

	public int delete(int id) throws Exception;

	public User findUserById(int id) throws Exception;

	public ArrayList<User> findAll() throws Exception;

	public User findUserByUserName(String username) throws Exception;

}
