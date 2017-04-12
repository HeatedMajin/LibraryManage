package cn.majin.Web.User;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.User;
import cn.majin.service.UserService;
import cn.majin.serviceImpl.UserServiceImpl;

//修改用户的密码
public class ChangPasswordServlet extends HttpServlet {
	private String changePasswordPage = "/WEB-INF/jsp/user/changePassword.jsp";

	// 提供密码修改界面
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher(changePasswordPage).forward(request, response);
	}

	// 接受并校验密码修改表单
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		try {
			// 用户登录后会吧登录信息保存在session中
			User u = (User) request.getSession().getAttribute("user");
			// 用户表单中输入的旧密码
			String password = request.getParameter("oldpassword");
			Map<String, String> p_errors = new HashMap<String, String>();
			if (u == null || !u.getPassword().equals(password)) {
				// 旧密码输入错误
				p_errors.put("oldpassword", "旧密码错误，请重新输入~");
				request.setAttribute("p_errors", p_errors);
				request.getRequestDispatcher(changePasswordPage).forward(request, response);
				return;
			}
			p_errors.put("oldpassword", "");

			// 旧密码正确允许修改密码，但是要检验两次输入的密码是不是一直
			String newpassowrd = request.getParameter("newpassword");
			String confirmpassowrd = request.getParameter("confirmpassword");
			if (newpassowrd == null||newpassowrd.equals("")) {
				// 密码为空
				p_errors.put("newpassowrd", "密码不能为空，请重新输入~");
				request.setAttribute("p_errors", p_errors);
				request.getRequestDispatcher(changePasswordPage).forward(request, response);
				return;
			}
			p_errors.put("newpassowrd", "");
			if (confirmpassowrd == null||newpassowrd.equals("")) {
				// 确认密码为空
				p_errors.put("confirmpassword", "确认密码不能为空，请重新输入~");
				request.setAttribute("p_errors", p_errors);
				request.getRequestDispatcher(changePasswordPage).forward(request, response);
				return;
			}
			if (!newpassowrd.equals(confirmpassowrd)) {
				// 两次输入的密码不一致
				p_errors.put("confirmpassword", "两次密码不一致，请重新输入~");
				request.setAttribute("p_errors", p_errors);
				request.getRequestDispatcher(changePasswordPage).forward(request, response);
				return;
			}
			p_errors.put("confirmpassword", "");

			// 允许修改密码
			UserService service = new UserServiceImpl();
			service.changPassword(u.getUsername(), confirmpassowrd);

			request.setAttribute("notice", "修改密码成功~");
			request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
			return;
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "修改密码失败~");
			request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
		}
	}

}
