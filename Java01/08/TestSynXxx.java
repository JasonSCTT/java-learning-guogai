/*
使用Collections管理同步容器
*/
import java.util.*;
public class TestSynXxx {
	public static void main(String[] args) {
		List<String> list = new ArrayList<>();
		list.add("111");
		list.add("222");
		list.add("333");
		//此方法可以让list变为同步的
		List<String> synList = Collections.synchronizedList(list);
	}
}