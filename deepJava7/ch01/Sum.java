/**
*在J2SE 5中引入了一个新特性就允许在方法声明中使用可变长度的参数，参数是以数组的形式传递的。
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
	//编译器会出现警告信息的原因是因为方法的参数的实际值 是通过数据来传递的，而数据中存储的是不可具体化的泛型类对象。自身存在类型安全问题。因此编译器给出警告。
	//使用@SafeVarargs注解时编译器遇到类似的情况，就不会在给出相关的警告信息。
	//@SafeVarargs注解只能用在参数长度可变的方法或构造方法上，且方法必须声明为static或final。否则会出现编译错误。
	//使用的前提是开发人员必须确保这个方法的实现中对泛型类型参数的处理不会引发类型安全问题。
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
        return new ArrayList<>(a);              //ArrayList此时是一个内部类
    }
