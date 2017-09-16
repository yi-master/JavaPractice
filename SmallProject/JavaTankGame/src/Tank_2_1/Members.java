package Tank_2_1;

public class Members {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

// �ӵ���
class Shot implements Runnable {
	int x;
	int y;
	int direct;
	int speed = 1;// �ӵ���ʼ�ٶ�Ϊ1
	// �ӵ��Ƿ񻹻���
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

			case 0:// �ӵ�����
				y -= speed;
				break;
			case 1:// �ӵ�����
				x += speed;
				break;
			case 2:// �ӵ�����
				y += speed;
				break;
			case 3:// �ӵ�����
				x -= speed;
				break;
			}
			// ����ӵ�����
			System.out.println("shot: x=" + x + "  y=" + y);

			// �ӵ��߳�����
			// �ж��ӵ��Ƿ�������Ե
			if (x < 0 || x > 400 || y < 0 || y > 300) {
				this.isLive = false;
				break;
			}
		}
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
	// ̹�˵�����
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

// �ҵ�̹��
class Hero extends Tank {

	// �ӵ�
	Shot s = null;

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	// �����ӵ�
	public void shotEnemy() {

		switch (this.direct) {

		case 0:// ̹�˷�����
			s = new Shot(x + 10, y, 0);
			break;
		case 1:// ̹�˷�����
			s = new Shot(x + 30, y + 10, 1);
			break;
		case 2:// ̹�˷�����
			s = new Shot(x + 10, y + 30, 2);
			break;
		case 3:// ̹�˷�����
			s = new Shot(x, y + 10, 3);
			break;
		}
		// �����ӵ��߳�
		Thread t = new Thread(s);
		t.start();
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

// ���˵�̹��
class EnemyTank extends Tank {

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

}
