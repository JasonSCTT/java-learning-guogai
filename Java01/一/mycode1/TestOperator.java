/**
*�������߼���λ���������չ�����
*
*
*/
public class TestOperator {
	public static void main(String[] args) {
		//double d = 10.2 % 3;   //1.1999999999999993
		//System.out.println(d);

		int a = 4;
		int b = a++;              //ִ�����b=4���ȸ�b��ֵ����������
		int c = ++a;			  //ִ�����b=6�����������ڸ�ֵ��

		System.out.println(a);     //6
		System.out.println(b);	   //4
		System.out.println(c);	   //6

	
	//	int c = 3/0;
		
	
		//boolean c = 1<2&&2>(3/0);   //��·�߼���
		//System.out.println(c);
	
	
		//����λ����
		int m = 8;
		int n = 4;
		System.out.println(m&n);
		System.out.println(m|n);
		System.out.println(~m);
		System.out.println(m^n);
		
		/*
        int a = 3*2*2;
		int b = 3<<3; 			 //�����൱�ڣ�3*2*2;
		int c = 12/2/2;
		int d = 12>>2;           //�����൱�ڣ�12/2/2
		System.out.println(a);
		System.out.println(b);
		System.out.println(c);
		System.out.println(d);
	
		//��չ�����
		int  a = 3;
		a +=5;  //a = a+5;
		*/

		String str = "5";
		int s = 4;

		System.out.println(str + s);     //54  �ײ��+�������滻��StringBuilder,��apend()��������׷��.��Ϊ�ǿɱ��ַ���,Ч�ʽϸ�
		//System.out.println((new StringBuilder()).append(s).append(byte2).toString());

		//��Ŀ���������

		//x ? y : z;                        //x�Ǳ��boolean���ʽ,���Ϊtrue,�򷵻�y��ֵ,Ϊfalse,�򷵻�z��ֵ

		int a1 = 3;
		int b1 = 4;
		String str1 = "";

		str1 = (a1 < b1) ? "a<b" : "a>b";
		System.out.println(str1);           //a<b
		
	}
}