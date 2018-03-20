
/**
*对于资源的管理：谁申请，谁释放。Java语言把内存管理的任务交给了JVM，通过自动垃圾回收机制减少了开发人员的工作，
*但是对于像输入流和输出流和数据库连接这样的资源，还需要手动释放。
*
*为了简化这种典型的应用，Java对try语句进行了增强。使它可以支持对资源的管理，保证资源总是释放掉的。
*我们只需要关注业务逻辑即可，资源的释放是自动完成的。
*
*有一个前提就是其Java类实现java.lang.AutoCloneable接口，否则会出现编译错误。当需要释放资源的时候，该接口close方法将自动被调用。
*
*要想实现，我们只需要实现此接口就行。
*
*
*/
import java.io.*;
public class ResourceBasicUsage {
	public static void main(String[] args) throws IOException {
		String path = "I:\\Java\\deepJava7\\ch01\\ResourceBasicUsage.java";
		System.out.println(readFile(path));
	}

	public static String readFile(String path) throws IOException {
		try(BufferedReader reader = new BufferedReader( new FileReader(path))) {
			StringBuilder builder = new StringBuilder();                           //使用StringBuilder进行追加。
			String line = null;

			while ((line = reader.readLine()) != null) {
				builder.append(line);
				builder.append(String.format("%n"));
			}
			return builder.toString();
		}
	}
}