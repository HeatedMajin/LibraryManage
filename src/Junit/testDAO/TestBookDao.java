package Junit.testDAO;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;

import org.junit.Test;

import cn.majin.Dao.BookDao;
import cn.majin.DaoImpl.BookDaoImpl;
import cn.majin.domain.Book;
import cn.majin.domain.PerBookStatistics;
import cn.majin.domain.PerMonthStatistics;

public class TestBookDao {
	BookDao dao = new BookDaoImpl();

	@Test
	public void testAdd() throws SQLException {
		Book b = new Book("12331", "123", "1", new Date(), "111");
		dao.addBook(b);
	}

	@Test
	public void testFindByID() {
		Book b = dao.getBookById("12331");
		System.out.println(b);
	}

	@Test
	public void testEdit() {
		Book b = new Book("12331", "1212312", "1", new Date(), "111");
		dao.editBook(b);
	}

	@Test
	public void testPerMonth() {
		List<PerMonthStatistics> list = dao.getPerMonthStatistics("2017");
		for (PerMonthStatistics p : list) {
			System.out.println(p.getMonth() + ":" + p.getBorrow_count());
		}
		System.out.println();
	}
}
