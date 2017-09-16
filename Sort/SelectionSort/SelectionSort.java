/*
 * 选择式排序：选择排序（升序）
 * 选择排序（Selection sort）是一种简单直观的排序算法。
 * 工作原理是每一次从待排序的数据元素中选出最小（或最大）的一个元素，
 * 存放在序列的起始位置，直到全部待排序的数据元素排完。
 */
package SelectionSort;

import java.util.*;

public class SelectionSort {

	public static void main(String[] args) {

		int len=100000;
		int[] arr=new int[len];
		for(int i=0;i<len;i++){
			//让程序随机产生一个1~100000的数
			//Math.random()产生一个0~1的数
			int t=(int)(Math.random()*10000);
			arr[i]=t;
		}

		// 创建一个Select类
		Select select = new Select();
		
		//在排序前打印系统时间
		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
		//选择排序
		select.sort(arr);
		
		//排序后打印时间
		calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
//		// 打印最终排序结果
//		System.out.println("Final sorting result: ");
//		for (int k = 0; k < arr.length; k++) {
//
//			System.out.print(arr[k] + " ");
//		}	
	}
}

class Select {

	int temp = 0;

	// 选择排序
	public void sort(int arr[]) {

		// 最小的数不参与比较
		for (int i = 0; i < arr.length - 1; i++) {

			// 假设第i个数是最小的数
			int min = arr[i];
			// 记录最小数的下标
			int minIndex = i;
			for (int j = i + 1; j < arr.length; j++) {

				if (min > arr[j]) {
					// 修改最小的数
					min = arr[j];
					minIndex = j;
				}
			}
			// 当内循环结束时找到此次最小值
			temp = arr[i];
			arr[i] = arr[minIndex];
			arr[minIndex] = temp;
			
		}
	}
}