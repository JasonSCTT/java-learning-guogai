/*
��̬��������ʽ���̳й�ϵ����д���෽������������ָ���������
*/
public class FruitApp {
	public static void main(String[] args) {
		Fruit f = new Apple();
		test(new Apple());		
	}

	/**
	*�β�ʹ�ö�̬
	*/
	public static void test(Fruit f) {
		System.out.println("gogo");
	}
	/**
	*��������ʹ�ö�̬
	*
	*/
	public static Fruit test1() {
		return new Apple();
	}
}