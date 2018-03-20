/**
*异常是一种简洁的方式表示了程序中可能出现的错误以及对应这些错误的处理方式。
*适当的使用异常处理技术可以提高代码的可靠性、可维护性、可读性。使用不当，可能出现相反的效果
*
*Java7对异常处理做了两个重要的改动：一个是支持在catch子句中同时捕获多个异常，另一个是捕获和重新抛出异常类型更加的具体。
*Java语言中的异常主要围绕try-catch-finally,throws,throw 这几个关键字展开的，
*
*throws用来声明一个方法可能抛出的异常，对方法体中的可能抛出的异常需要进行声明。throw用来在遇到错误时抛出一个具体的异常。
*
*try-catch-finallt用来捕获异常并进行处理，Java中的异常分为受检查异常和非受检查异常。
*
*非受检查异常指的是java.lang.RuntimeException和java.lang.Error.这两种异常类型。而其他异常被称为受检查异常
*两种类型的异常本质上没有区别，唯一的差别在于使用受检查异常的合法性需要在编译器来检查。应该优先使用非受检查异常。
*
*如果打算在类中使用非受检查异常，通过至少需要一个类继承自RuntimeException的异常类。如果还需要使用受检查异常，则还需要另外一个继承自Exception的异常类。
*
*在不同的抽象层上定义相关的异常并形成一个完整的层次结构。
*
*包装异常的目的在于是异常只能出现在其某对应的抽象层上，当一个异常被抛出时，如果没有被捕获到，就会一直沿着调用栈往上传递，
*直到被上层方法捕获或者最终由JVM虚拟机来处理。使得上层代码看到不需要关注底层异常。
*
*所有的异常基类java.lang.Throwable就支持在构造方法中传入另外一个异常作为参数，而这个参数表示的异常被包装在新的异常中。可以通过getCause方法来获取
*
*
*异常存在的一个很重要的意义在于，当错误方式时，调用者可以对错误进行处理，从产生的错误中恢复。
*
*支持国际化异常消息的异常类的基类
*/

import java.util.ResourceBundle;
import java.text.MessageFormat;

public abstract class LocalizedException extends Exception {

	public static final String DEFAULT_BASE_NAME = "C://messages";

	private String baseName = DEFAULT_BASE_NAME;

	protected ResourceBundle resourceBundle;

	private String messageKey;

	public LocalizedException(String messageKey) {
		this.messageKey = messageKey;
		initResourceBunble();
	}

	public LocalizedException(String baseName,String messageKey) {
		this.baseName = baseName;
		this.messageKey = messageKey;
		initResourceBunble();
	}


	private void initResourceBunble() {
		resourceBundle = ResourceBundle.getBundle(baseName);         //重点是在这里进行绑定。
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public abstract String getLocalizedMessage();                     //此方法留着子类中实现

	public String getMessage() {
		return getLocalizedMessage();
	}

	protected String format(Object ... args) {
		String message = resourceBundle.getString(messageKey);

		return MessageFormat.format(message,args);						//信息格式转换
	}




}