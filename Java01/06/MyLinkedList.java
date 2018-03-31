/**
*实现自己的LinkedList，底层采用双向链表的形式
*
*/

public class MyLinkedList /**implements List*/{               //实现此接口需要实现它所有定义的方法。其实在底层是继承AbstractList

	transient Node first;          //表示头节点，可以通过头节点找到任意的节点
	transient Node last;				//最后一个节点

	private int size;				//链表的个数

	public MyLinkedList () {

	}
	public int size() {
		return size;
	}

	/**
	*添加元素 ,此时方式是向前插入，也可以从最后一个节点开始插入
	*
	*final Node l = last;
	*final Node newNode = new Node(l,obj,null);
	*last = newNode();
	*当只有一个元素，第一个开始最后一个，
	*否则l.next = nowNode;      //最后一个下一个变为新节点
	*size++
	*/
	private void add(Object obj) {
		//底层是这么写的，佩服。
		final Node f = first;                      //用final修饰类意味着不可变。
		final Node newNode = new Node(null,obj,f);
		first = newNode;

		if (f == null) {                       
			//first = n;       						//赋值给第一个节点，
			last = newNode;							//如果只有一个元素，则最后一个也是第一个
		}else {
			f.previous = newNode;                   //最后一个的前一个。
		}
		size++;
	}

	/**
	*获取根据索引获取元素
	*/
	public Object get(int index) {
		checkElementIndex(index);
		return node(index).obj;               //返回索引处的节点obj
	}

	public Object getFirst() {
		final  Node f = first;
		if (f == null) {
			throw new RuntimeException("NoSuchElementException");
		}
		return f.obj;
	}

	public Object getLast() {
		final Node l = last;
		if (l == null) {
			throw new RuntimeException("NoSuchElementException");
		}
		return l.obj;
	}

	public Object peek() {
		final Node f = first;
		return (f == null) ? null : f.obj;
	}

	/**
	*边界检查
	*/
	public void checkElementIndex(int index) {
		if (index < 0 || index >= size) {
			throw new RuntimeException("越界啦，请重新输入index");
		}
	}

	/**
	*清除元素
	*/
	public void clear () {
		for (Node x = first; x != null; ) {       //从开始直到x为 null
			Node next = x.next;                  //x.next 赋值给next。
			x.obj = null;
			x.next = null;
			x.previous =  null;
			x = next;
		}
		first = last = null;                      //开始和结束都设置为null，
		size = 0;								  //size置为0
	}


    /**
     * Unlinks non-null node x.
     */
    
    /**
    *
    *
    E unlink(Node<E> x) {
        // assert x != null;
        final E element = x.item;
        final Node<E> next = x.next;
        final Node<E> prev = x.prev;

        if (prev == null) {
            first = next;
        } else {
            prev.next = next;
            x.prev = null;
        }

        if (next == null) {
            last = prev;
        } else {
            next.prev = prev;
            x.next = null;
        }

        x.item = null;
        size--;
        modCount++;
        return element;
    }
    */

	/**
	*通过Node查找元素
	*/
	Node node(int index) {
		if (index < (size >> 1)) {                   //先判断index在中间位置的左边还是右边
			Node x = first;
			for (int i = 0; i < index; i++) {		//从第一个向后找
				x = x.next;
			}
			return x;
		}else {
			Node x = last;
			for (int i = size - 1; i > index; i-- ) {    //从最后往前找
				x = x.previous;
			}
			return x;
		}
	}

	private static class Node {					//私有的静态内部类，代码简洁，只为外部类服务，外部类可以直接调用成员变量。

		 Node previous;      					//表示节点的前一个
		 Object obj;						    //节点自己
		 Node next;		  				    //节点的下一个节点

		public Node() {

		}

		public Node(Node previous,Object obj,Node next) {
			this.previous = previous;
			this.obj = obj;
			this.next = next;
		}

		

	}

	public static void main(String[] args) {
		MyLinkedList list = new MyLinkedList();
		list.add("aa");
		list.add("bb");
		System.out.println(list.size());
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}

		System.out.println(list.getFirst());
		System.out.println(list.getLast());
		System.out.println(list.peek());
		list.clear();
		System.out.println(list.size());
	}

}

/**
*表示一个节点类
*
*/
/*
class Node {
	private Object previous;      //表示节点的前一个
	private Object obj;			 //节点自己
	private Object next;		//节点的下一个节点

	public Node() {

	}

	public Node(Object previous,Object obj,Object next) {
		this.previous = previous;
		this.obj = obj;
		this.next = next;
	}

	public void setPrevious(Object pervious) {
		this.previous = previous;
	}

	public Object getPrevious() {
		return previous;
	}

	public void setObj(Object obj) {
		this.obj = obj;
	}

	public Object getObj() {
		return obj;
	}

	public void setNext(Object next)  {
		this.next = next;
	}

	public Object getNext() {
		return next;
	}
}*/