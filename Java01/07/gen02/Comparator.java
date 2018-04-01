/*
接口中泛型字母只能使用在方法中，不能使用在全局常量中，因为它是被static修饰的
*/
public interface Comparator<T> {

	//T int A = 0;     //此时不能使用泛型
	void compare(T t);
	
}