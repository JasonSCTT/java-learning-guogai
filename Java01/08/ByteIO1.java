/*
д���ļ�
1.������ϵ File����Ŀ�ĵ�
2.ѡ������OututStream��FileOutputStream
3.������write() + flush
4.�ͷ���Դ
*/
import java.io.*;
public class ByteIO1 {
	public static void main(String[] args) {
		//������ϵ����File����
		File dest = new File("I:/Java/Java01/08/test.properties");
		//ѡ����
		OutputStream out = null;
		try {
			//��׷�ӵ���ʽѡ��true��
			out = new FileOutputStream(dest,true);   //д��false���Ǹ��ǵ���ʽ
			String str = "gogogogoogogogo \r\n";
			out.write(str.getBytes());

			//ûд�ɹ�ǿ��ˢ��
			out.flush();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("û���ҵ����ļ�");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("�ļ�д��ʧ��");
		}finally {
			try {
				if (out != null) {
					//�ͷ���Դ
					out.close();
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("��Դ�ر�ʧ��");
			}
		}
	}
}