public class TestArray {
	public static void main(String[] args) {
		//���ڻ����������ʹ��������飺byte��short��int��long��float��double��char��boolean
		//1.����byte��short��int��long���ԣ������������Ժ�Ĭ��ֵΪ0
		int[] scores = new int[4];

		scores[0] = 44;
		scores[2] = 66;
		for (int i = 0; i < scores.length; i++) {
			System.out.println(scores[i]);
		}

		byte[] scores1 = new byte[4];
		scores1[1] = 4;
		scores1[2] = 5;
		for (int i = 0; i < scores1.length; i++) {
			System.out.println(scores1[i]);
		}

		System.out.println("--------------------------------------------------");
		
		//2.����float��double���ԣ�Ĭ��Ϊ0.0
		float[] f = new float[5];                     //.ArrayIndexOutOfBoundsException: 3
		f[3] = 1.2F;
		for (int i = 0; i < f.length; i++) {
			System.out.println(f[i]);
		}

		System.out.println("--------------------------------------------------");

		//3.����char���ԣ�Ĭ��Ϊ�ո�
		char[] c = new char[2];
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);
		}

		System.out.println("--------------------------------------------------");

		//4.����boolean���ԣ�Ĭ��Ϊfalse
		boolean[] b = new boolean[4];
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}

		System.out.println("--------------------------------------------------");

		//5.�����������͵ı������ɵ�������˵��Ĭ�ϳ�ʼ��Ϊnull
		String[] strs = new String[4];
		strs[1] = "AA";
		strs[2] = "BB";
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}
}