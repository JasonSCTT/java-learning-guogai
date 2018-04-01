/*
？通配符使用
在类中不能使用？通配符，可以用在声明类型及声明方法的参数上，不能用在声明类上
声明时可以用，但使用时需要指定具体的类型
可以接收泛型的任意类型，只能接收和输出，但是不能修改，因为方法在声明时无法知道具体的类型，因此不能修改
？ extends : <= 上限  指定的类型为子类或自身
？ super ： >= 下限   相当于指定的类型自身或父类
*/

public class Student<T> {
	T score;

	public static void main(String[] args) {
		// Student<?> stu = new Student<?>();     // 不带限制范围的类或接口
		Student<?> stu = new Student<String>();
		test(new Student<Integer>());
		test2(new Student<Apple>());
		//test3(new Student<Apple>());  //泛型没有多态             //无法通过方法调用转换将实际参数Student<Apple>转换为Student<Fruit>
		//test4(new Student<Apple>());	//编译时看左边，运行时看右边
		test4(new Student<Object>());
		test4(new Student<Fruit>());
		//test4(new Student<?>());

	}

	public static void test(Student<?> stt) {     //方法的形参上也可以使用

	}

	public static void test3(Student<Fruit> stu) {

	}
	// <=
	public static void test2(Student<? extends Fruit> stu) {

	}
	//>=
	public static void test4(Student<? super Fruit> stu) {

	}
}