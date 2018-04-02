/*
Properties资源配置文件的读写
1.key与Value只能为字符串
2.存储于读取
*/
import java.util.Properties;
import java.io.*;
public class TestProperties1 {
	public static void main(String[] args) throws FileNotFoundException,IOException {
		//创建对象
		Properties pro = new Properties();
		pro.setProperty("1","www.google.com");
		pro.setProperty("2","www.github.com");
		
		//存储到指定文件中
		pro.store(new FileOutputStream(new File("I:\\Java\\Java01\\08\\test.properties")),"测试文件");
	}
}