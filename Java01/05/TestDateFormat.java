/**
*测试DateFormat类用法
*/

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
public class TestDateFormat {
	public static void main(String[] args) throws Exception {
		//DateFormat是一个抽象类，不能被实例化，只能通过它的子类来完成
		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date d = new Date(10000);
		String str = df.format(d);         //将时间对象按格式字符串，转为字符串
		System.out.println(str);

		Date d1 = new Date();
		String str1 = df.format(d1);       //2018年03月29日
		System.out.println(str1);
		System.out.println("______________________________________________________");

		String str2 = "2018-8-8";           //两个必须匹配，不然会报java.text.ParseException: Unparseable date: "2018-8，8"
		DateFormat df1 = new SimpleDateFormat("yyyy-MM-dd");
		Date time = df1.parse(str2);         //将字符串对象按格式字符串，转为日期。
		System.out.println(time);

		String str3 = "1999-9-9 12:13:50";
		SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		Date time1 = sdf.parse(str3);
		System.out.println(time1);            //Thu Sep 09 00:13:50 CST 1999
		long l1 = time1.getTime();		     //936807230000
		System.out.println(l1);
		

	}
}