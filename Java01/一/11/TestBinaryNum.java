/**
*JDK7新特性之二进制整数下划线分隔符
*在编译时会自动忽略数字之间的下划线，但不支持_123这种形式
*/
public class TestBinaryNum {
	public static void main(String[] args) {
		int a = 0b0000_0000_0000_0000_0000_0000_0000_0011;
		int b = 1_234_456_890;
		System.out.println(a);     	//3
		System.out.println(b);		//1234456890
	}
}