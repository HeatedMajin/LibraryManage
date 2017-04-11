package cn.majin.Web.Book;

import java.io.IOException;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.beanutils.BeanUtils;

import cn.majin.Utils.WebUtils;
import cn.majin.domain.PageBean;
import cn.majin.domain.QueryInfo;
import cn.majin.domain.SearchConditionBean;
import cn.majin.service.BookService;
import cn.majin.serviceImpl.BookServiceImpl;
import edu.fudan.nlp.cn.ner.TimeNormalizer;
import edu.fudan.nlp.cn.ner.TimeUnit;
import edu.fudan.nlp.cn.tag.CWSTagger;
import gnu.trove.set.hash.THashSet;

public class SearchBookServlet extends HttpServlet {

	private static String TNPath;
	private static String SegPath;
	private BookService impl = new BookServiceImpl();

	@Override
	public void init(ServletConfig config) throws ServletException {
		super.init(config);
		try {

			TNPath = this.getServletContext().getRealPath("/WEB-INF/classes/models/TimeExp.m");
			SegPath = this.getServletContext().getRealPath("/WEB-INF/classes/models/seg.m");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	// 提供查询的界面
	public void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");

		if ("language".equals(type)) {
			request.getRequestDispatcher("/WEB-INF/jsp/languageSearch.jsp").forward(request, response);
		} else if ("filter".equals(type)) {
			request.getRequestDispatcher("/WEB-INF/jsp/filterSearch.jsp").forward(request, response);
		} else if ("ambigous".equals(type)) {
			request.getRequestDispatcher("/WEB-INF/jsp/ambiSearch.jsp").forward(request, response);
		}
	}

	// 处理查询的请求
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		SearchConditionBean bean = null;
		// 使用过滤条件进行查询
		// 把筛选填入的参数封装到bean中
		bean = WebUtils.request2bean(request, SearchConditionBean.class);
		if ("language".equals(type)) {// 使用自然语言进行查询
			// 把自然语言中的信息封装到bean中
			bean = doLanguageSearch(request, response);
		}else if("ambigous".equals(type)){
			String name = request.getParameter("q"); 
			bean.setName(name);
		}
		// 把传递来的页面信息封装到QueryInfo中
		QueryInfo info = WebUtils.request2bean(request, QueryInfo.class);

		// 执行sql，获取书本的list集合
		PageBean pageBean = impl.getBooks(bean, info);

		// 将集合放进request域中，转发到listBook.jsp上显示
		// 改进后转发的listBookLikeBaidu.jsp
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/WEB-INF/jsp/listBookLikeBaidu.jsp").forward(request, response);
	}

	// 根据自然语言生成查询条件
	private SearchConditionBean doLanguageSearch(HttpServletRequest request, HttpServletResponse response) {
		SearchConditionBean bean = new SearchConditionBean();
		try {
			// 获取用户输入的自然语言
			String target = request.getParameter("str");
			if (target == null || target.trim().equals("")) {
				return bean;
			}
			// 从自然语句中获取到时间
			TimeNormalizer normalizer = new TimeNormalizer(TNPath);
			normalizer.parse(target);
			TimeUnit[] unit = normalizer.getTimeUnit();
			if (unit != null && unit.length >= 2) {
				// 只保留有效的两个(最大和最小)
				int maxIndex = 0;
				int minIndex = 0;
				for (int i = 1; i < unit.length; i++) {
					if (unit[i].getTime().getTime() > unit[maxIndex].getTime().getTime()) {
						maxIndex = i;
					} else if (unit[i].getTime().getTime() < unit[minIndex].getTime().getTime()) {
						minIndex = i;
					}
				}
				Date date1 = unit[minIndex].getTime();
				Date date2 = unit[maxIndex].getTime();

				bean.setPublishDate1(date1);
				bean.setPublishDate2(date2);
			} else if (unit != null && unit.length == 1) {
				Date date1 = unit[0].getTime();
				bean.setPublishDate1(date1);
			}
			// 从自然语言中解析出 name author
			// 并把它们填充到bean中
			BeanUtils.populate(bean, split(target));
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * 按照字典对target进行分词
	 * 
	 * @ book字典---判断分好的词是不是book
	 * @ author字典---判断分好的词是不是author
	 **/
	private Map<String, String> split(String target) throws Exception {

		// 加载书本词库
		List<String> book_dict = impl.getBookDictionary();
		// 加载作者词库
		List<String> author_dict = impl.getAuthorDictionary();
		// 加载书名和作者词库，用于分词
		List<String> dictionary = impl.getDictionarys();

		// 根据两个词库，进行分词
		Map<String, String> map = new HashMap<String, String>();
		CWSTagger tag2 = new CWSTagger(SegPath);
		tag2.setDictionary(new THashSet(dictionary));
		// 将分词结果保存到数组中
		String[] arr = tag2.tag2Array(target);
		// 记录是否找到了book和author，找到了就不再找了
		boolean book = false;
		boolean author = false;
		for (int x = 0; x < arr.length; x++) {
			if (!book && book_dict.contains(arr[x])) {// 判断arr[x]是不是在字典中
				map.put("name", arr[x]);
			} else if (!author && author_dict.contains(arr[x])) {
				map.put("author", arr[x]);
			} else if (author && book) {
				return map;// 都找到了，及时结束
			}
		}
		return map;
	}
}
