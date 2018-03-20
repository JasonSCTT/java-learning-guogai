/**
 * 基础异常
 * 要想实现其他异常，只需要继承此异常就可以。
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message,new Throwable(message));      //调用基类中的构造方法。对传递进行的信息进行重新包装。
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
    public BaseException(String message,Throwable cause) {
        super(message,cause);
    }
}