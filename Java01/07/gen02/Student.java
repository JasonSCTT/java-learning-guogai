/*
*泛型类：声明时使用泛型
*
*字母：
* T Type 表示类型
* K V 分别代表键值中的key Value
* E 代表Element
* ？ 表示任意类型的
*
*在使用时才确定类型
*
*注意：
*1、泛型只能使用引用类型,不能使用基本类型
*2、泛型使用时不能使用在静态字段，和静态方法上，因为泛型是在运行时才能确定，而静态方法在编译时就需要确定类型
*
*
*
*
*/

public class Student<T1,T2> {

	private T1 javaScore;
	private T2 oracleScore;

	//public static T1 test;  //无法从静态上下文中引用非静态 类型变量 T1
	/*
	sublime快捷键：

	Ctrl+Shift+/ 注释多行。
	Ctrl+Shift+K 删除整行。
	Ctrl+Shift+D 复制光标所在整行，插入到下一行。
	Ctrl+D 选中光标所占的文本，继续操作则会选中下一个相同的文本。
	Ctrl+Shift+F：查找并替换
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
		//Student<String,int> stu = new Student<String,int>();	找到意外的类型
		//使用时指定类型(引用类型)
		Student<String,Integer> stu = new Student<String,Integer>();     //new Student<>()；这里可以省略，会自动推断
		//1.安全：类型检查
		stu.setJavaSocre("优秀");
		stu.setOracleScore(99);

		//2.省心:类型转换
		int it = stu.getOracleScore();            //自动拆箱，也可以使用Integer类型接收
		System.out.println(it);
	}
}