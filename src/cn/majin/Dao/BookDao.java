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

	// ����һ����
	void addBook(Book b) throws SQLException;

	/**
	 * �õ���ҳ��ѯ�Ľ��
	 * 
	 * @param cbean
	 *            ��װ��ѯ������Bean
	 * @param info
	 *            ��װҳ����Ϣ��bean
	 * @return ��ѯ������ܵ�����������ѯ���ݣ�
	 */
	QueryResult getBooksByCondition(SearchConditionBean cbean, QueryInfo info);

	// ɾ��һ����
	void delBook(String id);

	// �޸�һ����
	void editBook(Book b);

	// ����idȥ����һ����
	Book getBookById(String bid);

	// ��һ����
	void borrowBook(String username, String book_id);

	// �鿴һ���û���ȫ������
	List<Book> getMyBorrow(String username);

	// ���л���
	void returnBook(String book_id);

	// ��ȡȫ���û��Ľ������
	List<Book> getAllBorrow();

	// ��ȡͼ��Ľ���ͳ��
	List<PerBookStatistics> getPerBookStatistics();

	// ��ȡÿ�µĽ�����
	List<PerMonthStatistics> getPerMonthStatistics(String year);

	// ��ȡ�鱾���ֵ��ֵ�
	List<String> getBookDictionary();

	// ��ȡ�鱾�����ߵ��ֵ�
	List<String> getDictionarys();

	// ��ȡ���ߵ��ֵ�
	List<String> getAuthorDictionary();
}