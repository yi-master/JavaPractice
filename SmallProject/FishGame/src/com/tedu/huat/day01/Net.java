package com.tedu.huat.day01;

import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;

public class Net {

	/**
	 * Net�ࣨģ��Fish�ࣩ 1.ͼƬ 2.���� 3.��Ⱥ͸ߵ� 4.ͨ���޲ι�������ȡͼƬ���ҳ�ʼ��
	 */
	// ���Ƿ���ʾ
	boolean flag;

	// ͼƬ
	BufferedImage img = null;
	// ��ͼƬ����������

	// ����
	int x;
	int y;
	// ͼƬ�д�С����Ⱥ͸߶�
	int width;
	int height;

	int index = 0;

	public Net() throws IOException {
		File file = new File("images/net_1.png");
		img = ImageIO.read(file);

		width = img.getWidth();
		height = img.getHeight();
	}

	// ��װһ���л�����ķ���
	public void changeLocation(int x, int y) {
		this.x = x;
		this.y = y;
	}

	// ��װһ�����㷽��
	public boolean catchFish(Fish fish,int power) {
		// �����ٽ��
		int dx = this.x - fish.x;// [0,fish.width/2]
		// �����ٽ��
		int dy = this.y - fish.y;// [0,fish.height/2]
		if (dx > 0 && dx < fish.width / 2 && dy > 0 && dy < fish.height / 2) {
			fish.blood-=power;	
		}
		return fish.blood<=0;
	}

	// �л�����
	public void changeNet(int power) {
		File file = new File("images/net_" + power + ".png");
		//���������ڲ���������Ҫ������쳣�������ʱ�����
		try {
			img=ImageIO.read(file);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		//����ͼƬ������������ͼƬ�Ŀ�Ⱥ͸߶ȷ����˱仯
		width=img.getWidth();
		height=img.getHeight();
	}

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

}
