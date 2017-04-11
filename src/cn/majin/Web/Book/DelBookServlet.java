package cn.majin.Web.Book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.domain.Book;
import cn.majin.domain.User;
import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;
import cn.majin.serviceImpl.ManageService;

/**
 * 删除一本书
 * 
 * @author majin
 *
 */
public class DelBookServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			String id = request.getParameter("id");
			User u = (User) request.getSession().getAttribute("user");
			BookService impl = new BookServiceImpl();
			ManageService manageService = new ManageService();
			// 从数据库中删除书本
			Book  b=impl.getBookById(id);
			impl.delBook(id);
			// 在log4change中落上用户名记录
			manageService.updateOp_user(u.getUsername(),b.getName()+"("+id+")");
			request.setAttribute("notice", "删除成功");
		} catch (Exception e) {
			e.printStackTrace();
			request.setAttribute("notice", "删除失败~");
		}
		request.getRequestDispatcher("/WEB-INF/jsp/notice.jsp").forward(request, response);
	}

}
