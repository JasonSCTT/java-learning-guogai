/**
*ѡ�������㷨��
*ÿһ�˴Ӵ������������ȡ����С��һ��Ԫ��
*˳������Ѿ��ź���������
*/
public class SelectSort {
	public static void main(String[] args) {
		int[] nums = new int[] {43,21,63,-54,-13,100};

		//��¼ÿ�αȽϵ���Сֵ�±�
		int minIndex = 0;
		int arrayLength = nums.length;
		//��������
		for (int i = 0;	i < arrayLength - 1; i++ ) {
			//ÿ�μ���i����С���±�ֵ
			minIndex = i;
			for (int j = i + 1; j < arrayLength; j++) {
				if (nums[minIndex] > nums[j]) {
					minIndex = j;
				}
			}

			//�жϽ������±��Ƿ�Ϊ�Լ�
			if (minIndex != i) {
				int temp = nums[minIndex];
				nums[minIndex] = nums[i];
				nums[i] = temp;
			}
			
		}
		//������
		for (int n : nums) {
				System.out.print(n + "->");
			}
	}
}