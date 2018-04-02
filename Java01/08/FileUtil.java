/**
*�ļ�����������
*/
import java.io.*;
public class FileUtil {


    /**
	*�ļ��Ŀ���
	*@param Դ�ļ�·��
	*@Param Ŀ¼�ļ�·��
	*@throws FileNotFoundException��IOException
	*@return
	*
	*/
	public static void copyFile(String srcPath,String destPath) throws FileNotFoundException,IOException {
		copyFile(new File(srcPath),new File(destPath));
	}	

	/**
	*�ļ��Ŀ���
	*@param Դ�ļ�File����
	*@Param Ŀ¼�ļ�File����
	*@throws FileNotFoundException��IOException
	*@return
	*
	*/
	public static void copyFile(File src,File dest) throws FileNotFoundException,IOException {
		if (!src.isFile()) {
			System.out.println("ֻ�ܿ����ļ�");
			throw new RuntimeException("ֻ�ܿ����ļ�");
		}

		//2.ѡ���������������
		InputStream in = new FileInputStream(src);
		OutputStream os = new FileOutputStream(dest);
		//3.�ļ��Ŀ��� ѭ��+��ȡ��д��
		byte[] flush = new byte[1024];
		int len = 0;
		while((len = in.read(flush)) != -1) {
			os.write(flush,0,len);
		}
		os.flush();

		//4.�ͷ���Դ���ȴ򿪺�رյ��Źر�
		os.close();
		in.close();
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