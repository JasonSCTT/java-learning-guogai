/**
*���ӻ����� 
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
		System.out.println("����������(��ʽ���գ�2088-8-8)��");
		String inputStr = scanner.nextLine();

		DateFormat df = new SimpleDateFormat("yyyy-MM-dd");
		Date date = df.parse(inputStr);        //���ַ������ַ�����ʽתΪʱ�����
		System.out.println(date);

		Calendar c = new GregorianCalendar();
		 c.setTime(date);                  //��ָ����ʱ�����ý�ȥ
		 int day = c.get(Calendar.DATE);
		 c.set(Calendar.DATE,1);
		 System.out.println(day);
		
		 int maxDate = c.getActualMaximum(Calendar.DATE);        //��ȡ������ж�����
		//1.����֮ǰ��˵�ҿ��Եģ�����취������ס����Ҫ���ꡣ
		System.out.println("��\tһ\t��\t��\t��\t��\t��");        //\t��ʾ�Ʊ��
		 for (int i = 1; i < c.get(Calendar.DAY_OF_WEEK) ; i++) {
		 	System.out.print("\t");
		 }
		//2.����һ�����ж����죬��ӡ������
		for (int i = 1; i <=maxDate; i++) {
			if (i == day) {
				System.out.print("*");
			}
			System.out.print(i + "\t");
			int w = c.get(Calendar.DAY_OF_WEEK);
			if (w == Calendar.SATURDAY) {						//��ǰ���ڵ��������ͻ���
				System.out.print("\n");                         //n��ʾ���з�
			}
			c.add(Calendar.DATE,1);
		}
	}

	/**
	*
	*˼·��
	*1.�ȴ�ӡ����һ����������
	*2.����һ���ֶ��������������
	* 
	*
	*/
}