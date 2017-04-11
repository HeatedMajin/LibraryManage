package Junit.testDAO;

import java.sql.Connection;
import java.sql.PreparedStatement;

import org.junit.Test;

import cn.majin.Utils.JdbcUtils;

public class AddBook {

	@Test
	public void add() throws Exception {
		Connection conn  = JdbcUtils.getConnection();
		String sql= "insert into book (id,name,author,publishdate,detail) values(?,?,?,sysdate,'魔幻现实主义 写了一个家族的百年兴衰。豆瓣评分9分以上。看了一个月，名字很长很复杂，我看这本书的时候是靠着感觉的，感觉上知道谁是谁，看此书需要耐心，平静下来。')";
		PreparedStatement statement  = conn.prepareStatement(sql);
		for (int i = 0; i < 300; i++) {
			//statement.setString(1, "999999999"+i+"");
			statement.setString(1, "9999999999"+i+"");
			statement.setString(2, "百年孤独"+i);
			statement.setString(3, "马尔克斯"+i);
			statement.executeUpdate();
		}
	}
}
