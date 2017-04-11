package cn.majin.Web.Book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.Utils.WebUtils;
import cn.majin.domain.Book;

public class AddBookServlet extends HttpServlet {

	// 提供用户添加书本的界面
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/addBook.jsp").forward(request, response);
	}

	// 接受用户添加书本后的表单
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * 获取Book对象,向数据库中注册该Book对象
		 */
		Book book = WebUtils.request2bean(request, Book.class);
		book.checkAndRegister(request,response);
	}

}
