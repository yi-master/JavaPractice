package Tank_2;

public class Members {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

// ̹����
class Tank {

	// ̹�˵ĺ�����
	int x = 0;
	// ̹�˵�������
	int y = 0;
	// ̹�˵ķ���(0��ʾ���ϣ�1��ʾ�ң�2��ʾ�£�3��ʾ��)
	int direct = 0;// ��ʼ��������
	// ̹�˵��ٶ�(��ʼ�ٶ�Ϊ1)
	int speed = 1;
	//̹�˵�����
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

// �ҵ�̹��
class Hero extends Tank {

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	// ̹�������ƶ�
	public void moveUp() {
		y -= speed;
	}

	// ̹�������ƶ�
	public void moveRight() {
		x += speed;
	}

	// ̹�������ƶ�
	public void moveDown() {
		y += speed;
	}

	// ̹�������ƶ�
	public void moveLeft() {
		x -= speed;
	}
}

//���˵�̹��
class EnemyTank extends Tank{

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
}
