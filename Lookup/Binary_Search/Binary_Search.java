package Binary_Search;

import java.util.Calendar;

public class Binary_Search {

	public static void main(String[] args) {

		int arr[]={1,3,4,5,7,8,9};
		
		// 创建Binary类
		Binary binary = new Binary();

		// 在查找前打印系统时间
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		// 调用Binary类中的排序方法
		binary.search(0,arr.length-1,8,arr);

		// 查找后打印时间
		calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

	}

}

class Binary {
	//在排好序的情况下查找
	public void search(int leftIndex, int rightIndex, int val, int arr[]) {

		// 首先找到中间的数
		int midIndex = (rightIndex + leftIndex) / 2;
		int midVal = arr[midIndex];
		
		if (rightIndex >= leftIndex) {
			
			// 如果要找的数比midVal大
			if (midVal > val) {
				// 在arr左边查找
				search(leftIndex, midIndex - 1, val, arr);
			} else if (midVal < val) {
				// 在arr右边查找
				search(midIndex + 1, rightIndex, val, arr);
			} else if (midVal == val) {
				System.out.println("Find index: " + midIndex);
			}
		} else {
			System.out.println("The number of lookups does not exist.");
		}
	}
}