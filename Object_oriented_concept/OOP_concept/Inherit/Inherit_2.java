/*
 * ���ϣ���������е��ø���Ĺ��췽������Ҫ������Ĺ��캯���е���
 */
package Inherit;

public class Inherit_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Son son=new Son(12, "Tom");
	}

}

class Father{
	int age;
	String name;
	public Father(int age,String name){
		
		this.age=age;
		this.name=name;	
		System.out.println("Fater");
	}
}

class Son extends Father{
	public Son(int age,String name){
		
		super(age,name);
		System.out.println("Son");
	}
}