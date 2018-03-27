/**
*���Կɱ��ַ����У�StirngBuilder���̲߳���ȫ�ģ�Ч�ʸߣ�StringBuffer���̰߳�ȫ��Ч�ʵͣ�ÿ��������synchronized����
*String�ǲ����Ա�ģ�+ �������ײ����StingBuilder��õķ�����append()��
*/
public class TestStringBuilder {
	public static void main(String[] args) {
		StringBuilder sb = new StringBuilder();       //�ַ������ʼ����Ϊ10
		StringBuilder sb1 = new StringBuilder(32);    //�ַ������ʼ������Ϊ32
		StringBuilder sb2 = new StringBuilder("abc");  //�ַ������ʼ����Ϊ32
		sb2.append("defg");
		sb2.append(true).append("123").append("321");   //super.append(str); return this;  ���ص�ǰ�ַ�����
		System.out.println(sb2);

		StringBuilder sb3 = new StringBuilder("a");
		for (int i = 0; i < 10; i++) {
			sb3.append(i);
		}
		System.out.println(sb3);

		StringBuilder sb4 = new StringBuilder("abcdefghijkl");
		sb4.delete(4,5).delete(1,4);
		System.out.println(sb4);

		sb4.reverse();
		System.out.println(sb4);
	}
}