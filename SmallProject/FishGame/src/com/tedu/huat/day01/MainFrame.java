/*
 * ��Ϸ������
 * @author yi_master
 * @since 1.0
 */

package com.tedu.huat.day01;

import java.io.IOException;

import javax.swing.JFrame;

public class MainFrame {

	/**
	 * @param args
	 * @throws IOException 
	 */
	public static void main(String[] args) throws IOException {
		//��Ǯ��
		JFrame frame=new JFrame("�������1.0");
		//���ô�С
		frame.setSize(800,480);
		//���д���
		frame.setLocationRelativeTo(null);
		//�������ӵ������
		Pool p=new Pool();
		frame.add(p);
		//����Ĭ���˳��رշ�ʽ
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//show��Ǯ��(��ʾ����)
		frame.setVisible(true);
		//������Ϸ
		p.action();
		
	}

}
