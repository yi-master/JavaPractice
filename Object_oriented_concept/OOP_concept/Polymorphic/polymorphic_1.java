/*
 * 多态
 * 概念
 * 多态：指一个引用（类型）在不同情况下的多种状态
 * 多态是指通过指向父类的指针，来调用在不同子类中实现的方法
 * 
 * 注意事项
 * 1.Java运行父类的引用变量引用它的子类实例（对象）
 * 如：Animal animal=new Cat();
 * 这种转换是自动完成的
 * 
 */

package Polymorphic;

public class polymorphic_1 {

	public static void main(String[] args) {

		// Cat cat = new Cat();
		// cat.cry();
		// Dog dog = new Dog();
		// dog.cry();

		// 多态
		// //这个动物一会是猫，一会是狗
		// //Animal所有有子类都可以用其对象animal访问
		// Animal animal=new Cat();
		// animal.cry();//如果猫（Cat）中有覆盖方法则调用猫的方法，否则调用动物（Animal）本身的方法
		// Cat cat2=new Cat();
		// Animal animal=cat2;
		// animal=new Dog();
		// animal.cry();

		Master master = new Master();
		master.feed(new Animal(), new FoodName());
		master.feed(new Dog(), new Bone());
		master.feed(new Cat(), new Fish());
	}
}

// 主人类
class Master {
	// 给动物喂食物，使用多态方法就可以用一个
	public void feed(Animal animal, FoodName food) {
		animal.eat();
		food.showName();
	}
}

// 食物类(食物父类)
class FoodName {
	String name;

	public void showName() {
		System.out.println("Food");
	}
}

// 鱼类（食物子类）
class Fish extends FoodName {
	public void showName() {
		System.out.println("fish");
	}
}

// 骨头类（食物子类）
class Bone extends FoodName {
	public void showName() {
		System.out.println("bone");
	}
}

// 动物类(动物父类)
class Animal {

	String name;
	int age;

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

	// 动物哭（此方法注销则无法实现多态，调用子类方法）
	public void cry() {
		System.out.println("Animal cry");
	}

	// 动物吃东西
	public void eat() {
		System.out.println("Animal eat food");
	}
}

//狗类（动物子类）
class Dog extends Animal {
	// 狗哭
	public void cry() {
		System.out.println("Dog cry");
	}

	// 狗吃东西
	public void eat() {
		System.out.println("Dog eat bone");
	}
}

//猫类（动物子类）
class Cat extends Animal {
	// 猫哭
	public void cry() {
		System.out.println("Cat cry");
	}

	public void eat() {
		System.out.println("Cat eat fish");
	}
}