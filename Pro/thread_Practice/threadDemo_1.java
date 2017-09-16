/*
 * 进程：运行中的应用程序，每个进程都会有自己独立的地址空间（内存空间）
 * 线程：是进程中的一个实体
 * 1.轻量级的进程
 * 2.没有独立的地址空间（内存空间）
 * 3.线程是由进程创建
 * 4.一个进程可以拥有多个线程（即多线程编程）
 * 5.线有几种状态：
 * 1).新建状态(new)
 * 2).就绪(可运行)状态(Runnable),对象.start();
 * 3).运行状态(Running)
 * 4).阻塞状态(Blocked)
 * 5).死亡状态(Dead)
 */
/*
 * extends继承方式实现线程
 * 1.继承(extends)Thread类
 * 类名 extends Thread
 * 2.重写run()函数
 * 3.创建需要启动线程的对象
 * 类名 对象名 = new 类名();
 * 4.启动线程
 * 对象名.start();
 */
package thread_Practice;

/*
 * Java是单继承继承
 * Java中一个类要当作线程来使用有两种方法
 * 1.通过继承(extends)Thread类，重写run函数，来开发线程
 * 2.用Runnable接口，重写run函数，来开发线程
 */

public class threadDemo_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 创建一个Cat对象
		Cat cat = new Cat();
		// 启动线程会导致run函数运行
		cat.start();//让线程处于可运行状态
	}

}

// 1.通过继承(extends)Thread类，重写run函数，来开发线程
class Cat extends Thread {

	int times = 0;

	// 重写run函数
	public void run() {
		
		//while循环控制线程状态（可运行->运行->阻塞->可运行）
		while (true) {

			// 休眠一秒
			// sleep会让线程进入到blocked（阻塞）状态，并释放资源(释放CPU、内存等资源)
			try {
				Thread.sleep(1000);// 1000表示1000ms=1s
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("extends Thread " + times);
			if (times == 10) {

				// 退出(线程死亡)
				break;
			}
		}
	}

}