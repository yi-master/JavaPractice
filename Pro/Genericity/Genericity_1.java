/*
 * ����
 * java���ü����෺�Ͷ��巽ʽ
 * ������<����> ������=new ������<����>();
 * �磺ArrayList<Dog> al=new ArrayList<Dog>();
 * 
 * ���ã�
 * 1.��߰�ȫ
 * 2.��ߴ���������
 * �����ò�ȷ����һ���������ʾ����һ������
 * ͨ��������ƿ����õ����һϵ����Ϣ���Ӷ���ߴ������Խ��
 */
package Genericity;

import java.util.*;

public class Genericity_1 {
	public static void main(String[] args) {
		
		ArrayList<Dog> al=new ArrayList<Dog>();
		
		//����һֻ��
		Dog dog1=new Dog();
		//���뵽����
		al.add(dog1);
		
		//ȡ��
//		Dog temp=(Dog)al.get(0);
		Dog temp=al.get(0);
	}
}

class Cat{
	private String color;
	private int age;
	
	public String getColor() {
		return color;
	}
	public void setColor(String color) {
		this.color = color;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}
	
}

class Dog{
	private String name;
	private int age;
	
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public int getAge() {
		return age;
	}
	public void setAge(int age) {
		this.age = age;
	}

}