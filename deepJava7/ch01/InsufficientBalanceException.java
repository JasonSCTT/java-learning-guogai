//在使用的时候，每个需要国际化的异常类只需要继承LocalizedException。并实现getLocalizedMessage方法即可。

import java.math.BigDecimal;
public class InsufficientBalanceException extends LocalizedException{
	private BigDecimal requested;
	private BigDecimal balance;
	private BigDecimal shortage;

	public InsufficientBalanceException(BigDecimal requested, BigDecimal balance) {
		super("INSUFFICIENT_BALANCE_EXCEPTION");      //子类的构造方法中指定异常消息在消息资源文件中对应的名称。
		this.requested = requested;
		this.balance = balance;
		this.shortage = requested.subtract(balance);
	}

	public String getLocalizedMessage() {
		return format(balance,requested,shortage);  //使用format方法可以对消息进行格式化处理。
	}
}