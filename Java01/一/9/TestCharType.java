/**
*����char����
*/
public class TestCharType {
	public static void main(String[] args) {
		char c1 = 'a';
		char c2 = '��';      //Java�ڲ�����unicode����,ռ�������ֽڱ�ʾһ���ַ�,��Χ��0-65535֮��.
		char c3 = '\'';		//\n��ʾ����,\r��ʾ�س�,\t��ʾ�Ʊ��
		System.out.println(c1);
		System.out.println(c2);
		System.out.print(c3);

		char c4 = 'a';				//ASCLL��aΪ97,cΪ99
		int i = c4 + 2;
		char c5 = (char)i;    		//������Ҫǿ��ת��,��Ϊintռ��4���ֽ�,charռ������.��ʱ���c,
		System.out.println(c5);

		/**
		*ѭ����ӡa-z
		*/
		for(int j = 0; j < 26; j++) {
			char temp = (char)(c4 + j);     //�� a�Ļ�����++,
			System.out.print(temp);
		}

		/**
		*boolean����
		*/

		boolean b = true;
		if (b) {
			System.out.println("true");
		}
	}
}