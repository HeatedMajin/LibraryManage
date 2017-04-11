package Junit.testDAO;

import java.util.Date;

import org.junit.Test;

import cn.majin.Dao.UserDao;
import cn.majin.DaoImpl.UserDaoImpl;
import cn.majin.domain.User;

public class TestUserDao {
	UserDao dao =new UserDaoImpl();
	@Test
	public void testAdd() {
		User u = new User("ss", "gender", "password", "email", "phone", new Date(), "preference");
		dao.addUser(u);
	}
	
	@Test
	public void testupdate(){
		User u = new User("ss", "gr", "password", "email", "phone", new Date(), "preference");
		dao.editUser(u);
	}
	
	@Test
	public void testfind(){
		User u = dao.findUser("ss", "gr");
		System.out.println(u);
	}
	
	@Test
	public void testChangPassword(){
		dao.changePassword("majin", "145");
	}
	
}
