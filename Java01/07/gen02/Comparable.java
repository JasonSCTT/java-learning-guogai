/*
泛型接口 与继承同理
重写方法随父类而定

擦除：
	1.在使用时没有指定具体的类型
	2.子类继承时没有指定具体的类型

处理：
	1.擦除后不类型检查
	2.一但擦除后按Object接收
	3.依然存在编译警告，加上Object可以去除，不完全等同于Object
*/
public interface Comparable<T> {
	void compara(T t);
}

/*
声明子类指定具体类型
*/
class Comp implements Comparable<String> {
	@Override
	public void compara(String t) {

	}
}
/*
擦除
*/
class Comp1 implements Comparable {

	@Override
	public void compara(Object t) {

	}
}

/*
父类擦除子类泛型
*/

class Comp2<T> implements Comparable {

	@Override
	public void compara(Object t) {

	}

}

/*
子类泛型>=父类泛型
*/
class Comp3<T> implements Comparable<T>{

	@Override
	public void compara(T t) {

	}

}

//父类泛型子类擦除，会报错
/*
class Comp4 implements  Comparable<T> {

}
*/