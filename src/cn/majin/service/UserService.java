package cn.majin.service;

import cn.majin.Exception.UserIsShutException;
import cn.majin.domain.User;

public interface UserService {

	/*
	 * �û���¼
	 */
	User login(String username, String password) throws UserIsShutException;

	/*
	 * �û�ע��
	 */
	boolean register(User u);

	/*
	 * �û��޸�
	 */
	void editUser(User u);

	//�޸��û�����
	void changPassword(String username, String passowrd);


}