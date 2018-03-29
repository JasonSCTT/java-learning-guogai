/**
*���ֲ��ҷ�
*/

public class BinarySearch {
	public static void main(String[] args) {
		int[] nums = new int[] {2,3,4,5,6,78,99};
		System.out.println(binarySearch3(nums,78));
		System.out.println(binarySearch(nums,6));
		System.out.println(binarySearch(nums,4,5,6));
		
	}

	public static int binarySearch3(int[] nums,int key) {
		int start = 0;				//��ʼ�±�
		int end = nums.length - 1;	//�����±�
		while (start <= end) {
			int mindle = (start + end)/2;      //����м�����
			if (key > nums[mindle] ) {			//��key�����м䣬��ȥ��ǰ�벿�֡�
				start = mindle + 1;
			}else if (key < nums[mindle]) {    //���������벿��
				end = mindle -1;
			}else {
				return mindle;					//�������м��ֵ
			}
		}

		//�Ҳ����򷵻�-1��
		return -1;
	}

	public static int binarySearch(int[] nums,int key) {
		return binarySearch0(nums,0,nums.length,key);
	}

	public static int binarySearch(int[] nums,int fromIndex,int toIndex, int key) {
		rangeCheck(nums.length,fromIndex,toIndex);
		return binarySearch0(nums,fromIndex,toIndex,key);
	}

	public static int binarySearch0(int[] nums,int fromIndex,int toIndex,int key) {
		int start = fromIndex;
		int end = toIndex - 1;

		while(start <= end) {
			int mid = (start + end) >>> 1;

			int midVal = nums[mid];       //����д��ֱ��һЩ

			if (midVal < key) {          //�м�ֵС��key
				start = mid + 1;
			}else if (midVal > key) {
				end = mid - 1;
			}else {
				return mid;
			}
		}
		return -(start + 1);
	}

	    /**
     * Checks that {@code fromIndex} and {@code toIndex} are in
     * the range and throws an appropriate exception, if they aren't.
     */
    private static void rangeCheck(int length, int fromIndex, int toIndex) {
        if (fromIndex > toIndex) {   //��ʼ�������ڽ�������
            throw new RuntimeException ( "IllegalArgumentException" +
                "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {          //��ʼ����С��0
            throw new RuntimeException("ArrayIndexOutOfBoundsException:" + fromIndex);
        }
        if (toIndex > length) {			//��������С�����鳤��
            throw new RuntimeException("ArrayIndexOutOfBoundsException:" +toIndex);
        }
    }
}