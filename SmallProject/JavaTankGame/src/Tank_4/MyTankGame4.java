/*
 * ���ܣ�̹�˴�ս��Ϸ4.0
 * 1.����̹��(����(Graphics)ʵ��)
 * 2.�ҵ�̹�˿������������ƶ�(ͨ�����̿���,�����̼���(KeyListener)ʵ��)
 * 3.�����ӵ����ӵ�����,�������5�ţ��������߳�ʵ�֣�
 * 4.����̹���ƶ�
 * 5.�ӵ�����̹�ˣ��б�ըЧ����
 * 6.��ֹ����̹���໥�ص��˶�
 * 	6.1���ж��Ƿ���ײ����������̹�˵ĺ���д��EnemyTank����
 * 7.���Դ���
 * 	7.1�и���ʼ��Panel
 * 	7.2��˸Ч��
 * 8.����������Ϸʱ��ͣ�����
 * 	8.1���û������ͣʱ���ӵ��ٶȺ�̹���ٶ�Ϊ0���Լ�̹�˷���Ҫ�仯
 * 9.���Լ�¼��ҳɼ�
 * 	9.1�ļ���
 * 	9.2дһ����¼�࣬��¼��ҳɼ�
 * 	9.3������ҳɼ�
 * 	9.4�����˳���Ϸ�����Լ�¼��ʱ����̹�����꣬�����Իָ���
 * 10.�����Ч
 * 	10.1�������ļ��Ĳ���
 */
package Tank_4;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

//��ʼ���
class MyStartPanel extends JPanel implements Runnable {

	int times = 0;

	public MyStartPanel() {
		AePlayWave apw = new AePlayWave("src/�ز�/sound.wav");
		apw.start();
	}

