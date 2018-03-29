/**
*Integer包装类
*/
public class TestInteger {
	public static void main(String[] args) {
		Integer i = new Integer(3000);
		System.out.println(Integer.MAX_VALUE);    //2147483647
		System.out.println(Integer.MIN_VALUE);    //-2147483648
		System.out.println(Integer.toHexString(i)); //bb8

		Integer i2 = Integer.parseInt("111");
		System.out.println(i2 + 10);  //将字符串转为10进制的整数 arses the string argument as a signed decimal integer.

		Integer i3 = new Integer("222");
		System.out.println(i3.intValue());

		Integer a = 200;
		Integer b = 300;
		Integer c = b;
		System.out.println(c);

		Integer i4 = -128;
		Integer i5 = -128;   			//[-128-127]之间的数，仍然会当作基本数据类型来处理
		System.out.println(i4 == i5);    //true

		System.out.println("----------------------");

		Integer i6 = 1234;
		Integer i7 = 1234;
		System.out.println(i6 == i7);    //false
		System.out.println(i6.equals(i7));//true

	}
}