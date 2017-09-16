package Binary_Search;

import java.util.Calendar;

public class Binary_Search {

	public static void main(String[] args) {

		int arr[]={1,3,4,5,7,8,9};
		
		// ����Binary��
		Binary binary = new Binary();

		// �ڲ���ǰ��ӡϵͳʱ��
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		// ����Binary���е����򷽷�
		binary.search(0,arr.length-1,8,arr);

		// ���Һ��ӡʱ��
		calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

	}

}

class Binary {
	//���ź��������²���
	public void search(int leftIndex, int rightIndex, int val, int arr[]) {

		// �����ҵ��м����
		int midIndex = (rightIndex + leftIndex) / 2;
		int midVal = arr[midIndex];
		
		if (rightIndex >= leftIndex) {
			
			// ���Ҫ�ҵ�����midVal��
			if (midVal > val) {
				// ��arr��߲���
				search(leftIndex, midIndex - 1, val, arr);
			} else if (midVal < val) {
				// ��arr�ұ߲���
				search(midIndex + 1, rightIndex, val, arr);
			} else if (midVal == val) {
				System.out.println("Find index: " + midIndex);
			}
		} else {
			System.out.println("The number of lookups does not exist.");
		}
	}
}