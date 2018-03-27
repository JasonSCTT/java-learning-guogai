/**
*对数组元素求和
*/

public class TestGetSum {
	public static void main(String[] args) {
		int[][] datas = new int[][] {{1,2,3},{2,3,4},{4,5,6}};

		int sum = 0;
		for (int i = 0; i < datas.length; i++) {
			for (int j = 0; j < datas[i].length; j++ ) {
				System.out.print(datas[i][j] + " ");
				sum += datas[i][j];
			}
			System.out.println();  
		}

		System.out.println(sum);
	}
}