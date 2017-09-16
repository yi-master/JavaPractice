package com.tedu.huat.day01;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Net {

	/**
	 * Net类（模仿Fish类） 1.图片 2.坐标 3.宽度和高的 4.通过无参构造器读取图片并且初始化
	 */
	// 网是否显示
	boolean flag;

	// 图片
	BufferedImage img = null;
	// 网图片的中心坐标

	// 坐标
	int x;
	int y;
	// 图片有大小：宽度和高度
	int width;
	int height;

	int index = 0;

	public Net() throws IOException {
		File file = new File("images/net_1.png");
		img = ImageIO.read(file);

		width = img.getWidth();
		height = img.getHeight();
	}

	// 封装一个切换坐标的方法
	public void changeLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// 封装一个捕鱼方法
	public boolean catchFish(Fish fish,int power) {
		// 左右临界点
		int dx = this.x - fish.x;// [0,fish.width/2]
		// 上下临界点
		int dy = this.y - fish.y;// [0,fish.height/2]
		if (dx > 0 && dx < fish.width / 2 && dy > 0 && dy < fish.height / 2) {
			fish.blood-=power;	
		}
		return fish.blood<=0;
	}

	// 切换网格
	public void changeNet(int power) {
		File file = new File("images/net_" + power + ".png");
		//由于匿名内部类里面需要处理该异常，建议此时捕获掉
		try {
			img=ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//由于图片被更换，所以图片的宽度和高度发生了变化
		width=img.getWidth();
		height=img.getHeight();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
