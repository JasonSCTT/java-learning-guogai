/*
������
1��·���ָ�����    :
2���ļ��ָ�����	\
��ҪĿ����Ϊ�˿�ƽ̨ʹ��
*/
import java.io.*;
public class TestFile {
	public static void main(String[] args) {
		System.out.println(File.pathSeparator);
		System.out.println(File.separator);

		//·����ʾ��ʽ
		String path = "I:\\Java\\test.java";
		path = "I:" + File.separator + "Java" + File.separator + "test.java";
		path = "I:/Java/test.java";
	}
}