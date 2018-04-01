/*
多态的两种形式：继承关系，重写父类方法，父类引用指向子类对象
*/
public class FruitApp {
	public static void main(String[] args) {
		Fruit f = new Apple();
		test(new Apple());		
	}

	/**
	*形参使用多态
	*/
	public static void test(Fruit f) {
		System.out.println("gogo");
	}
	/**
	*返回类型使用多态
	*
	*/
	public static Fruit test1() {
		return new Apple();
	}
}