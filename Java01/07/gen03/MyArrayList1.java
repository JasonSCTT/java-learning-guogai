/*
�򻯰������,����ӿڼ��ṩ����
�ӿڵĺô����Ƿ�����̬��ֻ��Ҫ�����׼���ڲ�ʵ�ֵ�ϸ�ڲ���Ҫ��ע
*/
import java.util.Iterator;
public class MyArrayList1 implements java.lang.Iterable<String> {
	private String[] elem = {"a","b","c","d","e","f"};
	private int size = elem.length;



	/**
	*�ṩһ���������������ڲ����ڲ���
	*/
	public Iterator<String> iterator() {
		return new Iterator<String>(){

		private int cursor = -1;

		/**
		*�ж��Ƿ������һ��Ԫ��
		*
		*/
		public boolean hasNext() {
			return cursor + 1 < size;
		}

		/**
		*��ȡ��һ��Ԫ��
		*
		*/
		public String next() {
			cursor++;       //�ƶ�һ��
			return elem[cursor];
		}
		//ɾ��Ԫ��
		public void remove() {

		}
	};
	}

	

	public static void main(String[] args) {

		MyArrayList1 list = new MyArrayList1();
		//��ʱ���Ե���iterator��������Iterator����ÿ�ζ�newһ���µ�MyIt
		Iterator<String> it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());
			it.remove();    //�����ڲ鿴��ʱ��ɾ��Ԫ��
		}

		System.out.println("--------------------");

		it = list.iterator();
		while(it.hasNext()) {
			System.out.println(it.next());  
		}
		System.out.println("---------------��ǿforѭ��-------------------");
		//�ײ��ǲ���Iterable�ӿڵ�iterator����ʵ�ֵģ���ϮC#�� 
		//���߱�������һ���£���ǿforѭ������ʵ��java.lang.Iterable�ӿڣ���дiterator����
		for(String temp : list) {
			System.out.println(temp);
			//�ڲ������α�������ʹ����ǿforѭ����û��remove������
		}
		
	}
}
/*

        final void checkForComodification() {
            if (modCount != expectedModCount)
                throw new ConcurrentModificationException();
        }
*/