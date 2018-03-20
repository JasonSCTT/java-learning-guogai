/**
*在Java7中改进了catch子句的语法。允许在其中指定多种异常，每个异常使用"|"来分割。
*此时不能出现重复的异常。也不允许一个是另外一个的子类。
*
*在异常进行处理时，如果遇到当前代码无法处理的异常，应该把异常重新抛出，交由调用栈的上层代码来处理。
*在重新抛出异常的时候，需要判断异常的类型，Java7对异常类型做了更加精确的判断。
*
*/

public class ExceptionHandle {
	public void handle() {
		ExceptionThrower thrower = new ExceptionThrower();

		try{
			thrower.manyExceptions();
		}catch(ExceptionA | ExceptionB ab){
		}catch(ExceptionC c) {

		}
	}

	public void testSequence() {              				  //这段代码是可以编译通过的。
		try{
			Integer.parseInt("guo");
		}catch(NumberFormatException | RuntimeException e) {  //这里涉及到捕获多个异常内部的实现方式

		}
	}

	public void testRuntimeException() {
		try{
			Integer.parseInt("xiao");
		}catch(RuntimeException | NumberFormatException e) {    //这里会出现编译错误，
			//原因在于编译器的做法其实是把捕获的多个异常的catch子句转换成了多个catch子句。每个子句中捕获一个异常。
		}
	}
}