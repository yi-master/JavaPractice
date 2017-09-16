/*
 * ����ʽ���򣺲�����������
 * ����˼�룺
 * ��n���������Ԫ�ؿ���һ��������һ�������
 * ��ʼʱ�����ֻ����һ��Ԫ�أ���������n-1��Ԫ��
 * ���������ÿ�δ��������ȡ����һ��Ԫ�أ����������������������Ԫ�ص���������бȽ�
 * �������뵽�������ʵ�λ�ã�����µ������
 */
package InsertionSort;

import java.util.Calendar;

public class InsertionSort {
	
	public static void main(String[] args) {

		int len=10;
		int[] arr=new int[len];
		for(int i=0;i<len;i++){
			//�ó����������һ��1~10000����
			//Math.random()����һ��0~1����
			int t=(int)(Math.random()*10000);
			arr[i]=t;
		}

		// ����һ��insert��
		Insert insert=new Insert();
		
		//������ǰ��ӡϵͳʱ��
		Calendar calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
		//��������
		insert.sort(arr);
		
		//������ӡʱ��
		calendar=Calendar.getInstance();
		System.out.println(calendar.getTime());
		
		// ��ӡ����������
		System.out.println("Final sorting result: ");
		for (int k = 0; k < arr.length; k++) {

			System.out.print(arr[k] + " ");
		}
	}
}

//��������
class Insert{
	
	//����
	public void sort(int arr[]){
		
		for(int i=1;i<arr.length;i++){
			
			int insertVal=arr[i];
			//intsertVal׼����ǰһ�����Ƚ�
			int index =i-1;
			while(index>=0&&insertVal<arr[index]){
				//��arr[index]����ƶ���λ
				arr[index+1]=arr[index];
				//��index��ǰ�ƶ�
				index--;
			}
			//��insertVar���뵽�ʵ�λ��
			arr[index+1]=insertVal;
		}
	}
}