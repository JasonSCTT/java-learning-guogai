/**
 * �����쳣
 * Ҫ��ʵ�������쳣��ֻ��Ҫ�̳д��쳣�Ϳ��ԡ�
 */
public class BaseException extends RuntimeException {

    public BaseException(String message) {
        super(message,new Throwable(message));      //���û����еĹ��췽�����Դ��ݽ��е���Ϣ�������°�װ��
    }

    public BaseException(Throwable cause) {
        super(cause);
    }
    public BaseException(String message,Throwable cause) {
        super(message,cause);
    }
}