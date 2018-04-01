/*
简化版迭代器
*/
public class MyArrayList {
	private String[] elem = {"a","b","c","d","e","f"};
	private int size = elem.length;

	private int cursor = -1;


	/**
	*判断是否存在下一个元素
	*
	*/
	public boolean hasNext() {
		return cursor + 1 < size;
	}

	/**
	*获取下一个元素
	*
	*/
	public String next() {
		cursor++;       //移动一次
		return elem[cursor];
	}
	//删除元素
	public void remove() {

	}

	public static void main(String[] args) {

		MyArrayList list = new MyArrayList();
		//这里只能走一遍，要想再走就没办法，此时可以提供了内部类
		while(list.hasNext()) {
			System.out.println(list.next());
		}
		
	}
}