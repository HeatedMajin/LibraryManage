package cn.majin.DaoImpl;

import java.math.BigDecimal;
import java.sql.SQLException;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.majin.Dao.BookDao;
import cn.majin.Exception.DaoException;
import cn.majin.Utils.JdbcUtils;
import cn.majin.domain.Book;
import cn.majin.domain.Borrow;
import cn.majin.domain.PerBookStatistics;
import cn.majin.domain.PerMonthStatistics;
import cn.majin.domain.QueryInfo;
import cn.majin.domain.QueryResult;
import cn.majin.domain.SearchConditionBean;
import cn.majin.domain.Support;
import edu.fudan.ml.types.Dictionary;

public class BookDaoImpl implements BookDao {
	// 增加一本书
	public void addBook(Book b) throws SQLException {
		try {
			// 向数据库中添加数据
			String sql = "insert into book(id,name,author,publishdate,detail) values(?,?,?,?,?)";
			Object[] params = { b.getId(), b.getName(), b.getAuthor(), new java.sql.Date(b.getPublishDate().getTime()),
					b.getDetail() };
			QueryRunner runner = new QueryRunner(true);
			runner.update(JdbcUtils.getConnection(), sql, params);

		} catch (SQLException e) {
			throw e;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	/**
	 * 得到分页查询的结果
	 * 
	 * @param cbean
	 *            封装查询条件的Bean
	 * @param info
	 *            封装页面信息的bean
	 * @return 查询结果（总的数据量，查询内容）
	 */
	public QueryResult getBooksByCondition(SearchConditionBean cbean, QueryInfo info) {
		try {
			QueryResult result = new QueryResult();

			/***************************************
			 * 获取查询的内容
			 ***************************************/
			// 根据info来生成限定之后的查询
			int start = (info.getCurrentPage() - 1) * info.getPageSize();
			int end = info.getCurrentPage() * info.getPageSize();

			QueryRunner runner = new QueryRunner(true);
			String sql = "select * from (select rownum rn,e.*from (" + cbean.generateSQL()
					+ ") e)where rn >=? and rn<=?";
			Object[] params = { start, end };
			List<Book> books = runner.query(JdbcUtils.getConnection(), sql, new BeanListHandler<Book>(Book.class),
					params);
			/***************************************
			 * 获取书本被谁接了，以及借走的时间
			 ***************************************/
			if (books != null) {
				for (Book b : books) {
					sql = "select username borrowedBy,borrowDate from borrow where book_id=?";
					runner = new QueryRunner();
					Borrow borrow = runner.query(JdbcUtils.getConnection(), sql, b.getId(),
							new BeanHandler(Borrow.class));
					b.setBorrow(borrow);
				}
			}
			result.setContext(books);

			/***************************************
			 * 获取数据量
			 ***************************************/
			sql = "select count(*) from (" + cbean.generateSQL() + ")";
			int totalSize = ((BigDecimal) (runner.query(JdbcUtils.getConnection(), sql, new ArrayHandler())[0]))
					.intValue();
			result.setTotalSize(totalSize);

			return result;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 删除一本书
	public void delBook(String id) {
		try {
			String sql = "delete from book where id=?";
			QueryRunner runner = new QueryRunner(true);
			runner.update(JdbcUtils.getConnection(), sql, id);
		} catch (SQLException e) {
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 修改一本书
	public void editBook(Book b) {
		try {
			String sql = "update book b set name=?,author=?,publishdate=?,detail=? where id=?";
			Object[] params = { b.getName(), b.getAuthor(), new java.sql.Date(b.getPublishDate().getTime()),
					b.getDetail(), b.getId() };
			QueryRunner runner = new QueryRunner(true);
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 根据id去查找一本书
	public Book getBookById(String bid) {
		try {
			String sql = "select * from book where id=?";
			QueryRunner runner = new QueryRunner();
			Book book = runner.query(JdbcUtils.getConnection(), sql, bid, new BeanHandler(Book.class));

			if (book == null) {
				return null;
			}
			/***************************************
			 * 获取书本被谁接了，以及借走的时间
			 ***************************************/
			sql = "select username borrowedBy,borrowDate from borrow where book_id=?";
			runner = new QueryRunner();
			Borrow borrow = runner.query(JdbcUtils.getConnection(), sql, book.getId(), new BeanHandler(Borrow.class));
			book.setBorrow(borrow);
			return book;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 借一本书
	public void borrowBook(String username, String book_id) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "insert into borrow(username,book_id,borrowDate) values(?,?,sysdate)";
			Object[] params = { username, book_id };
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 查看一个用户的全部借阅
	public List<Book> getMyBorrow(String username) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select * from book where id in(select book_id	from borrow	where username=?)";
			List<Book> books = runner.query(JdbcUtils.getConnection(), sql, username, new BeanListHandler(Book.class));
			/***************************************
			 * 书本被借走的时间
			 ***************************************/
			if (books != null) {
				for (Book b : books) {
					sql = "select username borrowedBy,borrowDate from borrow where book_id=?";
					runner = new QueryRunner();
					Borrow borrow = runner.query(JdbcUtils.getConnection(), sql, b.getId(),
							new BeanHandler(Borrow.class));
					b.setBorrow(borrow);
				}
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 进行还书
	public void returnBook(String book_id) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "delete from borrow where book_id=?";
			runner.update(JdbcUtils.getConnection(), sql, book_id);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 获取全部用户的借阅情况
	public List<Book> getAllBorrow() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select * from book natural join (select book_id id,username borrowedBy from borrow)";
			List<Book> books = runner.query(JdbcUtils.getConnection(), sql, new BeanListHandler(Book.class));
			/***************************************
			 * 获取书本被谁接了，以及借走的时间
			 ***************************************/
			if (books != null) {
				for (Book b : books) {
					sql = "select username borrowedBy,borrowDate from borrow where book_id=?";
					runner = new QueryRunner();
					Borrow borrow = runner.query(JdbcUtils.getConnection(), sql, b.getId(),
							new BeanHandler(Borrow.class));
					b.setBorrow(borrow);
				}
			}
			return books;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 查看每本图书的借阅统计
	public List<PerBookStatistics> getPerBookStatistics() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select id,name,borrow_count from book natural join (select book_id id,borrow_count from borrowPerBook)";
			List<PerBookStatistics> statistics = runner.query(JdbcUtils.getConnection(), sql,
					new BeanListHandler(PerBookStatistics.class));
			return statistics;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 获取每个月的借阅量
	public List<PerMonthStatistics> getPerMonthStatistics(String year) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select month,borrow_count from borrowPerMonth where year='" + year + "' order by month";
			List<PerMonthStatistics> list = runner.query(JdbcUtils.getConnection(), sql,
					new BeanListHandler(PerMonthStatistics.class));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 得到所有的作者名，用于生成作者字典 和 书本作者字典
	public List<String> getAuthorDictionary() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select distinct author from book";
			List<String> list = runner.query(JdbcUtils.getConnection(), sql, new ColumnListHandler<String>(1));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 得到所有的书本名，用于生成书本字典 和 书本作者字典
	public List<String> getBookDictionary() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select distinct name from book";
			List<String> list = runner.query(JdbcUtils.getConnection(), sql, new ColumnListHandler<String>(1));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	// 得到所有书本名和作者名生成的字典
	public List<String> getDictionarys() {
		List<String> list = this.getAuthorDictionary();
		list.addAll(this.getBookDictionary());
		return list;
	}

	// 获取模糊查询的类似书本名称和其相应的数量
	public List<Support> ambiSearchSupport(String value) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql ="select * from (select name,count(name) count from book where  name like ? group by name) where rownum>=0 and rownum <=4";
			List<Support> list = runner.query(JdbcUtils.getConnection(), sql, "%" + value + "%",
					new BeanListHandler(Support.class));
			return list;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}
}
