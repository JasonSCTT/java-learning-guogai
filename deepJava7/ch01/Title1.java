
//switch������������ʽֻ�ܰ�����������������ݵ����͡�����char��short��byte��int
//�ײ�ʵ�����Ƚ��ַ���תΪ�������ͣ��Ӷ����Ը����ַ��������жϡ�
//��switch�����case�У����ʽ��ֵ����Ϊnull����������NullPoionterException�쳣����case���Ӿ���Ҳ���ܳ���null���������ֱ������
public class Title1 {
	public static void main(String[] args) {
		System.out.println(generate("��С��","Ů"));
	}

	//switch�����ʹ���ַ��� 

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