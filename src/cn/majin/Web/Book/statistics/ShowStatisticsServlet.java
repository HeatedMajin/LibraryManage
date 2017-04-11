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

//��ʾ����
public class ShowStatisticsServlet extends HttpServlet {

	private BookService service = new BookServiceImpl();

	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		doPost(request, response);
	}

	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String todo = request.getParameter("todo");
		if ("perbook".equals(todo)) {
			// �鿴ͼ��Ľ�����Ϣͳ��
			List<PerBookStatistics> statistics = service.getPerBookStatistics();
			//��statistic�����б�ת��Json�б�
			List<JsonBean> json = JsonUtils.PerBookStatistics2JsonBean(statistics);
			//��JsonBean�б����� Json����
			Gson gson  =new Gson();
			String data  = gson.toJson(json);
			//��json���ݷ������У���ҳ����ֱ��ȥȡ
			request.setAttribute("data", data);
			request.getRequestDispatcher("/WEB-INF/jsp/book/perbook_statistics.jsp").forward(request, response);
			return;
		}else if ("permonth".equals(todo)) {
			// �鿴ͼ��Ľ�����Ϣͳ��
			String year = "2017";
			List<PerMonthStatistics> statistics = service.getPerMonthStatistics(year);
			//��statistic�����б�ת�� Json����
			int[] json = JsonUtils.PerMonthStatistics2Json(statistics);
			Gson gson  =new Gson();
			String data  = gson.toJson(json);
			//��json���ݷ������У���ҳ����ֱ��ȥȡ
			request.setAttribute("json", data);
			request.getRequestDispatcher("/WEB-INF/jsp/book/permonth_statistics.jsp").forward(request, response);
			return;
		}
	}

}
