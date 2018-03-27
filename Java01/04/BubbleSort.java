/*
*冒泡排序算法
*1.比较相邻的两个元素，如果第一个比第二个大则交换位置，是一种稳定的算法
*/

public class BubbleSort {
	public static void main(String[] args) {
		int[] nums = new int[] {4,2,6,8,-2,3,12};
		for (int i = 0; i < nums.length - 1; i++) {
			for (int j = 0; j < nums.length-1-i; j++) {
				if (nums[j] > nums[j+1]) {
					int temp = nums[j];
					nums[j] = nums[j+1];
					nums[j+1] = temp;
				}
			}
		}
		for (int n : nums ) {
			System.out.print(n + "->");
		}
	}
}