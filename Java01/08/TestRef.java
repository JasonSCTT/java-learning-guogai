/*
���õķ��ࣺ
ǿ���ã�����ָ�����GC����ʱ������
�����ã�����ʱ���ܻ���(JVM�ڴ治��ʱ)
�����ã�����ʱ��������
�����ã������������ã���Ҫ���ٶ��󱻻��յ�״̬�����ܵ���ʹ�ã����������ö�������ʹ��
*/
import java.lang.ref.*;
public class TestRef {

	public static void main(String[] args) {
		//�ַ��������أ������ڶ��С�
		String str = new String("guo gogo");
		//�����ù������ֻҪ���������ù�������ڣ���JVM����ʱ��������
		WeakReference<String> wr = new WeakReference<>(str);
		System.out.println("GC����ǰ��" + wr.get());
		//�Ͽ�����
		str = null;
		//֪ͨ����
		System.gc();
		System.runFinalization();
		//���󱻻���
		System.out.println("GC����֮��" + wr.get());
	}

	public static void testString() {
		//�ַ��������أ�����(���ܱ�����)
		String str = "guo gogo";
		//�����ù������
		WeakReference<String> wr = new WeakReference<>(str);
		System.out.println("GC����ǰ��" + wr.get());
		//�Ͽ�����
		str = null;
		//֪ͨ����
		System.gc();
		System.runFinalization();
		//�����ر�����
		System.out.println("GC����֮��" + wr.get());
	}
}