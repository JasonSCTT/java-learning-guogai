/**
*�쳣
*/

public class TestException {
	public static void main(String[] args) {
		//int i = 1/0;							//��ʱ���׳�ArithmeticException
		try {
			Thread.sleep(1000);					//��ʱ���ܼ����쳣�����뱻������׳�
			//int j = 1/0;
		}catch(InterruptedException e) {
			e.toString();       //��ʾ�쳣�������Ͳ����쳣��ԭ��
			e.getMessage();		//ֻ��ʾ�쳣������ԭ��
			//e.printStackTrace();//���������쳣�¼�����ʱ�Ķ�ջ������ 
		}

		Computer c = null;		//ջ�ڴ��б���c�����Ƕѿռ�û�б�cָ��Ķ���
		//Ҫ���������ڵ������ж���
		if (c != null) {
			c.start();             // java.lang.NullPointerException�������϶�����null����null���÷��������Ե�ǰ���׳�NullPointerException�쳣
		}

		String str = "123abf";    //ֻ�����ָ�ʽ���ܱ�תΪ��װ��Ķ���
		Integer i = new Integer(str);  //.NumberFormatException: For input string: "123abf"

	}	
}

class Computer {
	void start() {
		System.out.println("���������");
	}
}