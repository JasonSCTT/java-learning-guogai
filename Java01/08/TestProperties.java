/*
Properties��Դ�����ļ��Ķ�д
1.key��Valueֻ��Ϊ�ַ���
2.�洢�ڶ�ȡ
*/
import java.util.Properties;
public class TestProperties {
	public static void main(String[] args) {
		//��������
		Properties pro = new Properties();
		pro.setProperty("1","www.google.com");
		pro.setProperty("2","www.github.com");
		//����������Ҹ�����������ʹ�ú���Ĭ�ϵ�ֵ
		System.out.println(pro.getProperty("3","www.google.com.hk"));
	}
}