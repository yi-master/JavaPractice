/*
 * �ӿ�
 * ���֮��
 * �ӿھ��Ǹ���һЩû�����ݵķ�������װ��һ��
 * ��ĳ����Ҫʹ��ʱ�����ݾ����������Щ����д����
 * �﷨��
 * class ���� implements �ӿ�{
 * 		����;
 * 		����;
 * }
 * 
 * ע������
 * 1.�ӿڲ��ܱ�ʵ����������������ʵ������
 * 2.�ӿ��е����з���������󷽷����ǳ��󷽷��ȣ������������壨�������д����ţ�
 * 3.һ�������ʵ�ֶ���ӿ�
 * 4.�ӿ��п����б�������������private��protected����
 * 5.�ӿ��еı����������Ǿ�̬��(static)��������final���������Ƿ��static
 * 6.��Java�����У����Ѿ����õı��������ڽӿ��У���Ϊȫ�ֱ���ʹ��
 * ������ʽ���ӿ���.������
 * 7.һ���ӿڲ��ܼ̳��������࣬���ǿ��Լ̳б�Ľӿ�
 */
package Interface;

public class test {

	public static void main(String[] args) {

		Computer computer1 = new Computer();
		Camera camera1 = new Camera();
		Phone phone1 = new Phone();
		computer1.useUsb(camera1);
		computer1.useUsb(phone1);
		System.out.println(Usb.a);
	}
}

interface Usb3 {// �ӿڼ̳нӿ�

	public void begin();
}

interface Usb2 extends Usb3 {

	public void run();
}

interface Usb {

	int a = 1;

	public void start();// ��ʼ

	public void stop();// ֹͣ
}

// ��д������࣬��ʵ�֣�implements��Usb�ӿ�
// ��һ����ʵ����һ���ӿڣ���Ҫ����������ӿڵ����з�����ȫ��ʵ��
// ��ʵ�֣�implements���Ľӿڼ̳�(extends)�������ӿڣ��������ӿڵ����з�����ȻҪ�ڸ�����ʵ��
// ��û��ʵ�֣�����ʹ�ã�implements��
class Camera implements Usb, Usb2 {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Camera Start");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Camera Stop");
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

	}

	@Override
	public void begin() {
		// TODO Auto-generated method stub

	}

}

// ��д�ֻ���
class Phone implements Usb {

	@Override
	public void start() {
		// TODO Auto-generated method stub
		System.out.println("Phone Start");
	}

	@Override
	public void stop() {
		// TODO Auto-generated method stub
		System.out.println("Phone Stop");
	}
}

// ������
class Computer {

	// ʹ��Usb�ӿ�
	public void useUsb(Usb usb) {

		usb.start();
		usb.stop();
	}
}