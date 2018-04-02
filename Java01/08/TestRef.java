/*
引用的分类：
强引用：引用指向对象，GC运行时不回收
软引用：运行时可能回收(JVM内存不够时)
弱引用：运行时立即回收
虚引用：类似于无引用，主要跟踪对象被回收的状态，不能单独使用，必须与引用队列联合使用
*/
import java.lang.ref.*;
public class TestRef {

	public static void main(String[] args) {
		//字符串常量池，存在于堆中。
		String str = new String("guo gogo");
		//弱引用管理对象，只要加入弱引用管理对象内，在JVM运行时立即回收
		WeakReference<String> wr = new WeakReference<>(str);
		System.out.println("GC运行前：" + wr.get());
		//断开引用
		str = null;
		//通知回收
		System.gc();
		System.runFinalization();
		//对象被回收
		System.out.println("GC运行之后：" + wr.get());
	}

	public static void testString() {
		//字符串常量池，共享(不能被回收)
		String str = "guo gogo";
		//弱引用管理对象
		WeakReference<String> wr = new WeakReference<>(str);
		System.out.println("GC运行前：" + wr.get());
		//断开引用
		str = null;
		//通知回收
		System.gc();
		System.runFinalization();
		//常量池被回收
		System.out.println("GC运行之后：" + wr.get());
	}
}