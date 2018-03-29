/**
*单例模式
*/
public class Singleton {

	public static volatile Singleton singleton = null;

	private Singleton() {
		System.out.println("gogo");
		if (singleton!=null) {
			throw new RuntimeException("此类对象为单例模式，已被实例化！！");
		}
	}

	public static Singleton getInstance () {
		if (singleton == null) {
			synchronized(Singleton.class) {
				if (singleton == null) {
					singleton = new Singleton();
				}
			}
		}
		return singleton;
	}

	public static void main(String[] args) {
		Singleton.getInstance();
	}
}