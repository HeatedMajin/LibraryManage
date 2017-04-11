package Junit.testService;

import org.junit.Test;

import cn.majin.Exception.UserIsShutException;
import cn.majin.domain.User;
import cn.majin.service.UserService;
import cn.majin.serviceImpl.UserServiceImpl;

public class TestUserService {
	@Test
	public void testLogin() throws UserIsShutException {
		String name = "123";
		String password = "123";
		UserService uService = new UserServiceImpl();
		User u = uService.login(name, password);
		System.out.println(u);

	}
	
}
