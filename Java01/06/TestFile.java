/**
*测试File文件和目录
*/
import java.io.File;
import java.io.IOException;
public class TestFile {
	public static void main(String[] args) {
		File f = new File("I:\\Java\\Java01\\06\\TestFile.java");
		File f2 = new File("I:/Java/Java01/06");
		File f3 = new File(f2,"TestFile.java");
		File f4 = new File(f2,"TestFile666.java");
		File f5 = new File(f2,"/aa/bb/cc");
		f5.makir();   //只能创建单个目录
		f5.mkdirs();  //可以递归创建


		try {
			f4.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		boolean b = f4.delete();
		System.out.println(b);

		if (f.isFile()) {
			System.out.println("是一个文件");
		}
		if (f2.isDirectory()) {
			System.out.println("是一个目录");	
		}
	}
}