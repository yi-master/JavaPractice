/*
 * static关键字
 * 对于非静态数据成员，每个类对象都有自己的拷贝。
 * 而静态数据成员被当作是类的成员。
 * 无论这个类的对象被定义了多少个，静态数据成员在程序中也只有一份拷贝，由该类型的所有对象共享访问。
 * 也就是说，静态数据成员是该类的所有对象所共有的。
 * 对该类的多个对象来说，静态数据成员只分配一次内存，供所有对象共用。
 * 所以，静态数据成员的值对每个对象都是一样的，它的值可以更新
 */
package Object_oriented;

public class UseStatic {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// Dog dog1= new Dog(3,"Big Write");
		// human h1 = new human(dog1,33, "yasuo");
		// human h2=new human(dog1,44,"liqing");
		// h1.showInfo();
		// h1.dog.showInfo();

		Child ch1 = new Child(3, "aa");
		ch1.joinGame();
		Child ch2 = new Child(6, "bb");
		ch2.joinGame();
		Child ch3 = new Child(3, "cc");
		ch3.joinGame();
		System.out.println(ch2.total);
	}
}

class human {
	int age;
	String name;
	Dog dog;// 引用类型

	public human(Dog dog, int age, String name) {
		this.age = age;
		this.name = name;
		this.dog = dog;
	}

	public void showInfo() {
		System.out.println("This person name is " + name);
	}
}

class Dog {
	int age;
	String name;

	public Dog(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public void showInfo() {
		System.out.println("The dog name is " + name);
	}
}

class Child {
	int age;
	String name;
	// total时静态变量可以被任何一个对象访问，对于Child类的对象是共用的
	static int total = 0;

	public Child(int age, String name) {
		this.age = age;
		this.name = name;
	}

	public void joinGame() {
		total++;
		System.out.println("Have a child join the game.");
	}
}