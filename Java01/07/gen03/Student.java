/*
��ͨ���ʹ��
�����в���ʹ�ã�ͨ��������������������ͼ����������Ĳ����ϣ�����������������
����ʱ�����ã���ʹ��ʱ��Ҫָ�����������
���Խ��շ��͵��������ͣ�ֻ�ܽ��պ���������ǲ����޸ģ���Ϊ����������ʱ�޷�֪����������ͣ���˲����޸�
�� extends : <= ����  ָ��������Ϊ���������
�� super �� >= ����   �൱��ָ���������������
*/

public class Student<T> {
	T score;

	public static void main(String[] args) {
		// Student<?> stu = new Student<?>();     // �������Ʒ�Χ�����ӿ�
		Student<?> stu = new Student<String>();
		test(new Student<Integer>());
		test2(new Student<Apple>());
		//test3(new Student<Apple>());  //����û�ж�̬             //�޷�ͨ����������ת����ʵ�ʲ���Student<Apple>ת��ΪStudent<Fruit>
		//test4(new Student<Apple>());	//����ʱ����ߣ�����ʱ���ұ�
		test4(new Student<Object>());
		test4(new Student<Fruit>());
		//test4(new Student<?>());

	}

	public static void test(Student<?> stt) {     //�������β���Ҳ����ʹ��

	}

	public static void test3(Student<Fruit> stu) {

	}
	// <=
	public static void test2(Student<? extends Fruit> stu) {

	}
	//>=
	public static void test4(Student<? super Fruit> stu) {

	}
}