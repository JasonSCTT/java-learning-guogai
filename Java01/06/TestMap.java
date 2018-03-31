/**
*测试Map的基本用法
*
*/
import java.util.HashMap;
public class TestMap {
	public static void main(String[] args) {
		HashMap<Integer,String> map = new HashMap<Integer,String>(20);
		map.put(1,"gogo");
		map.put(2,"guo");
		map.put(3,"you");

		System.out.println(map.isEmpty());
		System.out.println(map.get(1));
		System.out.println(map.get(2));

		System.out.println(map.remove(2));
		System.out.println(map.size());     //2

		boolean b = map.containsKey(3);     //true
		System.out.println(b);

		boolean b1 = map.containsValue("gogo");
		System.out.println(b1);

		//清空map集合
		map.clear();





	}
}