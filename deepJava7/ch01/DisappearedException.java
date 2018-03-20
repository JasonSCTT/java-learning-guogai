public class DisappearedException {
	public static void main(String[] args) {
		show();
	}
	public static void show() throws BaseException {
		try{
			Integer.parseInt("GUO");
		}catch(NumberFormatException nfe) {
			throw new BaseException(nfe);				//在控制权转移到调用栈上一层代码之前，finally语句块总是执行。
		}finally {
			try{
				int result = 2 / 0;                       //这里发生异常会往上传递，而之前的异常就消失了，
			}catch(ArithmeticException ae) {
				throw new BaseException(ae);
			}
		}
	}
}