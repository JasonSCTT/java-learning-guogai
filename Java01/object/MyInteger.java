public class MyInteger {

	Integer i = 90;                         //自动包装成对象

	char c = 'x';
	Character ch = new Character(c);

	public static void main(String[] args) {
		MyInteger m = new MyInteger();
		System.out.println(m.ch);
		System.out.println(m.i);
	}
}