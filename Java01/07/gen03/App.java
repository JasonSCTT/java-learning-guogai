/*
在泛型中没有多态形式
通配符是为了实现多态的功能
*/
public class App {
	public static void main(String[] args) {
		//A<Fruit> a = new A<Apple>();   不能以这种形式
		A<Fruit> a = new A<Fruit>();
	}

	//形参使用多态
	public static void test1(A<Fruit> f) {

	}

	public static A<Fruit> test2() {
		return null;
	}
}