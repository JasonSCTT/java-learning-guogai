public class MyString {
	public int storage(String s) {
		return s.length() * 2;
	}
	public static void main(String[] args) {
		MyString ms = new MyString();
		int len = ms.storage("guo");       //6   一个字符占用两个字节
		System.out.println(len);
	}
}