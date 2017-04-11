package cn.majin.Web.Book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.Book;
import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;

/**
 * 显示书本的详细信息
 * @author majin
 *
 */
public class ShowBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		BookService impl = new BookServiceImpl();
		
		String id = request.getParameter("id");
		Book b =impl.getBookById(id); 
		request.setAttribute("bookDetail", b);
		request.getRequestDispatcher("/WEB-INF/jsp/showBookDetail.jsp").forward(request, response);
		
	}

}
