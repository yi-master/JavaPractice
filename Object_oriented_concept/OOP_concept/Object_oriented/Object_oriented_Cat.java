/*
 * 类与对象
 * 这个例子用来理解面向对象的基本概念
 * 类：是抽象的，表示一类事物，如人类，动物，猫
 * 对象：是具体的，代表一个具体的事物
 * 类与对象的关系：类是对象的模板，对象是类的一个个体，实例
 * 
 * 如何定义一个类
 * package 包名
 * class 类名（extends 父类 implements 接口名）{
 * 		成员变量;
 *		构造方法;
 * 		成员方法;
 * }
 */
/*
 * 构造方法
 * 构造方法的修饰符可以是任意的（即可以是public、protected、默认、private）
 * 构造方法名与类名相同
 * 构造方法没有返回值
 * 一个类可以有多个构造方法
 * 构造方法主要作用是初始化成员属性（变量）
 * 每一个类都有一个默认的构造方法
 * 在创建新对象时，系统自动的调用该类的构造方法
 *  
 * 类的实例化
 * 类名 对象名=new 构造方法
 */
package Object_oriented;

public class Object_oriented_Cat {

	public static void main(String[] args) {
		// 创建一个猫对象（第一只猫）
		Cat cat1 = new Cat();
		// 访问对象的属性 （即对象名.属性名）
		cat1.age = 3;
		cat1.name = "小红";
		cat1.color = "Red";

		// 创建第二只猫
		Cat cat2 = new Cat();
		cat2.age = 98;
		cat2.name = "小黑";
		cat2.color = "Black";

		System.out.println(cat1.myMaster.age);

		Cat cat3 = cat1;// 使对象cat3和cat1指向同一内存空间
		cat3.age = 10;// 修改对象cat3的值会使对象cat1的值改变
		// cat1.age的初始值是3，修改后为10
		System.out.println("对象cat1的年龄：" + cat1.age + "\n对象cat3的年龄：" + cat3.age);

		Person p1 = new Person(1, "liqing");//Person(1, "liqing")为构造方法赋值
		Person p2 = new Person("yasuo");
		System.out.println(p2.name);
		p1.speak();// 调用Person类中的speak()方法
		p1.calculation();// 调用Person类中无参数的calculation()方法
		p1.calculation(1000);// 调用Person类中有参数的calculation()方法
		int res = p1.add(1, 30);
		System.out.println(res);
		p1.multiplicationTable(9);
	}
}

class Person {

	// 行为(方法)
	// 方法名的首字母一般小写
	// 无返回值、无参数的成员方法
	public void speak() {
		System.out.println("I can speak Putonghua.");
	}

	public void calculation() {
		int result = 0;
		for (int i = 1; i <= 1000; i++) {
			result += i;
		}
		System.out.println(result);
	}

	// 无返回值、有参数的成员方法
	public void calculation(int n) {
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result += i;
		}
		System.out.println(result);
	}

	// 有返回值、有参数的成员方法
	// 返回类型(int)和返回结果(return )类型要一致
	public int add(int num1, int num2) {
		return num1 + num2;
	}

	public void multiplicationTable(int n) {
		for (int i = 1; i <= n; i++) {
			for (int j = 1; j <= i; j++) {
				System.out.print(i + "*" + j + "=" + i * j + "	");
			}
			System.out.println();
		}
	}

	int age;
	String name;

	//构造方法
	public Person(int age, String name) {
		System.out.println("构造方法1");
		this.age = age;
		this.name = name;
	}

	public Person(String name) {
		System.out.println("构造方法2");
		this.name = name;
	}
}

// 一个类指向另一个类称为引用
// 统一管理猫的属性
class Cat {
	int age;
	String name;
	String color;

	Master myMaster = new Master();
}

class Master {
	int age = 8;
	String name;
	String address;
}