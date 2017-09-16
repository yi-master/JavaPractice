/*
 * 泛型
 * java常用集合类泛型定义方式
 * 集合类<类名> 对象名=new 集合类<类名>();
 * 如：ArrayList<Dog> al=new ArrayList<Dog>();
 * 
 * 作用：
 * 1.提高安全
 * 2.提高代码重用率
 * 可以用不确定的一个类型类表示任意一个类型
 * 通过反射机制可以拿到类的一系列信息，从而提高代码的优越性
 */
package Genericity;

import java.util.*;

public class Genericity_1 {
	public static void main(String[] args) {
		
		ArrayList<Dog> al=new ArrayList<Dog>();
		
		//创建一只狗
		Dog dog1=new Dog();
		//放入到集合
		al.add(dog1);
		
		//取出
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