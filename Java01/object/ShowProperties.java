
/**
*��ʾ���г����ϵͳ�е�����
*The System class contains several useful class fields and methods. It cannot be instantiated. 
*/
public class ShowProperties {
	public static void main(String[] args) {
		System.getProperties().list(System.out);                  //Determines the current system properties.
		System.out.println(System.getProperty("user.name"));	//Gets the system property indicated by the specified key.
		System.out.println(System.getProperty("java.library.path"));
		System.out.println(System.currentTimeMillis());         //Returns the current time in milliseconds.

	}
}