	public void paint(Graphics g) {
		super.paint(g);
		g.fillRect(0, 0, this.getWidth() - 100, this.getHeight() - 100);

		// ������Ϣ����
		g.setColor(Color.white);
		Font font = new Font("������κ", Font.BOLD, 30);
		g.setFont(font);

		// ��ֹtimesԽ��
		if (times > 100) {
			times = 0;
		}

		if (times % 2 == 0) {
			// ��ʾ��Ϣ
			g.drawString("Game Start", 150, 150);
		}
	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		while (true) {

			try {
				// ����0.5s
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

// �ҵ����
class MyPanel extends JPanel implements KeyListener, Runnable {

	WindowSize ws = null;

	// ����һ���ҵ�̹��
	Hero hero = null;

	// ������˵�̹��
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// ����̹������
	int enemySize = 5;

	// ����̹��λ�õļ�¼
	Vector<Node> nodes = new Vector<Node>();

	// �����ӵ���ը����
	Vector<Bomb> bombs = new Vector<Bomb>();

	// ����ͼƬ
	Image images[] = null;

	// ���캯��
	public MyPanel(String flag) {

		ws = new WindowSize();

		// �ָ���¼
		Recorder.getRecording();

		// �����ҵ�̹��
		hero = new Hero(100, 100);// �ҵ�̹�˳�ʼ����Ϊ��100,100��,��ʼ������̹�����ϽǶ���Ϊ׼
		// �����ҵ�̹������
		hero.setType(1);
		if (flag.equals("NewGame")) {
			// ��ʼ�����˵�̹��
			for (int i = 0; i < enemySize; i++) {

				// ��������̹��
				EnemyTank et = new EnemyTank((i + 1) * 60, 0);
				// ���õ���̹������
				et.setType(0);
				// ���õ���̹�˳�ʼ����(������)
				et.setDirect(2);

				// ��MyPanel�ĵ������������õ���̹��
				et.setEts(ets);

				// ��������̹���߳�
				Thread t = new Thread(et);
				t.start();
				// ������̹�˼����ӵ�
				Shot s = new Shot(et.x + 10, et.y + 30, 2);
				et.ss.add(s);
				// �����ӵ��߳�
				Thread t2 = new Thread(s);
				t2.start();
				// �ѵ���̹�˼��뵽������
				ets.add(et);
			}
		} else {// ������Ϸ
			nodes = new Recorder().getNodesAndEnemeyNumber();

			// ��ʼ�����˵�̹��
			for (int i = 0; i < nodes.size(); i++) {

				Node node = nodes.get(i);
				// ��������̹��
				EnemyTank et = new EnemyTank(node.x, node.y);
				// ���õ���̹������
				et.setType(0);
				// ���õ���̹�˳�ʼ����(������)
				et.setDirect(2);

				// ��MyPanel�ĵ������������õ���̹��
				et.setEts(ets);

				// ��������̹���߳�
				Thread t = new Thread(et);
				t.start();
				// ������̹�˼����ӵ�
				Shot s = new Shot(et.x + 10, et.y + 30, 2);
				et.ss.add(s);
				// �����ӵ��߳�
				Thread t2 = new Thread(s);
				t2.start();
				// �ѵ���̹�˼��뵽������
				ets.add(et);
			}
		}
		// ��ʼ��ͼƬ(����ͼƬ���һ���ӵ���ը)
		images = new Image[3];
		try {
			images[0] = ImageIO.read(new File("src/�ز�/bomb_1.gif"));
			images[1] = ImageIO.read(new File("src/�ز�/bomb_2.gif"));
			images[2] = ImageIO.read(new File("src/�ز�/bomb_3.gif"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

		// images[0] =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/�ز�/bomb_1.gif"));
		// images[1] =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/�ز�/bomb_2.gif"));
		// images[2] =
		// Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/�ز�/bomb_3.gif"));
	}

	// ������ʾ��Ϣ
	public void showInfo(Graphics g) {

		// ������ʾ��Ϣ̹�ˣ���̹�˲��μ�ս����
		// ����̹����ʾ��Ϣ
		this.drawTank(120, 340, g, 0, 0);// ����̹��
		// ���û���
		g.setColor(Color.BLACK);
		Font font0 = new Font("����", Font.BOLD, 12);
		g.setFont(font0);
		g.drawString("Enemy tank surplus: " + Recorder.getEnemyNumber(), 160, 360);
		this.drawTank(120, 380, g, 0, 1);// �ҵ�̹��
		// ���û���
		g.setColor(Color.BLACK);
		Font font1 = new Font("����", Font.BOLD, 14);
		g.setFont(font1);
		g.drawString("Remaining life of our tanks: " + Recorder.getMyLife(), 160, 400);

		// ��������ܳɼ�
		this.drawTank(460, 75, g, 0, 0);// ����̹��
		g.setColor(Color.BLACK);
		Font font = new Font("����", Font.BOLD, 19);
		g.setFont(font1);
		g.drawString("Total score", 460, 30);
		g.drawString("You've killed: ", 460, 60);
		g.drawString("	��" + Recorder.getDestroyEnemy(), 485, 95);
	}

	// ��дpaint
	public void paint(Graphics g) {
		super.paint(g);

		// ���ñ�����ɫ(��ɫ)
		g.fillRect(0, 0, ws.getWide() - 150, ws.getHigh() - 170);

		// ������ʾ��Ϣ
		this.showInfo(g);

		// ���ú��������ҵ�̹��
		if (hero.isLive == true) {

			this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, hero.getType());
		}
		// ������ss��ȡ��ÿ���ӵ�������
		for (int i = 0; i < hero.ss.size(); i++) {

			Shot myShot = hero.ss.get(i);

			// ����һ���ӵ�(�ӵ������꣬�ӵ������꣬�ӵ����ӵ���)
			if (myShot != null && myShot.isLive == true) {// ����ӵ�������

				g.drawRect(myShot.x, myShot.y, 1, 1);
			}
			// �жϸ��ӵ������Ƿ�����������������������Ƴ����ӵ�
			if (myShot.isLive == false) {
				// ������ss���Ƴ����ӵ�
				hero.ss.remove(myShot);
			}
		}

		// �����ӵ���ը
		for (int i = 0; i < bombs.size(); i++) {

			// ���Ա�ը�Ƿ���
			// System.out.println("bombs.size()=" + bombs.size());
			// ȡ��ը��
			Bomb b = bombs.get(i);

			if (b.life > 6) {
				// ��ͼƬ���������꣬���ߣ�����λ�ã�
				// this��ʾ���ڵ�ǰ�����
				g.drawImage(images[0], b.x, b.y, 30, 30, this);
			} else if (b.life > 3) {
				g.drawImage(images[1], b.x, b.y, 30, 30, this);
			} else {
				g.drawImage(images[2], b.x, b.y, 30, 30, this);
			}

			// ��b������ֵ��С
			b.lifeDown();
			// ���ӵ���ը����Ϊ0���Ͱ����bombs����ȥ��
			if (b.life == 0) {

				bombs.remove(b);
			}
		}

		// ���ú����������˵�̹��
		for (int i = 0; i < ets.size(); i++) {

			EnemyTank et = ets.get(i);

			if (et.isLive == true) {// ���̹�˻�����

				// ����̹��
				this.drawTank(et.getX(), et.getY(), g, et.getDirect(), et.getType());

				// ��������̹���ӵ�
				for (int j = 0; j < et.ss.size(); j++) {
					// ȡ���ӵ�
					Shot enemyShot = et.ss.get(j);
					if (enemyShot.isLive == true) {
						g.drawRect(enemyShot.x, enemyShot.y, 1, 1);
					} else {// �������̹�������ʹ�Vector��ȥ�������ӵ�
						et.ss.remove(enemyShot);
					}
				}
			}
		}
	}

	// �����ӵ��Ƿ������
	public void hitMe() {

		// ȡ��ÿһ�����˵�̹��
		for (int i = 0; i < this.ets.size(); i++) {
			// ȡ����i��̹��
			EnemyTank et = ets.get(i);

			// ȡ��ÿһ���ӵ�
			for (int j = 0; j < et.ss.size(); j++) {
				// ȡ����j���ӵ�
				Shot enemyShot = et.ss.get(j);
				if (hero.isLive == true) {// ���̹�˻�����
					// ���ú�������̹��
					this.hitTank(enemyShot, hero);
				}
			}
		}
	}

	// �ж��ҵ��ӵ��Ƿ���е���̹��
	public void hitEnemyTank() {

		// ȡ���ҵ�ÿ���ӵ�
		for (int i = 0; i < hero.ss.size(); i++) {
			// ȡ����i���ӵ�
			Shot myShot = hero.ss.get(i);
			// �ж��ӵ��Ƿ���Ч
			if (myShot.isLive == true) {
				// ȡ��ÿ��̹�ˣ������ж�
				for (int j = 0; j < this.ets.size(); j++) {
					// ȡ����j��̹��
					EnemyTank et = ets.get(j);

					if (et.isLive == true) {// ���̹�˻�����
						// ���ú�������̹�˲��ж�
						if (this.hitTank(myShot, et)) {
							// ���ٵ�������,�����ҵĳɼ�
							Recorder.reduceEnemyNumber();
						}
					}
				}
			}
		}
	}

	// �ж��ӵ��Ƿ����̹�ˣ��ӵ���̹�ˣ�
	public boolean hitTank(Shot s, Tank tank) {

		boolean bool = false;

		// �жϵ���̹�˷���
		switch (tank.direct) {

		// ����̹�˷����ϻ���
		case 0:// ����
		case 2:// ����
			if (s.x >= tank.x && s.x <= tank.x + 20 && s.y >= tank.y && s.y <= tank.y + 30) {
				// �жϻ���

				// �ӵ�����
				s.isLive = false;
				// ����̹������
				tank.isLive = false;

				bool = true;
				// �����ӵ���ը
				Bomb b = new Bomb(tank.x, tank.y);
				// ����ӵ���ը��Vector����
				bombs.add(b);
			}
			break;
		// ����̹�˷����һ���
		case 1:// ����
		case 3:// ����
			if (s.x >= tank.x && s.x <= tank.x + 30 && s.y >= tank.y && s.y <= tank.y + 20) {
				// �жϻ���

				// �ӵ�����
				s.isLive = false;
				// ����̹������
				tank.isLive = false;
				bool = true;
				// �����ӵ���ը
				Bomb b = new Bomb(tank.x, tank.y);
				// ����ӵ���ը��Vector����
				bombs.add(b);
			}
		}
		return bool;
	}

	// ����̹�ˣ������꣬�����꣬���ʣ��������ͣ�
	public void drawTank(int x, int y, Graphics g, int direct, int type) {

		// �ж�̹������
		switch (type) {
		case 0:
			// ���û�����ɫ��������ǳ��ɫ̹�ˣ�
			g.setColor(Color.CYAN);
			break;
		case 1:
			// ���û�����ɫ����������ɫ̹�ˣ�
			g.setColor(Color.yellow);
		}

		// �жϷ���
		switch (direct) {
		case 0:// ����
				// �����ҵ�̹�ˣ���ʱ���װ��һ��������
				// 1.����߾���
			g.fill3DRect(x, y, 5, 30, false);
			// 2.�����ұ߾���
			g.fill3DRect(x + 15, y, 5, 30, false);
			// 3.�����м����
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 4.����Բ��
			g.fillOval(x + 5, y + 10, 10, 10);
			// 5.������
			g.drawLine(x + 10, y + 15, x + 10, y);
			break;
		case 1:// ����
				// �����ҵ�̹�ˣ���ʱ���װ��һ��������
				// 1.����߾���
			g.fill3DRect(x, y, 30, 5, false);
			// 2.�����ұ߾���
			g.fill3DRect(x, y + 15, 30, 5, false);
			// 3.�����м����
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 4.����Բ��
			g.fillOval(x + 10, y + 5, 10, 10);
			// 5.������
			g.drawLine(x + 15, y + 10, x + 30, y + 10);
			break;
		case 2:// ����
				// �����ҵ�̹�ˣ���ʱ���װ��һ��������
				// 1.����߾���
			g.fill3DRect(x, y, 5, 30, false);
			// 2.�����ұ߾���
			g.fill3DRect(x + 15, y, 5, 30, false);
			// 3.�����м����
			g.fill3DRect(x + 5, y + 5, 10, 20, false);
			// 4.����Բ��
			g.fillOval(x + 5, y + 10, 10, 10);
			// 5.������
			g.drawLine(x + 10, y + 15, x + 10, y + 30);
			break;
		case 3:// ����
				// �����ҵ�̹�ˣ���ʱ���װ��һ��������
				// 1.����߾���
			g.fill3DRect(x, y, 30, 5, false);
			// 2.�����ұ߾���
			g.fill3DRect(x, y + 15, 30, 5, false);
			// 3.�����м����
			g.fill3DRect(x + 5, y + 5, 20, 10, false);
			// 4.����Բ��
			g.fillOval(x + 10, y + 5, 10, 10);
			// 5.������
			g.drawLine(x + 15, y + 10, x, y + 10);
			break;
		}
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	// �������´���a��d�ң�s�£�w�ϣ�
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		// �����ҵ�̹�˵ķ���
		if (e.getKeyCode() == KeyEvent.VK_W) {
			// �����ƶ�
			this.hero.setDirect(0);
			this.hero.moveUp();
		} else if (e.getKeyCode() == KeyEvent.VK_D) {
			// �����ƶ�
			this.hero.setDirect(1);
			this.hero.moveRight();
		} else if (e.getKeyCode() == KeyEvent.VK_S) {
			// �����ƶ�
			this.hero.setDirect(2);
			this.hero.moveDown();
		} else if (e.getKeyCode() == KeyEvent.VK_A) {
			// �����ƶ�
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		// �����ӵ�(����J������)
		if (e.getKeyCode() == KeyEvent.VK_J) {

			if (this.hero.ss.size() < 5) {
				// ��������СС��5ʱ���Է��䣨�����ӵ����ͬ����ʾ5���ӵ���
				this.hero.shotEnemy();
			}
		}

		// �ػ�Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub

	}

	@Override
	public void run() {
		// TODO Auto-generated method stub
		// ÿ��100ms�ػ����
		while (true) {

			try {
				Thread.sleep(100);
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}

			this.hitEnemyTank();// �һ��е���̹��
			this.hitMe();// ����̹�˻�����

			// �ػ�
			this.repaint();
		}
	}
}

public class MyTankGame4 extends JFrame implements ActionListener {

	WindowSize ws = null;
	MyPanel mp = null;
	MyStartPanel msp = null;

	// �˵��б�
	JMenuBar jmb = null;
	JMenu jm[] = null;
	JMenuItem jmi[] = null;
	int menuSize = 1;
	int menuItemSize = 4;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyTankGame4 mtg = new MyTankGame4();
	}

	// ���캯��
	public MyTankGame4() {

		ws = new WindowSize();
		// mp = new MyPanel();
		//
		// // ��������̣߳�ÿ��100msˢ����壩
		// Thread t = new Thread(mp);
		// t.start();
		//
		// // ע�����
		// this.addKeyListener(mp);

		// �����˵��Ͳ˵�ѡ��
		jmb = new JMenuBar();
		jm = new JMenu[menuSize];
		jmi = new JMenuItem[menuItemSize];

		jm[0] = new JMenu("Game(G)");
		jm[0].setMnemonic('G');// ���Ƿ�

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

		// Ϊjm���jmi
		for (int i = 0; i < jmi.length; i++) {
			jm[0].add(jmi[i]);
		}

		// Ϊjmb���jm
		for (int j = 0; j < jm.length; j++) {
			jmb.add(jm[j]);
		}

		this.setJMenuBar(jmb);// ��Ӳ˵���

		msp = new MyStartPanel();
		Thread t = new Thread(msp);
		t.start();
		this.add(msp);

		// �����������
		this.setVisible(true);
		this.setSize(ws.getWide(), ws.getHigh());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// ���û���ͬ���������ͬ�Ĵ���
		if (e.getActionCommand().equals("New Game")) {
			// ����ս�����
			mp = new MyPanel("NewGame");

			// ����ս������̣߳�ÿ��100msˢ��ս����壩
			Thread t = new Thread(mp);
			t.start();
			// ע�����
			this.addKeyListener(mp);

			// ɾ����ʼ���
			this.remove(msp);
			// ���ս�����
			this.add(mp);
			// ��ʾս�����
			this.setVisible(true);
		} else if (e.getActionCommand().equals("Exit")) {
			// �û�����˳�ϵͳ�˵�
			// ����ɼ�
			Recorder.keepRecording();
			System.exit(0);
		} else if (e.getActionCommand().equals("Save Exit")) {
			// �����˳�
			Recorder r = new Recorder();
			r.setEts(mp.ets);
			// ������ٵ��˵������͵��˵�����
			r.keepRecordEnemy();

			// �˳�
			System.exit(0);// 0�����˳���-1�쳣�˳�

		} else if (e.getActionCommand().equals("Continue Game")) {
			// ����ս�����
			mp = new MyPanel("ContinueGame");

			// ����ս������̣߳�ÿ��100msˢ��ս����壩
			Thread t = new Thread(mp);
			t.start();
			// ע�����
			this.addKeyListener(mp);

			// ɾ����ʼ���
			this.remove(msp);
			// ���ս�����
			this.add(mp);
			// ��ʾս�����
			this.setVisible(true);
		}
	}
}