package cn.majin.Utils;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Map;

import javax.servlet.http.HttpServletRequest;

import org.apache.commons.beanutils.BeanUtils;
import org.apache.commons.beanutils.ConvertUtils;
import org.apache.commons.beanutils.Converter;

import cn.majin.domain.SearchConditionBean;

public class WebUtils {

	public static <T> T request2bean(HttpServletRequest request,Class<T> beanclass){
		try {
			Map map =request.getParameterMap(); 
			T bean  = beanclass.newInstance();
			ConvertUtils.register(new Converter() {
				
				@Override
				public Object convert(Class arg0, Object value) {
					if(value==null || !(value instanceof String)){
						return null;
					}
					String str = (String)value;
					if(str.trim().equals("")){
						return null;
					}
					
					SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
					try {
						return dateFormat.parse(str);
					} catch (ParseException e) {
						e.printStackTrace();
						return null;
					}
					
				}
			}, Date.class);
			BeanUtils.populate(bean, map);
			return bean;
		} catch (Exception e) {
			e.printStackTrace();
			throw new RuntimeException(e);
		}
	}

	
}
