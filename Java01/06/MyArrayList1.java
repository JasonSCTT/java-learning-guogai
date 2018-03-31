/**
*模拟JDK中ArrayList的实现
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
	*进行初始化，
	*/
	public MyArrayList() {
		//value = new Object[16];
		this(16);               //调用另外一个构造器
	}

	public MyArrayList(int size) {
		if (size < 0) {
			throw new RuntimeException("初始化时不能为负数，此时size大小为：" + size);
		}
		value = new Object[size];
	}

	/**
	*获取数组的个数
	*/
	public int size() {
		return size;
	}

	/**
	*判断容器是否为空
	*/
	public boolean isEmpty() {
		return size == 0;
	}

	/**
	*添加元素
	*/
	public void add(Object obj) {
		value[size] = obj;
		size++;
		//此时判断size是否小于value的大小。
		if (size >= value.length) {
			int newCapacity = value.length*2;
			Object[] newList = new Object[newCapacity];
			for (int i = 0; i < value.length; i++) {
				newList[i] = value[i];                 //脩颅禄路卤茅脌煤拢卢掳脩戮脡脢媒脳茅脭陋脣脴赂鲁脰碌赂酶脨脗脢媒脳茅    
			}
			value = newList;
		}
	}

	/**
	*判断索引首次出现的位置
	*/
	public int indexOf(Object o) {
		if (o == null) {
			for (int i = 0; i < size;i++ ) {
				if (value[i] == null) {        //首先判断出现null的索引。
					return i;
				}
			}
		}else {
			for (int i = 0; i < size; i++) {
				if (o.equals(value[i])) {    //然后在使用equals比较value[i]位置的元素是否相等
					return i;
				}
			}
		}
		return -1;
	}

	/**
	*判断索引最后一次出现的配置
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

        int numMoved = size - index - 1;      //需要操作的元素个数
        if (numMoved > 0)
            System.arraycopy(value, index+1, value, index,
                             numMoved);
        value[--size] = null; // 让垃圾器去回收

        return oldValue;
	}

	/**
	*设置新的元素
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
	*获取数组元素
	*/
	public Object get(int index ) throws Exception {
		if (index < 0 || index > size - 1) {
			throw new RuntimeException("数组下标越界");     //此时会抛出一个异常，
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
		array.add("aaa");					//在添加的时候会自动进行扩容2倍
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