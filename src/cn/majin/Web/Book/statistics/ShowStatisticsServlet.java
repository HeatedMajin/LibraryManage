package cn.majin.Web.Book.statistics;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import cn.majin.Utils.JsonUtils;
import cn.majin.domain.JsonBean;
import cn.majin.domain.PerBookStatistics;
import cn.majin.domain.PerMonthStatistics;
import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;

//显示报表
public class ShowStatisticsServlet extends HttpServlet {

	private BookService service = new BookServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String todo = request.getParameter("todo");
		if ("perbook".equals(todo)) {
			// 查看图书的借阅信息统计
			List<PerBookStatistics> statistics = service.getPerBookStatistics();
			//将statistic对象列表转成Json列表
			List<JsonBean> json = JsonUtils.PerBookStatistics2JsonBean(statistics);
			//将JsonBean列表生成 Json数据
			Gson gson  =new Gson();
			String data  = gson.toJson(json);
			//将json数据放在域中，在页面中直接去取
			request.setAttribute("data", data);
			request.getRequestDispatcher("/WEB-INF/jsp/book/perbook_statistics.jsp").forward(request, response);
			return;
		}else if ("permonth".equals(todo)) {
			// 查看图书的借阅信息统计
			String year = "2017";
			List<PerMonthStatistics> statistics = service.getPerMonthStatistics(year);
			//将statistic对象列表转成 Json数据
			int[] json = JsonUtils.PerMonthStatistics2Json(statistics);
			Gson gson  =new Gson();
			String data  = gson.toJson(json);
			//将json数据放在域中，在页面中直接去取
			request.setAttribute("json", data);
			request.getRequestDispatcher("/WEB-INF/jsp/book/permonth_statistics.jsp").forward(request, response);
			return;
		}
	}

}
