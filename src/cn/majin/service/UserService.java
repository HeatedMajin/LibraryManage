package cn.majin.service;

import cn.majin.Exception.UserIsShutException;
import cn.majin.domain.User;

public interface UserService {

	/*
	 * 用户登录
	 */
	User login(String username, String password) throws UserIsShutException;

	/*
	 * 用户注册
	 */
	boolean register(User u);

	/*
	 * 用户修改
	 */
	void editUser(User u);

	//修改用户密码
	void changPassword(String username, String passowrd);


}