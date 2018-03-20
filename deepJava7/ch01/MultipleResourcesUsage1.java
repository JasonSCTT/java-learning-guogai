/**
*在对多个资源进行管理的时候，在释放每个资源都可能产生异常，
*
*
*
*/
import java.io.*;
//进行文件的复制，使用try-with-resource语句进行管理两个资源的示例。
public class MultipleResourcesUsage {
	public static void main(String[] args) throws IOException {
		String fromPath = "I:\\Java\\deepJava7\\ch01\\MultipleResourcesUsage.java";
		String toPath = "I:\\Java\\deepJava7\\ch01\\MultipleResourcesUsage1.java";

		copyFile(fromPath,toPath);
	}

	public static void copyFile(String fromPath,String toPath) throws IOException {
		try(InputStream input = new FileInputStream(fromPath);
			OutputStream output = new FileOutputStream(toPath)) {
			byte[] buffer = new byte[10240];
			int len = -1;
			while((len = input.read(buffer)) != -1) {                 //读进来，写出去
				output.write(buffer,0,len);
			}
		}
	}
}