/*
 * 抽象类的必要性
 * 当父类的一些方法不能确定时
 * 可以使用abstract关键字来修饰该方法（抽象方法）
 * 用abstract来修饰该类（抽象类）
 * 
 * 用abstract关键字来修饰一个类时，这个类叫抽象类
 * 用abstract关键字来修饰一个方法时，这个方法叫抽象方法
 * 
 * 注意事项
 * 抽象类不能被实例化（即创建对象实例化）
 * 抽象类不一定要包含abstract方法（即抽象类可以没有抽象方法）
 * 抽象类可以有实现了的方法（非抽象方法）
 * 一旦类包含了abstract方法，则这个类必须声明为abstract
 * 抽象方法不能有主体（即不能有大括号）如：（错）abstract public void cry(){ }
 * 正确的抽象类，如：（对）abstract public void cry();
 * 
 */
package Abstract;

public class test {
	public static void main(String[] args) {
		Cat cat1=new Cat();
		cat1.cry();
		
		//抽象类中实现了的方法（非抽象方法）子类可以使用
		cat1.fly();
	}
}

//抽象类
abstract class Animal{
	String name;
	int age;
	//抽象方法在父类中不能实现，只能在其子类类中实现
	abstract public void cry();
	
	//抽象类可以有实现了的方法（非抽象方法）
	public void fly(){
		System.out.println("I can fly");
	}
		
}
//当一个类继承的父类为抽象类，则需要把抽象类中所有抽象方法全部实现
class Cat extends Animal{

	//实现抽象类中所有抽象方法
	public void cry() {
		// TODO Auto-generated method stub
		System.out.println("Cat cry");
	}
	
}