public class TestHashcode {

	public static void main(String[] args) {

		//System.out.println("��".testHashcode().toString());
		//String str = "��";
		//str.testHashcode().toString();

	}

    public static int testhashCode() {
        int h = hash;
        if (h == 0 && value.length > 0) {
            char val[] = value;

            for (int i = 0; i < value.length; i++) {
                h = 31 * h + val[i];                          //�����31��һ��������
            }
            hash = h;                                          //���ظ�hash
        }
        return h;
    }
}