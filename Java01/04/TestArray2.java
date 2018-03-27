/**
*二维数组的初始化和使用
*
*/
public class TestArray2 {
	public static void main(String[] args) {
		int[] scores = new int[10];
		int[][] scores1;
		String names[][];

		//第一种静态初始化
		scores1 = new int[][] {{1,2,3},{2,3,4},{5,6,7}};

		//第二种动态初始化
		names = new String[4][6];  //第一种方式
		names = new String[4][];	//第二种方式
		names[0] = new String[1];
		names[1] = new String[2];
		names[2] = new String[3];
		names[3] = new String[4];

		//2.来引用某一个具体的元素
		int[][] i = new int[3][2];
		i[0][1] = 89;
		i[1][0] = 100;

		//3数组的长度
		//二维数组的属性：length
		System.out.println(i.length);			//3
		System.out.println(i[0].length);		//2
		System.out.println(names.length);		//4
		System.out.println(names[3].length);	//4

		//遍历二维数组

		for (int m = 0; m < scores1.length; m++ ) {			//外层控制行数
			for (int n = 0; n < scores1[m].length; n++) {
				System.out.print(scores1[m][n] + " ");
			}
			System.out.println();  //打印完一行时换行
		}


	}
}