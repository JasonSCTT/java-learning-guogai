/**
*����Scanner���ʹ�ã���ν��ռ��̵�����
*
*/
import java.util.Scanner;
public class TestScanner {
	public static void main(String[] args) {
		test2();
	}
	public static void test2() {
		Scanner s = new Scanner(System.in);
		System.out.println("������һ��������");
		int a = s.nextInt();
		System.out.println("�����뱻������");
		int b = s.nextInt();
		int sum = a + b;
		System.out.println("����������Ϊ��" + sum);
	}

	public static void test1() {
		Scanner s = new Scanner(System.in);
		String str = s.next();                       	//next()�������������У��ȴ��û��������ݲŽ���
		System.out.println("����������ǣ� " + str);
	}
}