/*
WeakHashMap 键为弱类型，GC时会被立即回收
*/
import java.util.WeakHashMap;
public class TestWeakHashMap {
	public static void main(String[] args) {
		WeakHashMap<String,String> wh = new WeakHashMap<>();
		//常量池对象不会被回收
		wh.put("a","aa");
		wh.put("b","bb");
		//存放在堆中的对象被回收
		wh.put(new String("c"),"cc");
		wh.put(new String("d"),"cc");
		System.gc();
		System.runFinalization();
		System.out.println(wh.size());  //2
		
		
	}
}