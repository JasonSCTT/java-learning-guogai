/**
*算术、逻辑、位运算符、扩展运算符
*
*
*/
public class TestOperator {
	public static void main(String[] args) {
		//double d = 10.2 % 3;   //1.1999999999999993
		//System.out.println(d);

		int a = 4;
		int b = a++;              //执行完毕b=4，先给b赋值，在自增。
		int c = ++a;			  //执行完毕b=6，先自增，在赋值。

		System.out.println(a);     //6
		System.out.println(b);	   //4
		System.out.println(c);	   //6

	
	//	int c = 3/0;
		
	
		//boolean c = 1<2&&2>(3/0);   //短路逻辑与
		//System.out.println(c);
	
	
		//测试位运算
		int m = 8;
		int n = 4;
		System.out.println(m&n);
		System.out.println(m|n);
		System.out.println(~m);
		System.out.println(m^n);
		
		/*
        int a = 3*2*2;
		int b = 3<<3; 			 //左移相当于：3*2*2;
		int c = 12/2/2;
		int d = 12>>2;           //右移相当于：12/2/2
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	
		//扩展运算符
		int  a = 3;
		a +=5;  //a = a+5;
		*/

		String str = "5";
		int s = 4;

		System.out.println(str + s);     //54  底层把+操作符替换成StringBuilder,用apend()方法进行追加.因为是可变字符串,效率较高
		//System.out.println((new StringBuilder()).append(s).append(byte2).toString());

		//三目条件运算符

		//x ? y : z;                        //x是表格boolean表达式,如果为true,则返回y的值,为false,则返回z的值

		int a1 = 3;
		int b1 = 4;
		String str1 = "";

		str1 = (a1 < b1) ? "a<b" : "a>b";
		System.out.println(str1);           //a<b
		
	}
}