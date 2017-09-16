/*
 * �ӿ�ʵ�ֶ�̬
 * ��һ����������ͬ����������������壨Ҳ���Ƕ�����ࣩ��������������
 * ǰ�ڰ󶨣���̬�󶨣����ڳ�������֮ǰ���а󶨣��ɱ����������ӳ���ʵ�֣����ڱ���ʱ����ȷ�������ͣ�
 * ���ڰ󶨣���̬�󶨣���������ʱ���ݶ�������ͽ��а󶨣��з������û���ʵ�֣���������ʱ����ȷ�������ͣ�
 */
package Interface;

public class CarShop {

	// �۳�����
	private int money = 0;

	// ����һ����
	public void sellCar(Car car) {
		System.out.println(car.getName() + " " + car.getPrice());
		// �����������ۼ۵�����
		money += car.getPrice();
	}

	// �۳�������
	public int getMoney() {
		return money;
	}

	public static void main(String[] args) {
		CarShop aCS = new CarShop();
		// ����һ������
		aCS.sellCar(new BMW());
		// ����һ������QQ
		aCS.sellCar(new CheryQQ());
		System.out.println("Gross income:"+aCS.getMoney());
	}
}

// �����ӿ�
interface Car {
	String getName();

	int getPrice();
}

// ����
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

// ����QQ
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
