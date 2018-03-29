/**
*测试Date类的用法
*
*/
import java.util.Date;
public class TestDate {
	public static void main(String[] args) {
		Date d = new Date();		//默认构造器则调用底层的System.currentTimeMillis(); 获取系统的当前时间数
		long t = System.currentTimeMillis();
		System.out.println(t);      //1522287552059
		System.out.println(d);      //Thu Mar 29 09:37:26 CST 2018

		Date d1 = new Date(1000);
		System.out.println(d1);     //Thu Jan 01 08:00:01 CST 1970
		d1.setTime(100000);         //可以重新设置Date的值
		System.out.println(d1.getTime());   //获取当前时间的毫秒值，返回值为long类型1000


	}
}