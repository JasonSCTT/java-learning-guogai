public class CommandLine {
	public static void main(String[] args) {
			if (args.length == 0) {
				System.out.println("û���������");
			}else {
				System.out.println("������б����£�");
				for (int i = 0; i < args.length; i++) {                  //ѭ����������н��յ��Ĳ���
					System.out.println("Arguments��" + (i + 1) + ":" + args[i]);
				}
			}
		}	
}