/*
 * ���Ͷ���
 * ����<����> ������=new ����<����>(�������������)
 * �磺		
 * Gen<Bird> gen1=new Gen<Bird>(new Bird());
 * Gen<Integer> gen2=new Gen<Integer>(1);
 *		
 * �ŵ�
 * 1.���Ͱ�ȫ
 * 2.������
 * 3.�������
 * 4.���ܽϸߣ�
 * �÷���Java��GJ����д�Ĵ������ΪJava���������������������������Ϣ��
 * ��Щ��Ϣ��Java��������һ�����Ż��ṩ����
 */
package Genericity;

import java.lang.reflect.*;

public class Genericity_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gen<Bird> gen1=new Gen<Bird>(new Bird());
		Gen<Integer> gen2=new Gen<Integer>(1);
		gen1.showTypeName();
		gen2.showTypeName();
	}

}
//�����Bird
class Bird{
	
	public void test1(){
		System.out.println("Aaa");
	}
	public void count(int a,int b){
		System.out.println(a+b);
	}
}

//������
class Gen<T>{
	private T o;
	
	//���캯��
	public Gen(T o){
		this.o=o;
	}
	
	//�õ�T����������
	public void showTypeName(){
		System.out.println("Type: "+o.getClass().getName());
		//ͨ��������ƣ����Եõ�T������͵ĺܶ���Ϣ������õ���Ա��������
		Method m[]=o.getClass().getDeclaredMethods();
		//��ӡ
		for(int i=0;i<m.length;i++){
			System.out.println(m[i].getName());
		}
	}
}