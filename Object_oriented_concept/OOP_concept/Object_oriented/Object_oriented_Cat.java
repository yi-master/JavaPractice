/*
 * �������
 * ���������������������Ļ�������
 * �ࣺ�ǳ���ģ���ʾһ����������࣬���è
 * �����Ǿ���ģ�����һ�����������
 * �������Ĺ�ϵ�����Ƕ����ģ�壬���������һ�����壬ʵ��
 * 
 * ��ζ���һ����
 * package ����
 * class ������extends ���� implements �ӿ�����{
 * 		��Ա����;
 *		���췽��;
 * 		��Ա����;
 * }
 */
/*
 * ���췽��
 * ���췽�������η�����������ģ���������public��protected��Ĭ�ϡ�private��
 * ���췽������������ͬ
 * ���췽��û�з���ֵ
 * һ��������ж�����췽��
 * ���췽����Ҫ�����ǳ�ʼ����Ա���ԣ�������
 * ÿһ���඼��һ��Ĭ�ϵĹ��췽��
 * �ڴ����¶���ʱ��ϵͳ�Զ��ĵ��ø���Ĺ��췽��
 *  
 * ���ʵ����
 * ���� ������=new ���췽��
 */
package Object_oriented;

public class Object_oriented_Cat {

	public static void main(String[] args) {
		// ����һ��è���󣨵�һֻè��
		Cat cat1 = new Cat();
		// ���ʶ�������� ����������.��������
		cat1.age = 3;
		cat1.name = "С��";
		cat1.color = "Red";

		// �����ڶ�ֻè
		Cat cat2 = new Cat();
		cat2.age = 98;
		cat2.name = "С��";
		cat2.color = "Black";

		System.out.println(cat1.myMaster.age);

		Cat cat3 = cat1;// ʹ����cat3��cat1ָ��ͬһ�ڴ�ռ�
		cat3.age = 10;// �޸Ķ���cat3��ֵ��ʹ����cat1��ֵ�ı�
		// cat1.age�ĳ�ʼֵ��3���޸ĺ�Ϊ10
		System.out.println("����cat1�����䣺" + cat1.age + "\n����cat3�����䣺" + cat3.age);

		Person p1 = new Person(1, "liqing");//Person(1, "liqing")Ϊ���췽����ֵ
		Person p2 = new Person("yasuo");
		System.out.println(p2.name);
		p1.speak();// ����Person���е�speak()����
		p1.calculation();// ����Person�����޲�����calculation()����
		p1.calculation(1000);// ����Person�����в�����calculation()����
		int res = p1.add(1, 30);
		System.out.println(res);
		p1.multiplicationTable(9);
	}
}

class Person {

	// ��Ϊ(����)
	// ������������ĸһ��Сд
	// �޷���ֵ���޲����ĳ�Ա����
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

	// �޷���ֵ���в����ĳ�Ա����
	public void calculation(int n) {
		int result = 0;
		for (int i = 1; i <= n; i++) {
			result += i;
		}
		System.out.println(result);
	}

	// �з���ֵ���в����ĳ�Ա����
	// ��������(int)�ͷ��ؽ��(return )����Ҫһ��
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

	//���췽��
	public Person(int age, String name) {
		System.out.println("���췽��1");
		this.age = age;
		this.name = name;
	}

	public Person(String name) {
		System.out.println("���췽��2");
		this.name = name;
	}
}

// һ����ָ����һ�����Ϊ����
// ͳһ����è������
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