public class TestArray {
	public static void main(String[] args) {
		//对于基本数据类型创建的数组：byte、short、int、long、float、double、char、boolean
		//1.对于byte、short、int、long而言，创建的数组以后，默认值为0
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
		
		//2.对于float、double而言：默认为0.0
		float[] f = new float[5];                     //.ArrayIndexOutOfBoundsException: 3
		f[3] = 1.2F;
		for (int i = 0; i < f.length; i++) {
			System.out.println(f[i]);
		}

		System.out.println("--------------------------------------------------");

		//3.对于char而言：默认为空格
		char[] c = new char[2];
		for (int i = 0; i < c.length; i++) {
			System.out.println(c[i]);
		}

		System.out.println("--------------------------------------------------");

		//4.对于boolean而言：默认为false
		boolean[] b = new boolean[4];
		for (int i = 0; i < b.length; i++) {
			System.out.println(b[i]);
		}

		System.out.println("--------------------------------------------------");

		//5.对于引用类型的变量构成的数组来说：默认初始化为null
		String[] strs = new String[4];
		strs[1] = "AA";
		strs[2] = "BB";
		for (int i = 0; i < strs.length; i++) {
			System.out.println(strs[i]);
		}
	}
}