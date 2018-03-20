
import static java.lang.System.out;
import java.io.*;
//��ȡ�ļ�����ӡ������̨
//�����Դ��ʼ��ʱ��try����г����쳣�����ͷ���Դ�Ĳ�������ִ�С�try����е��쳣���׳���
//���ͷ���Դʱ���ֵ��쳣�ᱻԤ�Ƶ��쳣��ӽ�ȥ����ͨ��Throwable.addSupperssed()������ʵ�֡�
public class ResourceBasicUsage1 {
	public static void main(String[] args) throws IOException {
		String path = "I:\\Java\\deepJava7\\ch01\\LocalizedException.java";
		out.println(readFile(path));
	}

	public static String readFile(String path) throws IOException {
		try(BufferedReader reader = new BufferedReader(new FileReader(path))) {      //��ʱ����Դ���Զ��ͷ�
			StringBuilder builder = new StringBuilder();							//��StringBuilder����׷�ӡ�
			String line = null;
			while((line = reader.readLine()) !=null) {								//�ж��ļ��Ƿ��ȡ���
				builder.append(line);
				builder.append("%n");
			}
			return builder.toString();
		}
	}
}