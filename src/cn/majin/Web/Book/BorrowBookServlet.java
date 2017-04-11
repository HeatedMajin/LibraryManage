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
		// ��ȡ��¼�û�����
		User u = (User) request.getSession().getAttribute("user");
		if (u == null) {
			//û�е�½ȥ��½
			request.setAttribute("notice", "���ȵ�¼...<a href='/" + request.getContextPath() + "' target='_top'>���ҵ�¼</a>");
			request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
			return;
		}
		String username = u.getUsername();
		String book_id = request.getParameter("book_id");
		
		BookService service = new BookServiceImpl();
		service.borrowBook(username,book_id);
		
	}

}
