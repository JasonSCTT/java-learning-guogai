/*
文件的拷贝

1.建立连接 File对象 源头 目的地
2.选择流
	文件输入流 InputStream FileInputStream
	文件输出流 OutputStream FileOutputStream
3.操作
	byte[] flush = new byte[1024];
	int  len = 0;
	while((len = 输入流.read(flush)) != -1) {
		输出流.write(flush,0,len);
	}
	输出流.flush
4.释放资源：关闭两个流
*/
import java.io.*;
public class CopyFileDemo {
	public static void main(String[] args)  {
		String src = "E:/学习知识.png";
		String dest = "I:/Java/gogo.png";
		try {
			copyFile(src,dest);
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件不存在");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("拷贝文件异常");
		}
	}

	/**
	*文件的拷贝
	*@param 源文件路径
	*@Param 目录文件路径
	*@throws FileNotFoundException，IOException
	*@return
	*
	*/
	public static void copyFile(String srcPath,String destPath) throws FileNotFoundException,IOException {
		//1.建立连接 File对象，源头，目的地
		File src = new File(srcPath);
		File dest = new File(destPath);
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
}