/**
*测试标识符的写法
*
*标识符必须以字母、下划线，$符号开头
*/
public class Welcome {
	public static void main(String[] args) {
		int adb = 3;

		int $abc = 4;

		int $ = 5;

		int _abc = 6;

		//int _ = 6;     		//Java SE 8 之后的发行版中可能不支持使用 '_' 作为标识符

		//int 123abc = 456;  	//标识符不能以数字开头

		//int abc# = 456;     	//标识符不能包含除了字母、数字、下划线、$符号之外的其它符号#

		//int class = 456;      //标识符不能以Java中的32个关键字

		int 加油 = 666；         //Java采用了Unicode，所以可以使用。universal，通用的字符编码。
	}
}