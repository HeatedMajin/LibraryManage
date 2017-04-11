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
		// 获取到用户现在输入的
		String value = request.getParameter("q");

		// 从数据库中获取数据
		BookDaoImpl dao = new BookDaoImpl();
		List<Support> list = dao.ambiSearchSupport(value);

		// 把数据组装成一个Json串
		List<String[]> gson = new ArrayList<String[]>();

		for (Support search : list) {
			gson.add(new String[] { search.getName(), search.getCount() + "" });
		}
		String result = new Gson().toJson(gson); // google的api，把集合中的数据组装成一个json串
		// 写给客户机
		response.getOutputStream().write(("KISSY.Suggest.callback({'result':" + result + "})").getBytes("UTF-8"));

	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doGet(request, response);
	}

}
