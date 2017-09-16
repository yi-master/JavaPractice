/*
 * 交换式排序：快速排序（升序）
 * 比key大的数放右边，小的数放左边
 * 一趟快速排序的算法是：
1）设置两个变量i、j，排序开始的时候：i=0，j=N-1；
2）以第一个数组元素作为关键数据，赋值给key，即key=A[0]；
3）从j开始向前搜索，即由后开始向前搜索(j--)，找到第一个小于key的值A[j]，将A[j]和A[i]互换；
4）从i开始向后搜索，即由前开始向后搜索(i++)，找到第一个大于key的A[i]，将A[i]和A[j]互换；
5）重复第3、4步，直到i=j； (3,4步中，没找到符合条件的值，即3中A[j]不小于key,4中A[i]不大于key的时候改变j、i的值，使得j=j-1，i=i+1，直至找到为止。找到符合条件的值，进行交换的时候i， j指针位置不变。另外，i==j这一过程一定正好是i+或j-完成的时候，此时令循环结束）。
 */
package SwapSort;

import java.util.Calendar;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int len=10;
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			// 让程序随机产生一个-10000~10000的数
			// Math.random()产生一个0~1的数
			int t = (int) (Math.random() * 10000*Math.pow(-1, (int)(Math.random()*10)));
			arr[i] = t;
		}
		
		// 创建Quick类
		Quick quick=new Quick();

		// 在排序前打印系统时间
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		// 调用Quick类中的排序方法
		quick.sort(0,arr.length-1,arr);

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

class Quick{
	
	public void sort(int left,int right,int arr[]){
		if(left>=right){
			return;
		}
		int i=left;
		int j=right;
		int key=arr[left];

		while(i<j){
			
			while(arr[j]>key){
				j--;
			}
			while(arr[i]<key){
				i++;
			}
			
			arr[i]^=arr[j];
			arr[j]^=arr[i];
			arr[i]^=arr[j];
		}
//		while(i<j){
//			while(i<j&&arr[j]>=key){
//				j--;
//			}arr[i]=arr[j];
//			while(i<j&&arr[i]<=key){
//				i++;
//			}arr[j]=arr[i];
//		}
		arr[i]=key;
		this.sort(left,i-1,arr);
		this.sort(i+1,right,arr);
	}
}