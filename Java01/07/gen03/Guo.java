/*
泛型的嵌套使用
*/
public class Guo<T> {
	T stu;
	public static void main(String[] args) {
		//泛型嵌套
		Guo<Student<String>> g =  new Guo<Student<String>>();
		//从外到内拆分
		g.stu = new Student<String>();
		Student<String> stu = g.stu;
		String score = stu.score;
		System.out.println(score);   //null
	}
}