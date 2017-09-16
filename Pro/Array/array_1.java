/*
 * 数组
 * 定义方式
 * 数据类型 数组名[]=new 数据类型[大小]
 * 如：int a[]=new int[5];
 * 
 * 引用（使用）
 * 数组名[下标]
 * 如使用数组a的第三个数，则a[2]
 * 
 * 初始化数组
 * int a[]={2,3,4,6,7};相当于int a[]=new int[5];
 */
package Array;

public class array_1 {
	
	public static void main(String[] args) {
		
//		int x=1;
//		float a[]=new float[x];
		
		//定义一个可以存放留个float类型的数组
		float arr[]=new float[6];
		
		//使用for赋值
		//给数组各个元素赋值
		arr[0]=3;
		arr[1]=5;
		arr[2]=1;
		arr[3]=3.4f;
		arr[4]=2;
		arr[5]=50;
		
		float all=0;
		//遍历数组
		for(int i=0;i<arr.length;i++){
			all+=arr[i];
		}
		System.out.println(all);
	}
}
