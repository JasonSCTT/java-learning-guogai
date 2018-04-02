/*
文件的读取
一、读取文件
1.建立联系，File对象
2.选择流，InputStream,FileInputStream
3.操作 byte[] b = new byte[] + read + 读取大小
	输出
4.关闭流
*/
import java.io.File;
import java.io.InputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;

public class ByteIO {
	public static void main(String[] args) {
		//1.建立联系
		File src   = new File("I:/Java/Java01/08/ByteIO.java");
		//2.选择流，放在外边是为了提升作用域，方便关闭操作
		InputStream input = null;
		try{
			 input = new FileInputStream(src);
			//3、操作不断读取缓冲数组
			byte[] b = new byte[1024];
			int len = 0;   //接收实际的大小

			//循环读取
			while((len = input.read(b)) != -1) {
				//将字节数组转为字符串
				System.out.println(new String(b,0,len));
			}
		}catch(FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("文件不存在");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("读取文件失败");
		}finally {
			//4.释放资源
			try {
				if (input != null) {
					input.close();
				}
			}catch (Exception e) {
				e.printStackTrace();
				System.out.println("资源文件关闭失败");
			}
		}

	}
}