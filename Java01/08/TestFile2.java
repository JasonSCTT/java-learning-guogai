/*
File类常用方法操作
*/
import java.io.File;
public class TestFile2 {
	public static void main(String[] args) {
		String path = "I:/Java/Java01/08/test.properties";
		File f = new File(path);
		//是否存在
		System.out.println("文件是否存在：" + f.exists());    //true
		System.out.println("文件是否可读：" + f.canRead() + "文件是否可写：" + f.canWrite());

		//isFile()
		//isDirectory()
		if (f.isFile()) {
			System.out.println("是文件");
		}else {
			//没有真实存在的默认为文件夹
			System.out.println("是文件夹");
		}
		System.out.println("是否为绝对路径" + f.isAbsolute());
		//不能读取文件夹的长度
		System.out.println("长度"+ f.length());
		
	}
}