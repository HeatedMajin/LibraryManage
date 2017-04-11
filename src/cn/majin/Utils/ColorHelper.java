package cn.majin.Utils;

import org.junit.Test;

public class ColorHelper {
	public int cindex;
//
//	public ColorHelper() {
//		cindex = 0x111111;
//	}
//
//	public String getColor() {
//		cindex += 0x111111;
//		if (cindex > 0x888080) {
//			cindex = 0x111111;
//		}
//		String result = Integer.toString(cindex, 16);
//		if (result.length() < 6) {
//			result = "0" + result;
//		}
//		return "#" + result;
//	}
	 public static String[] colors = { "#008000", "#208000", "#408000",
	 "#608000", "#808000",
	 "#a08000", "#c08000", "#ff8000", "#ff80ff", "#c060ff", "#a060ff",
	 "#8060ff", "#6080ff ","#0080ff", "#2080ff", };
	
	 public ColorHelper() {
	 cindex = 0;
	 }
	
	 public String getColor() {
	 if (cindex > colors.length) {
	 cindex = 0;
	 }
	 return colors[cindex++];
	 }
	// @Test
	// public void test() {
	// for (int i = 0; i < 65; i++) {
	// System.out.println(this.getColor());
	// }
	// }

}
