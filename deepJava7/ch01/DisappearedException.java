public class DisappearedException {
	public static void main(String[] args) {
		show();
	}
	public static void show() throws BaseException {
		try{
			Integer.parseInt("GUO");
		}catch(NumberFormatException nfe) {
			throw new BaseException(nfe);				//�ڿ���Ȩת�Ƶ�����ջ��һ�����֮ǰ��finally��������ִ�С�
		}finally {
			try{
				int result = 2 / 0;                       //���﷢���쳣�����ϴ��ݣ���֮ǰ���쳣����ʧ�ˣ�
			}catch(ArithmeticException ae) {
				throw new BaseException(ae);
			}
		}
	}
}