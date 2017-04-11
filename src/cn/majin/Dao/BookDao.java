package cn.majin.Dao;

import java.sql.SQLException;
import java.util.List;

import cn.majin.domain.Book;
import cn.majin.domain.PerBookStatistics;
import cn.majin.domain.PerMonthStatistics;
import cn.majin.domain.QueryInfo;
import cn.majin.domain.QueryResult;
import cn.majin.domain.SearchConditionBean;
import edu.fudan.ml.types.Dictionary;

public interface BookDao {

	// 增加一本书
	void addBook(Book b) throws SQLException;

	/**
	 * 得到分页查询的结果
	 * 
	 * @param cbean
	 *            封装查询条件的Bean
	 * @param info
	 *            封装页面信息的bean
	 * @return 查询结果（总的数据量，查询内容）
	 */
	QueryResult getBooksByCondition(SearchConditionBean cbean, QueryInfo info);

	// 删除一本书
	void delBook(String id);

	// 修改一本书
	void editBook(Book b);

	// 根据id去查找一本书
	Book getBookById(String bid);

	// 借一本书
	void borrowBook(String username, String book_id);

	// 查看一个用户的全部借阅
	List<Book> getMyBorrow(String username);

	// 进行还书
	void returnBook(String book_id);

	// 获取全部用户的借阅情况
	List<Book> getAllBorrow();

	// 获取图书的借阅统计
	List<PerBookStatistics> getPerBookStatistics();

	// 获取每月的借阅量
	List<PerMonthStatistics> getPerMonthStatistics(String year);

	// 获取书本名字的字典
	List<String> getBookDictionary();

	// 获取书本和作者的字典
	List<String> getDictionarys();

	// 获取作者的字典
	List<String> getAuthorDictionary();
}