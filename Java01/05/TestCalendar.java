/**
*测试Calendar日期类
*/
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.Date;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
public class TestCalendar {
	public static void main(String[] args) {
		Calendar c = new GregorianCalendar();      //需要newCalendar的子类GregorianCalendar
		c.set(2088,1,8);							//这里的1月在老外眼里是2月，
		Date d = c.getTime();       			 //Wed Sep 08 10:57:01 CST 2088
		System.out.println(d);
		long times = c.getTimeInMillis();          //3745450743564
		System.out.println(times);

		System.out.println("------------------------------------");

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd hh:mm:ss");
		String str = df.format(times);
		System.out.println(str);                    //2088-09-08 11:02:08

		System.out.println("--------------------------------------------------");
		Calendar c1 = new GregorianCalendar();
		c1.set(Calendar.YEAR,2019);
		c1.set(Calendar.MONTH,3);
		c1.set(Calendar.DAY_OF_MONTH,28);
		c1.setTime(new Date());
		//如果没有时分秒，则设置的当前时间的
		Date d1 = c1.getTime();
		System.out.println(d1);

		System.out.println("---------------------------------------");
		System.out.println(c1.get(Calendar.YEAR));
		System.out.println(c1.get(Calendar.DAY_OF_YEAR));   //88

		System.out.println("-----------------------------------");

		/**
		*测试日期计算
		*/

		Calendar c2 = new GregorianCalendar();
		c2.add(Calendar.YEAR,20);
		Date d2 = c2.getTime();
		//
		System.out.println(c2);
		System.out.println(c2.get(Calendar.YEAR));      //2038

		
	}
}