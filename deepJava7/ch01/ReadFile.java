/**
*
*抛出try语句块中产生的原始异常
*
*/

import java.io.*;
public class ReadFile {
	public static void main(String[] args) {
         String fileName = "I:\\Java\\deepJava7\\ch01\\BaseException.java";
         read(fileName);
	}

	public static void read(String fileName) {
		FileInputStream input = null;
		IOException readException = null;                 //核心思想就是把每个异常信息都记录下来

		try {
			input = new FileInputStream(fileName);
		}catch(IOException ex){
			readException = ex;
		}finally {
			if (input != null) {
				try{
					input.close();                          //关闭资源，也有可能会抛出异常
				}catch(IOException ex) {

					if (readException != null) {
						readException = ex;					//此时把产生的异常信息记录下来
					}
				}
				
			}

			if (readException != null) {
				throw new BaseException(readException);        //这里进行包装拋上去。
			}
		}

	}
}