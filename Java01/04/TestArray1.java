/**
*数组一旦初始化，其长度不变
*/
public class TestArray1 {
	public static void main(String[] args) {
		int[] i = new int[] {12,33,12};
		int[] j = new int[10];
		for (int k = 0; k < i.length; k++) {
			j[k] = i[k];
		}

		j[3] = 34;
		j[4] = 44;

		for (int k = 0; k < j.length; k++) {
			System.out.println(j[k]);
		}
	}
}
