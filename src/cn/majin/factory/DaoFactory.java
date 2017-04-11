package cn.majin.factory;

import java.io.InputStream;
import java.util.Properties;

//service层与Dao层解耦，必须是Dao被Factory来生产实例
public class DaoFactory {

	// 使工厂变成单例工厂
	public static DaoFactory factory = new DaoFactory();

	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		return factory;
	}

	// 配置文件读取一次就够了
	private static Properties prop = new Properties();

	static {
		try {
			InputStream input = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);// 初始化异常
		}
	}

	// 根据传入的接口类型，从配置文件中为这个接口生产实例
	public <T> T generateDaoImpl(Class<T> clazz) {
		try {
			// 接口的完整名称
			String qualifiedName = clazz.getName();
			// 接口实现类的完整名称
			String implName = prop.getProperty(qualifiedName);
			// 将接口的完整名称变成对象，使用接口来引用
			T t = (T) DaoFactory.class.getClassLoader().loadClass(implName).newInstance();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
