/**
*�����Զ�ת�ͺ�ǿ��ת��
*/
public class TestCast {
	public static void main(String[] args) {
		/*byte b1 = 123;
		//byte b2 = 129;      //��ʱ������-128-127��Χ
		//char c1 = -3;		  //����0-65536֮�䣬Ҳ�ᱨ��

		char c = 'a';
		int i = c;    			 //��ʱ�ǿ����Զ�ת����

		long d01 = 123451;
		float f1 = d01;        //��ʱ�ǿ���ת�ģ�����ֻ�Ǳ���������Χ�������Ǳ����ֽ����Ĵ�С
		*/

		//ǿ��ת��
		int ii = 100;
		//int ii = -100;
		char c1 = (char)ii;
		System.out.println(c1);   //100��ASCLL�б�ʾd  //����һ������ǿ��ת��Ϊ��һ����ʱ�����ֳ�����Ŀ�����͵ı�ʾ��Χ������Ա���ͨ��������û������

		//���ʽ��������������

		int a = 3;
		long b = 123;
		double d = 5.3;
		int c = (int)(a + b);			//��ʱ�ᱨ�������������long������Ҫ�벻������ ��Ҫǿ������ת����
		float f = (float)(a + d);       //Ҳ��Ҫǿ������ת��

		int money = 1_000_000_000;   

		int years = 20;

		long total = (long)money * years;     //��ʱ�᷵�ظ�������Ϊ�Ѿ��������Ҫǿ��ת��Ϊ�����long,��Ҫע����ǲ���(long)(money * years)

		System.out.println(total);

		/**
		*һ����70����������
		*/
		long times = 70L*60*24*365*70;
		System.out.println(times);


	}
}