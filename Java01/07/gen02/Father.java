/*
����Ϊ����
1.����
2.����
Ҫôͬʱ������Ҫô������ڵ��ڸ�������� 
�����������������Ϊ���ͣ�class Child5 extends Father<T,T1> {...}

1.��������
����ڸ����У��游�����
����������У����������
2.������д
�游�����
*/
public abstract class Father<T,T1> {      /*�������������̳еģ������г��󷽷��������������*/
	T name;

	public abstract void test(T t);

}

/*
��������ʱָ������
����Ϊ���������
����ͬ��
*/
class Child1 extends Father<String,Integer> {

	String t2;

	@Override  
	public void test(String t) {
		
	}
}
/*
����Ϊ������,������ʹ��ʱȷ��
*/
class Child2<T,T1> extends Father<T,T1> {

	T1 t2;

	@Override  
	public void test(T t) {
		
	}
}
/*
����Ϊ�����࣬���಻ָ����������ͣ����͵Ĳ���
*/

class Child3<T,T1> extends Father {
	T1 name;   //��ʹ��ʱָ������
	@Override   //������д������ΪObject
	public void test(Object t) {

	}
}

/*
����͸���ͬʱ����
*/
class Child4 extends Father {
	//T name;    //��ʱ����ʹ��T����
	@Override
	public void test(Object t) {

	}
}

/*
�����������������ָ������
//��ʱ���ܱ���ͨ��
class Child5 extends Father<T,T1> {
	//T name;    //��ʱ����ʹ��T����
	@Override
	public void test(T t) {

	}
}
*/