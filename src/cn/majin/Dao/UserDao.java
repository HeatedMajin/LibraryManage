package cn.majin.Dao;

import cn.majin.domain.User;

public interface UserDao {

	/*
	 * �����ݿ��м����û�
	 */
	void addUser(User u);

	/*
	 * �����û�������������û�,���������û������룬�������ֻ����һ��һ�����
	 */
	User findUser(String username, String password);

	/*
	 * ����û����ǲ����Ѿ�����
	 */
	boolean isExist(String username);

	/*
	 * �޸��û���Ϣ
	 */
	void editUser(User u);

	// �޸��û�������
	void changePassword(String username, String passowrd);

	boolean isShut(String username);

}