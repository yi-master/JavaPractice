/*
 * 1.Java采用委托机制处理事件
 * 2.Java中的事件是分类的，比如窗体事件、鼠标事件、操作事件等
 * 3.Java中的一个类要监听某个事件，则必须实现相应的事件监听接口
 * 4.事件监听接口有多种，针对不同情况实现不同监听接口，如监听鼠标事件就要实现MouseListener
 * 5.实现监听接口的类（事件监听类/者）中，需要重写处理函数，
 * 如实现ActionListener接口，就要重写actionPerformed(ActionEvent e)方法
 * 6.在事件源中需要注册事件监听类。否则事件监听类接收不到事件源发生的事件
 */
package event_Practice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * 功能：事件处理机制
 */

import javax.swing.JPanel;

public class eventDemo_1 extends JFrame implements ActionListener{

	JPanel mp = null;
	JButton jb1 = null;
	JButton jb2 = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		eventDemo_1 eD1 = new eventDemo_1();
	}

	public eventDemo_1() {

		mp = new JPanel();
		jb1 = new JButton("black");
		jb2 = new JButton("red");

		this.add(jb1, BorderLayout.NORTH);
		//mp.setBackground(Color.BLACK);//设置默认背景色
		this.add(mp);
		this.add(jb2, BorderLayout.SOUTH);
		
		Cat myCat1=new Cat();
		
		//注册监听(让JFrame和myCat1监听)
		//各类对象都能监听
		//事件源（谁触发事件）对象jb1、jb2，事件监听（谁知道事件发生）对象是myCat1
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb1.addActionListener(myCat1);//jb1发生事件想让myCat1知道
		jb2.addActionListener(myCat1);
		
		//指定action命令
		jb1.setActionCommand("black");
		jb2.setActionCommand("red");
		
		this.setSize(200, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	//对事件处理的方法（ActionEvent e是事件对象）
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//判断是哪个按钮被点击
		if(e.getActionCommand().equals("black")){
			System.out.println("The black button is clicked");
			mp.setBackground(Color.BLACK);
		}else if(e.getActionCommand().equals("red")){
			System.out.println("The red button is clicked");
			mp.setBackground(Color.RED);
		}else{
			System.out.println("I don't know");
		}
	}
}

//任何一个类，只要他它实现了相应的接口(implements ActionListener)，就可以去监听某个事件源
class Cat implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("black")){
			System.out.println("The cat knows you pressed the red button");
		}else if(e.getActionCommand().equals("red")){
			System.out.println("The cat knows you pressed the red button");
		}else{
			System.out.println("I don't know");
		}
	}
	
}

/*
 * 1.一个类要实现监听的步骤
 * a.实现相应的接口（keyListener,MouseListener,ActionListener,WindowListener）
 * b.把接口的处理方法根据需要重新编写（Override）
 * c.在事件源注册监听
 * d.事件传递是靠事件对象
 */
