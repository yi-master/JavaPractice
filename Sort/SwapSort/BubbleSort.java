/*
 * 交换式排序：冒泡排序（升序）
 */
package SwapSort;

import java.util.Calendar;

public class BubbleSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		int len=10;
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			// 让程序随机产生一个1~10000的数
			// Math.random()产生一个0~1的数
			int t = (int) (Math.random() * 10000);
			arr[i] = t;
		}

		// 创建Bubble类
		Bubble bubble = new Bubble();

		// 在排序前打印系统时间
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		// 调用Bubble类中的排序方法
		bubble.sort(arr);

		// 排序后打印时间
		calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		// 打印最终排序结果
		System.out.println("Final sorting result: ");
		for (int k = 0; k < arr.length; k++) {

			System.out.print(arr[k] + " ");
		}
	}

}

class Bubble {
	public void sort(int arr[]) {

		int temp = 0;
		// 冒泡排序
		for (int i = 0; i < arr.length - 1; i++) {// 外层循环（决定排序次数）

			/*
			 * 如果改为for(int j=0;j<arr.length-1-i;j++) 可以减少比较次数，但不能打印每趟排序过程
			 * 因此下面的输出方式要改，输出结果必须在最后打印
			 */
			for (int j = 0; j < arr.length - 1; j++) {
				// 内层循环（逐个比较，若前一个数比后一个数大，则交换）
				if (arr[j] > arr[j + 1]) {
					// 三种交换方式
					// 两个相等的数不能使用异或运算（^）进行交换
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
