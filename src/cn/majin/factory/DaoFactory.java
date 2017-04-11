package cn.majin.factory;

import java.io.InputStream;
import java.util.Properties;

//service����Dao����������Dao��Factory������ʵ��
public class DaoFactory {

	// ʹ������ɵ�������
	public static DaoFactory factory = new DaoFactory();

	private DaoFactory() {
	}

	public static DaoFactory getInstance() {
		return factory;
	}

	// �����ļ���ȡһ�ξ͹���
	private static Properties prop = new Properties();

	static {
		try {
			InputStream input = DaoFactory.class.getClassLoader().getResourceAsStream("dao.properties");
			prop.load(input);
		} catch (Exception e) {
			e.printStackTrace();
			throw new ExceptionInInitializerError(e);// ��ʼ���쳣
		}
	}

	// ���ݴ���Ľӿ����ͣ��������ļ���Ϊ����ӿ�����ʵ��
	public <T> T generateDaoImpl(Class<T> clazz) {
		try {
			// �ӿڵ���������
			String qualifiedName = clazz.getName();
			// �ӿ�ʵ�������������
			String implName = prop.getProperty(qualifiedName);
			// ���ӿڵ��������Ʊ�ɶ���ʹ�ýӿ�������
			T t = (T) DaoFactory.class.getClassLoader().loadClass(implName).newInstance();
			return t;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

}
