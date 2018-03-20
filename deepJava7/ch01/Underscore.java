import static java.lang.System.out;

public class Underscore {
	public static void main(String[] args) {
		out.println(1_500_000);
		double value1 = 5_6.3_4;         //目的是为了方便阅读
										//原则是下划线只能出现在数字中，Java编译器只需要在扫描代码的时候把下划线去掉即可。
		int value2 = 56_3_3_3_1;		//不能出现"_100"这样的形式，因为这样就要求编译器实现做出更复杂的判断。
		out.println(value1);
		out.println(value2);
	}
}