/*
 * 游戏主界面
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
		//买钱包
		JFrame frame=new JFrame("捕鱼达人1.0");
		//设置大小
		frame.setSize(800,480);
		//居中窗口
		frame.setLocationRelativeTo(null);
		//将鱼池添加到面板中
		Pool p=new Pool();
		frame.add(p);
		//设置默认退出关闭方式
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		//show出钱包(显示画框)
		frame.setVisible(true);
		//启动游戏
		p.action();
		
	}

}
