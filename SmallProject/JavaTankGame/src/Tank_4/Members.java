package Tank_4;

import java.io.*;
import java.util.*;
import javax.sound.sampled.*;

public class Members {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}

// 设置窗口的大小
class WindowSize {

	int wide = 600, high = 500;

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

// 音效类
class AePlayWave extends Thread {

	private String filename;

	public AePlayWave(String wavfile) {
		filename = wavfile;

	}

	public void run() {

		File soundFile = new File(filename);

		AudioInputStream audioInputStream = null;
		try {
			audioInputStream = AudioSystem.getAudioInputStream(soundFile);
		} catch (Exception e1) {
			e1.printStackTrace();
			return;
		}

		AudioFormat format = audioInputStream.getFormat();
		SourceDataLine auline = null;
		DataLine.Info info = new DataLine.Info(SourceDataLine.class, format);

		try {
			auline = (SourceDataLine) AudioSystem.getLine(info);
			auline.open(format);
		} catch (Exception e) {
			e.printStackTrace();
			return;
		}

		auline.start();
		int nBytesRead = 0;
		// 这是缓冲
		byte[] abData = new byte[512];

		try {
			while (nBytesRead != -1) {
				nBytesRead = audioInputStream.read(abData, 0, abData.length);
				if (nBytesRead >= 0)
					auline.write(abData, 0, nBytesRead);
			}
		} catch (IOException e) {
			e.printStackTrace();
			return;
		} finally {
			auline.drain();
			auline.close();
		}

	}

}

// 记录坐标点和方向
class Node {
	int x, y;
	int direct;

	public Node(int x, int y, int direct) {
		this.x = x;
		this.y = y;
		this.direct = direct;
	}
}

// 记录类(可保存玩家设置)
class Recorder {

	// 记录每关有多少敌人
	private static int enemyNumber = 20;
	// 已消灭的敌人数量
	private static int destroyEnemy = 0;
	// 设置我的生命
	private static int myLife = 3;// 最多三条命

	// 从文件中恢复记录点
	static Vector<Node> nodes = new Vector<Node>();

	private static FileWriter fw = null;
	private static BufferedWriter bw = null;

	private static FileReader fr = null;
	private static BufferedReader br = null;

	private Vector<EnemyTank> ets = new Vector<EnemyTank>();

	public Vector<EnemyTank> getEts() {
		return ets;
	}

	public void setEts(Vector<EnemyTank> ets) {
		this.ets = ets;
	}

