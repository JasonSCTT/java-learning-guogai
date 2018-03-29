/**
*��һ������Զд����
*/
public class Singleton1 {
	private volatile static Singleton1  singleton1 = null;

	private Singleton1 () {                         //���췽����˽�л�
		System.out.println("����ģʽ�Ѿ�������");
		if (singleton1 != null) {
			throw new RuntimeException("�������Ϊ����ģʽ���Ѿ���ʵ������");
		}

	}
	public static Singleton1 getInstance () {
			if (singleton1 == null) {                  //ʹ��˫�ؼ��
				synchronized(Singleton1.class) {       //synchronized��ס����Singleton1�����
					if (singleton1 == null) {
						singleton1 = new Singleton1();
					}
				}
			}
			return singleton1;
	}
	public static void main(String[] args) {
		Singleton1.getInstance();
	}
}
