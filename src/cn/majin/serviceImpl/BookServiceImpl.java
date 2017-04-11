package cn.majin.serviceImpl;

import java.sql.SQLException;
import java.util.List;

import cn.majin.Dao.BookDao;
import cn.majin.domain.Book;
import cn.majin.domain.PerBookStatistics;
import cn.majin.domain.PerMonthStatistics;
import cn.majin.domain.PageBean;
import cn.majin.domain.QueryInfo;
import cn.majin.domain.QueryResult;
import cn.majin.domain.SearchConditionBean;
import cn.majin.factory.DaoFactory;
import cn.majin.service.BookService;
import edu.fudan.ml.types.Dictionary;

public class BookServiceImpl implements BookService {

	private BookDao impl = DaoFactory.getInstance().generateDaoImpl(BookDao.class);

	// ���һ����
	public void addBook(Book b) throws SQLException {
		// �����ݿ������
		impl.addBook(b);
	}

	/**
	 * ��ȡҪ��ʾҳ��Ļ�����Ϣ
	 * 
	 * @param cbean
	 *            ��ѯ���õĲ�ѯ����
	 * @param info
	 *            ��ǰ��ҳ����Ϣ
	 * @return
	 */
	public PageBean getBooks(SearchConditionBean cbean, QueryInfo info) {

		// �����ݿ��в�ѯ ���� �� ��������
		QueryResult result = impl.getBooksByCondition(cbean, info);

		PageBean pageBean = new PageBean();
		pageBean.setContext(result.getContext());
		pageBean.setCurrentPage(info.getCurrentPage());
		pageBean.setTotalSize(result.getTotalSize());
		pageBean.setPageSize(info.getPageSize());

		return pageBean;
	}

	// ɾ��һ����
	public void delBook(String id) {
		impl.delBook(id);
	}

	// �޸�һ����
	public void editBook(Book b) {
		impl.editBook(b);
	}

	// ������������ȡ��
	public Book getBookById(String id) {
		return impl.getBookById(id);
	}

	// ����
	public void borrowBook(String username, String book_id) {
		impl.borrowBook(username, book_id);
	}

	// ��ʾ�Լ��ӵ�������
	public List<Book> getMyBorrow(String username) {

		return impl.getMyBorrow(username);
	}

	// ����
	public void returnBook(String book_id) {
		impl.returnBook(book_id);
	}

	@Override
	public List<Book> getAllBorrow() {
		return impl.getAllBorrow();
	}

	// �鿴ͼ��Ľ���ͳ��
	public List<PerBookStatistics> getPerBookStatistics() {
		return impl.getPerBookStatistics();
	}

	// �鿴ÿ���µĽ�����
	public List<PerMonthStatistics> getPerMonthStatistics(String year) {
		return impl.getPerMonthStatistics(year);
	}

	// �õ��鱾���ֵ��ֵ�
	public List<String> getBookDictionary() {
		return impl.getBookDictionary();
	}

	// �õ��鱾�����ߵ��ֵ�
	public List<String> getDictionarys() {
		return impl.getDictionarys();
	}

	// �õ����ߵ��ֵ�
	public List<String> getAuthorDictionary() {
		return impl.getAuthorDictionary();
	}
}
