/**
*������Դ�ļ������쳣
*/
import java.io.*;
public class TestFileLoad {
	public static void main(String[] args) {

		FileReader reader = null;
		try {
			//�����׳�java.io.FileNotFoundException��
			reader = new FileReader("I:\\Java\\Java01\\06\\TestFileLoad.java");
			char temp  = (char)reader.read();
			System.out.println("�����������ǣ�" + temp);
		}catch (FileNotFoundException e) {                 //�����쳣ͨ������catch��ǰ�棬����ͨ���ں���
			System.out.println("��Դ�ļ�û���ҵ�");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("��ȡ�ļ�����");
		}finally {
			System.out.println("������û���쳣���Ҷ���ִ�еġ�");
			try {
				if (reader != null) {         //��ʱ������Ҳ��Ҫ�ǿ��жϣ�
					reader.close();				//��ʵ����ȷ�ķ�ʽ����try withResource�� ֱ�ӷŵ�try(����){}catch{}
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}