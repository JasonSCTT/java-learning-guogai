/**
*可视化日历 
*/
import java.util.Date;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Scanner;
public class VisualCalendar {
	public static void main(String[] args) throws Exception {
		Scanner scanner = new Scanner(System.in);
		System.out.println("请输入日期(格式按照：2088-8-8)：");
		String inputStr = scanner.nextLine();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(inputStr);        //将字符串按字符串格式转为时间对象
		System.out.println(date);

		Calendar c = new GregorianCalendar();
		 c.setTime(date);                  //将指定的时间设置进去
		 int day = c.get(Calendar.DATE);
		 c.set(Calendar.DATE,1);
		 System.out.println(day);
		
		 int maxDate = c.getActualMaximum(Calendar.DATE);        //获取这个月有多少天
		//1.做事之前先说我可以的，在想办法做，稳住，不要浮躁。
		System.out.println("日\t一\t二\t三\t四\t五\t六");        //\t表示制表符
		 for (int i = 1; i < c.get(Calendar.DAY_OF_WEEK) ; i++) {
		 	System.out.print("\t");
		 }
		//2.根据一个月有多少天，打印出天数
		for (int i = 1; i <=maxDate; i++) {
			if (i == day) {
				System.out.print("*");
			}
			System.out.print(i + "\t");
			int w = c.get(Calendar.DAY_OF_WEEK);
			if (w == Calendar.SATURDAY) {						//当前日期等于周六就换行
				System.out.print("\n");                         //n表示换行符
			}
			c.add(Calendar.DATE,1);
		}
	}

	/**
	*
	*思路：
	*1.先打印出日一二三四五六
	*2.根据一天又多少天输出天数，
	* 
	*
	*/
}