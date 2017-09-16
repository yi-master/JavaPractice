/*
 * 如果希望在子类中调用父类的构造方法，就要在子类的构造函数中调用
 */
package Inherit;

public class Inherit_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Son son=new Son(12, "Tom");
	}

}

class Father{
	int age;
	String name;
	public Father(int age,String name){
		
		this.age=age;
		this.name=name;	
		System.out.println("Fater");
	}
}

class Son extends Father{
	public Son(int age,String name){
		
		super(age,name);
		System.out.println("Son");
	}
}