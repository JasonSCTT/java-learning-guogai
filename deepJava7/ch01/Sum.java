/**
*��J2SE 5��������һ�������Ծ������ڷ���������ʹ�ÿɱ䳤�ȵĲ��������������������ʽ���ݵġ�
*
*/
public class Sum {

	public static void main(String[] args) {
		System.out.println(sum(1,3,5));
		System.out.println(useVararge(3,4,5));
	}
	public static int sum(int ... args) {
		int result = 0;
		for (int value : args ) {
			result += value;
		}
		return result;
	}
	//����������־�����Ϣ��ԭ������Ϊ�����Ĳ�����ʵ��ֵ ��ͨ�����������ݵģ��������д洢���ǲ��ɾ��廯�ķ������������������Ͱ�ȫ���⡣��˱������������档
	//ʹ��@SafeVarargsע��ʱ�������������Ƶ�������Ͳ����ڸ�����صľ�����Ϣ��
	//@SafeVarargsע��ֻ�����ڲ������ȿɱ�ķ������췽���ϣ��ҷ�����������Ϊstatic��final���������ֱ������
	//ʹ�õ�ǰ���ǿ�����Ա����ȷ�����������ʵ���жԷ������Ͳ����Ĵ������������Ͱ�ȫ���⡣
	@SafeVarargs
	public static <T> T useVararge(T ... args) {
		return args.length > 0 ? args[0] : null;
	}

}

    /**
     * Returns a fixed-size list backed by the specified array.  (Changes to
     * the returned list "write through" to the array.)  This method acts
     * as bridge between array-based and collection-based APIs, in
     * combination with {@link Collection#toArray}.  The returned list is
     * serializable and implements {@link RandomAccess}.
     *
     * <p>This method also provides a convenient way to create a fixed-size
     * list initialized to contain several elements:
     * <pre>
     *     List&lt;String&gt; stooges = Arrays.asList("Larry", "Moe", "Curly");
     * </pre>
     *
     * @param a the array by which the list will be backed
     * @return a list view of the specified array
     */
    @SafeVarargs
    public static <T> List<T> asList(T... a) {
        return new ArrayList<>(a);              //ArrayList��ʱ��һ���ڲ���
    }
