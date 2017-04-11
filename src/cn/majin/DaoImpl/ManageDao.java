package cn.majin.DaoImpl;

import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;
import org.apache.commons.dbutils.handlers.ColumnListHandler;

import cn.majin.Utils.JdbcUtils;
import cn.majin.domain.ChangeBean;
import cn.majin.domain.Role;
import cn.majin.domain.User;

public class ManageDao {

	// �õ����ݿ�����еĸ���
	public List<ChangeBean> getAllChange() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select * from log4change";
			List<ChangeBean> changes = runner.query(JdbcUtils.getConnection(), sql,
					new BeanListHandler(ChangeBean.class));

			return changes;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// �õ����е��û�
	public List<User> getAllUser() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select * from users";
			List<User> users = runner.query(JdbcUtils.getConnection(), sql, new BeanListHandler(User.class));

			if (users == null) {
				return null;
			}
			for (User u : users) {
				sql = "select * from role r,user_role ur where ur.username=? and r.id=ur.role_id";
				List<Role> roles = runner.query(JdbcUtils.getConnection(), sql, u.getUsername(),
						new BeanListHandler(Role.class));
				u.setRoles(roles);
			}
			return users;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// �õ����еĽ�ɫ
	public List<Role> getAllRole() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select id,name from role";
			List<Role> roles = runner.query(JdbcUtils.getConnection(), sql, new BeanListHandler(Role.class));

			return roles;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// ����ɫ����ĳ���û�
	public void setUserRole(String uname, String[] rid) {
		try {
			QueryRunner runner = new QueryRunner();

			// �ֽ��û���ɫȫ��ɾ��
			String sql = "delete from user_role where username=?";
			runner.update(JdbcUtils.getConnection(), sql, uname);

			// ���û���ɫ�����������
			if (rid != null) {
				for (String id : rid) {
					sql = "insert into user_role(username,role_id) values(?,?)";
					Object[] params = { uname, id };
					runner.update(JdbcUtils.getConnection(), sql, params);
				}
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// �����û���������һ���û�
	public User getUserByName(String name) {
		try {
			QueryRunner runner = new QueryRunner();

			String sql = "select * from users where username=?";
			User u = runner.query(JdbcUtils.getConnection(), sql, name, new BeanHandler(User.class));
			if (u == null) {
				return null;
			}
			sql = "select * from role r,user_role ur where ur.username=? and r.id=ur.role_id";
			List<Role> roles = runner.query(JdbcUtils.getConnection(), sql, u.getUsername(),
					new BeanListHandler(Role.class));
			u.setRoles(roles);

			return u;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// ��ţ���һ���˺Źؽ��ض���С�ڱ���
	public void fenghao(String uname) {
		try {
			QueryRunner runner = new QueryRunner();

			String sql = "insert into shutUser values(?)";
			runner.update(JdbcUtils.getConnection(), sql, uname);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// ��ȡȫ�������˵��˺�
	public List<String> getAllShutUsername() {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select * from shutUser";
			return runner.query(JdbcUtils.getConnection(), sql, new ColumnListHandler("username"));
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// ���˺Ž��н��
	public void jiefeng(String uname) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "delete from shutUser where username = ?";
			runner.update(JdbcUtils.getConnection(), sql, uname);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	// ���û�����ɾ������¼ʱ��Ҫ��ȡ���û���������
	public void updateOp_user(String uname, String book_name) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "update log4change set op_user = ?,op_type=op_type||?  where op_user = 'unknown'";
			Object[] params = { uname,":"+ book_name };
			runner.update(JdbcUtils.getConnection(), sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}
}
