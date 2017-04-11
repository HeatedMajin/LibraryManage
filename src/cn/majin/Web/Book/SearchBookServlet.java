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

	// �ṩ��ѯ�Ľ���
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

	// �����ѯ������
	public void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String type = request.getParameter("type");
		SearchConditionBean bean = null;
		// ʹ�ù����������в�ѯ
		// ��ɸѡ����Ĳ�����װ��bean��
		bean = WebUtils.request2bean(request, SearchConditionBean.class);
		if ("language".equals(type)) {// ʹ����Ȼ���Խ��в�ѯ
			// ����Ȼ�����е���Ϣ��װ��bean��
			bean = doLanguageSearch(request, response);
		}else if("ambigous".equals(type)){
			String name = request.getParameter("q"); 
			bean.setName(name);
		}
		// �Ѵ�������ҳ����Ϣ��װ��QueryInfo��
		QueryInfo info = WebUtils.request2bean(request, QueryInfo.class);

		// ִ��sql����ȡ�鱾��list����
		PageBean pageBean = impl.getBooks(bean, info);

		// �����ϷŽ�request���У�ת����listBook.jsp����ʾ
		// �Ľ���ת����listBookLikeBaidu.jsp
		request.setAttribute("pageBean", pageBean);
		request.getRequestDispatcher("/WEB-INF/jsp/listBookLikeBaidu.jsp").forward(request, response);
	}

	// ������Ȼ�������ɲ�ѯ����
	private SearchConditionBean doLanguageSearch(HttpServletRequest request, HttpServletResponse response) {
		SearchConditionBean bean = new SearchConditionBean();
		try {
			// ��ȡ�û��������Ȼ����
			String target = request.getParameter("str");
			if (target == null || target.trim().equals("")) {
				return bean;
			}
			// ����Ȼ����л�ȡ��ʱ��
			TimeNormalizer normalizer = new TimeNormalizer(TNPath);
			normalizer.parse(target);
			TimeUnit[] unit = normalizer.getTimeUnit();
			if (unit != null && unit.length >= 2) {
				// ֻ������Ч������(������С)
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
			// ����Ȼ�����н����� name author
			// ����������䵽bean��
			BeanUtils.populate(bean, split(target));
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	/**
	 * �����ֵ��target���зִ�
	 * 
	 * @ book�ֵ�---�жϷֺõĴ��ǲ���book
	 * @ author�ֵ�---�жϷֺõĴ��ǲ���author
	 **/
	private Map<String, String> split(String target) throws Exception {

		// �����鱾�ʿ�
		List<String> book_dict = impl.getBookDictionary();
		// �������ߴʿ�
		List<String> author_dict = impl.getAuthorDictionary();
		// �������������ߴʿ⣬���ڷִ�
		List<String> dictionary = impl.getDictionarys();

		// ���������ʿ⣬���зִ�
		Map<String, String> map = new HashMap<String, String>();
		CWSTagger tag2 = new CWSTagger(SegPath);
		tag2.setDictionary(new THashSet(dictionary));
		// ���ִʽ�����浽������
		String[] arr = tag2.tag2Array(target);
		// ��¼�Ƿ��ҵ���book��author���ҵ��˾Ͳ�������
		boolean book = false;
		boolean author = false;
		for (int x = 0; x < arr.length; x++) {
			if (!book && book_dict.contains(arr[x])) {// �ж�arr[x]�ǲ������ֵ���
				map.put("name", arr[x]);
			} else if (!author && author_dict.contains(arr[x])) {
				map.put("author", arr[x]);
			} else if (author && book) {
				return map;// ���ҵ��ˣ���ʱ����
			}
		}
		return map;
	}
}
