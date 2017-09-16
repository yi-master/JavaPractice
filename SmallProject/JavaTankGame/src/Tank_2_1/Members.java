package Tank_2_1;

public class Members {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

// 子弹类
class Shot implements Runnable {
	int x;
	int y;
	int direct;
	int speed = 1;// 子弹初始速度为1
	// 子弹是否还活着
	boolean isLive = true;

	public Shot(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {

				Thread.sleep(50);
			} catch (Exception e) {
				e.printStackTrace();
			}
			switch (direct) {

			case 0:// 子弹向上
				y -= speed;
				break;
			case 1:// 子弹向右
				x += speed;
				break;
			case 2:// 子弹向下
				y += speed;
				break;
			case 3:// 子弹向左
				x -= speed;
				break;
			}
			// 输出子弹坐标
			System.out.println("shot: x=" + x + "  y=" + y);

			// 子弹线程死亡
			// 判断子弹是否碰到边缘
			if (x < 0 || x > 400 || y < 0 || y > 300) {
				this.isLive = false;
				break;
			}
		}
	}
}

// 坦克类
class Tank {

	// 坦克的横坐标
	int x = 0;
	// 坦克的纵坐标
	int y = 0;
	// 坦克的方向(0表示向上，1表示右，2表示下，3表示左)
	int direct = 0;// 初始方向向上
	// 坦克的速度(初始速度为1)
	int speed = 1;
	// 坦克的类型
	int type = 0;

	public int getType() {
		return type;
	}

	public void setType(int type) {
		this.type = type;
	}

	public int getSpeed() {
		return speed;
	}

	public void setSpeed(int speed) {
		this.speed = speed;
	}

	public int getDirect() {
		return direct;
	}

	public void setDirect(int direct) {
		this.direct = direct;
	}

	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}

	public Tank(int x, int y) {
		this.x = x;
		this.y = y;
	}
}

// 我的坦克
class Hero extends Tank {

	// 子弹
	Shot s = null;

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	// 发射子弹
	public void shotEnemy() {

		switch (this.direct) {

		case 0:// 坦克方向朝上
			s = new Shot(x + 10, y, 0);
			break;
		case 1:// 坦克方向朝右
			s = new Shot(x + 30, y + 10, 1);
			break;
		case 2:// 坦克方向朝下
			s = new Shot(x + 10, y + 30, 2);
			break;
		case 3:// 坦克方向朝左
			s = new Shot(x, y + 10, 3);
			break;
		}
		// 启动子弹线程
		Thread t = new Thread(s);
		t.start();
	}

	// 坦克向上移动
	public void moveUp() {
		y -= speed;
	}

	// 坦克向右移动
	public void moveRight() {
		x += speed;
	}

	// 坦克向下移动
	public void moveDown() {
		y += speed;
	}

	// 坦克向左移动
	public void moveLeft() {
		x -= speed;
	}
}

// 敌人的坦克
class EnemyTank extends Tank {

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

}
