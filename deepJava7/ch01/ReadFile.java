/**
*
*�׳�try�����в�����ԭʼ�쳣
*
*/

import java.io.*;
public class ReadFile {
	public static void main(String[] args) {
         String fileName = "I:\\Java\\deepJava7\\ch01\\BaseException.java";
         read(fileName);
	}

	public static void read(String fileName) {
		FileInputStream input = null;
		IOException readException = null;                 //����˼����ǰ�ÿ���쳣��Ϣ����¼����

		try {
			input = new FileInputStream(fileName);
		}catch(IOException ex){
			readException = ex;
		}finally {
			if (input != null) {
				try{
					input.close();                          //�ر���Դ��Ҳ�п��ܻ��׳��쳣
				}catch(IOException ex) {

					if (readException != null) {
						readException = ex;					//��ʱ�Ѳ������쳣��Ϣ��¼����
					}
				}
				
			}

			if (readException != null) {
				throw new BaseException(readException);        //������а�װ����ȥ��
			}
		}

	}
}