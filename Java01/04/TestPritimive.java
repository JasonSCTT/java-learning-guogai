/**
*������������������ݵĴ���ʱ���Զ���ֵ
*
*/

public class TestPritimive {
	public static void main(String[] args) {
		//����Pritimive�Ķ���d
		Pritimive d = new Pritimive();
		//����d������Ԫ��
		for (int i = 0; i < d.b.length; i++) {
			System.out.println(d.b[i]);
		}

		System.out.println("---------------------------------------");
		//��d������Ԫ�����¸�ֵ
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