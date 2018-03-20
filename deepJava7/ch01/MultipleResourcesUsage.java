/**
*
*
*
*
*/
import java.io.*;
//�����ļ��ĸ��ƣ�ʹ��try-with-resource�����й���������Դ��ʾ����
public class MultipleResourcesUsage {
	public static void main(String[] args) throws IOException {
		String fromPath = "I:\\Java\\deepJava7\\ch01\\MultipleResourcesUsage.java";
		String toPath = "I:\\Java\\deepJava7\\ch01\\MultipleResourcesUsage1.java";

		copyFile(fromPath,toPath);
	}

	public static void copyFile(String fromPath,String toPath) throws IOException {
		try(InputStream input = new FileInputStream(fromPath);
			OutputStream output = new FileOutputStream(toPath)) {
			byte[] buffer = new byte[10240];
			int len = -1;
			while((len = input.read(buffer)) != -1) {                 //��������д��ȥ
				output.write(buffer,0,len);
			}
		}
	}
}