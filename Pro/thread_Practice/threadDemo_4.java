package thread_Practice;

/*
 * �����̵߳�һЩע������
 * ͬһ���̲߳����������Σ��̶߳���ֻ������һ���̣߳�
 */
public class threadDemo_4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//������������(��������������ͬ����cow1)
//		Cow cow1=new Cow();
//		cow1.start();
//		cow1.start();
		
		//��������(������������ͬ���ֱ���t,t2����ͬΪt��t2�ͻ����)
		Ox ox1=new Ox();
		Thread t=new Thread(ox1);
		Thread t2=new Thread(ox1);
		t.start();
		t2.start();
	}

}


//��ţ��
class Cow extends Thread{
	
	public void run(){
		
		System.out.println("cat");
	}
}

//��ţ��
class Ox implements Runnable{

	@Override
	public void run() {
		// TODO Auto-generated method stub
		System.out.println("dog");
	}
}