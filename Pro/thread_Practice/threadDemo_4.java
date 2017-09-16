package thread_Practice;

/*
 * 关于线程的一些注意事项
 * 同一个线程不能启动两次（线程对象只能启动一个线程）
 */
public class threadDemo_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//不能启动两次(两次启动对象相同都是cow1)
//		Cow cow1=new Cow();
//		cow1.start();
//		cow1.start();
		
		//可以启动(两次启动对象不同，分别是t,t2，若同为t或t2就会出错)
		Ox ox1=new Ox();
		Thread t=new Thread(ox1);
		Thread t2=new Thread(ox1);
		t.start();
		t2.start();
	}

}


//奶牛类
class Cow extends Thread{
	
	public void run(){
		
		System.out.println("cat");
	}
}

//公牛类
class Ox implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("dog");
	}
}