/**
*ʵ���Լ���LinkedList���ײ����˫���������ʽ
*
*/

public class MyLinkedList /**implements List*/{               //ʵ�ִ˽ӿ���Ҫʵ�������ж���ķ�������ʵ�ڵײ��Ǽ̳�AbstractList

	transient Node first;          //��ʾͷ�ڵ㣬����ͨ��ͷ�ڵ��ҵ�����Ľڵ�
	transient Node last;				//���һ���ڵ�

	private int size;				//����ĸ���

	public MyLinkedList () {

	}
	public int size() {
		return size;
	}

	/**
	*���Ԫ�� ,��ʱ��ʽ����ǰ���룬Ҳ���Դ����һ���ڵ㿪ʼ����
	*
	*final Node l = last;
	*final Node newNode = new Node(l,obj,null);
	*last = newNode();
	*��ֻ��һ��Ԫ�أ���һ����ʼ���һ����
	*����l.next = nowNode;      //���һ����һ����Ϊ�½ڵ�
	*size++
	*/
	private void add(Object obj) {
		//�ײ�����ôд�ģ������
		final Node f = first;                      //��final��������ζ�Ų��ɱ䡣
		final Node newNode = new Node(null,obj,f);
		first = newNode;

		if (f == null) {                       
			//first = n;       						//��ֵ����һ���ڵ㣬
			last = newNode;							//���ֻ��һ��Ԫ�أ������һ��Ҳ�ǵ�һ��
		}else {
			f.previous = newNode;                   //���һ����ǰһ����
		}
		size++;
	}

	/**
	*��ȡ����������ȡԪ��
	*/
	public Object get(int index) {
		checkElementIndex(index);
		return node(index).obj;               //�����������Ľڵ�obj
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
	*�߽���
	*/
	public void checkElementIndex(int index) {
		if (index < 0 || index >= size) {
			throw new RuntimeException("Խ����������������index");
		}
	}

	/**
	*���Ԫ��
	*/
	public void clear () {
		for (Node x = first; x != null; ) {       //�ӿ�ʼֱ��xΪ null
			Node next = x.next;                  //x.next ��ֵ��next��
			x.obj = null;
			x.next = null;
			x.previous =  null;
			x = next;
		}
		first = last = null;                      //��ʼ�ͽ���������Ϊnull��
		size = 0;								  //size��Ϊ0
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
	*ͨ��Node����Ԫ��
	*/
	Node node(int index) {
		if (index < (size >> 1)) {                   //���ж�index���м�λ�õ���߻����ұ�
			Node x = first;
			for (int i = 0; i < index; i++) {		//�ӵ�һ�������
				x = x.next;
			}
			return x;
		}else {
			Node x = last;
			for (int i = size - 1; i > index; i-- ) {    //�������ǰ��
				x = x.previous;
			}
			return x;
		}
	}

	private static class Node {					//˽�еľ�̬�ڲ��࣬�����ֻ࣬Ϊ�ⲿ������ⲿ�����ֱ�ӵ��ó�Ա������

		 Node previous;      					//��ʾ�ڵ��ǰһ��
		 Object obj;						    //�ڵ��Լ�
		 Node next;		  				    //�ڵ����һ���ڵ�

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
*��ʾһ���ڵ���
*
*/
/*
class Node {
	private Object previous;      //��ʾ�ڵ��ǰһ��
	private Object obj;			 //�ڵ��Լ�
	private Object next;		//�ڵ����һ���ڵ�

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