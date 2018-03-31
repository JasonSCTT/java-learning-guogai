/**
*ʹ���б�ʵ��Stack
*/
import java.util.LinkedList;
public class Stack {
	private LinkedList<Object> list = new LinkedList<>();

	/**
	*ѹջ���ŵ���һ��λ��
	*/
	public void push(Object o) {
		list.addFirst(o);
	}

	/**
	*������һ��Ԫ��
	*/
	public Object peek() {
		return list.getFirst();
	}

	/*
	*�Ƴ���һ��Ԫ��
	*
	*/
	public Object pop() {
		return list.removeFirst();
	}

	/**
	*�ж��Ƿ�Ϊ��
	*/
	public boolean empty() {
		return list.isEmpty();
	}

	/**
	*��ӡ
	*/
	public String toString() {
		return list.toString();
	}



	public static void main(String[] args) {
		Stack stack = new Stack();
		stack.push(1);
		stack.push(2);
		stack.push(3);
		stack.push(4);
		stack.push(5);
		System.out.println(stack.toString());  //[5, 4, 3, 2, 1]

		System.out.println(stack.peek());       //5
		System.out.println(stack.toString());  //[5, 4, 3, 2, 1]

		System.out.println(stack.pop());
		System.out.println(stack.toString());  //[5, 4, 3, 2, 1]
	}
}