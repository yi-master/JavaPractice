package thread_Practice;

/*
 * 两个线程同时运行实现
 */

public class threadDemo_3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Pig pig = new Pig(10);
		Bird bird = new Bird(10);
		Thread t1 = new Thread(pig);
		Thread t2 = new Thread(bird);
		t1.start();
		t2.start();
	}

}

// 线程1
class Pig implements Runnable {

	int n = 0;
	int times = 0;

	public Pig(int n){
		
		this.n=n;
	}
	
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			try {

				Thread.sleep(1000);
			} catch (Exception e) {

			}

			times++;
			System.out.println("The Thread One(pig) " + times);
			if (times == n) {

				break;
			}
		}
	}

}

// 算数的鸟(线程2)
class Bird implements Runnable {

	int n = 0;
	int times = 0;
	int result = 0;

	public Bird(int n) {

		this.n = n;
	}

	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			try {
				Thread.sleep(500);
			} catch (Exception e) {

			}

			result += (++times);
			System.out.println("The thread two(bird) " + times+"\nThe now result is "+result);
			if (times == n) {

				System.out.println("The result is " + result);
				break;
			}
		}
	}

}