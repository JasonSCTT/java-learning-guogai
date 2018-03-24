代码示例，方便自己查看
### Welcome
```java
public class Welcome {
	public static void main(String[] args) {                  //args表示arguments，参数的意思。
		System.out.println("gogo,好好加油，认真敲每一行代码");
	}
}
 class Man {

 }

 class Car {

 }

 /**
 *这是一行注释，中间不能且套 ，必须成对编程，类名要见名之一，要有良好的缩进。
 *gogogo，JVM帮助我们隔绝了底层，实现了Java的跨平台。JVM是一种规范，每一个厂商都可以有自己的实现。
 *
 */
```
### Welcome
```java
/**
*测试标识符的写法
*
*标识符必须以字母、下划线，$符号开头
*/
public class Welcome {
	public static void main(String[] args) {
		int adb = 3;

		int $abc = 4;

		int $ = 5;

		int _abc = 6;

		//int _ = 6;     		//Java SE 8 之后的发行版中可能不支持使用 '_' 作为标识符

		//int 123abc = 456;  	//标识符不能以数字开头

		//int abc# = 456;     	//标识符不能包含除了字母、数字、下划线、$符号之外的其它符号#

		//int class = 456;      //标识符不能以Java中的32个关键字

		int 加油 = 666；         //Java采用了Unicode，所以可以使用。universal，通用的字符编码。
	}
}
```
### TestDataType
```java
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
```
### TestFloatType
```java
/**
*测试浮点数
*float是四个字节,double是8个字节.浮点数并不能精确的表示小数.它存在舍入误差
*/
public class TestFloatType {
	public static void main(String[] args) {

		double d = 3.14;      //浮点数默认类型为double
		//float f = 3.18;     //从double转换到float可能会有损失
		float   f1 = 3.18F;   //j将double转为float类型时可以在后面加F

		double d2 = 314e-2;   //科学计数法,表示3.14
		System.out.println(d2);

		float f3 = 0.1F;
		double d3 = 1.0/10;
		System.out.println(f3 == d3);      //false,因为double存在四舍五入,不能精确的表示,可以使用BigDecimal来表示大小数,BigInteger表示大整数.

	}
}
```
### TestDataType
```java
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
```
### TestCharType
```java
/**
*测试char类型
*/
public class TestCharType {
	public static void main(String[] args) {
		char c1 = 'a';
		char c2 = '郭';      //Java内部采用unicode编码,占用两个字节表示一个字符,范围在0-65535之间.
		char c3 = '\'';		//\n表示换行,\r表示回车,\t表示制表符
		System.out.println(c1);
		System.out.println(c2);
		System.out.print(c3);

		char c4 = 'a';				//ASCLL码a为97,c为99
		int i = c4 + 2;
		char c5 = (char)i;    		//这里需要强制转型,因为int占用4个字节,char占用两个.此时输出c,
		System.out.println(c5);

		/**
		*循环打印a-z
		*/
		for(int j = 0; j < 26; j++) {
			char temp = (char)(c4 + j);     //在 a的基础上++,
			System.out.print(temp);
		}

		/**
		*boolean类型
		*/

		boolean b = true;
		if (b) {
			System.out.println("true");
		}
	}
}
```
### TestCast

```java
/**
*测试自动转型和强制转型
*/
public class TestCast {
	public static void main(String[] args) {
		/*byte b1 = 123;
		//byte b2 = 129;      //此时超出了-128-127范围
		//char c1 = -3;		  //不再0-65536之间，也会报错。

		char c = 'a';
		int i = c;    			 //此时是可以自动转换的

		long d01 = 123451;
		float f1 = d01;        //此时是可以转的，这里只是表述容量范围，并不是本身字节数的大小
		*/

		//强制转型
		int ii = 100;
		//int ii = -100;
		char c1 = (char)ii;
		System.out.println(c1);   //100在ASCLL中表示d  //当将一种类型强制转换为另一类型时，而又超出了目标类型的表示范围，则可以编译通过，但是没有意义

		//表达式中类型提升问题

		int a = 3;
		long b = 123;
		double d = 5.3;
		int c = (int)(a + b);			//此时会报错，会向容量大的long提升，要想不报错，则 需要强制类型转换，
		float f = (float)(a + d);       //也需要强制类型转换

		int money = 1_000_000_000;

		int years = 20;

		long total = (long)money * years;     //此时会返回负数，因为已经溢出。需要强制转型为更大的long,需要注意的是不能(long)(money * years)

		System.out.println(total);

		/**
		*一个人70年心跳次数
		*/
		long times = 70L*60*24*365*70;
		System.out.println(times);


	}
}
```
