package cn.majin.Web.Book;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import cn.majin.Utils.WebUtils;
import cn.majin.domain.Book;

public class AddBookServlet extends HttpServlet {

	// �ṩ�û�����鱾�Ľ���
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.getRequestDispatcher("/WEB-INF/jsp/addBook.jsp").forward(request, response);
	}

	// �����û�����鱾��ı�
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		/*
		 * ��ȡBook����,�����ݿ���ע���Book����
		 */
		Book book = WebUtils.request2bean(request, Book.class);
		book.checkAndRegister(request,response);
	}

}
