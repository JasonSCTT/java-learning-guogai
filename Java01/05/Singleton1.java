/**
*看一遍你永远写不会
*/
public class Singleton1 {
	private volatile static Singleton1  singleton1 = null;

	private Singleton1 () {                         //构造方法被私有化
		System.out.println("单例模式已经被创建");
		if (singleton1 != null) {
			throw new RuntimeException("此类对象为单例模式，已经被实例化了");
		}

	}
	public static Singleton1 getInstance () {
			if (singleton1 == null) {                  //使用双重检查
				synchronized(Singleton1.class) {       //synchronized锁住的是Singleton1类对象
					if (singleton1 == null) {
						singleton1 = new Singleton1();
					}
				}
			}
			return singleton1;
	}
	public static void main(String[] args) {
		Singleton1.getInstance();
	}
}
