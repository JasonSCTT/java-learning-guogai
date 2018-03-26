public class StaticTest {
	static int a = 66;            //全局只有一份，s1和s2对象共享同一个a变量

	public static void main(String[] args) {
		StaticTest s1 = new StaticTest();
		StaticTest s2 = new StaticTest();

		System.out.println(s1.a);
		System.out.println(s2.a);
	}
}