/**
 * 
 */
package com.hackersnews.idao;

import java.util.List;
import com.hackersnews.model.User;

/**
 * @author Alexis Holguin github:MoraHol
 *
 */
public interface IUserDao {
	public int save(User user);
	public int update(User user);
	public int delete(int id);
	public User findUserById(int id);
	public List<User> findAll();
	
}
