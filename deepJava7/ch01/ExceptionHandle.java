/**
*��Java7�иĽ���catch�Ӿ���﷨������������ָ�������쳣��ÿ���쳣ʹ��"|"���ָ
*��ʱ���ܳ����ظ����쳣��Ҳ������һ��������һ�������ࡣ
*
*���쳣���д���ʱ�����������ǰ�����޷�������쳣��Ӧ�ð��쳣�����׳������ɵ���ջ���ϲ����������
*�������׳��쳣��ʱ����Ҫ�ж��쳣�����ͣ�Java7���쳣�������˸��Ӿ�ȷ���жϡ�
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

	public void testSequence() {              				  //��δ����ǿ��Ա���ͨ���ġ�
		try{
			Integer.parseInt("guo");
		}catch(NumberFormatException | RuntimeException e) {  //�����漰���������쳣�ڲ���ʵ�ַ�ʽ

		}
	}

	public void testRuntimeException() {
		try{
			Integer.parseInt("xiao");
		}catch(RuntimeException | NumberFormatException e) {    //�������ֱ������
			//ԭ�����ڱ�������������ʵ�ǰѲ���Ķ���쳣��catch�Ӿ�ת�����˶��catch�Ӿ䡣ÿ���Ӿ��в���һ���쳣��
		}
	}
}