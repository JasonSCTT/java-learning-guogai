/**
*ѧ����
*���Ƕ����ģ�壬����������ʵ�����õ��ġ�
*
*���ƶ���ĳ�ȡ�������ƵĲ��ֳ�ȡ����
*
*������ͨ�������� ����������Ժͷ���
*
*ͨ����ķ�ʽ����֯���룬ͨ����������װ(��֯)����
*/
public class Student {
	//��̬������
	String name;
	int id;       //ѧ��
	int age;
	String gender;
	int weight;

	Computer computer;

	//��̬�ķ���
	public void study() {
		System.out.println(name + "��ѧϰ ");
	}

	public void sayHello(String sname) {
		System.out.println(name + "��" + sname + "�ʺ�");
	}

	public static void main(String[] args) {
		//1�������ڷ������鿴�Ƿ�����Ķ��壬���û����ͨ��JVM��ClassLoader�����࣬ ���غ��ڷ����������������Ϣ�����롢static�����������ص���Ϣ��
		//2����Ϊs1�Ǿֲ����������Իᶨ����ջ�У�ջ���Զ������������ڴ桢�Ƚ������žֲ�������Ϣ��
		//3��new Studentͨ������Ĭ�ϵĹ������ڶ��д������󣬲���s1��ַָ��ö������ͨ��s1����������
		Student s = new Student();
		s.name = "guo";                   //�ڳ������в��ҡ�guo��
		s.study();
		s.sayHello("GG");                 //ͨ������ջ֡�ѡ�GG�����ݽ�������������ջ֡��ʧ

		Student s2 = new Student();       // Student���Ѿ����壬ֱ�Ӱ�s2�ĵ�ַָ��Student����
		s2.name = "GG";
		s2.age = 25;					  //�������������ǿ���ֱ�ӽ��и�ֵ��

		Computer c = new Computer();	  //�ȼ���Computer�࣬�ڷ��������������Ϣ���ڶ��д�������ջ�д���c������ָ��Computer����ĵ�ַ
		c.brand = "������";				   //�����ڳ������в鿴�Ƿ����иñ�����û�����ڳ������д�����ͨ��c��ַ�����������ʵ������
		c.cpuSpeed = 1000;
		s2.computer = c;                  //��c�ĵ�ַ��ֵ��s2��ȫ�ֱ�����
		System.out.println(s2.computer.brand);

	}
}

class Computer {
	String brand;
	int cpuSpeed;
}