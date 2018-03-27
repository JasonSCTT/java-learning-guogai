/**
*数组异常
*/
public class TestException {
	public static void main(String[] args) {
		//1.数组下标越界
		int[] i = new int[3];
		i[0] = 3;
		//i[3] = 4;           //java.lang.ArrayIndexOutOfBoundsException: 3
		//for (int m = 0; m < i.length; m++) {   // java.lang.ArrayIndexOutOfBoundsException: 3
		//	System.out.println(i[m]);
		//}

		boolean[] b = new boolean[4];
		//b = null;						// java.lang.NullPointerException 空指针异常
		System.out.println(b[1]);

		String[] str = new String[3];   //String数组初始化后都为null，打印时就会出错
		//System.out.println(str[2].toString());   // java.lang.NullPointerException

		//第三种
		int[][] n = new int[3][];  
		n[2][0] = 13;                // java.lang.NullPointerException

	}
}