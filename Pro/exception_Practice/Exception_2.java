/*
 * 抛出的异常是层层递进的
 * throws 异常 这种方式比较消耗资源
 */
package exception_Practice;

import java.io.FileReader;

public class Exception_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Father father=new Father();
		father.test1();
	}

}

class Father {
	private Son son =null;
	
	//构造函数
	public Father(){
		son =new Son();
	}
	
	public void test1() {
		System.out.println("test1");
		try {//管理Son类抛出异常
			son.test2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Father is dealing with this exception");
			e.printStackTrace();
		}
	}
}

class Son {
	public void test2() throws Exception {//抛出异常
		FileReader fr = null;
		fr = new FileReader("d:/eclipse workspace/test1.txt");
	}
}