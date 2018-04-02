/*
Properties资源配置文件的读写
1.key与Value只能为字符串
2.存储于读取
*/
import java.util.Properties;
public class TestProperties {
	public static void main(String[] args) {
		//创建对象
		Properties pro = new Properties();
		pro.setProperty("1","www.google.com");
		pro.setProperty("2","www.github.com");
		//如果存在则找给，不存在则使用后面默认的值
		System.out.println(pro.getProperty("3","www.google.com.hk"));
	}
}