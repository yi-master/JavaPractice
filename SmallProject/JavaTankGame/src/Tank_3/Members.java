package Tank_3;

import java.util.Vector;

public class Members {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
//设置窗口的大小
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
// 子弹爆炸类
class Bomb {

	// 定义炸坐标
	int x, y;
	int life = 9;// 子弹爆炸的生命，由三张图片组成
	boolean isLive = true;

	public Bomb(int x, int y) {

		this.x = x;
		this.y = y;
	}

	// 减少生命值
	public void lifeDown() {
		if (life > 0) {
			life--;
		} else {
			this.isLive = false;
		}
	}
}

// 子弹类
class Shot implements Runnable {
	int x;
	int y;
	int direct;
	int speed = 2;// 子弹初始速度为1
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
	//判断坦克是否存活
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

// 我的坦克
class Hero extends Tank {

	// 子弹
	Vector<Shot> ss = new Vector<Shot>();
	Shot s = null;

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}

	// 发射子弹
	public void shotEnemy() {

		switch (this.direct) {

		case 0:// 坦克方向朝上
				// 创建一颗子弹
			s = new Shot(x + 10, y, 0);
			// 把子弹加入向量
			ss.add(s);
			break;
		case 1:// 坦克方向朝右
			s = new Shot(x + 30, y + 10, 1);
			ss.add(s);
			break;
		case 2:// 坦克方向朝下
			s = new Shot(x + 10, y + 30, 2);
			ss.add(s);
			break;
		case 3:// 坦克方向朝左
			s = new Shot(x, y + 10, 3);
			ss.add(s);
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

// 敌人的坦克（敌人坦克做成线程）
class EnemyTank extends Tank implements Runnable {

	WindowSize ws=null;
	
	int times=0;//不断累积此变量
	//定义向量，存放敌人的子弹
	Vector<Shot> ss=new Vector<Shot>();
	//敌人添加子弹（在刚刚创建敌人坦克和敌人坦克子弹死亡后）
	
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

			case 0:// 敌人坦克正在向上走
				for (int i = 0; i < 30; i++) {
					if (y > 0) {//防止坦克越界，保证坦克再指定范围内移动
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
			case 1:// 敌人坦克正在向右走
				for (int i = 0; i < 30; i++) {
					if (x < ws.getWide()-30) {//防止坦克越界，保证坦克再指定范围内移动
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
			case 2:// 敌人坦克正在向下走
				for (int i = 0; i < 30; i++) {
					if (y < ws.getHigh()-30) {//防止坦克越界，保证坦克再指定范围内移动
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
			case 3:// 敌人坦克正在向左走
				for (int i = 0; i < 30; i++) {
					if (x > 0) {//防止坦克越界，保证坦克再指定范围内移动
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
			
			this.times++;//每隔1.5s加1
			
			//是否需要给坦克加入新的子弹
			if(times%2==0){//每隔1.5s发一个子弹
				
				//判断子弹是否存在
				if(isLive==true){
					if(ss.size()<5){//敌方子弹最多连发5颗
					
						Shot s=null;
						//没有子弹(添加)
						switch (direct) {

						case 0:// 坦克方向朝上
								// 创建一颗子弹
							s = new Shot(x + 10, y, 0);
							// 把子弹加入向量
							ss.add(s);
							break;
						case 1:// 坦克方向朝右
							s = new Shot(x + 30, y + 10, 1);
							ss.add(s);
							break;
						case 2:// 坦克方向朝下
							s = new Shot(x + 10, y + 30, 2);
							ss.add(s);
							break;
						case 3:// 坦克方向朝左
							s = new Shot(x, y + 10, 3);
							ss.add(s);
							break;
						}
						
						//启动子弹线程
						Thread t=new Thread(s);
						t.start();
						
					}
				}
			}
			// 让坦克随机产生一个新的方向
			// this.direct=(int)(Math.random()*10)%4;
			this.direct = (int) (Math.random() * 4);

			// 判断敌人坦克是否死亡
			if (this.isLive == false) {
				// 让坦克死亡后退出线程
				break;
			}
			
		}
	}

}
