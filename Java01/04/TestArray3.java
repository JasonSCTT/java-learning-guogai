/*
 *  1.������Ԫ�ص����ֵ����Сֵ��ƽ�������ܺ͵�

 * 2.����ĸ��ơ���ת

 * 3.����Ԫ�ص�����

 */
import java.util.Arrays;
public class TestArray3 {
	public static void main(String[] args) {
		int[] data = new int[] {32,12,53,-31,65,-44,100};

		/**
		*�������ֵ
		*/
		int max = data[0];
		for (int i = 0; i < data.length; i++) {
			if (max < data[i]) {
				max = data[i];
			}
			
		}
		System.out.println("��������ֵΪ��" + max);

		/**
		*�������Сֵ
		*/
		int min = data[0];         //�����һ������Сֵ
		for (int i = 0; i < data.length; i++ ) {
			if (min > data[i]) {
				min = data[i];
			}
		}
		System.out.println("�������СֵΪ��" + min);

		/**
		*������ܺ�
		*/
		int sum = 0;
		for (int i = 0; i < data.length; i++) {
			sum += data[i];
		}

		System.out.println("������ܺ�Ϊ��" + sum);

		/**
		*�����ƽ��ֵ
		*/
		int avg = 0;
		avg = sum/data.length;
		System.out.println("�����ƽ��ֵΪ��" + avg);

		/**
		*����ĸ���
		*/
		int[] newData = new int[data.length];
		for (int i = 0; i < data.length; i++) {
			newData[i] = data[i];                       //ѭ�����и�ֵ
		}

		/**
		*���鷴ת
		*/
		for (int i = 0; i < data.length/2; i++) {
			int temp = data[i];
			data[i] = data[data.length - 1 - i];     //��һ�������һ�����н���
			data[data.length -1 - i] = temp;
		}
		/**
		*�ڷ�ת��ȥ
		*/
		for (int x = 0,y  = data.length - 1; x < y; x++,y--) {
			int temp = data[x];
			data[x] = data[y];
			data[y] = temp;
		}

		System.out.println("�ٴη�ת֮��");

		//�������

		for (int i = 0; i < data.length; i++) {
			System.out.print(data[i] + " ");
		}

		/**
		*ʹ��ð�����򣬴�С��������
		*����˼�����ûѭ��һ�ֽ����ķŵ������
		*/
		for (int i = 1; i < data.length - 1; i++) {    //��������
			for (int j = 0;  j < data.length-1-i;j++ ) {
				if (data[j] > data[j+1]) {
					int temp = data[j];
					data[j] = data[j+1];
					data[j+1] = temp; 
				}
			}
		}
		int[] arr = new int[] {3,-4,1,-6,5,9,43};
		/**
		*��ֱ��ѡ������
		*/
		for (int i = 0; i < arr.length - 1; i++) {
			for (int j = i; j < arr.length; j++) {
				if (arr[i] > arr[j]) {
					int temp = arr[i];
					arr[i] = arr[j];
					arr[j] = temp;
				}
			}
		}

		/**
		*ֱ��ѡ�����������㷨
		*/
		for (int i = 0; i < arr.length -1; i++) {
			int t = i;		//Ĭ��i������С��
			for (int j = i; j < arr.length; j++) {
				if (arr[i] < arr[j]) {
					t = j;
				}
			}
			if (t != i) {
				int temp = arr[t];
				arr[t] = arr[i];
				arr[i] = temp;
			}
		}

		Arrays.sort(arr);

		//�������
		System.out.print("���������Ϊ��" );
		for (int i = 0; i < arr.length; i++) {
			System.out.print( arr[i] + " ");
		}
	}
}