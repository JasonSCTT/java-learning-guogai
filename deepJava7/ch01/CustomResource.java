/**
*自定义资源使用AutoCloseable接口实现
*
*/
public class CustomResource implements AutoCloseable {
	public void close() throws IOException {
		System.out.println("进行资源释放");
	}

	public void useCustomResource() throws IOException {
		try(CustomResource resource = new CustomResource()) {
			System.out.println("使用资源");
		}
	}
}