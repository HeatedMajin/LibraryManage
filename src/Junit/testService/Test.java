package Junit.testService;

public class Test {

	/**
	 * ≤‚ ‘String.
	 */
	@org.junit.Test
	public void test1() {
		String str = "abcdefgand";
		System.out.println(str.indexOf("a"));
		System.out.println(str.indexOf("bc"));
		System.out.println(str.indexOf("h"));
		System.out.println(str.substring(0, str.lastIndexOf("and")));
	}

}
