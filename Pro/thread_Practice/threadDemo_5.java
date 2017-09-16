/*
 * 同步：就是协同步调，按预定的先后次序进行运行。
 * 进程、线程同步，可理解为进程或线程A和B一块配合，
 * A执行到一定程度时要依靠B的某个结果，于是停下来，示意B运行；
 * B依言执行，再将结果给A；A再继续操作。
 * 
 * 在多线程编程里面，一些敏感数据不允许被多个线程同时访问，
 * 此时就使用同步访问技术，保证数据在任何时刻，最多有一个线程访问，以保证数据的完整性。
 * 
 * java处理线程同步
 * synchronized(Object){你需要同步的代码}
 * 
 * 工作原理：
 * synchronized(Object标志位为1)->Object标志位变为0（其它线程暂时阻塞，让出CPU资源）->
 * 执行需要同步的代码块（Object标志位变为1，取消其它线程阻塞）->
 * 执行下一个线程（Object标志位为变为0）
 * 
 * 详解：
 * java任意类型的对象都有一个标志位，该标志位具有0、1两种状态其初始状态为1，
 * 当某个线程执行了synchronized(Object)语句后，Object对象的标志位变为0状态，
 * 当标志位为0状态，表明已经有另外的线程正在执行synchronized包括的代码，
 * 那么这个线程将暂时阻塞，让出CPU资源，直到另外的线程执行完相关的同步代码
 * （即直到执行完整个synchronized语句中的代码块后），并将Object对象的标志位有回到1状态。
 * 这个线程的阻塞就被取消，线程能继续运行，该线程又将Object的标志位变为0状态，
 * 防止其它的线程再进入相关的同步代码块中。
 * （注：标志位状态修改由系统自动完成）
 * 
 * 如果有多个线程因等待同一个对象的标志位而处于阻塞状态是，
 * 当该对象的标志位恢复到1状态时，只会有一个线程能够进入同步代码执行，
 * 其他线程仍处于阻塞状态。
 */
package thread_Practice;

public class threadDemo_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 定义三个售票窗口
		TicketWindow tw1 = new TicketWindow();
		// TicketWindow tw2=new TicketWindow();
		// TicketWindow tw3=new TicketWindow();

		// 三个线程同时启动同一个售票窗口类
		Thread t1 = new Thread(tw1);
		Thread t2 = new Thread(tw1);
		Thread t3 = new Thread(tw1);

		t1.start();
		t2.start();
		t3.start();
	}

}

// 售票窗口类
class TicketWindow implements Runnable {

	// 一共两千张票
	private int nums = 2000;
	private Lock lock=new Lock();
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			//保证其原子性【同步代码块】(三个窗口不会买同一张票)
			/*
			 * lock类可以不要，换成this，synchronized(任何对象)用来控制线程并发
			 * synchronized括号中的对象的功能类似看门狗，即保证三个窗口不会买同一张票
			 * 其作用例如，只有一个厕所，但是有多个人要用，当一个用时，其他人必须等着
			 */
			synchronized (lock) {
				
				// 先判断是否还有票
				if (nums > 0) {

					// 显示售票信息
					// Thread.currentThread().getName()(当前线程的名字)
					System.out.println(Thread.currentThread().getName() + " In the sale of the " + nums + " ticket");

					// 设置出票速度1s
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}

					nums--;
				} else {

					// 售票结束
					break;
				}
			}
		}
	}
}

//锁类
class Lock{
	
	
}
