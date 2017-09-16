/*
 * ѡ��ʽ����ѡ����������
 * ѡ������Selection sort����һ�ּ�ֱ�۵������㷨��
 * ����ԭ����ÿһ�δӴ����������Ԫ����ѡ����С������󣩵�һ��Ԫ�أ�
 * ��������е���ʼλ�ã�ֱ��ȫ�������������Ԫ�����ꡣ
 */
package SelectionSort;

import java.util.*;

public class SelectionSort {

	public static void main(String[] args) {

		int len=100000;
		int[] arr=new int[len];
		for(int i=0;i<len;i++){
			//�ó����������һ��1~100000����
			//Math.random()����һ��0~1����
			int t=(int)(Math.random()*10000);
			arr[i]=t;
		}

		// ����һ��Select��
		Select select = new Select();
		
		//������ǰ��ӡϵͳʱ��
		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
		//ѡ������
		select.sort(arr);
		
		//������ӡʱ��
		calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
//		// ��ӡ����������
//		System.out.println("Final sorting result: ");
//		for (int k = 0; k < arr.length; k++) {
//
//			System.out.print(arr[k] + " ");
//		}	
	}
}

class Select {

	int temp = 0;

	// ѡ������
	public void sort(int arr[]) {

		// ��С����������Ƚ�
		for (int i = 0; i < arr.length - 1; i++) {

			// �����i��������С����
			int min = arr[i];
			// ��¼��С�����±�
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {

				if (min > arr[j]) {
					// �޸���С����
					min = arr[j];
					minIndex = j;
				}
			}
			// ����ѭ������ʱ�ҵ��˴���Сֵ
			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
			
		}
	}
}