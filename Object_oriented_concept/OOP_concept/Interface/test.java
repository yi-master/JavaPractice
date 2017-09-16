/*
 * 接口
 * 解决之道
 * 接口就是给出一些没有内容的方法，封装到一起，
 * 到某个类要使用时，根据具体情况吧这些方法写出来
 * 语法：
 * class 类名 implements 接口{
 * 		方法;
 * 		变量;
 * }
 * 
 * 注意事项
 * 1.接口不能被实例化（即创建对象实例化）
 * 2.接口中的所有方法（如抽象方法、非抽象方法等）都不能有主体（即不能有大括号）
 * 3.一个类可以实现多个接口
 * 4.接口中可以有变量，但不能用private和protected修饰
 * 5.接口中的变量本质上是静态的(static)，而且是final，不管你是否加static
 * 6.在Java开发中，常把经常用的变量定义在接口中，作为全局变量使用
 * 访问形式：接口名.变量名
 * 7.一个接口不能继承其它的类，但是可以继承别的接口
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

interface Usb3 {// 接口继承接口

	public void begin();
}

interface Usb2 extends Usb3 {

	public void run();
}

interface Usb {

	int a = 1;

	public void start();// 开始

	public void stop();// 停止
}

// 编写照相机类，并实现（implements）Usb接口
// 当一个类实现了一个接口，就要求该类把这个接口的所有方法，全部实现
// 若实现（implements）的接口继承(extends)了其他接口，则其他接口的所有方法仍然要在该类中实现
// 若没有实现，则不能使用（implements）
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

// 编写手机类
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

// 电脑类
class Computer {

	// 使用Usb接口
	public void useUsb(Usb usb) {

		usb.start();
		usb.stop();
	}
}