/**
*����Date����÷�
*
*/
import java.util.Date;
public class TestDate {
	public static void main(String[] args) {
		Date d = new Date();		//Ĭ�Ϲ���������õײ��System.currentTimeMillis(); ��ȡϵͳ�ĵ�ǰʱ����
		long t = System.currentTimeMillis();
		System.out.println(t);      //1522287552059
		System.out.println(d);      //Thu Mar 29 09:37:26 CST 2018

		Date d1 = new Date(1000);
		System.out.println(d1);     //Thu Jan 01 08:00:01 CST 1970
		d1.setTime(100000);         //������������Date��ֵ
		System.out.println(d1.getTime());   //��ȡ��ǰʱ��ĺ���ֵ������ֵΪlong����1000


	}
}