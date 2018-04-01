/*
泛型方法<> 返回类型签名
只能访问对象的信息，不能修改信息
*/
import java.io.Closeable;
import java.io.IOException;
public class TestMethod {
	public static void main(String[] args) {
		test("aa");     //此时T会自动转为String类型	
	}
	public static <T> void test(T a) {
		System.out.println(a);
	}
	@SuppressWarnings("unchecked")
	public static <T extends Closeable> void test(T... a) {   //表示可变参数
		for (T temp : a) {
			try{
				if (null != temp) {
					System.out.println(temp);
				 	temp.close();
				}
			}catch(IOException e) {
				e.printStackTrace();
			}
		}
	}
}