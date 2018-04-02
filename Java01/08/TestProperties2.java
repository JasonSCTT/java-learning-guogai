/*
Properties资源配置文件的读写
1.key与Value只能为字符串
2.存储于读取
*/
import java.util.Properties;
import java.io.*;
public class TestProperties2 {
	public static void main(String[] args) throws FileNotFoundException,IOException {
		//创建对象
		Properties pro = new Properties();
		//加载配置文件
		pro.load(new FileReader("I:\\Java\\Java01\\08\\test.properties"));
		System.out.println(pro.getProperty("1"));
		System.out.println(pro.getProperty("2"));
		
	}
}