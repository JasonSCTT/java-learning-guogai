/*
Properties��Դ�����ļ��Ķ�д
1.key��Valueֻ��Ϊ�ַ���
2.�洢�ڶ�ȡ
*/
import java.util.Properties;
import java.io.*;
public class TestProperties2 {
	public static void main(String[] args) throws FileNotFoundException,IOException {
		//��������
		Properties pro = new Properties();
		//���������ļ�
		pro.load(new FileReader("I:\\Java\\Java01\\08\\test.properties"));
		System.out.println(pro.getProperty("1"));
		System.out.println(pro.getProperty("2"));
		
	}
}