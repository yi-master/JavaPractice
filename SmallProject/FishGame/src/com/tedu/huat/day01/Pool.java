/*
 * 鱼池类
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

	//子弹数
	int bullets=100;
	//分数
	int score;
	
	// 背景图片
	BufferedImage bgImg = null;

	// 放入20条鱼
	int num = 20;
	Fish[] fishs = new Fish[num];

	Net net = null;

	// 通过构造器读取图片
	// 1.找到图片的地址
	// 2.通过工具类读取图片
	public Pool() throws IOException {
		File file = new File("images/bg.jpg");
		bgImg = ImageIO.read(file);
		// 给fishs赋初始值
		for (int i = 0; i < fishs.length / 2 - 1; i++) {
			fishs[i] = new Fish(i + 1);// 第1条到第9条鱼，数组下标为0~8
			fishs[i + 9] = new Fish(i + 1);// 第10条到第18条鱼，数组下标为9~17
		}
		fishs[18] = new Fish(13);// 第19条鱼，下标：18
		fishs[19] = new Fish(14);// 第20条鱼，下标：19
		net = new Net();
	}

	// 绘制背景图片
	public void paint(Graphics g) {
		// 画图片
		g.drawImage(bgImg, 0, 0, null);
		// 画鱼
		for (int i = 0; i < fishs.length; i++) {
			g.drawImage(fishs[i].img, fishs[i].x, fishs[i].y, null);
		}// 画网
		if (net.flag) {
			g.drawImage(net.img, net.x - net.width / 2, net.y - net.height / 2,
					null);
		}
		//设置字体颜色
		Font font =new Font("隶书",Font.BOLD,20);
		g.setFont(font);
		g.setColor(Color.CYAN);
		g.drawString("捕鱼达人1.0",30,25);
		g.drawString("子弹数："+bullets,155,25);
		g.drawString("当前得分："+score,300,25);
		g.drawString("左键捕鱼，右键加强，滚轴充值："+power,450,25);
		//提醒游戏结束
		if(bullets<=0){
			Font f=new Font("隶书",Font.BOLD,100);
			g.setFont(f);
			g.setColor(Color.RED);
			g.drawString("Game Over", 180, 200);
		}
	}

	// 封装一个切换网格的方法
	int power = 1;

	public void changeNet() {
		power++;
		if (power == 8) {
			power = 1;
		}
		net.changeNet(power);
	}

	// 封装一循环捕鱼的业务逻辑
	public synchronized void catchFishs() {
		//如果子弹数小于能量，不能捕获
		if(bullets<power){
			return ;
		}
		bullets-=power;//点击一次，子弹数根据对应的网格能量变少
		for (int i = 0; i < fishs.length; i++) {
			// 如果能够捕获，状态值设为true
			if (net.catchFish(fishs[i], power)) {
				fishs[i].catched = true;
			}
		}
	}

	// 鱼池的业务逻辑
	public void action() {
		// 使用匿名内部类继承监听器
		MouseAdapter m = new MouseAdapter() {
			// 由于父类方法是空的，建议重写自定义业务逻辑
			public void mousePressed(MouseEvent e) {
				// 鼠标左键捕鱼，右键充值加能量，滚珠充值
				int code = e.getModifiers();
				// System.out.println(code);
				if (code == 16) {// 左键
					catchFishs();
				} else if (code == 4) {// 右键
					// 切换能量
					changeNet();
				} else {// 滚珠
						// 充值

				}

			}

			// 追加鼠标移动mouseMoved
			public void mouseMoved(MouseEvent e) {
				// 获取鼠标坐标
				int x = e.getX();
				int y = e.getY();
				// System.out.println("x:"+x+",y:"+y);
				net.changeLocation(x, y);
			}

			// 追加鼠标进入和移出事件
			public void mouseEntered(MouseEvent e) {
				net.flag = true;
			}

			public void mouseExited(MouseEvent e) {
				net.flag = false;
			}
		};
		// 注册监听器
		this.addMouseListener(m);
		this.addMouseMotionListener(m);

		// 启动线程，让鱼动起来
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
