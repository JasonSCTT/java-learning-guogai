/*
Properties��Դ�����ļ��Ķ�д
1.key��Valueֻ��Ϊ�ַ���
2.�洢�ڶ�ȡ
*/
import java.util.Properties;
import java.io.*;
public class TestProperties1 {
	public static void main(String[] args) throws FileNotFoundException,IOException {
		//��������
		Properties pro = new Properties();
		pro.setProperty("1","www.google.com");
		pro.setProperty("2","www.github.com");
		
		//�洢��ָ���ļ���
		pro.store(new FileOutputStream(new File("I:\\Java\\Java01\\08\\test.properties")),"�����ļ�");
	}
}