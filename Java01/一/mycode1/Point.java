/**
*���󣺼���������֮��ľ���
*
*/
public class Point {
	double x,y,z;

	//ͨ�����췽�����������󣬲����г�ʼ������
	public Point(double _x,double _y,double _z) {     //���ﶨ�������ʽ����
		x = _x;
		y = _y;
		z = _z;
	}

	public void setX(double _x) {
		x = _x;
	}

	public void setY(double _y) {
		y = _y;
	}

	public void setZ(double _z) {
		z = _z;
	}

	public double getDistence(Point p) {
		double result = Math.sqrt((x-p.x)*(x-p.x) + (y-p.y)*(y-p.y) + (z-p.z)*(z-p.z));
		return result;
	}

	public static void main(String[] args) {
		Point p1 = new Point(32,12.2,31);       //���ﶨ���ʵ�ʲ���������ʱ���βΡ�
		System.out.println(p1.x);

		Point p2 = new Point(234,42,532);
		System.out.println(p1.getDistence(p2));
	}
}