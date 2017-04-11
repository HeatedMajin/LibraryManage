package cn.majin.Web.Book;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.majin.DaoImpl.BookDaoImpl;
import cn.majin.domain.Support;

public class SearchSupportServlet extends HttpServlet {

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// ��ȡ���û����������
		String value = request.getParameter("q");

		// �����ݿ��л�ȡ����
		BookDaoImpl dao = new BookDaoImpl();
		List<Support> list = dao.ambiSearchSupport(value);

		// ��������װ��һ��Json��
		List<String[]> gson = new ArrayList<String[]>();

		for (Support search : list) {
			gson.add(new String[] { search.getName(), search.getCount() + "" });
		}
		String result = new Gson().toJson(gson); // google��api���Ѽ����е�������װ��һ��json��
		// д���ͻ���
		response.getOutputStream().write(("KISSY.Suggest.callback({'result':" + result + "})").getBytes("UTF-8"));

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
