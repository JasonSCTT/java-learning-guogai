/**
*使用列表实现Stack
*/
import java.util.LinkedList;
public class Stack {
	private LinkedList<Object> list = new LinkedList<>();

	/**
	*压栈，放到第一个位置
	*/
	public void push(Object o) {
		list.addFirst(o);
	}

	/**
	*弹出第一个元素
	*/
	public Object peek() {
		return list.getFirst();
	}

	/*
	*移除第一个元素
	*
	*/
	public Object pop() {
		return list.removeFirst();
	}

	/**
	*判断是否为空
	*/
	public boolean empty() {
		return list.isEmpty();
	}

	/**
	*打印
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