/**
*��������
*
*��ͬһ���У���������ͬ�������б����͡�������˳��ͬ�򹹳����ء����췽��Ҳ���Խ������ء�
*���ܸ��ݷ���ֵ�����жϣ���Ϊ���ڱ����ھ;����ˣ�����ʱֻҪ�������������
*/

public class TestOverLoad {
	public static void main(String[] args) {
		MyMath m = new MyMath();
		System.out.println(m.add(2,3));
		System.out.println(m.add(1,2,3));
	}
}

class MyMath {
	int a;
	int b;

	public MyMath() {}

	public MyMath(int a) {
		this.a = a;
	}

	public MyMath(int a,int b) {
		this.a = a;
		this.b = b;
	}

	public int add(int b,double a) {
		return (int)(a + b);
	}

	public int add(double a,int b) {
		return (int)(a + b);
	}

	public int add(int a,int b) {
		return a + b;
	}

	public int add(int a,int b,int c) {
		return a + b  + c;
	}
}