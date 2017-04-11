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
	 * �����ݿ��м����û�
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
	 * �����û�������������û�,���������û������룬�������ֻ����һ��һ�����
	 * 
	 * ���ң��ҵ������û��Ľ�ɫ���ŵ�userbean��
	 */
	public User findUser(String username, String password) {
		try {

			// �õ��û��Ļ�����Ϣ
			QueryRunner runner = new QueryRunner();
			String sql = "select username,gender,password,phone,birthday,email,preference from users where username = ? and password = ?";
			Object[] params = { username, password };
			User u = runner.query(JdbcUtils.getConnection(),sql, params, new BeanHandler(User.class));

			//�û�������˺ź�������󣬲���Ҫ�ٽ�һ����ȡ���ɫ
			if(u==null){
				return null;
			}
			// �õ��û��Ľ�ɫ
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
	 * ����û����ǲ����Ѿ�����
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
	 * �޸��û���Ϣ
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

	// �޸��û�������
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

	// �ж�һ���˺��ǲ��Ǳ����˵��˺�
	public boolean isShut(String username) {
		try {
			QueryRunner runner = new QueryRunner();
			String sql = "select count(*) from shutUser where username = ?";
			Object[] result = runner.query(JdbcUtils.getConnection(),sql, username, new ArrayHandler());
			int count = ((BigDecimal) result[0]).intValue();
			if (count == 0) {
				// �����û�б���
				return false;
			} else {
				// ����ű�����
				return true;
			}
		} catch (Exception e) {
			e.printStackTrace();
			throw new DaoException(e);
		}
	}

}
