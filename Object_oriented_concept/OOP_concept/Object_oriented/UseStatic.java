/*
 * static�ؼ���
 * ���ڷǾ�̬���ݳ�Ա��ÿ����������Լ��Ŀ�����
 * ����̬���ݳ�Ա����������ĳ�Ա��
 * ���������Ķ��󱻶����˶��ٸ�����̬���ݳ�Ա�ڳ�����Ҳֻ��һ�ݿ������ɸ����͵����ж�������ʡ�
 * Ҳ����˵����̬���ݳ�Ա�Ǹ�������ж��������еġ�
 * �Ը���Ķ��������˵����̬���ݳ�Աֻ����һ���ڴ棬�����ж����á�
 * ���ԣ���̬���ݳ�Ա��ֵ��ÿ��������һ���ģ�����ֵ���Ը���
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
	Dog dog;// ��������

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
	// totalʱ��̬�������Ա��κ�һ��������ʣ�����Child��Ķ����ǹ��õ�
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