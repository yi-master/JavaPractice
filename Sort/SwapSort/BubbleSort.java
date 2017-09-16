/*
 * ����ʽ����ð����������
 */
package SwapSort;

import java.util.Calendar;

public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int len=10;
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			// �ó����������һ��1~10000����
			// Math.random()����һ��0~1����
			int t = (int) (Math.random() * 10000);
			arr[i] = t;
		}

		// ����Bubble��
		Bubble bubble = new Bubble();

		// ������ǰ��ӡϵͳʱ��
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		// ����Bubble���е����򷽷�
		bubble.sort(arr);

		// ������ӡʱ��
		calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		// ��ӡ����������
		System.out.println("Final sorting result: ");
		for (int k = 0; k < arr.length; k++) {

			System.out.print(arr[k] + " ");
		}
	}

}

class Bubble {
	public void sort(int arr[]) {

		int temp = 0;
		// ð������
		for (int i = 0; i < arr.length - 1; i++) {// ���ѭ�����������������

			/*
			 * �����Ϊfor(int j=0;j<arr.length-1-i;j++) ���Լ��ٱȽϴ����������ܴ�ӡÿ���������
			 * �������������ʽҪ�ģ�����������������ӡ
			 */
			for (int j = 0; j < arr.length - 1; j++) {
				// �ڲ�ѭ��������Ƚϣ���ǰһ�����Ⱥ�һ�������򽻻���
				if (arr[j] > arr[j + 1]) {
					// ���ֽ�����ʽ
					// ������ȵ�������ʹ��������㣨^�����н���
					// temp=arr[j];
					// arr[j]=arr[j+1];
					// arr[j+1]=temp;

					// arr[j]=arr[j]^arr[j+1];
					// arr[j+1]=arr[j]^arr[j+1];
					// arr[j]=arr[j]^arr[j+1];

					arr[j] ^= arr[j + 1];
					arr[j + 1] ^= arr[j];
					arr[j] ^= arr[j + 1];
				}
				System.out.print(arr[j] + " ");
			}
			System.out.println(arr[arr.length - 1]);
		}
	}
}
