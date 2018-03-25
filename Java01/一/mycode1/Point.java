/**
*需求：计算两个点之间的距离
*
*/
public class Point {
	double x,y,z;

	//通过构造方法来创建对象，并进行初始化属性
	public Point(double _x,double _y,double _z) {     //这里定义的是形式参数
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
		Point p1 = new Point(32,12.2,31);       //这里定义的实际参数，运行时给形参。
		System.out.println(p1.x);

		Point p2 = new Point(234,42,532);
		System.out.println(p1.getDistence(p2));
	}
}