	// 读取记录
	public Vector<Node> getNodesAndEnemeyNumber() {
		try {
			fr = new FileReader("E:/123.txt");
			br = new BufferedReader(fr);
			// 读取第一行
			String n = br.readLine();
			// 读第一行后面的n行
			while ((n = br.readLine()) != null) {
				// 根据空格返回数组（即每遇到一个空格就就将空格前面的数据放置数组）
				String xyz[] = n.split(" ");

				Node node = new Node(Integer.parseInt(xyz[0]), Integer.parseInt(xyz[1]), Integer.parseInt(xyz[2]));
				nodes.add(node);
			}
			destroyEnemy = Integer.parseInt(n);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		return nodes;
	}

	// 保存击毁敌人数量与敌人坦克坐标和方向
	public void keepRecordEnemy() {
		try {
			fw = new FileWriter("E:/123.txt");
			bw = new BufferedWriter(fw);

			bw.write(destroyEnemy + "\r\n");

			// 保存当前还活着的敌人坦克坐标和方向
			for (int i = 0; i < ets.size(); i++) {
				// 取出第i个坦克
				EnemyTank et = ets.get(i);
				if (et.isLive) {
					String recode = et.x + " " + et.y + " " + et.direct;

					// 写入
					bw.write(recode + "\r\n");
				}
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 关闭流（后开先关）
			try {
				bw.close();
				fw.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	// 从文件中读取敌人坦克消灭数量的记录
	public static void getRecording() {
		try {
			fr = new FileReader("E:/123.txt");
			br = new BufferedReader(fr);

			String n = br.readLine();
			destroyEnemy = Integer.parseInt(n);
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			try {
				br.close();
				fr.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	// 保存玩家击毁敌人坦克数
	public static void keepRecording() {
		try {
			fw = new FileWriter("E:/123.txt");
			bw = new BufferedWriter(fw);

			bw.write(destroyEnemy + "\r\n");
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 关闭流（后开先关）
			try {
				bw.close();
				fw.close();
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
	}

	public static int getDestroyEnemy() {
		return destroyEnemy;
	}

	public static void setDestroyEnemy(int destroyEnemy) {
		Recorder.destroyEnemy = destroyEnemy;
	}

	public static int getEnemyNumber() {
		return enemyNumber;
	}

	public static void setEnemyNumber(int enemyNumber) {
		Recorder.enemyNumber = enemyNumber;
	}

	public static int getMyLife() {
		return myLife;
	}

	public static void setMyLife(int myLife) {
		Recorder.myLife = myLife;
	}

	// 敌人坦克死亡数和敌人坦克被消灭数量
	public static void reduceEnemyNumber() {

		--enemyNumber;
		++destroyEnemy;
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

	WindowSize ws = new WindowSize();

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
			if (x < 0 || x > ws.getWide() - 150 || y < 0 || y > ws.getHigh() - 170) {
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
	// 判断坦克是否存活
	boolean isLive = true;

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

	WindowSize ws = null;

	int times = 0;// 不断累积此变量
	// 定义向量，存放敌人的子弹
	Vector<Shot> ss = new Vector<Shot>();
	// 敌人添加子弹（在刚刚创建敌人坦克和敌人坦克子弹死亡后）

	// 定义一个向量，可以访问MyPanel上所有敌人坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();

	public EnemyTank(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
		ws = new WindowSize();
	}

	// 得到MyPanel的敌人坦克向量
	public void setEts(Vector<EnemyTank> ets) {

		this.ets = ets;
	}

	// 判断是否碰撞到其他敌人坦克
	public boolean isTouchOhterEnemy() {

		boolean b = false;// false表示未发生碰撞,true表示发送碰撞

		switch (this.direct) {

		case 0:
			// 坦克向上
			// 取出所有敌人坦克
			for (int i = 0; i < ets.size(); i++) {

				// 取出第i个坦克
				EnemyTank et = ets.get(i);
				// (这里的this指的是et自己)
				if (et != this) {// 若取出的坦克不是自己

					if (et.direct == 0 || et.direct == 2) {// 如果敌人方向向上或向下

						// 判断当前坦克左上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x >= et.x && this.x <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// 判断当前坦克左上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {// 如果敌人方向向右或向左

						// 判断当前坦克左上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x >= et.x && this.x <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						// 判断当前坦克右上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 1:
			// 坦克向右
			// 取出所有敌人坦克
			for (int i = 0; i < ets.size(); i++) {

				// 取出第i个坦克
				EnemyTank et = ets.get(i);
				// (这里的this指的是et自己)
				if (et != this) {// 若取出的坦克不是自己

					if (et.direct == 0 || et.direct == 2) {// 如果敌人方向向上或向下

						// 判断当前坦克右上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// 判断当前坦克右下角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 20 && this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {// 如果敌人方向向右或向左

						// 判断当前坦克右上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30 && this.y >= et.y && this.y <= et.y + 20) {
							return true;
						}
						// 判断当前坦克右下角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x + 30 >= et.x && this.x + 30 <= et.x + 30 && this.y + 20 >= et.y
								&& this.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 2:
			// 坦克向下
			// 取出所有敌人坦克
			for (int i = 0; i < ets.size(); i++) {

				// 取出第i个坦克
				EnemyTank et = ets.get(i);
				// (这里的this指的是et自己)
				if (et != this) {// 若取出的坦克不是自己

					if (et.direct == 0 || et.direct == 2) {// 如果敌人方向向上或向下

						// 判断当前坦克左上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x >= et.x && this.x <= et.x + 20 && this.y + 30 >= et.y && this.y + 30 <= et.y + 30) {
							return true;
						}
						// 判断当前坦克右上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 20 && this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {// 如果敌人方向向右或向左

						// 判断当前坦克右下角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x >= et.x && this.x <= et.x + 30 && this.y + 30 >= et.y && this.y + 30 <= et.y + 20) {
							return true;
						}
						// 判断当前坦克右下角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x + 20 >= et.x && this.x + 20 <= et.x + 30 && this.y + 30 >= et.y
								&& this.y + 30 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		case 3:
			// 坦克向左
			// 取出所有敌人坦克
			for (int i = 0; i < ets.size(); i++) {

				// 取出第i个坦克
				EnemyTank et = ets.get(i);
				// (这里的this指的是et自己)
				if (et != this) {// 若取出的坦克不是自己

					if (et.direct == 0 || et.direct == 2) {// 如果敌人方向向上或向下

						// 判断当前坦克左上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x >= et.x && this.x <= et.x + 20 && this.y >= et.y && this.y <= et.y + 30) {
							return true;
						}
						// 判断当前坦克左下角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x >= et.x && this.x <= et.x + 20 && this.y + 20 >= et.y && this.y + 20 <= et.y + 30) {
							return true;
						}
					}
					if (et.direct == 1 || et.direct == 3) {// 如果敌人方向向右或向左

						// 判断当前坦克左上角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x >= et.x && this.x <= et.x + 30 && this.y >= et.y && this.y + 20 <= et.y + 20) {
							return true;
						}
						// 判断当前坦克左下角的点的坐标与需判定坦克的上两个角的顶点的坐标的横纵坐标关系
						if (this.x >= et.x && this.x <= et.x + 30 && this.y + 20 >= et.y && this.y + 20 <= et.y + 20) {
							return true;
						}
					}
				}
			}
			break;
		}

		return b;
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 坦克的移动范围与战斗面板的大小有关
		while (true) {

			switch (this.direct) {

			case 0:// 敌人坦克正在向上走
				for (int i = 0; i < 30; i++) {
					if (y > 0 && this.isTouchOhterEnemy() == false) {// 防止坦克越界，保证坦克再指定范围内移动
						y -= speed;
					}
					try {
						Thread.sleep(50);// 30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 1:// 敌人坦克正在向右走
				for (int i = 0; i < 30; i++) {
					if ((x < (ws.getWide() - 180)) && this.isTouchOhterEnemy() == false) {// 防止坦克越界，保证坦克再指定范围内移动
						x += speed;
					}
					try {
						Thread.sleep(50);// 30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 2:// 敌人坦克正在向下走
				for (int i = 0; i < 30; i++) {
					if ((y < (ws.getHigh() - 200)) && this.isTouchOhterEnemy() == false) {// 防止坦克越界，保证坦克再指定范围内移动
						y += speed;
					}
					try {
						Thread.sleep(50);// 30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			case 3:// 敌人坦克正在向左走
				for (int i = 0; i < 30; i++) {
					if (x > 0 && this.isTouchOhterEnemy() == false) {// 防止坦克越界，保证坦克再指定范围内移动
						x -= speed;
					}
					try {
						Thread.sleep(50);// 30*50=1500ms=1.5s
					} catch (Exception e) {
						// TODO: handle exception
						e.printStackTrace();
					}
				}
				break;
			}

			this.times++;// 每隔1.5s加1

			// 是否需要给坦克加入新的子弹
			if (times % 2 == 0) {// 每隔1.5s发一个子弹

				// 判断子弹是否存在
				if (isLive == true) {
					if (ss.size() < 5) {// 敌方子弹最多连发5颗

						Shot s = null;
						// 没有子弹(添加)
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

						// 启动子弹线程
						Thread t = new Thread(s);
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
