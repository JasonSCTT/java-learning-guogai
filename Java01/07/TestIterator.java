i/**
*Iterator迭代器的使用 
*
*/
import java.util.List;
import java.util.ArrayList;
import java.util.Set;
import java.util.HashSet;
import java.util.Map;
import java.util.HashMap;
import java.util.Map.Entry;
import java.util.Iterator;
public class TestIterator {
	public static void main(String[] args) {

		List<String> list = new ArrayList<>();
		list.add("aaa");
		list.add("bbb");
		list.add("ccc");

		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		System.out.println("--------------------以下使用迭代器------------------------");
		//HashSet是无序不可重复的
		Set<String> set = new HashSet<>();
		set.add("aaa");
		set.add("bbb");
		set.add("ccc");
		set.add("aaa");
		Iterator i = set.iterator();
		while(i.hasNext()) {
			String str = (String)i.next();
			System.out.println(str);
		}


		System.out.println("----------------------------------------------------------");

		Map<Integer,String> map = new HashMap<>();
		map.put(1,"gg");
		map.put(2,"GG");
		map.put(3,"xx");
		Set keys = map.keySet();
		System.out.println(keys.getClass());
		/*for(Iterator i1 = keys.iterator(); i1.hasNext();) {
			Integer  keyStr =  (Integer)i1.next();
			System.out.println(keyStr + "------------" + map.get(keyStr));
		}*/
		for(Integer key : keys) {
			String value = map.get(key);
			System.out.println(key + "----------" + value);
		}

		System.out.println("-------------------------------------------------------------");

		  //1.调用map集合的方法keySet，所有的键存储到Set集合
        Set<String> set = map.keySet();
        System.out.println(set.getClass());    // class java.util.HashMap$KeySet  是HashMap的一个内部类实现的
        //2.遍历Set集合，获取Set集合中所有的元素key
       /* for (String key : set) {          //实现细节上使用的是iterator接口和它的hasNext()，next()方法。
            //3.调用map集合方法get，通过键取得值
            Integer value = map.get(key);
            System.out.println(key + "..." + value);
        }*/
       //第二种遍历方法 通过迭代器模式
        /*Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            //it.next()返回是Set集合元素，也就是Map集合中的键
            String key = it.next();
            Integer value = map.get(key);
            System.out.println(key + "..." + value);
        }*/
        //第三种遍历  forEach方法会接受一个Consumer接口类型作为参数，该接口是一个函数式接口(Functional Interface)，它是内部遍历器的实现方式。
       /* set.forEach((new Consumer<String>() {
            @Override
            public void accept(String key) {
                Integer value = map.get(key);
                System.out.println(key + "...." + value);
            }
        }));*/

       //第四种遍历  使用JDK1.8新特性Lambda表达式
        //set.forEach(( String key) -> System.out.println(key + "..." + map.get(key)));
        //set.forEach(key -> System.out.println( key + "..." + map.get(key)));
        //最终版本
        set.forEach(System.out :: println);

		
	}
}