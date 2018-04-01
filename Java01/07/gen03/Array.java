/*
没有泛型数组，声明时可以使用？通配符，但是分配内存时会报错

*/
public class Array {
	public static void main(String[] args) {
		Integer[] arr = new Integer[3];
		//tudent<String>[] stu = new Student<String>[12];   在分配内存的时候会失败，所以不能使用泛型数组
		Student<?>[] arr2 = new Student[19];

		MyArrayList<String> strList = new MyArrayList<String>();
		strList.add(0,"aa");
		String elem = strList.getElem(0);
		System.out.println(elem);

	}
}

class MyArrayList<E> {
	//E[] cap = new E[10]  是错误的，泛型没有数组，声明可以，但是不可以使用
	Object[] cap = new Object[12];

	public void add(int index,E e) {
		cap[index] = e;              //Object可以接收任意类型
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