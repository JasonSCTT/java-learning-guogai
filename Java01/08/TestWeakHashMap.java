/*
WeakHashMap ��Ϊ�����ͣ�GCʱ�ᱻ��������
*/
import java.util.WeakHashMap;
public class TestWeakHashMap {
	public static void main(String[] args) {
		WeakHashMap<String,String> wh = new WeakHashMap<>();
		//�����ض��󲻻ᱻ����
		wh.put("a","aa");
		wh.put("b","bb");
		//����ڶ��еĶ��󱻻���
		wh.put(new String("c"),"cc");
		wh.put(new String("d"),"cc");
		System.gc();
		System.runFinalization();
		System.out.println(wh.size());  //2
		
		
	}
}