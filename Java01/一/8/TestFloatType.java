/**
*���Ը�����
*float���ĸ��ֽ�,double��8���ֽ�.�����������ܾ�ȷ�ı�ʾС��.�������������
*/
public class TestFloatType {
	public static void main(String[] args) {

		double d = 3.14;      //������Ĭ������Ϊdouble
		//float f = 3.18;     //��doubleת����float���ܻ�����ʧ
		float   f1 = 3.18F;   //j��doubleתΪfloat����ʱ�����ں����F

		double d2 = 314e-2;   //��ѧ������,��ʾ3.14
		System.out.println(d2);  

		float f3 = 0.1F;
		double d3 = 1.0/10;
		System.out.println(f3 == d3);      //false,��Ϊdouble������������,���ܾ�ȷ�ı�ʾ,����ʹ��BigDecimal����ʾ��С��,BigInteger��ʾ������.

	}
}