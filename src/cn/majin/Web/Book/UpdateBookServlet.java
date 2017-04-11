package cn.majin.Web.Book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.Utils.WebUtils;
import cn.majin.domain.Book;
import cn.majin.domain.User;
import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;
import cn.majin.serviceImpl.ManageService;

public class UpdateBookServlet extends HttpServlet {
	private BookService impl = new BookServiceImpl();

	// Ϊ�û��ṩ�û���Ϣ�޸Ľ������ʾ
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		String id = request.getParameter("id");
		Book b = impl.getBookById(id);
		request.setAttribute("bookBean", b);
		request.getRequestDispatcher("/WEB-INF/jsp/editBook.jsp").forward(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		/*
		 * ��ȡBook����,�����ݿ���ע���Book����
		 */
		try {
			User u = (User) request.getSession().getAttribute("user");
			Book book = WebUtils.request2bean(request, Book.class);
			ManageService manageService = new ManageService();
			impl.editBook(book);
			manageService.updateOp_user(u.getUsername(),book.getName()+"("+book.getId()+")");
			request.setAttribute("notice", "�޸ĳɹ�");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "�޸�ʧ��~");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
	}

}
