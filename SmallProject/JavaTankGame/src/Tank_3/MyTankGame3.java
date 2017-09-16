/*
 * ���ܣ�̹�˴�ս��Ϸ3.0
 * 1.����̹��(����(Graphics)ʵ��)
 * 2.�ҵ�̹�˿������������ƶ�(ͨ�����̿���,�����̼���(KeyListener)ʵ��)
 * 3.�����ӵ����ӵ�����,�������5�ţ��������߳�ʵ�֣�
 * 4.����̹���ƶ�
 * 5.�ӵ�����̹�ˣ��б�ըЧ����
 */
package Tank_3;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.Vector;

//�ҵ����
class MyPanel extends JPanel implements KeyListener, Runnable {

	WindowSize ws = null;

	// ����һ���ҵ�̹��
	Hero hero = null;

	// ������˵�̹��
	Vector<EnemyTank> ets = new Vector<EnemyTank>();
	// ����̹������
	int enemySize = 3;

	// �����ӵ���ը����
	Vector<Bomb> bombs = new Vector<Bomb>();

	// ����ͼƬ
	Image images[] = null;

	// ���캯��
	public MyPanel() {

		ws = new WindowSize();

		// �����ҵ�̹��
		hero = new Hero(100, 100);// �ҵ�̹�˳�ʼ����Ϊ��100,100��,��ʼ������̹�����ϽǶ���Ϊ׼
		// �����ҵ�̹������
		hero.setType(1);

		// ��ʼ�����˵�̹��
		for (int i = 0; i < enemySize; i++) {

			// ��������̹��
			EnemyTank et = new EnemyTank((i + 1) * 80, 0);
			// ���õ���̹������
			et.setType(0);
			// ���õ���̹�˳�ʼ����(������)
			et.setDirect(2);
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

		// ��ʼ��ͼƬ(����ͼƬ���һ���ӵ���ը)
		images = new Image[3];
		try {
			images[0]=ImageIO.read(new File("D:/eclipse workspace/JavaTankGame/src/�ز�/bomb_1.gif"));
			images[1]=ImageIO.read(new File("D:/eclipse workspace/JavaTankGame/src/�ز�/bomb_2.gif"));
			images[2]=ImageIO.read(new File("D:/eclipse workspace/JavaTankGame/src/�ز�/bomb_3.gif"));
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}
		
//		images[0] = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/�ز�/bomb_1.gif"));
//		images[1] = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/�ز�/bomb_2.gif"));
//		images[2] = Toolkit.getDefaultToolkit().getImage(Panel.class.getResource("/�ز�/bomb_3.gif"));
	}

	// ��дpaint
	public void paint(Graphics g) {
		super.paint(g);

		// ���ñ�����ɫ(��ɫ)
		g.fillRect(0, 0, ws.getWide(), ws.getHigh());

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
						// ���ú�������̹��
						this.hitTank(myShot, et);
					}
				}
			}
		}
	}

	// �ж��ӵ��Ƿ����̹�ˣ��ӵ���̹�ˣ�
	public void hitTank(Shot s, Tank tank) {

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

				// �����ӵ���ը
				Bomb b = new Bomb(tank.x, tank.y);
				// ����ӵ���ը��Vector����
				bombs.add(b);
			}
		}
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

public class MyTankGame3 extends JFrame {

	MyPanel mp = null;
	WindowSize ws = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		MyTankGame3 mtg = new MyTankGame3();
	}

	// ���캯��
	public MyTankGame3() {

		mp = new MyPanel();
		ws = new WindowSize();

		// ��������̣߳�ÿ��100msˢ����壩
		Thread t = new Thread(mp);
		t.start();

		// ע�����
		this.addKeyListener(mp);

		this.add(mp);

		// �����������
		this.setVisible(true);
		this.setSize(ws.getWide(), ws.getHigh());
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}