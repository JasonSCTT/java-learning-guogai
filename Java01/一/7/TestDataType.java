/**
*测试整数数据类型(byte、short、int、long)
*一个byte站一个字节，short占2个、int占4个字节，long占8个字节。
*/
public class TestDataType {
	public static void main(String[] args) {
		int a = 10;
		int a2 = 010;           //8,以0开头的是八进制数
		int a3 = 0x10;			//16,16进制是以0x开头
		//byte b = 200;         byte表示-128-127 不兼容的类型: 从int转换到byte可能会有损失
		//System.out.println(b);
		System.out.println(a);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(Integer.toBinaryString(a));           //toBinaryString()方法是将数数字转为二进制数
		System.out.println(Integer.toOctalString(a));			 //toOctalString()方法将数字转为八进制数
		System.out.println(Integer.toHexString(a));			     //toHexString()转为十六进制数


		int a5 = 10;
		long a6 = 200;
		byte b2 = 100;			//如果整数大小没有超过byte/int/char的表示范围则可以自动转型

		long a7 = 12345678910L  //如果超过long的范围则在后面加L,注意建议要大写

	}
}