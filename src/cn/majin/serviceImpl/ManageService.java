package cn.majin.serviceImpl;

import java.util.List;

import cn.majin.DaoImpl.ManageDao;
import cn.majin.domain.ChangeBean;
import cn.majin.domain.Role;
import cn.majin.domain.User;

public class ManageService {
	
	private ManageDao dao = new ManageDao();

	public List<ChangeBean> getAllChange() {
		return dao.getAllChange();
	}

	public List<User> getAllUser() {
		return dao.getAllUser();
	}

	public List<Role> getAllRole() {
		return dao.getAllRole();
	}

	public void setUserRole(String uname, String[] rid) {
		dao.setUserRole(uname, rid);
	}
	
	public User getUserByName(String name){
		return dao.getUserByName(name);
	}

	public void fenghao(String uname) {
		dao.fenghao(uname);
	}

	public List<String> getAllShutUsername() {
		return dao.getAllShutUsername();
	}

	public void jiefeng(String uname) {
		dao.jiefeng(uname);
	}

	public void updateOp_user(String name,String book_name) {
		dao.updateOp_user(name,book_name);
	}
}
