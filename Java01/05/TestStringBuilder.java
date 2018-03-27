/**
*测试可变字符序列，StirngBuilder是线程不安全的，效率高，StringBuffer是线程安全，效率低，每个方法用synchronized修饰
*String是不可以变的，+ 操作符底层采用StingBuilder最长用的方法是append()，
*/
public class TestStringBuilder {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();       //字符数组初始长度为10
		StringBuilder sb1 = new StringBuilder(32);    //字符数组初始化长度为32
		StringBuilder sb2 = new StringBuilder("abc");  //字符数组初始长度为32
		sb2.append("defg");
		sb2.append(true).append("123").append("321");   //super.append(str); return this;  返回当前字符序列
		System.out.println(sb2);

		StringBuilder sb3 = new StringBuilder("a");
		for (int i = 0; i < 10; i++) {
			sb3.append(i);
		}
		System.out.println(sb3);

		StringBuilder sb4 = new StringBuilder("abcdefghijkl");
		sb4.delete(4,5).delete(1,4);
		System.out.println(sb4);

		sb4.reverse();
		System.out.println(sb4);
	}
}