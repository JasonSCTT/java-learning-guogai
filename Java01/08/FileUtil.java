/**
*文件拷贝工具类
*/
import java.io.*;
public class FileUtil {


    /**
	*文件的拷贝
	*@param 源文件路径
	*@Param 目录文件路径
	*@throws FileNotFoundException，IOException
	*@return
	*
	*/
	public static void copyFile(String srcPath,String destPath) throws FileNotFoundException,IOException {
		copyFile(new File(srcPath),new File(destPath));
	}	

	/**
	*文件的拷贝
	*@param 源文件File对象
	*@Param 目录文件File对象
	*@throws FileNotFoundException，IOException
	*@return
	*
	*/
	public static void copyFile(File src,File dest) throws FileNotFoundException,IOException {
		if (!src.isFile()) {
			System.out.println("只能拷贝文件");
			throw new RuntimeException("只能拷贝文件");
		}

		//2.选择输出流和输入流
		InputStream in = new FileInputStream(src);
		OutputStream os = new FileOutputStream(dest);
		//3.文件的拷贝 循环+读取和写出
		byte[] flush = new byte[1024];
		int len = 0;
		while((len = in.read(flush)) != -1) {
			os.write(flush,0,len);
		}
		os.flush();

		//4.释放资源，先打开后关闭倒着关闭
		os.close();
		in.close();
	}	

	/**
	*拷贝文件夹
	*/
	public static void copyDir(String srcPath,String destPath) {
		File src =  new File(srcPath);
		File dest = new File(destPath);
		copyDir(src,dest);
	}

	/**
	*拷贝文件夹
	*/
	public static void copyDir(File src,File dest) {
		if (src.isDirectory()) {          //是文件夹
			dest = new File(dest,src.getName());
		}
		copyDirDetail(src,dest);
	}
	/**
	*拷贝文件夹细节
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
			//确保目标文件夹存在
			dest.mkdirs();
			//获取下一级目录文件
			for (File sub : src.listFiles()) {
				copyDirDetail(sub,new File(dest,sub.getName()));
			}
		}

	} 
}