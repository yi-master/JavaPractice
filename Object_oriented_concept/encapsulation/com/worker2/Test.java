/*
 * 包的作用
 * 
 * Java封装:访问控制修饰符(成员变量和方法都可被修饰)
 * 各修饰符访问权限
 * 公开级别：public修饰，对外公开（同类，同包，子类，不同包都能访问）
 * 受保护级别：protected修饰，对子类和同一个包中的类公开（同类，同包，子类都能访问）
 * 默认级别：没有修饰符号，向同一个包的类公开（同类，同包都能访问）
 * 私有级别：private只有类本身可以访问，不对外公开（只用同类可以访问）
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