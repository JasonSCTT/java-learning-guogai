/*
�򻯰������
*/
public class MyArrayList {
	private String[] elem = {"a","b","c","d","e","f"};
	private int size = elem.length;

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

	public static void main(String[] args) {

		MyArrayList list = new MyArrayList();
		//����ֻ����һ�飬Ҫ�����߾�û�취����ʱ�����ṩ���ڲ���
		while(list.hasNext()) {
			System.out.println(list.next());
		}
		
	}
}