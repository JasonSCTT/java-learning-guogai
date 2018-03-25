/**
*学生类
*类是对象的模板，对象是由类实例化得到的。
*
*类似对象的抽取，把相似的部分抽取出来
*
*对象是通过引用来 操作类的属性和方法
*
*通过类的方式来组织代码，通过对象来封装(组织)数据
*/
public class Student {
	//静态的属性
	String name;
	int id;       //学号
	int age;
	String gender;
	int weight;

	Computer computer;

	//动态的方法
	public void study() {
		System.out.println(name + "在学习 ");
	}

	public void sayHello(String sname) {
		System.out.println(name + "向" + sname + "问好");
	}

	public static void main(String[] args) {
		//1、首先在方法区查看是否有类的定义，如果没有则通过JVM的ClassLoader加载类， 加载后在方法区就有了类的信息（代码、static变量、常量池等信息）
		//2、因为s1是局部变量，所以会定义在栈中，栈是自动分配连续的内存、先进后出存放局部变量信息。
		//3、new Student通过调用默认的构造器在堆中创建对象，并将s1地址指向该对象，最后通过s1来操作对象
		Student s = new Student();
		s.name = "guo";                   //在常量池中查找“guo”
		s.study();
		s.sayHello("GG");                 //通过开辟栈帧把“GG”传递进来，方法结束栈帧消失

		Student s2 = new Student();       // Student类已经定义，直接把s2的地址指向Student对象，
		s2.name = "GG";
		s2.age = 25;					  //基本数据类型是可以直接进行赋值的

		Computer c = new Computer();	  //先加载Computer类，在方法区定义类的信息，在堆中创建对象，栈中创建c变量，指向Computer对象的地址
		c.brand = "外星人";				   //首先在常量池中查看是否有有该变量，没有则在常量池中创建，通过c地址来操作对象的实例变量
		c.cpuSpeed = 1000;
		s2.computer = c;                  //将c的地址赋值给s2的全局变量，
		System.out.println(s2.computer.brand);

	}
}

class Computer {
	String brand;
	int cpuSpeed;
}