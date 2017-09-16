package Encapsulation;
/*
 * Java封装:访问控制修饰符(成员变量和方法都可被修饰)
 * 各修饰符访问权限
 * 公开级别：public修饰，对外公开（同类，同包，子类，不同包都能访问）
 * 受保护级别：protected修饰，对子类和同一个包中的类公开（同类，同包，子类都能访问）
 * 默认级别：没有修饰符号，向同一个包的类公开（同类，同包都能访问）
 * 私有级别：private只有类本身可以访问，不对外公开（只用同类可以访问）
 */



public class Encapsulation {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Clerk clerk1=new Clerk("aerfa", 18, 2600);
		System.out.println("The name is "+clerk1.name+"The salary is "+clerk1.getSalary());
	}

}
class Clerk {
	public String name;
	//私有属性（只能被当前类访问）
	private int age;
	private float salary;

	public Clerk(String name, int age, float salary) {
		this.name = name;
		this.age = age;
		this.salary = salary;
	}
	
	//通过一个成员方法去控制和访问私有的属性
	public float getSalary(){
		return salary;
	}
}
