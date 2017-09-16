/*
 * Runnable�ӿ�ʵ���߳�
 * 1.ʵ��Runnable�ӿ�
 * ���� implements Runnable
 * 2.��дrun()����
 * 3.������Ҫ�����̵߳Ķ���
 * 4.����Thread����
 * Thread �̶߳����� = new Thread(��Ҫ�����̵߳Ķ�����);
 * �磺	Dog implements Runnable
 * 		Dog dog = new Dog();
 *		Thread t = new Thread(dog);
 * 5.�����߳�
 * �̶߳�����.start();		
 */
/*
 * �̳�Thread��ʵ��Runnable������
 * 1.ʹ�ü̳�Threadʱֻ��newһ��ʵ������������start()������������һ���߳�
 * �磺  ���� extends Thread
 * 		���� ������ = new ����();
 * 		������.start();
 * 2.��ʹ��Runnableʱ��Ҫ��newһ��ʵ��Runnable��ʵ����֮����Thread����
 * �磺  Dog implements Runnable
 * 		Dog dog = new Dog();
 *		Thread t = new Thread(dog);
 *		t.start();
 *
 *������ʹ��Runnable�ӿڷ�ʽ�������߳�
 */
package thread_Practice;

public class threadDemo_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Dog dog = new Dog();
		// ����һ��Thread����
		Thread t = new Thread(dog);
		t.start();
	}

}

// 2.��Runnable�ӿ�(implements)����дrun�������������߳�
class Dog implements Runnable {

	int times = 0;

	// ��дrun����
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			// ����һ��
			// sleep�����߳̽��뵽blocked��������״̬�����ͷ���Դ
			try {
				Thread.sleep(1000);// 1000��ʾ1000ms=1s
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("implements Runnable " + times);
			if (times == 10) {

				// �˳�
				break;
			}
		}
	}
}