/**
*����DateFormat���÷�
*/

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TestDateFormat {
	public static void main(String[] args) throws Exception {
		//DateFormat��һ�������࣬���ܱ�ʵ������ֻ��ͨ���������������
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date(10000);
		String str = df.format(d);         //��ʱ����󰴸�ʽ�ַ�����תΪ�ַ���
		System.out.println(str);

		Date d1 = new Date();
		String str1 = df.format(d1);       //2018��03��29��
		System.out.println(str1);
		System.out.println("______________________________________________________");

		String str2 = "2018-8-8";           //��������ƥ�䣬��Ȼ�ᱨjava.text.ParseException: Unparseable date: "2018-8��8"
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = df1.parse(str2);         //���ַ������󰴸�ʽ�ַ�����תΪ���ڡ�
		System.out.println(time);

		String str3 = "1999-9-9 12:13:50";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date time1 = sdf.parse(str3);
		System.out.println(time1);            //Thu Sep 09 00:13:50 CST 1999
		long l1 = time1.getTime();		     //936807230000
		System.out.println(l1);
		

	}
}