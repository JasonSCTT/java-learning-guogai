/*
父类为泛型
1.属性
2.方法
要么同时擦除，要么子类大于等于父类的类型 
不能子类擦除，父类为泛型，class Child5 extends Father<T,T1> {...}

1.属性类型
如果在父类中，随父类而定
如果在子类中，随子类而定
2.方法重写
随父类而定
*/
public abstract class Father<T,T1> {      /*抽象类是用来继承的，包含有抽象方法的类叫做抽象类*/
	T name;

	public abstract void test(T t);

}

/*
子类声明时指定类型
属性为具体的类型
方法同理
*/
class Child1 extends Father<String,Integer> {

	String t2;

	@Override  
	public void test(String t) {
		
	}
}
/*
子类为泛型类,类型在使用时确定
*/
class Child2<T,T1> extends Father<T,T1> {

	T1 t2;

	@Override  
	public void test(T t) {
		
	}
}
/*
子类为泛型类，父类不指定具体的类型，泛型的擦除
*/

class Child3<T,T1> extends Father {
	T1 name;   //在使用时指定类型
	@Override   //子类重写方法变为Object
	public void test(Object t) {

	}
}

/*
子类和父类同时擦除
*/
class Child4 extends Father {
	//T name;    //此时不能使用T类型
	@Override
	public void test(Object t) {

	}
}

/*
错误：子类擦除，父类指定类型
//此时不能编译通过
class Child5 extends Father<T,T1> {
	//T name;    //此时不能使用T类型
	@Override
	public void test(T t) {

	}
}
*/