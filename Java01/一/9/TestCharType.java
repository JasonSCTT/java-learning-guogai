/**
*测试char类型
*/
public class TestCharType {
	public static void main(String[] args) {
		char c1 = 'a';
		char c2 = '郭';      //Java内部采用unicode编码,占用两个字节表示一个字符,范围在0-65535之间.
		char c3 = '\'';		//\n表示换行,\r表示回车,\t表示制表符
		System.out.println(c1);
		System.out.println(c2);
		System.out.print(c3);

		char c4 = 'a';				//ASCLL码a为97,c为99
		int i = c4 + 2;
		char c5 = (char)i;    		//这里需要强制转型,因为int占用4个字节,char占用两个.此时输出c,
		System.out.println(c5);

		/**
		*循环打印a-z
		*/
		for(int j = 0; j < 26; j++) {
			char temp = (char)(c4 + j);     //在 a的基础上++,
			System.out.print(temp);
		}

		/**
		*boolean类型
		*/

		boolean b = true;
		if (b) {
			System.out.println("true");
		}
	}
}