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

//����
public class ReturnBookServlet extends HttpServlet {
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {

			User u = (User) request.getSession().getAttribute("user");
			if (u == null) {
				// û�е�½ȥ��½
				request.setAttribute("notice",
						"���ȵ�¼...<a href='/" + request.getContextPath() + "' target='_top'>���ҵ�¼</a>");
				request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
				return;
			}

			BookService service = new BookServiceImpl();
			String book_id = request.getParameter("book_id");
			service.returnBook(book_id);// ���л���->�����ݿ��borrow��ɾ�������¼

			request.setAttribute("notice", "����ɹ����������軹���������һ������~");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "����ʧ�ܣ�������~");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
	}

}
