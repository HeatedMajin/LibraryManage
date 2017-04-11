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

	// 得到数据库的所有的更改
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

	// 得到所有的用户
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

	// 得到所有的角色
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

	// 将角色授予某个用户
	public void setUserRole(String uname, String[] rid) {
		try {
			QueryRunner runner = new QueryRunner();

			// 现将用户角色全部删掉
			String sql = "delete from user_role where username=?";
			runner.update(JdbcUtils.getConnection(), sql, uname);

			// 向用户角色表中添加数据
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

	// 根据用户名来查找一个用户
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

	// 封号，将一个账号关进特定“小黑表”中
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

	// 获取全部被封了的账号
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

	// 对账号进行解封
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

	// 对用户的增删改做记录时，要获取到用户名和书名
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
