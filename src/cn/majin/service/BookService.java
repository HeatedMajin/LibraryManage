package cn.majin.service;

import java.sql.SQLException;
import java.util.List;

import cn.majin.domain.Book;
import cn.majin.domain.PerBookStatistics;
import cn.majin.domain.PerMonthStatistics;
import cn.majin.domain.PageBean;
import cn.majin.domain.QueryInfo;
import cn.majin.domain.SearchConditionBean;
import edu.fudan.ml.types.Dictionary;

public interface BookService {

	void addBook(Book b) throws SQLException;

	/**
	 * 获取要显示页面的基本信息
	 * 
	 * @param cbean
	 *            查询设置的查询条件
	 * @param info
	 *            当前的页面信息
	 * @return
	 */
	PageBean getBooks(SearchConditionBean cbean, QueryInfo info);

	void delBook(String id);

	void editBook(Book b);

	Book getBookById(String id);

	void borrowBook(String username, String book_id);

	List<Book> getMyBorrow(String username);

	void returnBook(String book_id);

	List<Book> getAllBorrow();

	List<PerBookStatistics> getPerBookStatistics();

	List<PerMonthStatistics> getPerMonthStatistics(String year);

	List<String> getBookDictionary();

	List<String> getDictionarys();

	List<String> getAuthorDictionary();
}