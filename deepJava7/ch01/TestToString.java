//测试toString实现

public class TestToString {
	public static void main(String[] args) {
		int[] data = {1,2,3,4,5,6};
		System.out.println(toString(data));
	}
	public static String toString(int[] a) {
			if (a == null )                         //判断数组是否为null，
				return "null";
			int iMax = a.length - 1;
			if (iMax == -1) {						//判断数组是否为空数组
				return "[]";					
			}
			StringBuilder b = new StringBuilder();  //+字符串底层使用StringBuilder实现。

			b.append("[");
			for (int i = 0; ; i++ ) {
				b.append(a[i]);
				if (i == iMax) {
					return b.append("]").toString();          //判断是否是最后一个元素，
				}
				b.append(",");
			}
		}
}