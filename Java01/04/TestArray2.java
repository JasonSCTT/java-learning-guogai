/**
*��ά����ĳ�ʼ����ʹ��
*
*/
public class TestArray2 {
	public static void main(String[] args) {
		int[] scores = new int[10];
		int[][] scores1;
		String names[][];

		//��һ�־�̬��ʼ��
		scores1 = new int[][] {{1,2,3},{2,3,4},{5,6,7}};

		//�ڶ��ֶ�̬��ʼ��
		names = new String[4][6];  //��һ�ַ�ʽ
		names = new String[4][];	//�ڶ��ַ�ʽ
		names[0] = new String[1];
		names[1] = new String[2];
		names[2] = new String[3];
		names[3] = new String[4];

		//2.������ĳһ�������Ԫ��
		int[][] i = new int[3][2];
		i[0][1] = 89;
		i[1][0] = 100;

		//3����ĳ���
		//��ά��������ԣ�length
		System.out.println(i.length);			//3
		System.out.println(i[0].length);		//2
		System.out.println(names.length);		//4
		System.out.println(names[3].length);	//4

		//������ά����

		for (int m = 0; m < scores1.length; m++ ) {			//����������
			for (int n = 0; n < scores1[m].length; n++) {
				System.out.print(scores1[m][n] + " ");
			}
			System.out.println();  //��ӡ��һ��ʱ����
		}


	}
}