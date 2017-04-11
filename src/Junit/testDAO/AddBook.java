package Junit.testDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.majin.Utils.JdbcUtils;

public class AddBook {

	@Test
	public void add() throws Exception {
		Connection conn  = JdbcUtils.getConnection();
		String sql= "insert into book (id,name,author,publishdate,detail) values(?,?,?,sysdate,'ħ����ʵ���� д��һ������İ�����˥����������9�����ϡ�����һ���£����ֺܳ��ܸ��ӣ��ҿ��Ȿ���ʱ���ǿ��Ÿо��ģ��о���֪��˭��˭����������Ҫ���ģ�ƽ��������')";
		PreparedStatement statement  = conn.prepareStatement(sql);
		for (int i = 0; i < 300; i++) {
			//statement.setString(1, "999999999"+i+"");
			statement.setString(1, "9999999999"+i+"");
			statement.setString(2, "����¶�"+i);
			statement.setString(3, "�����˹"+i);
			statement.executeUpdate();
		}
	}
}
