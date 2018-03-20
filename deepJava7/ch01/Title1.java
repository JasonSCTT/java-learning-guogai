
//switch语句中条件表达式只能包含于整数类型相兼容的类型。包括char、short、byte、int
//底层实现是先将字符串转为整数类型，从而可以根据字符串进行判断。
//在switch语句中case中，表达式的值不能为null，否则会出现NullPoionterException异常。在case的子句中也不能出现null。否则会出现编译错误。
public class Title1 {
	public static void main(String[] args) {
		System.out.println(generate("郭小旭","女"));
	}

	//switch语句中使用字符串 

	public static String generate(String name,String gender) {
		String title = "";

		switch(gender) {
			case "男" :
				title = name + "先生";
				break;
			case "女" :
				title = name + "女士";
				break;
			default :
				title = name;
		}
		return title;
	}
}