//在Switch语句中使用字符串
//Java中的字符串可以包含Unicode转义码。
//实现原理：
//这个新特性是在编译层实现的，而在Java虚拟机和字节码层还只支持与整数类型相兼容的类型。目的是为了减少不必要的影响
//在java源代码的switch语句中使用字符串，在编译时期，编译器会根据源代码的含义进行转换。将字符串类型转为与整数类型相兼容的格式。
//JAD工具可以将字节码反编译为Java源代码。

public class Title {
	public static void main(String[] args) {
		System.out.println(generate("郭小旭","男"));
	}

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