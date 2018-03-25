/**
*用For和While分别计算100之内的基数和偶数和
*
*遇到难的问题,先进行分解,分而治之.
*/
public class TestWhileFor {
	public static void main(String[] args) {
		int oddSum = 0;      //用来保存基数和
		int evenSun = 0;     //用来保存偶数和

		for(int i = 0;i <= 100; i++) {
			if (i % 2 != 0) {
				oddSum += i;
			}else {
				evenSun += i;
			}
		}
		System.out.println("偶数和为:" + oddSum);
		System.out.println("偶数和为:" + evenSun);

		System.out.println("-------------------------------------------");
		for(int j = 1; j <= 1000; j++) {
			if (j % 5 == 0) {
				System.out.print(j + "\t");      //t表示制表符
			}
			if(j % 15 == 0) {
				System.out.println();
			}
		}

		System.out.println("----------------九九乘法表------------------------------");

		for (int i = 1; i <= 9;i++ ) {
			for (int j = 1; j <= i ; j++ ) {
				System.out.print(j + "*" + i + "=" + (i * j) + "\t");
			}
			System.out.println();
		}
	}
}