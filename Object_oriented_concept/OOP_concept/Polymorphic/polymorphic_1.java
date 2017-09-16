/*
 * ��̬
 * ����
 * ��̬��ָһ�����ã����ͣ��ڲ�ͬ����µĶ���״̬
 * ��̬��ָͨ��ָ�����ָ�룬�������ڲ�ͬ������ʵ�ֵķ���
 * 
 * ע������
 * 1.Java���и�������ñ���������������ʵ��������
 * �磺Animal animal=new Cat();
 * ����ת�����Զ���ɵ�
 * 
 */

package Polymorphic;

public class polymorphic_1 {

	public static void main(String[] args) {

		// Cat cat = new Cat();
		// cat.cry();
		// Dog dog = new Dog();
		// dog.cry();

		// ��̬
		// //�������һ����è��һ���ǹ�
		// //Animal���������඼�����������animal����
		// Animal animal=new Cat();
		// animal.cry();//���è��Cat�����и��Ƿ��������è�ķ�����������ö��Animal������ķ���
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

// ������
class Master {
	// ������ιʳ�ʹ�ö�̬�����Ϳ�����һ��
	public void feed(Animal animal, FoodName food) {
		animal.eat();
		food.showName();
	}
}

// ʳ����(ʳ�︸��)
class FoodName {
	String name;

	public void showName() {
		System.out.println("Food");
	}
}

// ���ࣨʳ�����ࣩ
class Fish extends FoodName {
	public void showName() {
		System.out.println("fish");
	}
}

// ��ͷ�ࣨʳ�����ࣩ
class Bone extends FoodName {
	public void showName() {
		System.out.println("bone");
	}
}

// ������(���︸��)
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

	// ����ޣ��˷���ע�����޷�ʵ�ֶ�̬���������෽����
	public void cry() {
		System.out.println("Animal cry");
	}

	// ����Զ���
	public void eat() {
		System.out.println("Animal eat food");
	}
}

//���ࣨ�������ࣩ
class Dog extends Animal {
	// ����
	public void cry() {
		System.out.println("Dog cry");
	}

	// ���Զ���
	public void eat() {
		System.out.println("Dog eat bone");
	}
}

//è�ࣨ�������ࣩ
class Cat extends Animal {
	// è��
	public void cry() {
		System.out.println("Cat cry");
	}

	public void eat() {
		System.out.println("Cat eat fish");
	}
}