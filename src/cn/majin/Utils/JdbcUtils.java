package cn.majin.Utils;

import java.io.InputStream;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

import javax.sql.DataSource;

import org.apache.commons.dbcp2.BasicDataSourceFactory;

public class JdbcUtils {

	private static DataSource ds = null;
	private static ThreadLocal<Connection> tl = new ThreadLocal<Connection>();

	static {
		try {
			// 读取dbcp的配置文件呢
			InputStream input = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties pro = new Properties();
			pro.load(input);

			// 使用配置文件来创建数据库的链接池
			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			ds = factory.createDataSource(pro);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

//	// 获取数据库的额连接池
//	public static DataSource getDataSource() {
//		return ds;
//	}

	// 从链接池中获取链接，这个链接是开启了事务的链接
	public static Connection getConnection() {
		try {
			// 获取当前线程上的返回
			Connection conn = tl.get();
			// 若当前线程上没有connection，则从数据库连接池中获取
			if (conn == null) {
				conn = ds.getConnection();
				conn.setAutoCommit(false);// 开启事务,只有当一次请求结束后，事务才会提交
				tl.set(conn);// 将connection绑定到当前线程上
			}
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// 提交事务，并从当前线程上释放链接
	public static void commitTransction() {
		// 获取当前线程上的返回
		Connection conn = null;
		try {
			conn = tl.get();
			if (conn != null) {
				conn.commit();
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		} finally {
			try {
				if (conn != null)
					conn.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
			tl.remove();
		}
	}
}
