/*
 *  从键盘读入学生成绩，找出最高分，并输出学生成绩等级。
	成绩>=最高分-10    等级为’A’   
	成绩>=最高分-20    等级为’B’
	成绩>=最高分-30    等级为’C’   
	其余                            等级为’D’
	
	提示：先读入学生人数，根据人数创建int数组，存放学生成绩。

 */
import java.util.Scanner;
public class TestStudentScore {
	public static void main(String[] args) {
		//1.创建Scanner对象并从键盘获取学生的个数n
		Scanner s = new Scanner(System.in);
		System.out.println("请输入学生的个数：");
		int count = s.nextInt();                      //count用来记录学生的个数

		//2.根据雪学生的个数n，创建一个长度为n的int类型数组
		int[] scores = new int[count];
		int maxScore = 0;
		//3.依次从键盘获取n个学生的成绩，并赋值给相应的数组元素，并获取n个学生中最大的成绩
		System.out.println("请输入" + count + "个成绩：");
		for (int i = 0; i < scores.length; i++) {
			int score = s.nextInt();                  //依次从键盘获取学生的成绩
			scores[i] = score;
			if (scores[i] > maxScore) {
				maxScore = scores[i];
			}
		}
		//4.遍历学生成绩的数组，并根据学生成绩与最高差值，赋予相应的等级
		for (int i = 0; i < scores.length; i++) {
			char level;
			if (scores[i] >= maxScore - 10) {
				level = 'A';
			}else if (scores[i] >= maxScore - 20) {
				level = 'B';
			}else if (scores[i] >= maxScore - 30) {
				level = 'C';
			}else {
				level = 'D';
			}
			System.out.println("student " + i + " score is " + scores[i] + " grade is " + level);
		}
	}
}