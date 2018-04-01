/*
简化版迭代器,加入接口及提供方法
接口的好处就是发生多态，只需要定义标准，内部实现的细节不需要关注
*/
import java.util.Iterator;
public class MyArrayList1 implements java.lang.Iterable<String> {
	private String[] elem = {"a","b","c","d","e","f"};
	private int size = elem.length;



	/**
	*提供一个方法访问匿名内部类内部类
	*/
	public Iterator<String> iterator() {
		return new Iterator<String>(){

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
	};
	}

	

	public static void main(String[] args) {

		MyArrayList1 list = new MyArrayList1();
		//此时可以调用iterator方法返回Iterator对象，每次都new一个新的MyIt
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			it.remove();    //可以在查看的时候删除元素
		}

		System.out.println("--------------------");

		it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());  
		}
		System.out.println("---------------增强for循环-------------------");
		//底层是采用Iterable接口的iterator方法实现的，抄袭C#， 
		//两者本质上是一回事，增强for循环必须实现java.lang.Iterable接口，重写iterator方法
		for(String temp : list) {
			System.out.println(temp);
			//在不考虑游标的情况下使用增强for循环，没有remove方法，
		}
		
	}
}
/*

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
*/