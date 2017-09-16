/*
 * 方法重装
 * 概论
 * 方法重载就是类的同一种功能的多种实现方式
 * 到底使用哪种方式，取决于调用者给出的参数
 * 
 * 注意事项
 * 方法名相同
 * 方法的参数类型，个数，顺序至少有一项不同
 * 方法返回类型可以不同(只是返回类型不同不能够过程重载)
 * 方法的修饰符可以不同(只是控制访问修饰符号不一样，不能构成重载)
 */
package Inherit;

public class OverLoad {

	public static void main(String[] args) {
	
		Abc abc1=new Abc();
		System.out.println(abc1.getMax(0.4f, 3.99f));
	}

}

//多种重载方式
class Abc{
	//返回较大整数
	public int getMax(int i, int j){
		if(i>j){
			return i;
		}else{
			return j;
		}
	}
	
	public float getMax(float i,float j){
		if(i>j){
			return i;
		}else{
			return j;
		}
	}
	
	public float getMax(double i,float j){
		if(i>j){
			return (float)i;
		}else{
			return (float)j;
		}
	}
	
	public float getMax(float j,double i){
		if(i>j){
			return (float)i;
		}else{
			return (float)j;
		}
	}
	
//	//只是返回类型不同，不能构成重载
//	public double getMax(float j,double i){
//		if(i>j){
//			return i;
//		}else{
//			return j;
//		}
//	}
//	//只是控制访问修饰符号不一样，不能构成重载
//	protected float getMax(float j,double i){
//		if(i>j){
//			return (float)i;
//		}else{
//			return (float)j;
//		}
//	}
}