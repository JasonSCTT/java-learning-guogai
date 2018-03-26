public class DataOnly {
	int a;
	double d;
	boolean b;

	public static void main(String[] args) {
		DataOnly data = new DataOnly();
		data.a = 666;
		data.d = 666.66;
		data.b = true;

		System.out.println(data.a);
		System.out.println(data.d);
		System.out.println(data.b);
	}
} 