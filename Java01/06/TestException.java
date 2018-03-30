/**
*异常
*/

public class TestException {
	public static void main(String[] args) {
		//int i = 1/0;							//此时会抛出ArithmeticException
		try {
			Thread.sleep(1000);					//此时是受检查的异常，必须被捕获或抛出
			//int j = 1/0;
		}catch(InterruptedException e) {
			e.toString();       //显示异常的类名和产生异常的原因
			e.getMessage();		//只显示异常产生的原因，
			//e.printStackTrace();//用来跟踪异常事件发生时的堆栈的内容 
		}

		Computer c = null;		//栈内存有变量c，但是堆空间没有被c指向的对象
		//要想避免可以在调用其判断下
		if (c != null) {
			c.start();             // java.lang.NullPointerException，本质上对象是null，用null调用方法和属性当前会抛出NullPointerException异常
		}

		String str = "123abf";    //只有数字格式才能被转为包装类的对象
		Integer i = new Integer(str);  //.NumberFormatException: For input string: "123abf"

	}	
}

class Computer {
	void start() {
		System.out.println("启动计算机");
	}
}