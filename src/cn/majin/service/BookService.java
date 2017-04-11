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
	 * ��ȡҪ��ʾҳ��Ļ�����Ϣ
	 * 
	 * @param cbean
	 *            ��ѯ���õĲ�ѯ����
	 * @param info
	 *            ��ǰ��ҳ����Ϣ
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