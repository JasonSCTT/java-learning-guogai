/*
û�з������飬����ʱ����ʹ�ã�ͨ��������Ƿ����ڴ�ʱ�ᱨ��

*/
public class Array {
	public static void main(String[] args) {
		Integer[] arr = new Integer[3];
		//tudent<String>[] stu = new Student<String>[12];   �ڷ����ڴ��ʱ���ʧ�ܣ����Բ���ʹ�÷�������
		Student<?>[] arr2 = new Student[19];

		MyArrayList<String> strList = new MyArrayList<String>();
		strList.add(0,"aa");
		String elem = strList.getElem(0);
		System.out.println(elem);

	}
}

class MyArrayList<E> {
	//E[] cap = new E[10]  �Ǵ���ģ�����û�����飬�������ԣ����ǲ�����ʹ��
	Object[] cap = new Object[12];

	public void add(int index,E e) {
		cap[index] = e;              //Object���Խ�����������
	}

	@SuppressWarnings("unchecked")
	public E[] getAll() {
		return (E[]) cap;
	}

	@SuppressWarnings("unchecked")
	public E getElem(int index) {
		return (E) cap[index];
	}
}

/*
    public E get(int index) {
    rangeCheck(index);

    return elementData(index);
    }
	
    @SuppressWarnings("unchecked")
    E elementData(int index) {
        return (E) elementData[index];
    }

*/