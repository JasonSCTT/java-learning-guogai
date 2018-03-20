
import static java.lang.System.out;
import java.io.*;
//读取文件并打印到控制台
//如果资源初始化时或try语句中出现异常，而释放资源的操作正常执行。try语句中的异常会抛出。
//在释放资源时出现的异常会被预制的异常添加进去，即通过Throwable.addSupperssed()方法来实现。
public class ResourceBasicUsage1 {
	public static void main(String[] args) throws IOException {
		String path = "I:\\Java\\deepJava7\\ch01\\LocalizedException.java";
		out.println(readFile(path));
	}

	public static String readFile(String path) throws IOException {
		try(BufferedReader reader = new BufferedReader(new FileReader(path))) {      //此时的资源会自动释放
			StringBuilder builder = new StringBuilder();							//用StringBuilder接收追加。
			String line = null;
			while((line = reader.readLine()) !=null) {								//判断文件是否读取完毕
				builder.append(line);
				builder.append("%n");
			}
			return builder.toString();
		}
	}
}