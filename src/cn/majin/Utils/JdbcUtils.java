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
			// ��ȡdbcp�������ļ���
			InputStream input = JdbcUtils.class.getClassLoader().getResourceAsStream("dbcpconfig.properties");
			Properties pro = new Properties();
			pro.load(input);

			// ʹ�������ļ����������ݿ�����ӳ�
			BasicDataSourceFactory factory = new BasicDataSourceFactory();
			ds = factory.createDataSource(pro);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

//	// ��ȡ���ݿ�Ķ����ӳ�
//	public static DataSource getDataSource() {
//		return ds;
//	}

	// �����ӳ��л�ȡ���ӣ���������ǿ��������������
	public static Connection getConnection() {
		try {
			// ��ȡ��ǰ�߳��ϵķ���
			Connection conn = tl.get();
			// ����ǰ�߳���û��connection��������ݿ����ӳ��л�ȡ
			if (conn == null) {
				conn = ds.getConnection();
				conn.setAutoCommit(false);// ��������,ֻ�е�һ���������������Ż��ύ
				tl.set(conn);// ��connection�󶨵���ǰ�߳���
			}
			return conn;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// �ύ���񣬲��ӵ�ǰ�߳����ͷ�����
	public static void commitTransction() {
		// ��ȡ��ǰ�߳��ϵķ���
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
