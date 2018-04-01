/*
*Object可以接收任意类型，是因为发生了多态
*
*/
public class Student {

	private Object javaee;
	private Object oracle;

	public Student () {

	}
	public Student(Object javaee,Object oracle) {
		this.javaee = javaee;
		this.oracle = oracle;
	}

	public void setJavaee(Object javaee) {
		this.javaee = javaee;
	}

	public Object getJavaee() {
		return javaee;
	}

	public  void setOracle(Object oracle) {
		this.oracle = oracle;
	}

	public Object getOracle() {
		return oracle;
	}


}