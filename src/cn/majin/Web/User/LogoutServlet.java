package cn.majin.Web.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

//让用户登出，清除session中的登录状态,转到登录界面
public class LogoutServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// session制空
		request.getSession().removeAttribute("user");
		// 显示登录界面
		response.sendRedirect(request.getContextPath());
	}
}
