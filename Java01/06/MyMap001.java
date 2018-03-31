/**
*自定义实现Map的功能
*
*HashMap是存放键值对的容器，根据Key对象查找对应的Value值。
*/
public class MyMap001 {

	MyEntry[] arr = new MyEntry[200];

	int size;

	public int size() {
		return size;
	}

	public void put(Object key,Object value) {
		MyEntry e = new MyEntry(key,value);
		arr[size++] = e;
	}

	public Object get(Object key) {
		for (int i = 0; i < size; i++) {       //循环查找key
			if (arr[i].key.equals(key)) {      //根据key找到value的值
				return arr[i].value;
			}
		}
		return null;
	}


	public static void main(String[] args) {
		MyMap001 map = new MyMap001();
		map.put("11","jj");
		map.put("22","数组+hashtable");

		System.out.println(map.get("22"));
		
	}
}

/**
*存放键值对的条目
*/
class MyEntry {

	Object key;
	Object value;

	public MyEntry() {

	}

	public MyEntry(Object key,Object value) {
		this.key = key;
		this.value = value;
	}
}