/*
*�����ࣺ����ʱʹ�÷���
*
*��ĸ��
* T Type ��ʾ����
* K V �ֱ�����ֵ�е�key Value
* E ����Element
* �� ��ʾ�������͵�
*
*��ʹ��ʱ��ȷ������
*
*ע�⣺
*1������ֻ��ʹ����������,����ʹ�û�������
*2������ʹ��ʱ����ʹ���ھ�̬�ֶΣ��;�̬�����ϣ���Ϊ������������ʱ����ȷ��������̬�����ڱ���ʱ����Ҫȷ������
*
*
*
*
*/

public class Student<T1,T2> {

	private T1 javaScore;
	private T2 oracleScore;

	//public static T1 test;  //�޷��Ӿ�̬�����������÷Ǿ�̬ ���ͱ��� T1
	/*
	sublime��ݼ���

	Ctrl+Shift+/ ע�Ͷ��С�
	Ctrl+Shift+K ɾ�����С�
	Ctrl+Shift+D ���ƹ���������У����뵽��һ�С�
	Ctrl+D ѡ�й����ռ���ı��������������ѡ����һ����ͬ���ı���
	Ctrl+Shift+F�����Ҳ��滻
	*/
	/*


	*/
	public Student() {

	}

	public Student(T1 javaScore,T2 oracleScore) {
		this.javaScore = javaScore;
		this.oracleScore = oracleScore;
	}

	public void setJavaSocre(T1 javaScore) {
		this.javaScore = javaScore;
	}

	public T1 getJavaScore() {
		return javaScore;
	}

	public void setOracleScore(T2 oracleScore) {
		this.oracleScore = oracleScore;
	}

	public T2 getOracleScore() {
		return oracleScore;
	}


	public static void main(String[] args) {
		//Student<String,int> stu = new Student<String,int>();	�ҵ����������
		//ʹ��ʱָ������(��������)
		Student<String,Integer> stu = new Student<String,Integer>();     //new Student<>()���������ʡ�ԣ����Զ��ƶ�
		//1.��ȫ�����ͼ��
		stu.setJavaSocre("����");
		stu.setOracleScore(99);

		//2.ʡ��:����ת��
		int it = stu.getOracleScore();            //�Զ����䣬Ҳ����ʹ��Integer���ͽ���
		System.out.println(it);
	}
}