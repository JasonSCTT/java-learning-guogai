/**
*测试重载
*
*在同一类中，方法名相同，参数列表类型、个数、顺序不同则构成重载。构造方法也可以进行重载。
*不能根据返回值类型判断，因为是在编译期就决定了，调用时只要不构成歧义就行
*/

public class TestOverLoad {
	public static void main(String[] args) {
		MyMath m = new MyMath();
		System.out.println(m.add(2,3));
		System.out.println(m.add(1,2,3));
	}
}

class MyMath {
	int a;
	int b;

	public MyMath() {}

	public MyMath(int a) {
		this.a = a;
	}

	public MyMath(int a,int b) {
		this.a = a;
		this.b = b;
	}

	public int add(int b,double a) {
		return (int)(a + b);
	}

	public int add(double a,int b) {
		return (int)(a + b);
	}

	public int add(int a,int b) {
		return a + b;
	}

	public int add(int a,int b,int c) {
		return a + b  + c;
	}
}