/**
*�����쳣
*/
public class TestException {
	public static void main(String[] args) {
		//1.�����±�Խ��
		int[] i = new int[3];
		i[0] = 3;
		//i[3] = 4;           //java.lang.ArrayIndexOutOfBoundsException: 3
		//for (int m = 0; m < i.length; m++) {   // java.lang.ArrayIndexOutOfBoundsException: 3
		//	System.out.println(i[m]);
		//}

		boolean[] b = new boolean[4];
		//b = null;						// java.lang.NullPointerException ��ָ���쳣
		System.out.println(b[1]);

		String[] str = new String[3];   //String�����ʼ����Ϊnull����ӡʱ�ͻ����
		//System.out.println(str[2].toString());   // java.lang.NullPointerException

		//������
		int[][] n = new int[3][];  
		n[2][0] = 13;                // java.lang.NullPointerException

	}
}