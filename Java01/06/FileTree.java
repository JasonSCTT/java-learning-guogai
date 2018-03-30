/**
*展示目录树
*/
import java.io.File;
public class FileTree {
	public static void main(String[] args) {
		File file = new File("I:\\Java\\Java01");
		printFile(file,0);
	}

	public static void printFile(File file,int level) {
		for (int i = 0; i < level; i++) {
			System.out.print("--|");
		}
		//获取文件名
		System.out.println(file.getName());

		//判断是否是目录，
		if (file.isDirectory()) {
			//获取文件目录
			File[] files = file.listFiles();
			for (File temp : files) {
				printFile(temp,level+1);
			}
		}
	}
}