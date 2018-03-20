//如果代码中有多个地方使用switch语句来枚举字符串。就考虑使用枚举类型进行替换。
//枚举类是一个完整的类，除了定义枚举值之外，还可以包含任意的方法和域，以及实现任意的接口。可以很好的与其他类进行交互。

public enum ResultCode {
    /**
     * 成功. ErrorCode : 0
     */
    SUCCESS("0", "成功"),
    /**
     * 未知异常. ErrorCode : 01
     */
    UnknownException("01", "未知异常"),
    /**
     * 系统异常. ErrorCode : 02
     */
    SystemException("02", "系统异常"),
    /**
     * 业务错误. ErrorCode : 03
     */
    BusinessException("03", "业务错误"),
    /**
     * 提示级错误. ErrorCode : 04
     */
    InfoException("04", "提示级错误"),
    /**
     * 数据库操作异常. ErrorCode : 020001
     */
    DBException("020001", "数据库操作异常"),
    /**
     * 参数验证错误. ErrorCode : 040001
     */
    ParamException("040001", "参数验证错误"),

    SystemMaintainException("11", "系统正在维护");

    private String _code;
    private String _msg;

    private ResultCode(String code, String msg) {
        _code = code;
        _msg = msg;
    }

    public String getCode() {
        return _code;
    }

    public String getMsg() {
        return _msg;
    }

    public static ResultCode getByCode(String code) {
        for (ResultCode ec : ResultCode.values()) {           //使用value属性可以获取全部枚举值。
            if (ec.getCode().equals(code)) {                   //使用equals()方法进行对比。
                return ec;
            }
        }
        return null;
    }
}