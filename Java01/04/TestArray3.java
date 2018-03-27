/*
 *  1.求数组元素的最大值、最小值、平均数、总和等

 * 2.数组的复制、反转

 * 3.数组元素的排序

 */
import java.util.Arrays;
public class TestArray3 {
	public static void main(String[] args) {
		int[] data = new int[] {32,12,53,-31,65,-44,100};

		/**
		*数组最大值
		*/
		int max = data[0];
		for (int i = 0; i < data.length; i++) {
			if (max < data[i]) {
				max = data[i];
			}
			
		}
		System.out.println("数组的最大值为：" + max);

		/**
		*数组的最小值
		*/
		int min = data[0];         //假设第一个是最小值
		for (int i = 0; i < data.length; i++ ) {
			if (min > data[i]) {
				min = data[i];
			}
		}
		System.out.println("数组的最小值为：" + min);

		/**
		*数组的总和
		*/
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		System.out.println("数组的总和为：" + sum);

		/**
		*数组的平均值
		*/
		int avg = 0;
		avg = sum/data.length;
		System.out.println("数组的平均值为：" + avg);

		/**
		*数组的复制
		*/
		int[] newData = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];                       //循环进行赋值
		}

		/**
		*数组反转
		*/
		for (int i = 0; i < data.length/2; i++) {
			int temp = data[i];
			data[i] = data[data.length - 1 - i];     //第一个和最后一个进行交换
			data[data.length -1 - i] = temp;
		}
		/**
		*在反转回去
		*/
		for (int x = 0,y  = data.length - 1; x < y; x++,y--) {
			int temp = data[x];
			data[x] = data[y];
			data[y] = temp;
		}

		System.out.println("再次反转之后：");

		//数组遍历

		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}

		/**
		*使用冒泡排序，从小到大排列
		*核心思想就是没循环一轮将最大的放到最后面
		*/
		for (int i = 1; i < data.length - 1; i++) {    //控制轮数
			for (int j = 0;  j < data.length-1-i;j++ ) {
				if (data[j] > data[j+1]) {
					int temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp; 
				}
			}
		}
		int[] arr = new int[] {3,-4,1,-6,5,9,43};
		/**
		*用直接选择排序
		*/
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		/**
		*直接选择排序最优算法
		*/
		for (int i = 0; i < arr.length -1; i++) {
			int t = i;		//默认i处是最小的
			for (int j = i; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					t = j;
				}
			}
			if (t != i) {
				int temp = arr[t];
				arr[t] = arr[i];
				arr[i] = temp;
			}
		}

		Arrays.sort(arr);

		//数组遍历
		System.out.print("排序后数组为：" );
		for (int i = 0; i < arr.length; i++) {
			System.out.print( arr[i] + " ");
		}
	}
}