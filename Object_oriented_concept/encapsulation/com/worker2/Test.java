/*
 * ��������
 * 
 * Java��װ:���ʿ������η�(��Ա�����ͷ������ɱ�����)
 * �����η�����Ȩ��
 * ��������public���Σ����⹫����ͬ�࣬ͬ�������࣬��ͬ�����ܷ��ʣ�
 * �ܱ�������protected���Σ��������ͬһ�����е��๫����ͬ�࣬ͬ�������඼�ܷ��ʣ�
 * Ĭ�ϼ���û�����η��ţ���ͬһ�������๫����ͬ�࣬ͬ�����ܷ��ʣ�
 * ˽�м���privateֻ���౾����Է��ʣ������⹫����ֻ��ͬ����Է��ʣ�
 */
 
package com.worker2;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dog dog1=new Dog();
		System.out.println(dog1.a);
	}

}
class Dog{
	public int a;
	protected String name;
	String color;
	private float price;
	
	public void ab1(){
		
		System.out.println(a);
	}
}