/*
 * �׳����쳣�ǲ��ݽ���
 * throws �쳣 ���ַ�ʽ�Ƚ�������Դ
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
	
	//���캯��
	public Father(){
		son =new Son();
	}
	
	public void test1() {
		System.out.println("test1");
		try {//����Son���׳��쳣
			son.test2();
		} catch (Exception e) {
			// TODO Auto-generated catch block
			System.out.println("Father is dealing with this exception");
			e.printStackTrace();
		}
	}
}

class Son {
	public void test2() throws Exception {//�׳��쳣
		FileReader fr = null;
		fr = new FileReader("d:/eclipse workspace/test1.txt");
	}
}