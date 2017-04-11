package cn.majin.Dao;

import cn.majin.domain.User;

public interface UserDao {

	/*
	 * 向数据库中加入用户
	 */
	void addUser(User u);

	/*
	 * 根据用户名和密码查找用户,这里由于用户名主码，所以最多只会有一个一个结果
	 */
	User findUser(String username, String password);

	/*
	 * 检查用户名是不是已经存在
	 */
	boolean isExist(String username);

	/*
	 * 修改用户信息
	 */
	void editUser(User u);

	// 修改用户的密码
	void changePassword(String username, String passowrd);

	boolean isShut(String username);

}