/*
绝对路径和相对路径构建File对象
*/
import java.io.*;
public class TestFile1 {
	public static void main(String[] args) {

		String parentPath = "I:/Java";
		String path = "test.java";

		File f = new File(parentPath,path);

		File f2 = new File("I:/Java/test.java");

		System.out.println(f.getName());
		System.out.println(f.getPath());
		System.out.println(f2.getName());
		System.out.println(f2.getPath());
		
	}

	public static void test1() {
		
	}
}