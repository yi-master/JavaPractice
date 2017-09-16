/*
 * �����
 */

package com.tedu.huat.day01;

import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.JPanel;

public class Pool extends JPanel {

	//�ӵ���
	int bullets=100;
	//����
	int score;
	
	// ����ͼƬ
	BufferedImage bgImg = null;

	// ����20����
	int num = 20;
	Fish[] fishs = new Fish[num];

	Net net = null;

	// ͨ����������ȡͼƬ
	// 1.�ҵ�ͼƬ�ĵ�ַ
	// 2.ͨ���������ȡͼƬ
	public Pool() throws IOException {
		File file = new File("images/bg.jpg");
		bgImg = ImageIO.read(file);
		// ��fishs����ʼֵ
		for (int i = 0; i < fishs.length / 2 - 1; i++) {
			fishs[i] = new Fish(i + 1);// ��1������9���㣬�����±�Ϊ0~8
			fishs[i + 9] = new Fish(i + 1);// ��10������18���㣬�����±�Ϊ9~17
		}
		fishs[18] = new Fish(13);// ��19���㣬�±꣺18
		fishs[19] = new Fish(14);// ��20���㣬�±꣺19
		net = new Net();
	}

	// ���Ʊ���ͼƬ
	public void paint(Graphics g) {
		// ��ͼƬ
		g.drawImage(bgImg, 0, 0, null);
		// ����
		for (int i = 0; i < fishs.length; i++) {
			g.drawImage(fishs[i].img, fishs[i].x, fishs[i].y, null);
		}// ����
		if (net.flag) {
			g.drawImage(net.img, net.x - net.width / 2, net.y - net.height / 2,
					null);
		}
		//����������ɫ
		Font font =new Font("����",Font.BOLD,20);
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("�������1.0",30,25);
		g.drawString("�ӵ�����"+bullets,155,25);
		g.drawString("��ǰ�÷֣�"+score,300,25);
		g.drawString("������㣬�Ҽ���ǿ�������ֵ��"+power,450,25);
		//������Ϸ����
		if(bullets<=0){
			Font f=new Font("����",Font.BOLD,100);
			g.setFont(f);
			g.setColor(Color.RED);
			g.drawString("Game Over", 180, 200);
		}
	}

	// ��װһ���л�����ķ���
	int power = 1;

	public void changeNet() {
		power++;
		if (power == 8) {
			power = 1;
		}
		net.changeNet(power);
	}

	// ��װһѭ�������ҵ���߼�
	public synchronized void catchFishs() {
		//����ӵ���С�����������ܲ���
		if(bullets<power){
			return ;
		}
		bullets-=power;//���һ�Σ��ӵ������ݶ�Ӧ��������������
		for (int i = 0; i < fishs.length; i++) {
			// ����ܹ�����״ֵ̬��Ϊtrue
			if (net.catchFish(fishs[i], power)) {
				fishs[i].catched = true;
			}
		}
	}

	// ��ص�ҵ���߼�
	public void action() {
		// ʹ�������ڲ���̳м�����
		MouseAdapter m = new MouseAdapter() {
			// ���ڸ��෽���ǿյģ�������д�Զ���ҵ���߼�
			public void mousePressed(MouseEvent e) {
				// ���������㣬�Ҽ���ֵ�������������ֵ
				int code = e.getModifiers();
				// System.out.println(code);
				if (code == 16) {// ���
					catchFishs();
				} else if (code == 4) {// �Ҽ�
					// �л�����
					changeNet();
				} else {// ����
						// ��ֵ

				}

			}

			// ׷������ƶ�mouseMoved
			public void mouseMoved(MouseEvent e) {
				// ��ȡ�������
				int x = e.getX();
				int y = e.getY();
				// System.out.println("x:"+x+",y:"+y);
				net.changeLocation(x, y);
			}

			// ׷����������Ƴ��¼�
			public void mouseEntered(MouseEvent e) {
				net.flag = true;
			}

			public void mouseExited(MouseEvent e) {
				net.flag = false;
			}
		};
		// ע�������
		this.addMouseListener(m);
		this.addMouseMotionListener(m);

		// �����̣߳����㶯����
		for (int i = 0; i < fishs.length; i++) {
			fishs[i].start();
		}
		while (true) {

			try {
				Thread.sleep(1000 / 24);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			repaint();
		}
	}
}
