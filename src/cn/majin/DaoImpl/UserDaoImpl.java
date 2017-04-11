package cn.majin.DaoImpl;

import java.math.BigDecimal;
import java.util.List;

import org.apache.commons.dbutils.QueryRunner;
import org.apache.commons.dbutils.handlers.ArrayHandler;
import org.apache.commons.dbutils.handlers.BeanHandler;
import org.apache.commons.dbutils.handlers.BeanListHandler;

import cn.majin.Dao.UserDao;
import cn.majin.Exception.DaoException;
import cn.majin.Utils.JdbcUtils;
import cn.majin.domain.Role;
import cn.majin.domain.User;
import oracle.net.aso.u;

public class UserDaoImpl implements UserDao {

	/*
	 * 向数据库中加入用户
	 */
	public void addUser(User u) {
		try {
			QueryRunner runner = new QueryRunner();

			Object[] params = { u.getUsername(), u.getGender(), u.getPassword(), u.getPhone(),
					new java.sql.Date(u.getBirthday().getTime()), u.getEmail(), u.getPreference() };
			String sql = "insert into users(username,gender,password,phone,birthday,email,preference) values(?,?,?,?,?,?,?)";
			runner.update(JdbcUtils.getConnection(),sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

	/*
	 * 根据用户名和密码查找用户,这里由于用户名主码，所以最多只会有一个一个结果
	 * 
	 * 并且，找到所有用户的角色，放到userbean中
	 */
	public User findUser(String username, String password) {
		try {

			// 得到用户的基本信息
			QueryRunner runner = new QueryRunner();
			String sql = "select username,gender,password,phone,birthday,email,preference from users where username = ? and password = ?";
			Object[] params = { username, password };
			User u = runner.query(JdbcUtils.getConnection(),sql, params, new BeanHandler(User.class));

			//用户输入的账号和密码错误，不需要再进一步获取其角色
			if(u==null){
				return null;
			}
			// 得到用户的角色
			sql = "select r.* from role r,user_role ur where r.id=ur.role_id and username=?";
			List<Role> roles = runner.query(JdbcUtils.getConnection(),sql, u.getUsername(), new BeanListHandler(Role.class));
			u.setRoles(roles);

			return u;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}

	}

	/*
	 * 检查用户名是不是已经存在
	 */
	public boolean isExist(String username) {
		try {
			QueryRunner runner = new QueryRunner();

			String sql = "select * from users where username = ?";
			User u = runner.query(JdbcUtils.getConnection(),sql, username, new BeanHandler(User.class));
			boolean isExist = false;
			if (u != null) {
				isExist = true;
			}
			return isExist;
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}

	}

	/*
	 * 修改用户信息
	 */
	public void editUser(User u) {
		try {
			QueryRunner runner = new QueryRunner();

			Object[] params = { u.getGender(), u.getPassword(), u.getPhone(),
					new java.sql.Date(u.getBirthday().getTime()), u.getEmail(), u.getPreference(), u.getUsername() };
			String sql = "update users set gender=?,password=?,phone=?,birthday=?,email=?,preference=? where username=?";
			runner.update(JdbcUtils.getConnection(),sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}

	}

	// 修改用户的密码
	public void changePassword(String username, String passowrd) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "update users set password=? where username =?";
			Object[] params = { passowrd, username };
			runner.update(JdbcUtils.getConnection(),sql, params);
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}

	}

	// 判断一个账号是不是被封了的账号
	public boolean isShut(String username) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select count(*) from shutUser where username = ?";
			Object[] result = runner.query(JdbcUtils.getConnection(),sql, username, new ArrayHandler());
			int count = ((BigDecimal) result[0]).intValue();
			if (count == 0) {
				// 这个好没有被封
				return false;
			} else {
				// 这个号被封了
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

}
