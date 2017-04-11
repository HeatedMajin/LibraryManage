package cn.majin.serviceImpl;

import cn.majin.Dao.UserDao;
import cn.majin.Exception.UserIsShutException;
import cn.majin.domain.User;
import cn.majin.factory.DaoFactory;
import cn.majin.service.UserService;

public class UserServiceImpl implements UserService {

	// UserDao dao = new UserDaoImpl(); // 这里可以使用Dao 工厂实现Dao层和Service层进行解绑
	private UserDao dao = DaoFactory.getInstance().generateDaoImpl(UserDao.class);

	/*
	 * 用户登录
	 */
	public User login(String username, String password) throws UserIsShutException {
		User result = dao.findUser(username, password);
		if (result == null) {
			// 这个用户名和密码的用户不存在
			return null;
		} else if(dao.isShut(username)){
			//这是一个被封的号
			throw new UserIsShutException();
		}else {
			// 刚好找到了这个用户
			return result;
		}
	}

	/*
	 * 用户注册
	 */
	public boolean register(User u) {
		String name = u.getUsername();
		if (dao.isExist(name)) {
			// 这个用户名字已经存在
			return false;
		} else {
			// 这个用户名不存在可以注册
			dao.addUser(u);
			return true;
		}
	}

	/*
	 * 用户修改
	 */
	public void editUser(User u) {
		dao.editUser(u);
	}

	// 修改用户密码
	public void changPassword(String username, String passowrd) {
		dao.changePassword(username, passowrd);

	}

}
