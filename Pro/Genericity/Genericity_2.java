/*
 * 泛型定义
 * 类名<类名> 对象名=new 类名<类名>(该类的数据类型)
 * 如：		
 * Gen<Bird> gen1=new Gen<Bird>(new Bird());
 * Gen<Integer> gen2=new Gen<Integer>(1);
 *		
 * 优点
 * 1.类型安全
 * 2.向后兼容
 * 3.层次清晰
 * 4.性能较高，
 * 用泛型Java（GJ）编写的代码可以为Java编译器和虚拟机带来更多类型信息，
 * 这些信息对Java程序做进一步的优化提供条件
 */
package Genericity;

import java.lang.reflect.*;

public class Genericity_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Gen<Bird> gen1=new Gen<Bird>(new Bird());
		Gen<Integer> gen2=new Gen<Integer>(1);
		gen1.showTypeName();
		gen2.showTypeName();
	}

}
//定义个Bird
class Bird{
	
	public void test1(){
		System.out.println("Aaa");
	}
	public void count(int a,int b){
		System.out.println(a+b);
	}
}

//泛型类
class Gen<T>{
	private T o;
	
	//构造函数
	public Gen(T o){
		this.o=o;
	}
	
	//得到T的类型名称
	public void showTypeName(){
		System.out.println("Type: "+o.getClass().getName());
		//通过反射机制，可以得到T这个类型的很多信息（比如得到成员函数名）
		Method m[]=o.getClass().getDeclaredMethods();
		//打印
		for(int i=0;i<m.length;i++){
			System.out.println(m[i].getName());
		}
	}
}