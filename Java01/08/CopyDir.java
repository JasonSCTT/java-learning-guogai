/*
�ļ��еĸ���
1.�ļ����� copyFile
2.�ļ����� mkdirs()
3.�ݹ�������Ｖ
*/
import java.io.*;
public class CopyDir {
	public static void main(String[] args) {
		//ԴĿ¼
		String srcPath = "I:/Java/DataStrcture/sort";
		//Ŀ��Ŀ¼
		String destPath = "I:/Git/";

		FileUtil.copyDir(srcPath,destPath);
	}


	/**
	*�����ļ���
	*/
	public static void copyDir(String srcPath,String destPath) {
		File src =  new File(srcPath);
		File dest = new File(destPath);
		copyDir(src,dest);
	}

	/**
	*�����ļ���
	*/
	public static void copyDir(File src,File dest) {
		if (src.isDirectory()) {          //���ļ���
			dest = new File(dest,src.getName());
		}
		copyDirDetail(src,dest);
	}
	/**
	*�����ļ���ϸ��
	*/
	public static void copyDirDetail(File src,File dest) {
		if (src.isFile()) {
			try {
				FileUtil.copyFile(src,dest);
			}catch (FileNotFoundException e) {
				e.printStackTrace();
			}catch(IOException e) {
				e.printStackTrace();
			}
		}else if (dest.isDirectory()) {
			//ȷ��Ŀ���ļ��д���
			dest.mkdirs();
			//��ȡ��һ��Ŀ¼�ļ�
			for (File sub : src.listFiles()) {
				copyDirDetail(sub,new File(dest,sub.getName()));
			}
		}

	} 
}