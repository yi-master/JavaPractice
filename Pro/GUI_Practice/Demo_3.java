package GUI_Practice;
/*
 * 流式布局：
 * 不能改变组件大小
 * 组件默认居中对齐
 * 
 * 步骤：
 * 1.继承JFrame
 * 2.定义你需要的组件
 * 3.创建组件
 * 4.添加组件
 * 5.设置布局管理器
 * 6.窗体属性设置
 * 7.显示窗体
 */

import javax.swing.*;
import java.awt.*;;

public class Demo_3 extends JFrame{

	//定义需要的组件
	JButton jb[]=new JButton[6];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_3 demo_3=new Demo_3();
	}
	
	public Demo_3(){
		
		//创建组件
		jb[0]=new JButton("关羽");
		jb[1]=new JButton("张飞");
		jb[2]=new JButton("赵云");
		jb[3]=new JButton("马超");
		jb[4]=new JButton("黄忠");
		jb[5]=new JButton("魏延");
		
		//添加组件
		this.add(jb[0]);
		this.add(jb[1]);
		this.add(jb[2]);
		this.add(jb[3]);
		this.add(jb[4]);
		this.add(jb[5]);
		
		//设置布局管理器
		this.setLayout(new FlowLayout(FlowLayout.CENTER));//居中对齐
		
		//设置窗体属性
		this.setTitle("流式布局案例");
		this.setSize(350,200);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);//禁止改变窗体大小
		//显示窗体
		this.setVisible(true);
	}

}
