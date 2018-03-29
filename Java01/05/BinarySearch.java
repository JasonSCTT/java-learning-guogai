/**
*二分查找法
*/

public class BinarySearch {
	public static void main(String[] args) {
		int[] nums = new int[] {2,3,4,5,6,78,99};
		System.out.println(binarySearch3(nums,78));
		System.out.println(binarySearch(nums,6));
		System.out.println(binarySearch(nums,4,5,6));
		
	}

	public static int binarySearch3(int[] nums,int key) {
		int start = 0;				//开始下标
		int end = nums.length - 1;	//结束下标
		while (start <= end) {
			int mindle = (start + end)/2;      //算出中间坐标
			if (key > nums[mindle] ) {			//当key大于中间，则去掉前半部分。
				start = mindle + 1;
			}else if (key < nums[mindle]) {    //否则租掉后半部分
				end = mindle -1;
			}else {
				return mindle;					//正好是中间的值
			}
		}

		//找不到则返回-1；
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

			int midVal = nums[mid];       //这样写会直观一些

			if (midVal < key) {          //中间值小于key
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
        if (fromIndex > toIndex) {   //开始索引大于结束索引
            throw new RuntimeException ( "IllegalArgumentException" +
                "fromIndex(" + fromIndex + ") > toIndex(" + toIndex + ")");
        }
        if (fromIndex < 0) {          //开始索引小于0
            throw new RuntimeException("ArrayIndexOutOfBoundsException:" + fromIndex);
        }
        if (toIndex > length) {			//结束索引小于数组长度
            throw new RuntimeException("ArrayIndexOutOfBoundsException:" +toIndex);
        }
    }
}