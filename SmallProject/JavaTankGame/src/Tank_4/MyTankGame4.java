/*
 * 功能：坦克大战游戏4.0
 * 1.画出坦克(画笔(Graphics)实现)
 * 2.我的坦克可以上下左右移动(通过键盘控制,即键盘监听(KeyListener)实现)
 * 3.发射子弹，子弹连发,最多连发5颗（向量，线程实现）
 * 4.敌人坦克移动
 * 5.子弹击毁坦克（有爆炸效果）
 * 6.防止敌人坦克相互重叠运动
 * 	6.1把判断是否碰撞到其他敌人坦克的函数写到EnemyTank类中
 * 7.可以闯关
 * 	7.1有个开始的Panel
 * 	7.2闪烁效果
 * 8.可以在玩游戏时暂停或继续
 * 	8.1当用户点击暂停时，子弹速度和坦克速度为0，以及坦克方向不要变化
 * 9.可以记录玩家成绩
 * 	9.1文件流
 * 	9.2写一个记录类，记录玩家成绩
 * 	9.3保存玩家成绩
 * 	9.4存盘退出游戏，可以记录当时敌人坦克坐标，并可以恢复。
 * 10.相关音效
 * 	10.1对声音文件的操作
 */
package Tank_4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

//开始面板
class MyStartPanel extends JPanel implements Runnable {

	int times = 0;

	public MyStartPanel() {
		AePlayWave apw = new AePlayWave("src/素材/sound.wav");
		apw.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, this.getWidth() - 100, this.getHeight() - 100);

		// 开关信息字体
		g.setColor(Color.white);
		Font font = new Font("华文新魏", Font.BOLD, 30);
		g.setFont(font);

		// 防止times越界
		if (times > 100) {
			times = 0;
		}

		if (times % 2 == 0) {
			// 提示信息
			g.drawString("Game Start", 150, 150);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				// 休眠0.5s
				Thread.sleep(500);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			times++;

			this.repaint();
		}
	}
}

// 我的面板
class MyPanel extends JPanel implements KeyListener, Runnable {

	WindowSize ws = null;

	// 定义一个我的坦克
	Hero hero = null;

	// 定义敌人的坦克
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// 敌人坦克数量
	int enemySize = 5;

	// 定义坦克位置的记录
	Vector<Node> nodes = new Vector<Node>();

	// 定义子弹爆炸集合
	Vector<Bomb> bombs = new Vector<Bomb>();

	// 定义图片
	Image images[] = null;

