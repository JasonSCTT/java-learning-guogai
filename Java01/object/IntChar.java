/**
*打印int和char的默认值
*/

public class IntChar {
	int a;
	boolean b;
	char c;
	float f;

	public static void main(String[] args) {
		IntChar ic = new IntChar();
		System.out.println(ic.a);    //0
		System.out.println(ic.b);    //false
		System.out.println(ic.c);	
		System.out.println(ic.f);    //0.0
	
	}
}