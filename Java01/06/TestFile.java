/**
*����File�ļ���Ŀ¼
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
		f5.makir();   //ֻ�ܴ�������Ŀ¼
		f5.mkdirs();  //���Եݹ鴴��


		try {
			f4.createNewFile();
		}catch(IOException e) {
			e.printStackTrace();
		}
		boolean b = f4.delete();
		System.out.println(b);

		if (f.isFile()) {
			System.out.println("��һ���ļ�");
		}
		if (f2.isDirectory()) {
			System.out.println("��һ��Ŀ¼");	
		}
	}
}