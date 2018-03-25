/**
*测试Scanner类的使用，如何接收键盘的输入
*
*/
import java.util.Scanner;
public class TestScanner {
	public static void main(String[] args) {
		test2();
	}
	public static void test2() {
		Scanner s = new Scanner(System.in);
		System.out.println("请输入一个加数：");
		int a = s.nextInt();
		System.out.println("请输入被加数：");
		int b = s.nextInt();
		int sum = a + b;
		System.out.println("计算结果，和为：" + sum);
	}

	public static void test1() {
		Scanner s = new Scanner(System.in);
		String str = s.next();                       	//next()方法会阻塞运行，等待用户输入内容才结束
		System.out.println("键盘输入的是： " + str);
	}
}