/*
 * ����
 * ���巽ʽ
 * �������� ������[]=new ��������[��С]
 * �磺int a[]=new int[5];
 * 
 * ���ã�ʹ�ã�
 * ������[�±�]
 * ��ʹ������a�ĵ�����������a[2]
 * 
 * ��ʼ������
 * int a[]={2,3,4,6,7};�൱��int a[]=new int[5];
 */
package Array;

public class array_1 {
	
	public static void main(String[] args) {
		
//		int x=1;
//		float a[]=new float[x];
		
		//����һ�����Դ������float���͵�����
		float arr[]=new float[6];
		
		//ʹ��for��ֵ
		//���������Ԫ�ظ�ֵ
		arr[0]=3;
		arr[1]=5;
		arr[2]=1;
		arr[3]=3.4f;
		arr[4]=2;
		arr[5]=50;
		
		float all=0;
		//��������
		for(int i=0;i<arr.length;i++){
			all+=arr[i];
		}
		System.out.println(all);
	}
}
