/*
 * Runnable接口实现线程
 * 1.实现Runnable接口
 * 类名 implements Runnable
 * 2.重写run()函数
 * 3.创建需要启动线程的对象
 * 4.创建Thread对象
 * Thread 线程对象名 = new Thread(需要启动线程的对象名);
 * 如：	Dog implements Runnable
 * 		Dog dog = new Dog();
 *		Thread t = new Thread(dog);
 * 5.启动线程
 * 线程对象名.start();		
 */
/*
 * 继承Thread和实现Runnable的区别
 * 1.使用继承Thread时只需new一个实例出来，调用start()方法即可启动一个线程
 * 如：  类名 extends Thread
 * 		类名 对象名 = new 类名();
 * 		对象名.start();
 * 2.在使用Runnable时需要先new一个实现Runnable的实例，之后用Thread调用
 * 如：  Dog implements Runnable
 * 		Dog dog = new Dog();
 *		Thread t = new Thread(dog);
 *		t.start();
 *
 *尽可能使用Runnable接口方式来创建线程
 */
package thread_Practice;

public class threadDemo_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dog dog = new Dog();
		// 创建一个Thread对象
		Thread t = new Thread(dog);
		t.start();
	}

}

// 2.用Runnable接口(implements)，重写run函数，来开发线程
class Dog implements Runnable {

	int times = 0;

	// 重写run函数
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			// 休眠一秒
			// sleep会让线程进入到blocked（阻塞）状态，并释放资源
			try {
				Thread.sleep(1000);// 1000表示1000ms=1s
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("implements Runnable " + times);
			if (times == 10) {

				// 退出
				break;
			}
		}
	}
}