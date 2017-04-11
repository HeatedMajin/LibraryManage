package cn.majin.Web.Book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.User;
import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;

public class BorrowBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 获取登录用户对象
		User u = (User) request.getSession().getAttribute("user");
		if (u == null) {
			//没有登陆去登陆
			request.setAttribute("notice", "请先登录...<a href='/" + request.getContextPath() + "' target='_top'>点我登录</a>");
			request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
			return;
		}
		String username = u.getUsername();
		String book_id = request.getParameter("book_id");
		
		BookService service = new BookServiceImpl();
		service.borrowBook(username,book_id);
		
	}

}
