/**
*ArrayList测试，ArrayList底层采用数组,之所以查询快，是因为数据有索引。
*				但是删除和添加效率较低，涉及到移动元素和底层赋值。扩容为原来的1.5倍
*				int newCapacity = oldCapacity + (oldCapacity >> 1);
*				
*	
*				LinkedList底层采用双链表，插入效率较高，只需要断开前缀和后缀。
*				但是查询中间元素效率较低，
*
*
*				Vector:和ArrayList唯一的区别就是在每个方法上加了synchronized关键字，线程安全，方法同步。
*				扩容为原来的两倍，int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
*                                         capacityIncrement : oldCapacity);
*/
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class TestArrayList {
	 //@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// private static final int DEFAULT_CAPACITY = 10;默认容量是10，扩容时在原有基础上加1.5倍
		List<Object> list = new ArrayList<>(20);     //建议给初始容量，防止扩容，底层采用Arrays.copyof,本质上是System.arraycopy
		list.add(111); 					//这里使用了包装类，自动装箱
		list.add(new Date());
		list.add(new Student());
		list.add("aaa");	

		System.out.println(list.size());    		//4
		System.out.println(list.isEmpty());			//false
		//System.out.println(list.remove("aaa"));		//true
		System.out.println(list.contains(111));      //true
		System.out.println(list.size());    		//3

		List<String> list2 = new ArrayList<>();
		list2.add("bbb");
		list2.add("ccc");
		list.add(list2);

		System.out.println(list.size());      //5

		System.out.println(list.get(4));      //[bbb, ccc]

		System.out.println("-----------------------------------");

		for (Object o : list) {
			System.out.println(o);
		}

		list.set(0,222);


	}
}

class Student {

}