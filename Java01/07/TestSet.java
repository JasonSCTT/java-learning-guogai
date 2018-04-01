/**
*测试Set集合用法
*HashSet无序不可中孚的，底层是采用hashMap，每个方法都调用HashMap中的方法
*/

import java.util.Set;
import java.util.HashSet;
public class TestSet {
	public static void main(String[] args) {
		Set<String> set = new HashSet<>();
		set.add("aaa");
		set.add("bbb");
		set.add(new String("aaa"));           //此时会把“aaa”替换掉
		System.out.println(set.size());
		System.out.println(set.contains("aaa"));
		System.out.println(set.isEmpty());    //false
		set.remove("bbb");
		System.out.println(set.contains("bbb"));   //flase
	}
}