/**
*չʾĿ¼��
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
		//��ȡ�ļ���
		System.out.println(file.getName());

		//�ж��Ƿ���Ŀ¼��
		if (file.isDirectory()) {
			//��ȡ�ļ�Ŀ¼
			File[] files = file.listFiles();
			for (File temp : files) {
				printFile(temp,level+1);
			}
		}
	}
}