	// 构造函数
	public MyPanel(String flag) {

		ws = new WindowSize();

		// 恢复记录
		Recorder.getRecording();

		// 创建我的坦克
		hero = new Hero(100, 100);// 我的坦克初始坐标为（100,100）,初始坐标以坦克左上角定点为准
		// 设置我的坦克类型
		hero.setType(1);
		if (flag.equals("NewGame")) {
			// 初始化敌人的坦克
			for (int i = 0; i < enemySize; i++) {

				// 创建敌人坦克
				EnemyTank et = new EnemyTank((i + 1) * 60, 0);
				// 设置敌人坦克类型
				et.setType(0);
				// 设置敌人坦克初始方向(方向朝下)
				et.setDirect(2);

				// 将MyPanel的敌人向量交给该敌人坦克
				et.setEts(ets);

				// 启动敌人坦克线程
				Thread t = new Thread(et);
				t.start();
				// 给敌人坦克加入子弹
				Shot s = new Shot(et.x + 10, et.y + 30, 2);
				et.ss.add(s);
				// 启动子弹线程
				Thread t2 = new Thread(s);
				t2.start();
				// 把敌人坦克加入到向量中
				ets.add(et);
			}
		} else {// 继续游戏
			nodes = new Recorder().getNodesAndEnemeyNumber();

			// 初始化敌人的坦克
			for (int i = 0; i < nodes.size(); i++) {

				Node node = nodes.get(i);
				// 创建敌人坦克
				EnemyTank et = new EnemyTank(node.x, node.y);
				// 设置敌人坦克类型
				et.setType(0);
				// 设置敌人坦克初始方向(方向朝下)
				et.setDirect(2);

				// 将MyPanel的敌人向量交给该敌人坦克
				et.setEts(ets);

				// 启动敌人坦克线程
				Thread t = new Thread(et);
				t.start();
				// 给敌人坦克加入子弹
				Shot s = new Shot(et.x + 10, et.y + 30, 2);
				et.ss.add(s);
				// 启动子弹线程
				Thread t2 = new Thread(s);
				t2.start();
				// 把敌人坦克加入到向量中
				ets.add(et);
			}
		}
		// 初始化图片(三张图片组成一颗子弹爆炸)
		images = new Image[3];
		try {
			images[0] = ImageIO.read(new File("src/素材/bomb_1.gif"));
			images[1] = ImageIO.read(new File("src/素材/bomb_2.gif"));
			images[2] = ImageIO.read(new File("src/素材/bomb_3.gif"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// images[0] =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/素材/bomb_1.gif"));
		// images[1] =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/素材/bomb_2.gif"));
		// images[2] =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/素材/bomb_3.gif"));
	}

	// 画出提示信息
	public void showInfo(Graphics g) {

		// 画出提示信息坦克（该坦克不参加战斗）
		// 画出坦克提示信息
		this.drawTank(120, 340, g, 0, 0);// 敌人坦克
		// 设置画笔
		g.setColor(Color.BLACK);
		Font font0 = new Font("宋体", Font.BOLD, 12);
		g.setFont(font0);
		g.drawString("Enemy tank surplus: " + Recorder.getEnemyNumber(), 160, 360);
		this.drawTank(120, 380, g, 0, 1);// 我的坦克
		// 设置画笔
		g.setColor(Color.BLACK);
		Font font1 = new Font("宋体", Font.BOLD, 14);
		g.setFont(font1);
		g.drawString("Remaining life of our tanks: " + Recorder.getMyLife(), 160, 400);

		// 画出完成总成绩
		this.drawTank(460, 75, g, 0, 0);// 敌人坦克
		g.setColor(Color.BLACK);
		Font font = new Font("宋体", Font.BOLD, 19);
		g.setFont(font1);
		g.drawString("Total score", 460, 30);
		g.drawString("You've killed: ", 460, 60);
		g.drawString("	×" + Recorder.getDestroyEnemy(), 485, 95);
	}

	// 重写paint
	public void paint(Graphics g) {
		super.paint(g);

		// 设置背景颜色(黑色)
		g.fillRect(0, 0, ws.getWide() - 150, ws.getHigh() - 170);

		// 画出提示信息
		this.showInfo(g);

		// 调用函数画出我的坦克
		if (hero.isLive == true) {

			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, hero.getType());
		}
		// 从向量ss中取出每颗子弹并画出
		for (int i = 0; i < hero.ss.size(); i++) {

			Shot myShot = hero.ss.get(i);

			// 画出一颗子弹(子弹横坐标，子弹纵坐标，子弹宽，子弹高)
			if (myShot != null && myShot.isLive == true) {// 如果子弹不存在

				g.drawRect(myShot.x, myShot.y, 1, 1);
			}
			// 判断该子弹死亡是否死亡，若死亡则从向量中移除该子弹
			if (myShot.isLive == false) {
				// 从向量ss中移除该子弹
				hero.ss.remove(myShot);
			}
		}

		// 画出子弹爆炸
		for (int i = 0; i < bombs.size(); i++) {

			// 测试爆炸是否发生
			// System.out.println("bombs.size()=" + bombs.size());
			// 取出炸弹
			Bomb b = bombs.get(i);

			if (b.life > 6) {
				// 画图片（对象，坐标，宽，高，画的位置）
				// this表示画在当前面板上
				g.drawImage(images[0], b.x, b.y, 30, 30, this);
			} else if (b.life > 3) {
				g.drawImage(images[1], b.x, b.y, 30, 30, this);
			} else {
				g.drawImage(images[2], b.x, b.y, 30, 30, this);
			}

			// 让b的生命值减小
			b.lifeDown();
			// 当子弹爆炸生命为0，就把其从bombs向量去掉
			if (b.life == 0) {

				bombs.remove(b);
			}
		}

		// 调用函数画出敌人的坦克
		for (int i = 0; i < ets.size(); i++) {

			EnemyTank et = ets.get(i);

			if (et.isLive == true) {// 如果坦克还活着

				// 画出坦克
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), et.getType());

				// 画出敌人坦克子弹
				for (int j = 0; j < et.ss.size(); j++) {
					// 取出子弹
					Shot enemyShot = et.ss.get(j);
					if (enemyShot.isLive == true) {
						g.drawRect(enemyShot.x, enemyShot.y, 1, 1);
					} else {// 如果敌人坦克死亡就从Vector中去掉敌人子弹
						et.ss.remove(enemyShot);
					}
				}
			}
		}
	}

	// 敌人子弹是否击中我
	public void hitMe() {

		// 取出每一个敌人的坦克
		for (int i = 0; i < this.ets.size(); i++) {
			// 取出第i颗坦克
			EnemyTank et = ets.get(i);

			// 取出每一颗子弹
			for (int j = 0; j < et.ss.size(); j++) {
				// 取出第j颗子弹
				Shot enemyShot = et.ss.get(j);
				if (hero.isLive == true) {// 如果坦克还活着
					// 调用函数击中坦克
					this.hitTank(enemyShot, hero);
				}
			}
		}
	}

