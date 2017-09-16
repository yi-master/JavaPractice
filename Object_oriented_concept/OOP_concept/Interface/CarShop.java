/*
 * 接口实现多态
 * 将一个方法调用同这个方法所属的主体（也就是对象或类）关联起来叫做绑定
 * 前期绑定（静态绑定）：在程序运行之前进行绑定，由编译器和连接程序实现（即在编译时可以确定的类型）
 * 后期绑定（动态绑定）：在运行时根据对象的类型进行绑定，有方法调用机制实现（即在运行时才能确定的类型）
 */
package Interface;

public class CarShop {

	// 售车收入
	private int money = 0;

	// 卖出一部车
	public void sellCar(Car car) {
		System.out.println(car.getName() + " " + car.getPrice());
		// 增加卖出车售价的收入
		money += car.getPrice();
	}

	// 售车总收入
	public int getMoney() {
		return money;
	}

	public static void main(String[] args) {
		CarShop aCS = new CarShop();
		// 卖出一辆宝马
		aCS.sellCar(new BMW());
		// 卖出一辆奇瑞QQ
		aCS.sellCar(new CheryQQ());
		System.out.println("Gross income:"+aCS.getMoney());
	}
}

// 汽车接口
interface Car {
	String getName();

	int getPrice();
}

// 宝马车
class BMW implements Car {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "BMW";
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 3000000;
	}

}

// 奇瑞QQ
class CheryQQ implements Car {

	@Override
	public String getName() {
		// TODO Auto-generated method stub
		return "CheryQQ";
	}

	@Override
	public int getPrice() {
		// TODO Auto-generated method stub
		return 200000;
	}

}
