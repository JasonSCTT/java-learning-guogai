public class CommandLine {
	public static void main(String[] args) {
			if (args.length == 0) {
				System.out.println("没有输入参数");
			}else {
				System.out.println("命令的列表如下：");
				for (int i = 0; i < args.length; i++) {                  //循环输出命令行接收到的参数
					System.out.println("Arguments：" + (i + 1) + ":" + args[i]);
				}
			}
		}	
}