package cn.majin.Web.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import cn.majin.Exception.UserIsShutException;
import cn.majin.Utils.Globals;
import cn.majin.Utils.WebUtils;
import cn.majin.domain.LoginFormBean;
import cn.majin.domain.User;
import cn.majin.service.UserService;
import cn.majin.serviceImpl.UserServiceImpl;

public class Login extends HttpServlet {

	// 提供登录界面，并且若是记住了密码，则不用用户输入
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		int x = 0;
		for (; cookies != null && x < cookies.length; x++) {
			if ("user".equals(cookies[x].getName())) {
				break;// 退出循环
			}
		}
		if (cookies != null && x < cookies.length) {
			String value = cookies[x].getValue();
			cookies[x].setMaxAge(0);// 使cookie失效
			String arr[] = value.split(":");
			if (arr.length == 2) {
				String name = arr[0];
				String password = arr[1];
				LoginFormBean bean = new LoginFormBean(name, password, "remember");
				request.setAttribute("formbean", bean);
			}
		}
		request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
		return;
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 得到用户名和密码
		LoginFormBean bean = WebUtils.request2bean(request, LoginFormBean.class);
		UserService userService = new UserServiceImpl();

		User user = null;
		try {
			// 对用户名和密码进行校验
			String username = bean.getUsername();
			String password = bean.getPassword();
			user = userService.login(username, password);
		} catch (UserIsShutException e) {
			request.setAttribute("notice", "你的账号因非法操作暂时被封，若想解封请联系管理员~");
			request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
			return;
		}

		if (null != user) {

			// 登录成功
			if ("remember".equals(bean.getRemember())) {
				// 把用户的登录账号和密码保存到客户及
				Cookie cookie = new Cookie("user", user.getUsername() + ":" + user.getPassword());
				cookie.setMaxAge(24 * 60 * 60);// 保存24个小时
				// cookie.setPath(request.getContextPath() + "/loginServlet");
				response.addCookie(cookie);
			}
			// 将用户信息保存到Session中
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("preference", Globals.preference);

			// 重定向
			response.sendRedirect(request.getContextPath() + "/index.jsp");

		} else {
			// 登录失败
			request.setAttribute("formbean", bean);
			request.setAttribute("error", "用户名或密码错误");
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;

		}
	}

}
