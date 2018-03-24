/**
*测试浮点数
*float是四个字节,double是8个字节.浮点数并不能精确的表示小数.它存在舍入误差
*/
public class TestFloatType {
	public static void main(String[] args) {

		double d = 3.14;      //浮点数默认类型为double
		//float f = 3.18;     //从double转换到float可能会有损失
		float   f1 = 3.18F;   //j将double转为float类型时可以在后面加F

		double d2 = 314e-2;   //科学计数法,表示3.14
		System.out.println(d2);  

		float f3 = 0.1F;
		double d3 = 1.0/10;
		System.out.println(f3 == d3);      //false,因为double存在四舍五入,不能精确的表示,可以使用BigDecimal来表示大小数,BigInteger表示大整数.

	}
}