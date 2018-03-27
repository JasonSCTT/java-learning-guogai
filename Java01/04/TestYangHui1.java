/**
*��ӡ�������
*
*/
public class TestYangHui1 {
	public static void main(String[] args) {
		//1.��ʼ����ά����
		int[][] yangHui = new int[10][];
		for (int i = 0; i < yangHui.length; i++) {
			yangHui[i] = new int[i + 1];
		}
		//Ϊÿ������Ԫ�ؽ��и�ֵ
		for (int i = 0; i < yangHui.length; i++) {
			for (int j = 0; j < yangHui[i].length; j++) {
				yangHui[i][0] = yangHui[i][i] = 1;

				if (i > 1 && j > 0 && j < i) {
					yangHui[i][j] = yangHui[i-1][j] + yangHui[i-1][j-1];
				}
			}
		}



		//2.��ӡ����

		for (int i = 0; i < yangHui.length; i++) {
			for (int j = 0; j < yangHui[i].length; j++) {
				System.out.print(yangHui[i][j] + "\t");
			}
			System.out.println();
		}
	}
}