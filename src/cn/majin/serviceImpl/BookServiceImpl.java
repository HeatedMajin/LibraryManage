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

	// 添加一本书
	public void addBook(Book b) throws SQLException {
		// 向数据库中添加
		impl.addBook(b);
	}

	/**
	 * 获取要显示页面的基本信息
	 * 
	 * @param cbean
	 *            查询设置的查询条件
	 * @param info
	 *            当前的页面信息
	 * @return
	 */
	public PageBean getBooks(SearchConditionBean cbean, QueryInfo info) {

		// 从数据库中查询 内容 和 总数据量
		QueryResult result = impl.getBooksByCondition(cbean, info);

		PageBean pageBean = new PageBean();
		pageBean.setContext(result.getContext());
		pageBean.setCurrentPage(info.getCurrentPage());
		pageBean.setTotalSize(result.getTotalSize());
		pageBean.setPageSize(info.getPageSize());

		return pageBean;
	}

	// 删除一本书
	public void delBook(String id) {
		impl.delBook(id);
	}

	// 修改一本书
	public void editBook(Book b) {
		impl.editBook(b);
	}

	// 根据书名，获取书
	public Book getBookById(String id) {
		return impl.getBookById(id);
	}

	// 借书
	public void borrowBook(String username, String book_id) {
		impl.borrowBook(username, book_id);
	}

	// 显示自己接的所有书
	public List<Book> getMyBorrow(String username) {

		return impl.getMyBorrow(username);
	}

	// 还书
	public void returnBook(String book_id) {
		impl.returnBook(book_id);
	}

	@Override
	public List<Book> getAllBorrow() {
		return impl.getAllBorrow();
	}

	// 查看图书的借阅统计
	public List<PerBookStatistics> getPerBookStatistics() {
		return impl.getPerBookStatistics();
	}

	// 查看每个月的借阅量
	public List<PerMonthStatistics> getPerMonthStatistics(String year) {
		return impl.getPerMonthStatistics(year);
	}

	// 得到书本名字的字典
	public List<String> getBookDictionary() {
		return impl.getBookDictionary();
	}

	// 得到书本和作者的字典
	public List<String> getDictionarys() {
		return impl.getDictionarys();
	}

	// 得到作者的字典
	public List<String> getAuthorDictionary() {
		return impl.getAuthorDictionary();
	}
}
