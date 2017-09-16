/*
 * final
 * 概念
 * 可以修饰变量或方法
 * 1.当不希望父类的某个方法被子类覆盖（override）时，可以使用final关键字修饰
 * 2.当不希望类的某个变量的值被修改，可以用final修饰
 * 如果一个变量是final，则在定义时必须赋初始值，否则会编译错误
 * 3.当不希望某个类被继承时，可以用final修饰
 * 
 * 注意事项
 * 1.final修饰的变量又叫 常量，一般用xx_xx_xx来命名
 * 2.final修饰的变量在定义时，必须赋值，并且以后不能再赋值
 * 
 * 什么时候用
 * 1.因为安全的考虑，类的某个方法不允许被修改
 * 2.类不会被其他的继承
 * 3.某些变量值是固定不变的，如圆周率π（3.1415926）
 */
package Object_oriented;

public class UseFinal {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Aa aa=new Aa();
		aa.show();
	}

}
//final修饰类表示该类不能被继承
final class Aa{
	int a=0;//如果不赋初始值，a=0
	
	final float rate=3.1415926f;

	//给方法用final修饰，则表示不能被修改，不能被覆盖
	final public void sendMessage(){
		System.out.println("Send message");
	}
	public void show(){
		System.out.println(rate);
	}
}

