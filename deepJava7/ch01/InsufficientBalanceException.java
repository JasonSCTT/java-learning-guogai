//��ʹ�õ�ʱ��ÿ����Ҫ���ʻ����쳣��ֻ��Ҫ�̳�LocalizedException����ʵ��getLocalizedMessage�������ɡ�

import java.math.BigDecimal;
public class InsufficientBalanceException extends LocalizedException{
	private BigDecimal requested;
	private BigDecimal balance;
	private BigDecimal shortage;

	public InsufficientBalanceException(BigDecimal requested, BigDecimal balance) {
		super("INSUFFICIENT_BALANCE_EXCEPTION");      //����Ĺ��췽����ָ���쳣��Ϣ����Ϣ��Դ�ļ��ж�Ӧ�����ơ�
		this.requested = requested;
		this.balance = balance;
		this.shortage = requested.subtract(balance);
	}

	public String getLocalizedMessage() {
		return format(balance,requested,shortage);  //ʹ��format�������Զ���Ϣ���и�ʽ������
	}
}