/**
*ģ��JDK��ArrayList��ʵ��
*
*/
public class MyArrayList1 {
	/**
	*The value is used for Object storage
	*/
	Object[] value;

	/**
	*The size is the number of Objects used.
	*/
	int size;

	/**
	*���г�ʼ����
	*/
	public MyArrayList() {
		//value = new Object[16];
		this(16);               //��������һ��������
	}

	public MyArrayList(int size) {
		if (size < 0) {
			throw new RuntimeException("��ʼ��ʱ����Ϊ��������ʱsize��СΪ��" + size);
		}
		value = new Object[size];
	}

	/**
	*��ȡ����ĸ���
	*/
	public int size() {
		return size;
	}

	/**
	*�ж������Ƿ�Ϊ��
	*/
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	*���Ԫ��
	*/
	public void add(Object obj) {
		value[size] = obj;
		size++;
		//��ʱ�ж�size�Ƿ�С��value�Ĵ�С��
		if (size >= value.length) {
			int newCapacity = value.length*2;
			Object[] newList = new Object[newCapacity];
			for (int i = 0; i < value.length; i++) {
				newList[i] = value[i];                 //Ñ­»·±éÀú£¬°Ñ¾ÉÊý×éÔªËØ¸³Öµ¸øÐÂÊý×é    
			}
			value = newList;
		}
	}

	/**
	*�ж������״γ��ֵ�λ��
	*/
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size;i++ ) {
				if (value[i] == null) {        //�����жϳ���null��������
					return i;
				}
			}
		}else {
			for (int i = 0; i < size; i++) {
				if (o.equals(value[i])) {    //Ȼ����ʹ��equals�Ƚ�value[i]λ�õ�Ԫ���Ƿ����
					return i;
				}
			}
		}
		return -1;
	}

	/**
	*�ж��������һ�γ��ֵ�����
	*Returns the index of the last occurrence of the specified element
    * in this list, or -1 if this list does not contain the element.
	*/
	public int lastIndexOf(Object o) {
		if (o == null) {
			for (int i = size - 1; i >= 0; i--) {
				if (value[i] == null) {
					return i;
				}
			}
		}else {
			for (int i = size -1; i >= 0; i--) {
				if (o.equals(value[i])) {
					return i;
				}
			}
		}
		return  -1;
	}

	public Object remove(int index) {
		rangeCheck(index);
		Object oldValue = value[index];

        int numMoved = size - index - 1;      //��Ҫ������Ԫ�ظ���
        if (numMoved > 0)
            System.arraycopy(value, index+1, value, index,
                             numMoved);
        value[--size] = null; // ��������ȥ����

        return oldValue;
	}

	/**
	*�����µ�Ԫ��
	*Replaces the element at the specified position in this list with
    * the specified element.
	*/
	public Object set(int index,Object o) {
		rangeCheck(index);
		Object oldValue = value[index];
		value[index] = o;
		return oldValue;
	}

	/**
	*��ȡ����Ԫ��
	*/
	public Object get(int index ) throws Exception {
		if (index < 0 || index > size - 1) {
			throw new RuntimeException("�����±�Խ��");     //��ʱ���׳�һ���쳣��
		}
		return value[index];
	}
	public void rangeCheck(int index) {
		if (index >= size) {
			throw new RuntimeException("IndexOutOfBoundsException" + outOfBoundsMsg(index));
		}
	}

	public String outOfBoundsMsg(int index) {
		return " index: " + index + "size" + size; 
	}

	public static void main(String[] args) throws Exception  {
		MyArrayList1 array = new MyArrayList1(2);
		array.add(3);
		array.add(4);
		array.add("aaa");					//����ӵ�ʱ����Զ���������2��
		array.add(new Human("guo"));
		array.add("aaa");
		array.add(4);
		array.add("guo");
		
		System.out.println(array.get(2));

		Human h = (Human) array.get(3);
		System.out.println(h.getName());

		System.out.println(array.size());     //4
		System.out.println(array.isEmpty());  //false

		System.out.println("----------------------------------------------");

		System.out.println(array.indexOf("guo"));  //4
		System.out.println(array.indexOf(4));
		System.out.println(array.lastIndexOf(4));

		System.out.println("-------------------------------------------------");
		array.set(6,"gg");
		System.out.println(array.get(1));
		System.out.println(array.size());
		array.remove(2);
		System.out.println(array.size());





	}
}

class Human {
	private String name;

	public Human () {

	}

	public Human (String name) {
		this.name = name;
	}

	public void SetName(String name) {
		this.name = name;
	}

	public String getName() {
		return name;
	}
}