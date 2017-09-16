/*
 * ������
 */
package com.tedu.huat.day01;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Random;

import javax.imageio.ImageIO;

public class Fish extends Thread {

	// �㱻ץʱ������Ч��
	BufferedImage[] struggles;
	// Ѫ��
	int blood;
	// ��Ĳ���
	int index_step;
	// ��һ��������ȡm��ֵ
	int k;
	// ���Ƿ񱻲���
	boolean catched;
	// ���ϻ�������
	BufferedImage[] images = new BufferedImage[10];
	// ����һ���������
	Random r = new Random();
	// ͼƬ
	BufferedImage img = null;
	// ����
	int x;
	int y;
	// ͼƬ�д�С����Ⱥ͸߶�
	int width;
	int height;

	int index = 0;

	// ͨ����������ȡͼƬ��ģ��������ȡͼƬ
	public Fish(int m) throws IOException {
		if (m > 7) {
			struggles = new BufferedImage[4];
		} else {
			struggles = new BufferedImage[2];
		}
		this.k = m;// ��ȡm��ֵ
		// �ļ�ǰ׺��
		String prefixName = m > 9 ? m + "" : "0" + m;
		// �ļ���׺��
		String sufixName = null;
		for (int i = 0; i < images.length; i++) {
			sufixName = i > 8 ? "10" : "0" + (i + 1);
			File file = new File("images/fish" + prefixName + "_" + sufixName
					+ ".png");
			images[i] = ImageIO.read(file);
		}
		// ���������鸳ֵ
		for (int i = 0; i < struggles.length; i++) {
			File file = new File("images/fish" + prefixName + "_catch_0"
					+ (i + 1) + ".png");
			struggles[i]=ImageIO.read(file);
		}
		// ����ǰ��ͼƬ����ʼֵ
		img = images[0];

		width = img.getWidth();
		height = img.getHeight();
		index_step = r.nextInt(7) + 3;// [3,10)
		blood = m * 5;
		// ��ʼ������
		x = r.nextInt(800);
		y = 50 + r.nextInt(380 - height);
	}

	// ���ƶ��ķ���
	public void move() {

		// ������������㱻����
		//��������һ�£��ڳ���
		if (x < -width || catched) {
			struggle();
			newFish();
		}
		this.x -= index_step;
	}

	// ��װһ��newFish
	public void newFish() {
		x = 800;
		y = 100 + r.nextInt(380 - height);
		catched = false;
		blood = k * 5;
	}

	//��װһ��������Ч���ķ���
	public void struggle(){
		for(int i=0;i<struggles.length;i++){
			img=struggles[i];
			//�ӳ�����Ч��
			try {
				Thread.sleep(1000/5);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
	// ����ζ��Ķ���
	public void change() {
		// ͼƬ˳��
		// 123456789
		// 01234567890123456789
		// 000111222333444555666777888999
		img = images[index++ / 2 % images.length];

	}

	// ��дrun����:�����Լ���
	public void run() {
		while (true) {
			move();
			change();
			// ˯��
			try {
				Thread.sleep(1000 / 24);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
