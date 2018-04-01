/**
*获取值：
*1.强制类型转换
*2.手动类型检查：可能出现	java.lang.ClassCastException
*
*泛型就是参数化类型，之所以使用泛型是因为类型不明确，装入的数据都被当作Object对象，
*然而获取值的时候往往需要类型转换，效率低下，容易产生 错误
*
*泛型所用：
*--安全：在编译的时候检查类型安全
*--省心：所有的强制转换都是自动和隐式的，提高代码的重用率。
*/
public class App {
	public static void main(String[] args) {

		Object obj = 50;
		int score = (int)obj;       //向下转型Object-->Integer-->int,在自动拆箱。
		System.out.println(score);	//50


		//存入整数int --->Integer --->Object。类型转换
		Student stu = new Student(59,49);
		int javaeeScore = (Integer)stu.getJavaee();
		// java.lang.ClassCastException: java.lang.Integer cannot be cast to java.lang.String
		//Integer不能转为String类型,需要先做出判断，
		String oracleScore = null;
		if (stu.getOracle() instanceof String) {
		 oracleScore = (String)stu.getOracle();
		}
		System.out.println("分数为：" + javaeeScore + "," + oracleScore );    //分数为：59,null
	}
}