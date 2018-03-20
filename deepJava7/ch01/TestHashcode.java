public class TestHashcode {

	public static void main(String[] args) {

		//System.out.println("男".testHashcode().toString());
		//String str = "男";
		//str.testHashcode().toString();

	}

    public static int testhashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];                          //这里的31是一个素数。
            }
            hash = h;                                          //返回给hash
        }
        return h;
    }
}