/*
�ļ��Ķ�ȡ
һ����ȡ�ļ�
1.������ϵ��File����
2.ѡ������InputStream,FileInputStream
3.���� byte[] b = new byte[] + read + ��ȡ��С
	���
4.�ر���
*/
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteIO {
	public static void main(String[] args) {
		//1.������ϵ
		File src   = new File("I:/Java/Java01/08/ByteIO.java");
		//2.ѡ���������������Ϊ�����������򣬷���رղ���
		InputStream input = null;
		try{
			 input = new FileInputStream(src);
			//3���������϶�ȡ��������
			byte[] b = new byte[1024];
			int len = 0;   //����ʵ�ʵĴ�С

			//ѭ����ȡ
			while((len = input.read(b)) != -1) {
				//���ֽ�����תΪ�ַ���
				System.out.println(new String(b,0,len));
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("�ļ�������");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("��ȡ�ļ�ʧ��");
		}finally {
			//4.�ͷ���Դ
			try {
				if (input != null) {
					input.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("��Դ�ļ��ر�ʧ��");
			}
		}

	}
}