/*
 * 继承的必要性
 * 除了父类的私有属性和方法都可以被子类继承
 * 子类最多只能继承一个父类
 * Java所有类收拾Object类的子类
 * 如果希望在子类中调用父类的构造方法，就要在子类的构造函数中调用
 */
package Inherit;

public class Inherit_1 {

	public static void main(String[] args) {
		Pupil p1= new Pupil();
		p1.printName();
	}
}

//将学生共有属性提出，做一个父类
class Student{
	//定义成员属性
	public int age;
	public String name;
	public float tuition;
	private String Occupation;
	
	//编程中，如果你不希望子类继承某个属性或者方法，就将其声明为私有（private）即可。
	
	public void printName(){
		System.out.println("The name is "+name);
	}
}


//小学生类
class Pupil extends Student{
	
	//缴费
	public void pay(float tuition){
		this.tuition=tuition;
	}
	
}

//小孩类（继承Pupil类，而Pupil类继承Student类，即拥有Student类和Pupil类的所有属性和方法）
class Kid extends Pupil{
	
}

//中学生类
class MiddleStudent extends Student{
		
		//缴费
		public void pay(float tuition){
			this.tuition=tuition*0.8f;
		}
}

//大学生类
class CollegeStudent extends Student{
	
	//缴费
	public void pay(float tuition){
		this.tuition=tuition*0.1f;
	}
}