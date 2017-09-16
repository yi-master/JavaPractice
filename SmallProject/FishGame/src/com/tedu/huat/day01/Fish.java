/*
 * 美人鱼
 */
package com.tedu.huat.day01;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Fish extends Thread {

	// 鱼被抓时的挣扎效果
	BufferedImage[] struggles;
	// 血量
	int blood;
	// 鱼的步伐
	int index_step;
	// 用一个变量获取m的值
	int k;
	// 鱼是否被捕获
	boolean catched;
	// 集合或者数组
	BufferedImage[] images = new BufferedImage[10];
	// 构建一个随机对象
	Random r = new Random();
	// 图片
	BufferedImage img = null;
	// 坐标
	int x;
	int y;
	// 图片有大小：宽度和高度
	int width;
	int height;

	int index = 0;

	// 通过构造器读取图片：模仿鱼池类读取图片
	public Fish(int m) throws IOException {
		if (m > 7) {
			struggles = new BufferedImage[4];
		} else {
			struggles = new BufferedImage[2];
		}
		this.k = m;// 获取m的值
		// 文件前缀名
		String prefixName = m > 9 ? m + "" : "0" + m;
		// 文件后缀名
		String sufixName = null;
		for (int i = 0; i < images.length; i++) {
			sufixName = i > 8 ? "10" : "0" + (i + 1);
			File file = new File("images/fish" + prefixName + "_" + sufixName
					+ ".png");
			images[i] = ImageIO.read(file);
		}
		// 给挣扎数组赋值
		for (int i = 0; i < struggles.length; i++) {
			File file = new File("images/fish" + prefixName + "_catch_0"
					+ (i + 1) + ".png");
			struggles[i]=ImageIO.read(file);
		}
		// 给当前的图片赋初始值
		img = images[0];

		width = img.getWidth();
		height = img.getHeight();
		index_step = r.nextInt(7) + 3;// [3,10)
		blood = m * 5;
		// 初始化坐标
		x = r.nextInt(800);
		y = 50 + r.nextInt(380 - height);
	}

	// 鱼移动的方法
	public void move() {

		// 如果鱼出界或者鱼被捕获
		//鱼先挣扎一下，在出现
		if (x < -width || catched) {
			struggle();
			newFish();
		}
		this.x -= index_step;
	}

	// 封装一个newFish
	public void newFish() {
		x = 800;
		y = 100 + r.nextInt(380 - height);
		catched = false;
		blood = k * 5;
	}

	//封装一个鱼挣扎效果的方法
	public void struggle(){
		for(int i=0;i<struggles.length;i++){
			img=struggles[i];
			//延迟挣扎效果
			try {
				Thread.sleep(1000/5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// 鱼的游动的动作
	public void change() {
		// 图片顺序
		// 123456789
		// 01234567890123456789
		// 000111222333444555666777888999
		img = images[index++ / 2 % images.length];

	}

	// 重写run方法:让鱼自己游
	public void run() {
		while (true) {
			move();
			change();
			// 睡眠
			try {
				Thread.sleep(1000 / 24);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
