/**
*String�ǲ��ɱ���ַ����У��ײ����char���顣
*/
public class TestString {
	public static void main(String[] args) {
		String str = "abc";
		String str1 = "abc";
		String str2 = new String("abc");
		System.out.println(str == str1);     //true
		System.out.println(str == str2);	 //false       //���ǵĵ�ַ����ͬ
		System.out.println(str.equals(str1));	//true    //�Ƚ������Ƿ����
		System.out.println(str1.equals(str2));  //true
		System.out.println(str.charAt(2));
		System.out.println(str.toString());
		System.out.println(str.length());

		System.out.println("----------------------------------------");
		System.out.println(str.indexOf('b'));   //1 �±��״γ��ֵ�λ��


		//String s = str.substring(-3);  //StringIndexOutOfBoundsException: String index out of range: -3
		String s = str.substring(0,2);   //ab
		System.out.println(s);

		String s1 = str.substring(0);
		System.out.println(s1);          //abc

		String s2 = str.replace('a','*');
		System.out.println(s2);

		String str3 = new String("abc,efg,mkl"); 
		String[] strArray = str3.split(",");
		for (String sa : strArray) {
			System.out.println(sa);
			
		}
		System.out.println("--------------------------------");
		for (int i = 0; i < strArray.length; i++) {
			System.out.println(strArray[i]);
		}

		String str4 = new String("  aa bb  bbb  ");
		String s4 = str4.trim();      //ȡ����β�Ŀո�
		System.out.println(s4);

		System.out.println("AA".equalsIgnoreCase("Aa"));
		System.out.println("abcd".indexOf("c"));
		System.out.println("efgkl".lastIndexOf("l"));
		System.out.println("efgkl".startsWith("ef"));
		System.out.println("efgkl".endsWith("ef"));
		System.out.println("efgkl".toUpperCase());
		System.out.println("ABC".toLowerCase());

		System.out.println("-------------------------------------");
		String str5 = new String("a");    //������������һ���ڶ��У�һ���ڳ�������
		for (int i = 0; i < 3; i++) {
			str5 += i;					//ÿ�δ���һ���µĶ���
		}
		System.out.println(str5);
	}
}