/**
*��ȡֵ��
*1.ǿ������ת��
*2.�ֶ����ͼ�飺���ܳ���	java.lang.ClassCastException
*
*���;��ǲ��������ͣ�֮����ʹ�÷�������Ϊ���Ͳ���ȷ��װ������ݶ�������Object����
*Ȼ����ȡֵ��ʱ��������Ҫ����ת����Ч�ʵ��£����ײ��� ����
*
*�������ã�
*--��ȫ���ڱ����ʱ�������Ͱ�ȫ
*--ʡ�ģ����е�ǿ��ת�������Զ�����ʽ�ģ���ߴ���������ʡ�
*/
public class App {
	public static void main(String[] args) {

		Object obj = 50;
		int score = (int)obj;       //����ת��Object-->Integer-->int,���Զ����䡣
		System.out.println(score);	//50


		//��������int --->Integer --->Object������ת��
		Student stu = new Student(59,49);
		int javaeeScore = (Integer)stu.getJavaee();
		// java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
		//Integer����תΪString����,��Ҫ�������жϣ�
		String oracleScore = null;
		if (stu.getOracle() instanceof String) {
		 oracleScore = (String)stu.getOracle();
		}
		System.out.println("����Ϊ��" + javaeeScore + "," + oracleScore );    //����Ϊ��59,null
	}
}