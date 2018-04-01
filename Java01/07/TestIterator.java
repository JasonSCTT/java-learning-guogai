i/**
*Iterator��������ʹ�� 
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

		System.out.println("--------------------����ʹ�õ�����------------------------");
		//HashSet�����򲻿��ظ���
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

		  //1.����map���ϵķ���keySet�����еļ��洢��Set����
        Set<String> set = map.keySet();
        System.out.println(set.getClass());    // class java.util.HashMap$KeySet  ��HashMap��һ���ڲ���ʵ�ֵ�
        //2.����Set���ϣ���ȡSet���������е�Ԫ��key
       /* for (String key : set) {          //ʵ��ϸ����ʹ�õ���iterator�ӿں�����hasNext()��next()������
            //3.����map���Ϸ���get��ͨ����ȡ��ֵ
            Integer value = map.get(key);
            System.out.println(key + "..." + value);
        }*/
       //�ڶ��ֱ������� ͨ��������ģʽ
        /*Iterator<String> it = set.iterator();
        while(it.hasNext()) {
            //it.next()������Set����Ԫ�أ�Ҳ����Map�����еļ�
            String key = it.next();
            Integer value = map.get(key);
            System.out.println(key + "..." + value);
        }*/
        //�����ֱ���  forEach���������һ��Consumer�ӿ�������Ϊ�������ýӿ���һ������ʽ�ӿ�(Functional Interface)�������ڲ���������ʵ�ַ�ʽ��
       /* set.forEach((new Consumer<String>() {
            @Override
            public void accept(String key) {
                Integer value = map.get(key);
                System.out.println(key + "...." + value);
            }
        }));*/

       //�����ֱ���  ʹ��JDK1.8������Lambda���ʽ
        //set.forEach(( String key) -> System.out.println(key + "..." + map.get(key)));
        //set.forEach(key -> System.out.println( key + "..." + map.get(key)));
        //���հ汾
        set.forEach(System.out :: println);

		
	}
}