/*
 * ����ʽ���򣺿�����������
 * ��key��������ұߣ�С���������
 * һ�˿���������㷨�ǣ�
1��������������i��j������ʼ��ʱ��i=0��j=N-1��
2���Ե�һ������Ԫ����Ϊ�ؼ����ݣ���ֵ��key����key=A[0]��
3����j��ʼ��ǰ���������ɺ�ʼ��ǰ����(j--)���ҵ���һ��С��key��ֵA[j]����A[j]��A[i]������
4����i��ʼ�������������ǰ��ʼ�������(i++)���ҵ���һ������key��A[i]����A[i]��A[j]������
5���ظ���3��4����ֱ��i=j�� (3,4���У�û�ҵ�����������ֵ����3��A[j]��С��key,4��A[i]������key��ʱ��ı�j��i��ֵ��ʹ��j=j-1��i=i+1��ֱ���ҵ�Ϊֹ���ҵ�����������ֵ�����н�����ʱ��i�� jָ��λ�ò��䡣���⣬i==j��һ����һ��������i+��j-��ɵ�ʱ�򣬴�ʱ��ѭ����������
 */
package SwapSort;

import java.util.Calendar;

public class QuickSort {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		int len=10;
		int[] arr = new int[len];
		for (int i = 0; i < len; i++) {
			// �ó����������һ��-10000~10000����
			// Math.random()����һ��0~1����
			int t = (int) (Math.random() * 10000*Math.pow(-1, (int)(Math.random()*10)));
			arr[i] = t;
		}
		
		// ����Quick��
		Quick quick=new Quick();

		// ������ǰ��ӡϵͳʱ��
		Calendar calendar = Calendar.getInstance();
		System.out.println(calendar.getTime());

		// ����Quick���е����򷽷�
		quick.sort(0,arr.length-1,arr);

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