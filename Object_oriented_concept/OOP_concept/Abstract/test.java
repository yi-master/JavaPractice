/*
 * ������ı�Ҫ��
 * �������һЩ��������ȷ��ʱ
 * ����ʹ��abstract�ؼ��������θ÷��������󷽷���
 * ��abstract�����θ��ࣨ�����ࣩ
 * 
 * ��abstract�ؼ���������һ����ʱ�������г�����
 * ��abstract�ؼ���������һ������ʱ����������г��󷽷�
 * 
 * ע������
 * �����಻�ܱ�ʵ����������������ʵ������
 * �����಻һ��Ҫ����abstract�����������������û�г��󷽷���
 * �����������ʵ���˵ķ������ǳ��󷽷���
 * һ���������abstract��������������������Ϊabstract
 * ���󷽷����������壨�������д����ţ��磺����abstract public void cry(){ }
 * ��ȷ�ĳ����࣬�磺���ԣ�abstract public void cry();
 * 
 */
package Abstract;

public class test {
	public static void main(String[] args) {
		Cat cat1=new Cat();
		cat1.cry();
		
		//��������ʵ���˵ķ������ǳ��󷽷����������ʹ��
		cat1.fly();
	}
}

//������
abstract class Animal{
	String name;
	int age;
	//���󷽷��ڸ����в���ʵ�֣�ֻ��������������ʵ��
	abstract public void cry();
	
	//�����������ʵ���˵ķ������ǳ��󷽷���
	public void fly(){
		System.out.println("I can fly");
	}
		
}
//��һ����̳еĸ���Ϊ�����࣬����Ҫ�ѳ����������г��󷽷�ȫ��ʵ��
class Cat extends Animal{

	//ʵ�ֳ����������г��󷽷�
	public void cry() {
		// TODO Auto-generated method stub
		System.out.println("Cat cry");
	}
	
}