/*
�ļ��Ŀ���

1.�������� File���� Դͷ Ŀ�ĵ�
2.ѡ����
	�ļ������� InputStream FileInputStream
	�ļ������ OutputStream FileOutputStream
3.����
	byte[] flush = new byte[1024];
	int  len = 0;
	while((len = ������.read(flush)) != -1) {
		�����.write(flush,0,len);
	}
	�����.flush
4.�ͷ���Դ���ر�������
*/
import java.io.*;
public class CopyFileDemo {
	public static void main(String[] args)  {
		String src = "E:/ѧϰ֪ʶ.png";
		String dest = "I:/Java/gogo.png";
		try {
			copyFile(src,dest);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("�ļ�������");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("�����ļ��쳣");
		}
	}

	/**
	*�ļ��Ŀ���
	*@param Դ�ļ�·��
	*@Param Ŀ¼�ļ�·��
	*@throws FileNotFoundException��IOException
	*@return
	*
	*/
	public static void copyFile(String srcPath,String destPath) throws FileNotFoundException,IOException {
		//1.�������� File����Դͷ��Ŀ�ĵ�
		File src = new File(srcPath);
		File dest = new File(destPath);
		if (!src.isFile()) {
			System.out.println("ֻ�ܿ����ļ�");
			throw new RuntimeException("ֻ�ܿ����ļ�");
		}

		//2.ѡ���������������
		InputStream in = new FileInputStream(src);
		OutputStream os = new FileOutputStream(dest);
		//3.�ļ��Ŀ��� ѭ��+��ȡ��д��
		byte[] flush = new byte[1024];
		int len = 0;
		while((len = in.read(flush)) != -1) {
			os.write(flush,0,len);
		}
		os.flush();

		//4.�ͷ���Դ���ȴ򿪺�رյ��Źر�
		os.close();
		in.close();
	}
}