/**
*选择排序算法：
*每一趟从待排序的序列中取出最小的一个元素
*顺序放入已经排好序的最后面
*/
public class SelectSort {
	public static void main(String[] args) {
		int[] nums = new int[] {43,21,63,-54,-13,100};

		//记录每次比较的最小值下标
		int minIndex = 0;
		int arrayLength = nums.length;
		//控制轮数
		for (int i = 0;	i < arrayLength - 1; i++ ) {
			//每次假设i是最小的下标值
			minIndex = i;
			for (int j = i + 1; j < arrayLength; j++) {
				if (nums[minIndex] > nums[j]) {
					minIndex = j;
				}
			}

			//判断交换的下标是否为自己
			if (minIndex != i) {
				int temp = nums[minIndex];
				nums[minIndex] = nums[i];
				nums[i] = temp;
			}
			
		}
		//输出结果
		for (int n : nums) {
				System.out.print(n + "->");
			}
	}
}