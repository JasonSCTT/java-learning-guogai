/*
���͵�Ƕ��ʹ��
*/
public class Guo<T> {
	T stu;
	public static void main(String[] args) {
		//����Ƕ��
		Guo<Student<String>> g =  new Guo<Student<String>>();
		//���⵽�ڲ��
		g.stu = new Student<String>();
		Student<String> stu = g.stu;
		String score = stu.score;
		System.out.println(score);   //null
	}
}