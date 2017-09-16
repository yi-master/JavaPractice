/*
 * ���̣������е�Ӧ�ó���ÿ�����̶������Լ������ĵ�ַ�ռ䣨�ڴ�ռ䣩
 * �̣߳��ǽ����е�һ��ʵ��
 * 1.�������Ľ���
 * 2.û�ж����ĵ�ַ�ռ䣨�ڴ�ռ䣩
 * 3.�߳����ɽ��̴���
 * 4.һ�����̿���ӵ�ж���̣߳������̱߳�̣�
 * 5.���м���״̬��
 * 1).�½�״̬(new)
 * 2).����(������)״̬(Runnable),����.start();
 * 3).����״̬(Running)
 * 4).����״̬(Blocked)
 * 5).����״̬(Dead)
 */
/*
 * extends�̳з�ʽʵ���߳�
 * 1.�̳�(extends)Thread��
 * ���� extends Thread
 * 2.��дrun()����
 * 3.������Ҫ�����̵߳Ķ���
 * ���� ������ = new ����();
 * 4.�����߳�
 * ������.start();
 */
package thread_Practice;

/*
 * Java�ǵ��̳м̳�
 * Java��һ����Ҫ�����߳���ʹ�������ַ���
 * 1.ͨ���̳�(extends)Thread�࣬��дrun�������������߳�
 * 2.��Runnable�ӿڣ���дrun�������������߳�
 */

public class threadDemo_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����һ��Cat����
		Cat cat = new Cat();
		// �����̻߳ᵼ��run��������
		cat.start();//���̴߳��ڿ�����״̬
	}

}

// 1.ͨ���̳�(extends)Thread�࣬��дrun�������������߳�
class Cat extends Thread {

	int times = 0;

	// ��дrun����
	public void run() {
		
		//whileѭ�������߳�״̬��������->����->����->�����У�
		while (true) {

			// ����һ��
			// sleep�����߳̽��뵽blocked��������״̬�����ͷ���Դ(�ͷ�CPU���ڴ����Դ)
			try {
				Thread.sleep(1000);// 1000��ʾ1000ms=1s
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			times++;
			System.out.println("extends Thread " + times);
			if (times == 10) {

				// �˳�(�߳�����)
				break;
			}
		}
	}

}