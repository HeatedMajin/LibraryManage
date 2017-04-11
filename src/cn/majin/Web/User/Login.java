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

	// �ṩ��¼���棬�������Ǽ�ס�����룬�����û�����
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		Cookie[] cookies = request.getCookies();
		int x = 0;
		for (; cookies != null && x < cookies.length; x++) {
			if ("user".equals(cookies[x].getName())) {
				break;// �˳�ѭ��
			}
		}
		if (cookies != null && x < cookies.length) {
			String value = cookies[x].getValue();
			cookies[x].setMaxAge(0);// ʹcookieʧЧ
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
		// �õ��û���������
		LoginFormBean bean = WebUtils.request2bean(request, LoginFormBean.class);
		UserService userService = new UserServiceImpl();

		User user = null;
		try {
			// ���û������������У��
			String username = bean.getUsername();
			String password = bean.getPassword();
			user = userService.login(username, password);
		} catch (UserIsShutException e) {
			request.setAttribute("notice", "����˺���Ƿ�������ʱ���⣬����������ϵ����Ա~");
			request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
			return;
		}

		if (null != user) {

			// ��¼�ɹ�
			if ("remember".equals(bean.getRemember())) {
				// ���û��ĵ�¼�˺ź����뱣�浽�ͻ���
				Cookie cookie = new Cookie("user", user.getUsername() + ":" + user.getPassword());
				cookie.setMaxAge(24 * 60 * 60);// ����24��Сʱ
				// cookie.setPath(request.getContextPath() + "/loginServlet");
				response.addCookie(cookie);
			}
			// ���û���Ϣ���浽Session��
			HttpSession session = request.getSession();
			session.setAttribute("user", user);
			session.setAttribute("preference", Globals.preference);

			// �ض���
			response.sendRedirect(request.getContextPath() + "/index.jsp");

		} else {
			// ��¼ʧ��
			request.setAttribute("formbean", bean);
			request.setAttribute("error", "�û������������");
			request.getRequestDispatcher("/WEB-INF/jsp/user/login.jsp").forward(request, response);
			return;

		}
	}

}
