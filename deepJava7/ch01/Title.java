//��Switch�����ʹ���ַ���
//Java�е��ַ������԰���Unicodeת���롣
//ʵ��ԭ��
//������������ڱ����ʵ�ֵģ�����Java��������ֽ���㻹ֻ֧����������������ݵ����͡�Ŀ����Ϊ�˼��ٲ���Ҫ��Ӱ��
//��javaԴ�����switch�����ʹ���ַ������ڱ���ʱ�ڣ������������Դ����ĺ������ת�������ַ�������תΪ��������������ݵĸ�ʽ��
//JAD���߿��Խ��ֽ��뷴����ΪJavaԴ���롣

public class Title {
	public static void main(String[] args) {
		System.out.println(generate("��С��","��"));
	}

	public static String generate(String name,String gender) {
		String title = "";

		switch(gender) {
			case "��" :
				title = name + "����";
				break;
			case "Ů" :
				title = name + "Ůʿ";
				break;
			default :
				title = name;	

		}
		return title;
	}
}