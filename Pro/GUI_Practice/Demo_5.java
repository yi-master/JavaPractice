package GUI_Practice;

/*
 *多种布局的使用
 *JPanel组件
 */

import javax.swing.*;
import java.awt.*;

public class Demo_5 extends JFrame {

	//定义组件
	JPanel jp1,jp2;
	JButton jb[]=new JButton[6];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_5 demo_5=new Demo_5();
	}
	
	//构造函数
	public Demo_5(){
		//创建组件
		//JPanl布局默认是FlowLayout
		jp1=new JPanel();
		jp2=new JPanel();
		
		jb[0]=new JButton("关羽");
		jb[1]=new JButton("张飞");
		jb[2]=new JButton("赵云");
		jb[3]=new JButton("马超");
		jb[4]=new JButton("黄忠");
		jb[5]=new JButton("魏延");
		
		//设置布局管理器
		
		//添加JPanel
		jp1.add(jb[0]);
		jp1.add(jb[1]);
		jp2.add(jb[2]);
		jp2.add(jb[3]);
		jp2.add(jb[4]);
		
		//把Panel加入JFrame
		this.add(jp1,BorderLayout.NORTH);
		this.add(jb[5],BorderLayout.CENTER);
		this.add(jp2, BorderLayout.SOUTH);
		
		
		this.setTitle("多布局");
		this.setSize(300, 300);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
