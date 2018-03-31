/**
*�Զ���ʵ��Map�Ĺ���
*
*HashMap�Ǵ�ż�ֵ�Ե�����������Key������Ҷ�Ӧ��Valueֵ��
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
		for (int i = 0; i < size; i++) {       //ѭ������key
			if (arr[i].key.equals(key)) {      //����key�ҵ�value��ֵ
				return arr[i].value;
			}
		}
		return null;
	}


	public static void main(String[] args) {
		MyMap001 map = new MyMap001();
		map.put("11","jj");
		map.put("22","����+hashtable");

		System.out.println(map.get("22"));
		
	}
}

/**
*��ż�ֵ�Ե���Ŀ
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