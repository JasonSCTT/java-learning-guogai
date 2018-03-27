/**
*��ӡ�������
*/
public class TestYanghui {
	public static void main(String[] args) {
		//1.��ʼ����ά����
		int[][] yanghui = new int[10][];
		for (int i = 0; i < yanghui.length; i++) {
			yanghui[i] = new int[i + 1];                //����newһ������
		}

		//��ʾ��Ϊÿ������Ԫ�ؽ��и�ֵ
		for (int i = 0; i < yanghui.length; i++) {
			for (int j = 0; j < yanghui[i].length; j++) {
				yanghui[i][0] = yanghui[i][i] = 1;

				if (i > 1 && j > 0 && j < i) {
				yanghui[i][j] = yanghui[i-1][j] + yanghui[i-1][j-1];
				}
			}
			
		}



		//2.��������
		for (int m = 0; m < yanghui.length; m++) {
			for (int n = 0; n < yanghui[m].length; n++) {
				System.out.print(yanghui[m][n] + " ");
			}
			System.out.println();
		}
	}
}