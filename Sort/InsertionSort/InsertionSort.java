/*
 * 插入式排序：插入排序（升序）
 * 基本思想：
 * 把n个待排序的元素看成一个有序表和一个无序表
 * 开始时有序表只包含一个元素，无序表包含n-1个元素
 * 排序过程中每次从无序表中取出第一个元素，把它排序码依次与有序表元素的排序码进行比较
 * 将它插入到有序表的适当位置，变成新的有序表
 */
package InsertionSort;

import java.util.Calendar;

public class InsertionSort {
	
	public static void main(String[] args) {

		int len=10;
		int[] arr=new int[len];
		for(int i=0;i<len;i++){
			//让程序随机产生一个1~10000的数
			//Math.random()产生一个0~1的数
			int t=(int)(Math.random()*10000);
			arr[i]=t;
		}

		// 创建一个insert类
		Insert insert=new Insert();
		
		//在排序前打印系统时间
		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
		//插入排序
		insert.sort(arr);
		
		//排序后打印时间
		calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
		// 打印最终排序结果
		System.out.println("Final sorting result: ");
		for (int k = 0; k < arr.length; k++) {

			System.out.print(arr[k] + " ");
		}
	}
}

//插入排序
class Insert{
	
	//排序
	public void sort(int arr[]){
		
		for(int i=1;i<arr.length;i++){
			
			int insertVal=arr[i];
			//intsertVal准备和前一个数比较
			int index =i-1;
			while(index>=0&&insertVal<arr[index]){
				//把arr[index]向后移动移位
				arr[index+1]=arr[index];
				//让index向前移动
				index--;
			}
			//将insertVar加入到适当位置
			arr[index+1]=insertVal;
		}
	}
}