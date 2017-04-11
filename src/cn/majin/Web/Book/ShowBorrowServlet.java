package cn.majin.Web.Book;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.Book;
import cn.majin.domain.PerBookStatistics;
import cn.majin.domain.User;
import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;

public class ShowBorrowServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String todo = request.getParameter("todo");
		BookService service = new BookServiceImpl();
		if ("my".equals(todo)) {
			User u = (User) request.getSession().getAttribute("user");
			if (u == null) {
				// û�е�½ȥ��½
				request.setAttribute("notice",
						"���ȵ�¼...<a href='/" + request.getContextPath() + "' target='_top'>���ҵ�¼</a>");
				request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
				return;
			}

			List<Book> books = service.getMyBorrow(u.getUsername());

			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/jsp/book/show_my_borrow_book.jsp").forward(request, response);
			return;
		} else if ("all".equals(todo)) {
			List<Book> books = service.getAllBorrow();

			request.setAttribute("books", books);
			request.getRequestDispatcher("/WEB-INF/jsp/book/show_all_borrow_book.jsp").forward(request, response);
			return;
		} 
	}

}
