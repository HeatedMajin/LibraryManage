package cn.majin.Web.Book;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.User;
import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;

//还书
public class ReturnBookServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			User u = (User) request.getSession().getAttribute("user");
			if (u == null) {
				// 没有登陆去登陆
				request.setAttribute("notice",
						"请先登录...<a href='/" + request.getContextPath() + "' target='_top'>点我登录</a>");
				request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
				return;
			}

			BookService service = new BookServiceImpl();
			String book_id = request.getParameter("book_id");
			service.returnBook(book_id);// 进行还书->从数据库的borrow中删掉借书记录

			request.setAttribute("notice", "还书成功，如您还需还书请继续上一步操作~");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "还书失败，请重试~");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
	}

}