	// 判断我的子弹是否击中敌人坦克
	public void hitEnemyTank() {

		// 取出我的每个子弹
		for (int i = 0; i < hero.ss.size(); i++) {
			// 取出第i颗子弹
			Shot myShot = hero.ss.get(i);
			// 判断子弹是否有效
			if (myShot.isLive == true) {
				// 取出每个坦克，与其判断
				for (int j = 0; j < this.ets.size(); j++) {
					// 取出第j个坦克
					EnemyTank et = ets.get(j);

					if (et.isLive == true) {// 如果坦克还活着
						// 调用函数击中坦克并判断
						if (this.hitTank(myShot, et)) {
							// 减少敌人数量,增加我的成绩
							Recorder.reduceEnemyNumber();
						}
					}
				}
			}
		}
	}

	// 判断子弹是否击中坦克（子弹，坦克）
	public boolean hitTank(Shot s, Tank tank) {

		boolean bool = false;

		// 判断敌人坦克方向
		switch (tank.direct) {

		// 敌人坦克方向朝上或朝下
		case 0:// 朝上
		case 2:// 朝下
			if (s.x >= tank.x && s.x <= tank.x + 20 && s.y >= tank.y && s.y <= tank.y + 30) {
				// 判断击中

				// 子弹死亡
				s.isLive = false;
				// 敌人坦克死亡
				tank.isLive = false;

				bool = true;
				// 创建子弹爆炸
				Bomb b = new Bomb(tank.x, tank.y);
				// 添加子弹爆炸至Vector集合
				bombs.add(b);
			}
			break;
		// 敌人坦克方向朝右或朝左
		case 1:// 朝右
		case 3:// 朝左
			if (s.x >= tank.x && s.x <= tank.x + 30 && s.y >= tank.y && s.y <= tank.y + 20) {
				// 判断击中

				// 子弹死亡
				s.isLive = false;
				// 敌人坦克死亡
				tank.isLive = false;
				bool = true;
				// 创建子弹爆炸
				Bomb b = new Bomb(tank.x, tank.y);
				// 添加子弹爆炸至Vector集合
				bombs.add(b);
			}
		}
		return bool;
	}

