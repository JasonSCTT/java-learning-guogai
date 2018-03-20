/**
*使用addSuppressed方法记录异常的示例
*
*当一个异常被抛出的时候可能还有其他异常因为该异常而被抑制住，从而无法抛出，
*这时候可以通过addSupperssed方法把压制住的异常信息记录下来，被压制住的异常在抛出的异常堆栈信息中，
*也可以通过getSuppressed方法来获取这些异常，从而不丢失任何异常信息。方便开发人员调试。
*
*/
import java.io.*;
public class FileRead1 {
	public static void main(String[] args) throws IOException {
		String fileName = "xxx.java";
		read(fileName);
	}

	public static void read(String fileName) throws IOException {
		FileInputStream input = null;
		IOException readException = null;

		try{
			input = new FileInputStream(fileName);
		}catch(IOException ex) {
			readException = ex;
		}finally {
			if (input != null) {
				try{
					input.close();
				}catch(IOException ex) {
					if (readException != null) {
						readException.addSuppressed(ex);        //关键点在于把finally中产生的异常通过addSuppressed方法添加到try语句产生的异常中
					}else {
						readException = ex;
					}
				}
			}
			if(readException != null) {
				throw readException;
			}
		}
	}
}