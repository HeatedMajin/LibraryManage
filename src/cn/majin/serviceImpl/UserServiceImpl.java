package cn.majin.serviceImpl;

import cn.majin.Dao.UserDao;
import cn.majin.Exception.UserIsShutException;
import cn.majin.domain.User;
import cn.majin.factory.DaoFactory;
import cn.majin.service.UserService;

public class UserServiceImpl implements UserService {

	// UserDao dao = new UserDaoImpl(); // �������ʹ��Dao ����ʵ��Dao���Service����н��
	private UserDao dao = DaoFactory.getInstance().generateDaoImpl(UserDao.class);

	/*
	 * �û���¼
	 */
	public User login(String username, String password) throws UserIsShutException {
		User result = dao.findUser(username, password);
		if (result == null) {
			// ����û�����������û�������
			return null;
		} else if(dao.isShut(username)){
			//����һ������ĺ�
			throw new UserIsShutException();
		}else {
			// �պ��ҵ�������û�
			return result;
		}
	}

	/*
	 * �û�ע��
	 */
	public boolean register(User u) {
		String name = u.getUsername();
		if (dao.isExist(name)) {
			// ����û������Ѿ�����
			return false;
		} else {
			// ����û��������ڿ���ע��
			dao.addUser(u);
			return true;
		}
	}

	/*
	 * �û��޸�
	 */
	public void editUser(User u) {
		dao.editUser(u);
	}

	// �޸��û�����
	public void changPassword(String username, String passowrd) {
		dao.changePassword(username, passowrd);

	}

}
