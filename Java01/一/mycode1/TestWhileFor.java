/**
*��For��While�ֱ����100֮�ڵĻ�����ż����
*
*�����ѵ�����,�Ƚ��зֽ�,�ֶ���֮.
*/
public class TestWhileFor {
	public static void main(String[] args) {
		int oddSum = 0;      //�������������
		int evenSun = 0;     //��������ż����

		for(int i = 0;i <= 100; i++) {
			if (i % 2 != 0) {
				oddSum += i;
			}else {
				evenSun += i;
			}
		}
		System.out.println("ż����Ϊ:" + oddSum);
		System.out.println("ż����Ϊ:" + evenSun);

		System.out.println("-------------------------------------------");
		for(int j = 1; j <= 1000; j++) {
			if (j % 5 == 0) {
				System.out.print(j + "\t");      //t��ʾ�Ʊ��
			}
			if(j % 15 == 0) {
				System.out.println();
			}
		}

		System.out.println("----------------�žų˷���------------------------------");

		for (int i = 1; i <= 9;i++ ) {
			for (int j = 1; j <= i ; j++ ) {
				System.out.print(j + "*" + i + "=" + (i * j) + "\t");
			}
			System.out.println();
		}
	}
}