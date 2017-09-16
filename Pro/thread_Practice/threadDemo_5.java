/*
 * ͬ��������Эͬ��������Ԥ�����Ⱥ����������С�
 * ���̡��߳�ͬ���������Ϊ���̻��߳�A��Bһ����ϣ�
 * Aִ�е�һ���̶�ʱҪ����B��ĳ�����������ͣ������ʾ��B���У�
 * B����ִ�У��ٽ������A��A�ټ���������
 * 
 * �ڶ��̱߳�����棬һЩ�������ݲ���������߳�ͬʱ���ʣ�
 * ��ʱ��ʹ��ͬ�����ʼ�������֤�������κ�ʱ�̣������һ���̷߳��ʣ��Ա�֤���ݵ������ԡ�
 * 
 * java�����߳�ͬ��
 * synchronized(Object){����Ҫͬ���Ĵ���}
 * 
 * ����ԭ��
 * synchronized(Object��־λΪ1)->Object��־λ��Ϊ0�������߳���ʱ�������ó�CPU��Դ��->
 * ִ����Ҫͬ���Ĵ���飨Object��־λ��Ϊ1��ȡ�������߳�������->
 * ִ����һ���̣߳�Object��־λΪ��Ϊ0��
 * 
 * ��⣺
 * java�������͵Ķ�����һ����־λ���ñ�־λ����0��1����״̬���ʼ״̬Ϊ1��
 * ��ĳ���߳�ִ����synchronized(Object)����Object����ı�־λ��Ϊ0״̬��
 * ����־λΪ0״̬�������Ѿ���������߳�����ִ��synchronized�����Ĵ��룬
 * ��ô����߳̽���ʱ�������ó�CPU��Դ��ֱ��������߳�ִ������ص�ͬ������
 * ����ֱ��ִ��������synchronized����еĴ����󣩣�����Object����ı�־λ�лص�1״̬��
 * ����̵߳������ͱ�ȡ�����߳��ܼ������У����߳��ֽ�Object�ı�־λ��Ϊ0״̬��
 * ��ֹ�������߳��ٽ�����ص�ͬ��������С�
 * ��ע����־λ״̬�޸���ϵͳ�Զ���ɣ�
 * 
 * ����ж���߳���ȴ�ͬһ������ı�־λ����������״̬�ǣ�
 * ���ö���ı�־λ�ָ���1״̬ʱ��ֻ����һ���߳��ܹ�����ͬ������ִ�У�
 * �����߳��Դ�������״̬��
 */
package thread_Practice;

public class threadDemo_5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����������Ʊ����
		TicketWindow tw1 = new TicketWindow();
		// TicketWindow tw2=new TicketWindow();
		// TicketWindow tw3=new TicketWindow();

		// �����߳�ͬʱ����ͬһ����Ʊ������
		Thread t1 = new Thread(tw1);
		Thread t2 = new Thread(tw1);
		Thread t3 = new Thread(tw1);

		t1.start();
		t2.start();
		t3.start();
	}

}

// ��Ʊ������
class TicketWindow implements Runnable {

	// һ����ǧ��Ʊ
	private int nums = 2000;
	private Lock lock=new Lock();
	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			//��֤��ԭ���ԡ�ͬ������顿(�������ڲ�����ͬһ��Ʊ)
			/*
			 * lock����Բ�Ҫ������this��synchronized(�κζ���)���������̲߳���
			 * synchronized�����еĶ���Ĺ������ƿ��Ź�������֤�������ڲ�����ͬһ��Ʊ
			 * ���������磬ֻ��һ�������������ж����Ҫ�ã���һ����ʱ�������˱������
			 */
			synchronized (lock) {
				
				// ���ж��Ƿ���Ʊ
				if (nums > 0) {

					// ��ʾ��Ʊ��Ϣ
					// Thread.currentThread().getName()(��ǰ�̵߳�����)
					System.out.println(Thread.currentThread().getName() + " In the sale of the " + nums + " ticket");

					// ���ó�Ʊ�ٶ�1s
					try {
						Thread.sleep(1000);
					} catch (Exception e) {
						// TODO: handle exception
					}

					nums--;
				} else {

					// ��Ʊ����
					break;
				}
			}
		}
	}
}

//����
class Lock{
	
	
}
