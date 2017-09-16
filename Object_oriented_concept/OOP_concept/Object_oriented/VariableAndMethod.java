/*
 * 调用类方法使用：类名.类方法名()或者对象名.方法名
 * 类方法（静态方法）中不能访问非静态变量（类变量）
 * 类方法（静态方法）只能访问静态变量
 * 非静态方法技能访问静态变量，也能访问非静态变量
 * 
 * 类变量（静态变量）
 * 加上static称为类变量或静态变量，否则称为实例变量
 * 类变量（静态变量）是与类相关的公共的属性
 * 实例变量属于每对象个体的属性
 * 类变量（静态变量）可以通过类名.类变量名直接访问
 * 
 * 类方法（静态方法）
 * 类方法（静态方法）属于与类相关的公共的方法
 * 实例方法属于每个对象个体的方法
 * 类方法（静态方法）可以通过类名.方法名直接访问
 */
package Object_oriented;

public class VariableAndMethod {

	static int i = 1;
	static {
		// 该静态区域块只执行一次
		// 不创建对象该区域仍会执行
		System.out.println("Static area be executed.");
		i++;
	}

	public VariableAndMethod() {
		System.out.println("Construction method be executed.");
		i++;
	}

	public static void main(String args[]) {
		 VariableAndMethod vam1=new VariableAndMethod();
		 System.out.println(vam1.i);
		 VariableAndMethod vam2=new VariableAndMethod();
		 System.out.println(vam2.i);

		student stu1 = new student(33, "aaa", 500);
		student stu2 = new student(22, "bbb", 960);
		System.out.println(student.getTotalTuition());
	
		
	}
}


class student {
	int age;
	String name;
	int tuition;
	static int totalTuition;

	public student(int age, String name, int tuition) {
		this.age = age;
		this.name = name;
		totalTuition += tuition;
	}

	// 返回总学费(这是一个类方法（静态方法）)
	// java中规则：类变量原则上用类方法去访问
	public static int getTotalTuition() {
		return totalTuition;
	}
}
