/*
 * ��������
 * ����
 * �������Ǿ��������и������͸���ĳ�����������ơ��������͡�����һ��
 * ��ô���Ǿ�˵������������˸�����Ǹ�������
 * ע������
 * 1.����ķ����������͡��������������ƣ�
 * Ҫ�͸��෽���ķ������͡�����������������ȫһ����
 * ����������
 * 2.���෽��������С���෽���ķ���Ȩ��
 * �������෽��ʹ�÷������η���С���෽���ķ���Ȩ�ޣ�
 * �磺����ķ�����public���Σ������෽���ķ���Ȩ�޲���С��public
 * ����ŷ������������෽���ķ���Ȩ��
 * �������෽��ʹ�÷������η������෽���ķ���Ȩ�ޣ�
 * �磺����ķ�����private���Σ������෽���ķ���Ȩ�޿��Դ��ڻ����private
 */

package Inherit;

public class OverRide {
	public static void main(String[] args) {
		
		Cat cat1=new Cat();
		cat1.cry();
		Dog dog1=new Dog();
		dog1.cry();
	}
}
//������
class Animal{
	int age;
	String name;
	
	public void cry(){
		System.out.println("Animals cry");
	}
}
//è��
class Cat extends Animal{
	//���Ǹ��෽��
	public void cry(){
		System.out.println("Cat cry");
	}
}

//����
class Dog extends Animal{
	//���Ǹ��෽��
	public void cry(){
		System.out.println("Dog cry");
	}
}