/*
写出文件
1.建立联系 File对象，目的地
2.选择流，OututStream、FileOutputStream
3.操作：write() + flush
4.释放资源
*/
import java.io.*;
public class ByteIO1 {
	public static void main(String[] args) {
		//建立联系创建File对象
		File dest = new File("I:/Java/Java01/08/test.properties");
		//选择流
		OutputStream out = null;
		try {
			//以追加的形式选择true，
			out = new FileOutputStream(dest,true);   //写成false则是覆盖的形式
			String str = "gogogogoogogogo \r\n";
			out.write(str.getBytes());

			//没写成功强制刷新
			out.flush();
		}catch (FileNotFoundException e) {
			e.printStackTrace();
			System.out.println("没有找到此文件");
		}catch(IOException e) {
			e.printStackTrace();
			System.out.println("文件写出失败");
		}finally {
			try {
				if (out != null) {
					//释放资源
					out.close();
				}
			}catch(Exception ex) {
				ex.printStackTrace();
				System.out.println("资源关闭失败");
			}
		}
	}
}