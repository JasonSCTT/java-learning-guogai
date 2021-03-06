/**
*Java是一门强类型编程语言，每个变量必须对其声明类型
*Java变量时程序中最基本的存储单元，其要素包括变量名、类型、作用域
*
*局部变量：在方法或语句块内部定义的变量，使用前必须先声明后初始化。
*实例变量：也叫做成员变量，定义在方法外部，或者类的内部。如果不自动初始化，JVM会自动帮我们初始化。
*
*final修饰变量只能初始化一次，修饰方法子类不能被重写，修饰类，不能继承也不能被实例化，如String类。主要是为了效率，安全。
*/
public class TestVariable {
	int t;    //实例变量、成员变量、属性、字段。类属于类，
	public static void main(String[] args) {
		int a;
		//int b = a + 3;           //TestVariable.java:13: 错误: 可能尚未初始化变量a
		a = 11;
		int x,y,z;  

		final int  C = 666;			//定义常量时需要主要变量名需要全部大写
		// C = 777;  				//无法为最终变量c分配值
		final int MAX_SPEED = 120;  //使用下划线来区分
		
	}
}