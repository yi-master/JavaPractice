package Tank_2;

public class Members {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

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
	//坦克的类型
	int type=0;
	
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

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
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

//敌人的坦克
class EnemyTank extends Tank{

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
}
