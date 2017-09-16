package Tank_3;

import java.util.Vector;

public class Members {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
//���ô��ڵĴ�С
class WindowSize{
	
	int wide=400,high=300;

	public int getWide() {
		return wide;
	}

	public void setWide(int wide) {
		this.wide = wide;
	}

	public int getHigh() {
		return high;
	}

	public void setHigh(int high) {
		this.high = high;
	}

}
// �ӵ���ը��
class Bomb {

	// ����ը����
	int x, y;
	int life = 9;// �ӵ���ը��������������ͼƬ���
	boolean isLive = true;

	public Bomb(int x, int y) {

		this.x = x;
		this.y = y;
	}

	// ��������ֵ
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}
	}
}

// �ӵ���
class Shot implements Runnable {
	int x;
	int y;
	int direct;
	int speed = 2;// �ӵ���ʼ�ٶ�Ϊ1
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
	//�ж�̹���Ƿ���
	boolean isLive=true;

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
	Vector<Shot> ss = new Vector<Shot>();
	Shot s = null;

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	// �����ӵ�
	public void shotEnemy() {

		switch (this.direct) {

		case 0:// ̹�˷�����
				// ����һ���ӵ�
			s = new Shot(x + 10, y, 0);
			// ���ӵ���������
			ss.add(s);
			break;
		case 1:// ̹�˷�����
			s = new Shot(x + 30, y + 10, 1);
			ss.add(s);
			break;
		case 2:// ̹�˷�����
			s = new Shot(x + 10, y + 30, 2);
			ss.add(s);
			break;
		case 3:// ̹�˷�����
			s = new Shot(x, y + 10, 3);
			ss.add(s);
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

// ���˵�̹�ˣ�����̹�������̣߳�
class EnemyTank extends Tank implements Runnable {

	WindowSize ws=null;
	
	int times=0;//�����ۻ��˱���
	//������������ŵ��˵��ӵ�
	Vector<Shot> ss=new Vector<Shot>();
	//��������ӵ����ڸոմ�������̹�˺͵���̹���ӵ�������
	
	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		ws=new WindowSize();
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub

		while (true) {

			switch (this.direct) {

			case 0:// ����̹������������
				for (int i = 0; i < 30; i++) {
					if (y > 0) {//��ֹ̹��Խ�磬��֤̹����ָ����Χ���ƶ�
						y -= speed;
					}
					try {
						Thread.sleep(50);//30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 1:// ����̹������������
				for (int i = 0; i < 30; i++) {
					if (x < ws.getWide()-30) {//��ֹ̹��Խ�磬��֤̹����ָ����Χ���ƶ�
						x += speed;
					}
					try {
						Thread.sleep(50);//30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 2:// ����̹������������
				for (int i = 0; i < 30; i++) {
					if (y < ws.getHigh()-30) {//��ֹ̹��Խ�磬��֤̹����ָ����Χ���ƶ�
						y += speed;
					}
					try {
						Thread.sleep(50);//30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 3:// ����̹������������
				for (int i = 0; i < 30; i++) {
					if (x > 0) {//��ֹ̹��Խ�磬��֤̹����ָ����Χ���ƶ�
						x -= speed;
					}
					try {
						Thread.sleep(50);//30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			}
			
			this.times++;//ÿ��1.5s��1
			
			//�Ƿ���Ҫ��̹�˼����µ��ӵ�
			if(times%2==0){//ÿ��1.5s��һ���ӵ�
				
				//�ж��ӵ��Ƿ����
				if(isLive==true){
					if(ss.size()<5){//�з��ӵ��������5��
					
						Shot s=null;
						//û���ӵ�(���)
						switch (direct) {

						case 0:// ̹�˷�����
								// ����һ���ӵ�
							s = new Shot(x + 10, y, 0);
							// ���ӵ���������
							ss.add(s);
							break;
						case 1:// ̹�˷�����
							s = new Shot(x + 30, y + 10, 1);
							ss.add(s);
							break;
						case 2:// ̹�˷�����
							s = new Shot(x + 10, y + 30, 2);
							ss.add(s);
							break;
						case 3:// ̹�˷�����
							s = new Shot(x, y + 10, 3);
							ss.add(s);
							break;
						}
						
						//�����ӵ��߳�
						Thread t=new Thread(s);
						t.start();
						
					}
				}
			}
			// ��̹���������һ���µķ���
			// this.direct=(int)(Math.random()*10)%4;
			this.direct = (int) (Math.random() * 4);

			// �жϵ���̹���Ƿ�����
			if (this.isLive == false) {
				// ��̹���������˳��߳�
				break;
			}
			
		}
	}

}
