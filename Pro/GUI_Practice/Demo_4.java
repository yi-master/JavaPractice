package GUI_Practice;
/*
 * 网格布局：
 * 窗体大小可控制组件大小
 * 组件大小都相同
 * 
 * 1.继承JFrame
 * 2.定义你需要的组件
 * 3.创建组件
 * 4.设置网格布局
 * 5.添加组件
 * 6.窗体属性设置
 * 7.显示窗体
 */
import javax.swing.*;
import java.awt.*;

public class Demo_4 extends JFrame{

	//定义组件
	JButton jbs[]=new JButton[9];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_4 demo_4=new Demo_4();
	}
	//构造函数
	public Demo_4(){
		//创建组件
		for(int i=0;i<9;i++)
			jbs[i]=new JButton(String.valueOf(i));
		
		//设置网格布局
		this.setLayout(new GridLayout(3,3,10,10));//三行三列的网格，后两个参数为水平垂直间隙
		
		//添加组件
		for(int i=0;i<9;i++)
			this.add(jbs[i]);
		
		//设置窗体属性
		this.setTitle("网格布局");
		this.setSize(350,200);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		//显示窗体
		this.setVisible(true);
	}
	
}
