/*
File�ೣ�÷�������
*/
import java.io.File;
public class TestFile2 {
	public static void main(String[] args) {
		String path = "I:/Java/Java01/08/test.properties";
		File f = new File(path);
		//�Ƿ����
		System.out.println("�ļ��Ƿ���ڣ�" + f.exists());    //true
		System.out.println("�ļ��Ƿ�ɶ���" + f.canRead() + "�ļ��Ƿ��д��" + f.canWrite());

		//isFile()
		//isDirectory()
		if (f.isFile()) {
			System.out.println("���ļ�");
		}else {
			//û����ʵ���ڵ�Ĭ��Ϊ�ļ���
			System.out.println("���ļ���");
		}
		System.out.println("�Ƿ�Ϊ����·��" + f.isAbsolute());
		//���ܶ�ȡ�ļ��еĳ���
		System.out.println("����"+ f.length());
		
	}
}