/*
常量：
1、路径分隔符：    :
2、文件分隔符：	\
主要目的是为了跨平台使用
*/
import java.io.*;
public class TestFile {
	public static void main(String[] args) {
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);

		//路径表示形式
		String path = "I:\\Java\\test.java";
		path = "I:" + File.separator + "Java" + File.separator + "test.java";
		path = "I:/Java/test.java";
	}
}