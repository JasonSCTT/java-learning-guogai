/**
*ArrayList���ԣ�ArrayList�ײ��������,֮���Բ�ѯ�죬����Ϊ������������
*				����ɾ�������Ч�ʽϵͣ��漰���ƶ�Ԫ�غ͵ײ㸳ֵ������Ϊԭ����1.5��
*				int newCapacity = oldCapacity + (oldCapacity >> 1);
*				
*	
*				LinkedList�ײ����˫��������Ч�ʽϸߣ�ֻ��Ҫ�Ͽ�ǰ׺�ͺ�׺��
*				���ǲ�ѯ�м�Ԫ��Ч�ʽϵͣ�
*
*
*				Vector:��ArrayListΨһ�����������ÿ�������ϼ���synchronized�ؼ��֣��̰߳�ȫ������ͬ����
*				����Ϊԭ����������int newCapacity = oldCapacity + ((capacityIncrement > 0) ?
*                                         capacityIncrement : oldCapacity);
*/
import java.util.Date;
import java.util.List;
import java.util.ArrayList;
public class TestArrayList {
	 //@SuppressWarnings("unchecked")
	public static void main(String[] args) {
		// private static final int DEFAULT_CAPACITY = 10;Ĭ��������10������ʱ��ԭ�л����ϼ�1.5��
		List<Object> list = new ArrayList<>(20);     //�������ʼ��������ֹ���ݣ��ײ����Arrays.copyof,��������System.arraycopy
		list.add(111); 					//����ʹ���˰�װ�࣬�Զ�װ��
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