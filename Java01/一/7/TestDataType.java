/**
*����������������(byte��short��int��long)
*һ��byteվһ���ֽڣ�shortռ2����intռ4���ֽڣ�longռ8���ֽڡ�
*/
public class TestDataType {
	public static void main(String[] args) {
		int a = 10;
		int a2 = 010;           //8,��0��ͷ���ǰ˽�����
		int a3 = 0x10;			//16,16��������0x��ͷ
		//byte b = 200;         byte��ʾ-128-127 �����ݵ�����: ��intת����byte���ܻ�����ʧ
		//System.out.println(b);
		System.out.println(a);
		System.out.println(a2);
		System.out.println(a3);
		System.out.println(Integer.toBinaryString(a));           //toBinaryString()�����ǽ�������תΪ��������
		System.out.println(Integer.toOctalString(a));			 //toOctalString()����������תΪ�˽�����
		System.out.println(Integer.toHexString(a));			     //toHexString()תΪʮ��������


		int a5 = 10;
		long a6 = 200;
		byte b2 = 100;			//���������Сû�г���byte/int/char�ı�ʾ��Χ������Զ�ת��

		long a7 = 12345678910L  //�������long�ķ�Χ���ں����L,ע�⽨��Ҫ��д

	}
}