/**
*打印杨辉三角
*
*/
public class TestYangHui1 {
	public static void main(String[] args) {
		//1.初始化二维数组
		int[][] yangHui = new int[10][];
		for (int i = 0; i < yangHui.length; i++) {
			yangHui[i] = new int[i + 1];
		}
		//为每个数组元素进行赋值
		for (int i = 0; i < yangHui.length; i++) {
			for (int j = 0; j < yangHui[i].length; j++) {
				yangHui[i][0] = yangHui[i][i] = 1;

				if (i > 1 && j > 0 && j < i) {
					yangHui[i][j] = yangHui[i-1][j] + yangHui[i-1][j-1];
				}
			}
		}



		//2.打印数组

		for (int i = 0; i < yangHui.length; i++) {
			for (int j = 0; j < yangHui[i].length; j++) {
				System.out.print(yangHui[i][j] + "\t");
			}
			System.out.println();
		}
	}
}