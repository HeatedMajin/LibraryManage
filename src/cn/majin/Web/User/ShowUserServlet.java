package cn.majin.Web.User;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ShowUserServlet extends HttpServlet {

	// 为用户提供用户信息界面的显示
		public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			String status = request.getParameter("status");
			request.setAttribute("status", status);
			request.getRequestDispatcher("/WEB-INF/jsp/UserInfo.jsp").forward(request, response);

		}

		public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
			doGet(request, response);
		}

}
