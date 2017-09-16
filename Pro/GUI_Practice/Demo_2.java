package GUI_Practice;
/*
 * 边界布局：
 * 
 * 1.继承JFrame
 * 2.定义你需要的组件
 * 3.创建组件
 * 4.添加组件
 * 5.窗体属性设置
 * 6.显示窗体
 */
import javax.swing.*;
import java.awt.*;

public class Demo_2 extends JFrame{

	//定义组件
	JButton jb[]=new JButton[6];//jb[0-5]
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_2 demo_2=new Demo_2();
	}
	
	public Demo_2(){
	//创建组件
	jb[1]=new JButton("中部");
	jb[2]=new JButton("北部");
	jb[3]=new JButton("东部");
	jb[4]=new JButton("南部");
	jb[5]=new JButton("西部");
	
	//添加各个组件
	this.add(jb[1],BorderLayout.CENTER);
	this.add(jb[2],BorderLayout.NORTH);
	this.add(jb[3],BorderLayout.EAST);
	this.add(jb[4],BorderLayout.SOUTH);
	this.add(jb[5],BorderLayout.WEST);
	
	//设置窗体属性
	this.setTitle("边界布局案例");
	this.setSize(300,200);
	this.setLocation(200,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//显示窗体
	this.setVisible(true);
	}
}
