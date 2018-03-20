/**
*�쳣��һ�ּ��ķ�ʽ��ʾ�˳����п��ܳ��ֵĴ����Լ���Ӧ��Щ����Ĵ���ʽ��
*�ʵ���ʹ���쳣������������ߴ���Ŀɿ��ԡ���ά���ԡ��ɶ��ԡ�ʹ�ò��������ܳ����෴��Ч��
*
*Java7���쳣��������������Ҫ�ĸĶ���һ����֧����catch�Ӿ���ͬʱ�������쳣����һ���ǲ���������׳��쳣���͸��ӵľ��塣
*Java�����е��쳣��ҪΧ��try-catch-finally,throws,throw �⼸���ؼ���չ���ģ�
*
*throws��������һ�����������׳����쳣���Է������еĿ����׳����쳣��Ҫ����������throw��������������ʱ�׳�һ��������쳣��
*
*try-catch-finallt���������쳣�����д���Java�е��쳣��Ϊ�ܼ���쳣�ͷ��ܼ���쳣��
*
*���ܼ���쳣ָ����java.lang.RuntimeException��java.lang.Error.�������쳣���͡��������쳣����Ϊ�ܼ���쳣
*�������͵��쳣������û������Ψһ�Ĳ������ʹ���ܼ���쳣�ĺϷ�����Ҫ�ڱ���������顣Ӧ������ʹ�÷��ܼ���쳣��
*
*�������������ʹ�÷��ܼ���쳣��ͨ��������Ҫһ����̳���RuntimeException���쳣�ࡣ�������Ҫʹ���ܼ���쳣������Ҫ����һ���̳���Exception���쳣�ࡣ
*
*�ڲ�ͬ�ĳ�����϶�����ص��쳣���γ�һ�������Ĳ�νṹ��
*
*��װ�쳣��Ŀ���������쳣ֻ�ܳ�������ĳ��Ӧ�ĳ�����ϣ���һ���쳣���׳�ʱ�����û�б����񵽣��ͻ�һֱ���ŵ���ջ���ϴ��ݣ�
*ֱ�����ϲ㷽���������������JVM�����������ʹ���ϲ���뿴������Ҫ��ע�ײ��쳣��
*
*���е��쳣����java.lang.Throwable��֧���ڹ��췽���д�������һ���쳣��Ϊ�����������������ʾ���쳣����װ���µ��쳣�С�����ͨ��getCause��������ȡ
*
*
*�쳣���ڵ�һ������Ҫ���������ڣ�������ʽʱ�������߿��ԶԴ�����д����Ӳ����Ĵ����лָ���
*
*֧�ֹ��ʻ��쳣��Ϣ���쳣��Ļ���
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
		resourceBundle = ResourceBundle.getBundle(baseName);         //�ص�����������а󶨡�
	}

	public void setBaseName(String baseName) {
		this.baseName = baseName;
	}

	public void setMessageKey(String messageKey) {
		this.messageKey = messageKey;
	}

	public abstract String getLocalizedMessage();                     //�˷�������������ʵ��

	public String getMessage() {
		return getLocalizedMessage();
	}

	protected String format(Object ... args) {
		String message = resourceBundle.getString(messageKey);

		return MessageFormat.format(message,args);						//��Ϣ��ʽת��
	}




}