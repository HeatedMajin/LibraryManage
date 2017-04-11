package cn.majin.Utils;

import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

import cn.majin.domain.JsonBean;
import cn.majin.domain.PerBookStatistics;
import cn.majin.domain.PerMonthStatistics;

public class JsonUtils {
	// 把PerBookStatistics列表转换成JsonBean的列表
	public static List<JsonBean> PerBookStatistics2JsonBean(List<PerBookStatistics> statis) {

		if (statis == null) {
			return null;
		}
		ColorHelper c = new ColorHelper();
		List<JsonBean> jsons = new ArrayList<JsonBean>();
		Iterator<PerBookStatistics> it = statis.iterator();
		while (it.hasNext()) {
			PerBookStatistics p = it.next();
			JsonBean bean = new JsonBean();
			bean.setName(p.getName());
			bean.setValue(p.getBorrow_count());
			bean.setColor(c.getColor());
			jsons.add(bean);
		}
		return jsons;
	}

	public static int[] PerMonthStatistics2Json(List<PerMonthStatistics> statistics) {

		Iterator<PerMonthStatistics> it = statistics.iterator();
		int[] month_count = new int[12];
		int shouldBe = 1;
		StringBuffer sb = new StringBuffer();
		while (it.hasNext()) {
			PerMonthStatistics p = it.next();
			if (Integer.parseInt(p.getMonth()) != shouldBe) {// 解决这个月没有借出书本，月份不存在的情况
				month_count[shouldBe-1] = 0;
			} else {
				month_count[shouldBe-1] = p.getBorrow_count();
			}
			shouldBe++;
		}
		return month_count;
	}
}
