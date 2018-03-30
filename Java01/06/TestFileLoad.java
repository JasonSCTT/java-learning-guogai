/**
*加载资源文件处理异常
*/
import java.io.*;
public class TestFileLoad {
	public static void main(String[] args) {

		FileReader reader = null;
		try {
			//可能抛出java.io.FileNotFoundException，
			reader = new FileReader("I:\\Java\\Java01\\06\\TestFileLoad.java");
			char temp  = (char)reader.read();
			System.out.println("读出的内容是：" + temp);
		}catch (FileNotFoundException e) {                 //子类异常通常放在catch的前面，父类通常在后面
			System.out.println("资源文件没有找到");
			e.printStackTrace();
		}catch (IOException e) {
			System.out.println("读取文件错误");
		}finally {
			System.out.println("不管有没有异常，我都会执行的。");
			try {
				if (reader != null) {         //此时在这里也需要非空判断，
					reader.close();				//其实最正确的方式是用try withResource。 直接放到try(这里){}catch{}
				}
			}catch (IOException e) {
				e.printStackTrace();
			}
		}
		
	}
}