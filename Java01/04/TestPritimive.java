/**
*检验基本数据类型数据的创建时的自动赋值
*
*/

public class TestPritimive {
	public static void main(String[] args) {
		//创建Pritimive的对象d
		Pritimive d = new Pritimive();
		//遍历d的数组元素
		for (int i = 0; i < d.b.length; i++) {
			System.out.println(d.b[i]);
		}

		System.out.println("---------------------------------------");
		//给d的数组元素重新赋值
		d.b[0] = true;
		d.b[1] = true;
		d.b[2] = true;
		for (int i = 0; i < d.b.length; i++) {
			System.out.println(d.b[i]);
		}
	}
}
class Pritimive {
	boolean[] b = new boolean[4];
}