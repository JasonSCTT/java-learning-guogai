/*
JDK7中使用泛型<> 只需要声明一次类型即可
*/

public class Test7 {
	public static void main(String[] args) {
		List<String> list = new  ArrayList<String>();
		List<String> list2 = new ArrayList<>();           //最后的可以省略，
	}
}