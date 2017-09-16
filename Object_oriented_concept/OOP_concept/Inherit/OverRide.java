/*
 * 方法覆盖
 * 概念
 * 方法覆盖就是子类有个方法和父类某个方法的名称、返回类型、参数一样
 * 那么我们就说这个方法覆盖了父类的那个方法。
 * 注意事项
 * 1.子类的方法返回类型、参数、方法名称，
 * 要和父类方法的返回类型、参数、方法名称完全一样，
 * 否则编译出错
 * 2.子类方法不能缩小父类方法的访问权限
 * （即子类方法使用访问修饰符缩小父类方法的访问权限）
 * 如：父类的方法是public修饰，则子类方法的访问权限不能小于public
 * 子类放方法可以扩大父类方法的访问权限
 * （即子类方法使用访问修饰符扩大父类方法的访问权限）
 * 如：父类的方法是private修饰，则子类方法的访问权限可以大于或等于private
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
//动物类
class Animal{
	int age;
	String name;
	
	public void cry(){
		System.out.println("Animals cry");
	}
}
//猫类
class Cat extends Animal{
	//覆盖父类方法
	public void cry(){
		System.out.println("Cat cry");
	}
}

//狗类
class Dog extends Animal{
	//覆盖父类方法
	public void cry(){
		System.out.println("Dog cry");
	}
}