	// 画出坦克（横坐标，纵坐标，画笔，方向，类型）
	public void drawTank(int x, int y, Graphics g, int direct, int type) {

		// 判断坦克类型
		switch (type) {
		case 0:
			// 设置画笔颜色（即画出浅蓝色坦克）
			g.setColor(Color.CYAN);
			break;
		case 1:
			// 设置画笔颜色（即画出黄色坦克）
			g.setColor(Color.yellow);
		}

		// 判断方向
		switch (direct) {
		case 0:// 向上
				// 画出我的坦克（到时候封装成一个函数）
				// 1.画左边矩形
			g.fill3DRect(x, y, 5, 30, false);
			// 2.画出右边矩形
			g.fill3DRect(x + 15, y, 5, 30, false);
			// 3.画出中间矩形
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 4.画出圆形
			g.fillOval(x + 5, y + 10, 10, 10);
			// 5.画出线
			g.drawLine(x + 10, y + 15, x + 10, y);
			break;
		case 1:// 向右
				// 画出我的坦克（到时候封装成一个函数）
				// 1.画左边矩形
			g.fill3DRect(x, y, 30, 5, false);
			// 2.画出右边矩形
			g.fill3DRect(x, y + 15, 30, 5, false);
			// 3.画出中间矩形
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 4.画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 5.画出线
			g.drawLine(x + 15, y + 10, x + 30, y + 10);
			break;
		case 2:// 向下
				// 画出我的坦克（到时候封装成一个函数）
				// 1.画左边矩形
			g.fill3DRect(x, y, 5, 30, false);
			// 2.画出右边矩形
			g.fill3DRect(x + 15, y, 5, 30, false);
			// 3.画出中间矩形
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 4.画出圆形
			g.fillOval(x + 5, y + 10, 10, 10);
			// 5.画出线
			g.drawLine(x + 10, y + 15, x + 10, y + 30);
			break;
		case 3:// 向左
				// 画出我的坦克（到时候封装成一个函数）
				// 1.画左边矩形
			g.fill3DRect(x, y, 30, 5, false);
			// 2.画出右边矩形
			g.fill3DRect(x, y + 15, 30, 5, false);
			// 3.画出中间矩形
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 4.画出圆形
			g.fillOval(x + 10, y + 5, 10, 10);
			// 5.画出线
			g.drawLine(x + 15, y + 10, x, y + 10);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	// 键被按下处理（a左，d右，s下，w上）
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// 设置我的坦克的方向
		if (e.getKeyCode() == KeyEvent.VK_W) {
			// 向上移动
			this.hero.setDirect(0);
			this.hero.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			// 向右移动
			this.hero.setDirect(1);
			this.hero.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			// 向下移动
			this.hero.setDirect(2);
			this.hero.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			// 向上移动
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		// 发射子弹(按下J键发射)
		if (e.getKeyCode() == KeyEvent.VK_J) {

			if (this.hero.ss.size() < 5) {
				// 当向量大小小于5时可以发射（即在子弹最多同屏显示5颗子弹）
				this.hero.shotEnemy();
			}
		}

		// 重绘Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// 每隔100ms重绘面板
		while (true) {

			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			this.hitEnemyTank();// 我击中敌人坦克
			this.hitMe();// 敌人坦克击中我

			// 重绘
			this.repaint();
		}
	}
}

public class MyTankGame4 extends JFrame implements ActionListener {

	WindowSize ws = null;
	MyPanel mp = null;
	MyStartPanel msp = null;

	// 菜单列表
	JMenuBar jmb = null;
	JMenu jm[] = null;
	JMenuItem jmi[] = null;
	int menuSize = 1;
	int menuItemSize = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyTankGame4 mtg = new MyTankGame4();
	}

	// 构造函数
	public MyTankGame4() {

		ws = new WindowSize();
		// mp = new MyPanel();
		//
		// // 启动面板线程（每隔100ms刷新面板）
		// Thread t = new Thread(mp);
		// t.start();
		//
		// // 注册监听
		// this.addKeyListener(mp);

		// 创建菜单和菜单选项
		jmb = new JMenuBar();
		jm = new JMenu[menuSize];
		jmi = new JMenuItem[menuItemSize];

		jm[0] = new JMenu("Game(G)");
		jm[0].setMnemonic('G');// 助记符

		jmi[0] = new JMenuItem("New Game(N)");
		jmi[0].setMnemonic('N');
		jmi[0].addActionListener(this);
		jmi[0].setActionCommand("New Game");

		jmi[1] = new JMenuItem("Exit(E)");
		jmi[1].setMnemonic('E');
		jmi[1].addActionListener(this);
		jmi[1].setActionCommand("Exit");

		jmi[2] = new JMenuItem("Save Exit(S)");
		jmi[2].setMnemonic('S');
		jmi[2].addActionListener(this);
		jmi[2].setActionCommand("Save Exit");

		jmi[3] = new JMenuItem("Continue Game(C)");
		jmi[3].setMnemonic('C');
		jmi[3].addActionListener(this);
		jmi[3].setActionCommand("Continue Game");

		// 为jm添加jmi
		for (int i = 0; i < jmi.length; i++) {
			jm[0].add(jmi[i]);
		}

		// 为jmb添加jm
		for (int j = 0; j < jm.length; j++) {
			jmb.add(jm[j]);
		}

		this.setJMenuBar(jmb);// 添加菜单条

		msp = new MyStartPanel();
		Thread t = new Thread(msp);
		t.start();
		this.add(msp);

		// 设置面板属性
		this.setVisible(true);
		this.setSize(ws.getWide(), ws.getHigh());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 对用户不同点击做出不同的处理
		if (e.getActionCommand().equals("New Game")) {
			// 创建战场面板
			mp = new MyPanel("NewGame");

			// 启动战场面板线程（每隔100ms刷新战场面板）
			Thread t = new Thread(mp);
			t.start();
			// 注册监听
			this.addKeyListener(mp);

			// 删除开始面板
			this.remove(msp);
			// 添加战场面板
			this.add(mp);
			// 显示战场面板
			this.setVisible(true);
		} else if (e.getActionCommand().equals("Exit")) {
			// 用户点击退出系统菜单
			// 保存成绩
			Recorder.keepRecording();
			System.exit(0);
		} else if (e.getActionCommand().equals("Save Exit")) {
			// 保存退出
			Recorder r = new Recorder();
			r.setEts(mp.ets);
			// 保存击毁敌人的数量和敌人的坐标
			r.keepRecordEnemy();

			// 退出
			System.exit(0);// 0正常退出，-1异常退出

		} else if (e.getActionCommand().equals("Continue Game")) {
			// 创建战场面板
			mp = new MyPanel("ContinueGame");

			// 启动战场面板线程（每隔100ms刷新战场面板）
			Thread t = new Thread(mp);
			t.start();
			// 注册监听
			this.addKeyListener(mp);

			// 删除开始面板
			this.remove(msp);
			// 添加战场面板
			this.add(mp);
			// 显示战场面板
			this.setVisible(true);
		}
	}
}