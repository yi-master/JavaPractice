package GUI_Practice;

import javax.swing.*;
import java.awt.*;

//JFrame是一个顶层容器（可以添加其他swing组件的类）
public class Demo_1 extends JFrame{
//需要的swing组件在此定义
	JButton jb1=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf=new JFrame();
		Demo_1 demo_1=new Demo_1();
	}
	
	
	public Demo_1(){
					
				//创建button按钮(JButton也是容器类)
				JButton jb1=new JButton("This is a button");
				//设置窗体
				this.setTitle("hello JFrame");
				//设置大小
				this.setSize(200,200);
				//添加JButton组件
				this.add(jb1);
				//设置初始位置
				this.setLocation(100, 200);
				//窗口关闭时，保证JFrame退出后台进程
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//显示
				this.setVisible(true);
	}
}
