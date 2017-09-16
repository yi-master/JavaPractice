package event_Practice;

import java.awt.*;
import java.awt.Event.*;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;
import java.awt.event.WindowEvent;
import java.awt.event.WindowListener;

import javax.swing.*;

public class eventDemo_3 extends JFrame{

	MyPanel_3 mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		eventDemo_3 eD3=new eventDemo_3();
	}

	//构造函数
	public eventDemo_3(){
		
		mp=new MyPanel_3();
		
		this.add(mp);
		
		//注册监听
		this.addMouseListener(mp);
		this.addKeyListener(mp);
		this.addMouseMotionListener(mp);
		this.addWindowListener(mp);
		
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}

/*
 * 1.让MyPanel_3知道鼠标按下的消息，并且知道点击的位置，即(x,y)
 * 2.让MyPanel_3知道哪个键按下了
 * 3.让MyPanel_3知道鼠标移动，拖拽(MouseMotionListener)
 * 4.让MyPanel_3知道窗口的变化(最大化，最小化，关闭)
 */

//一个类可以实现多个监听接口
class MyPanel_3 extends JPanel implements WindowListener,MouseListener,MouseMotionListener,KeyListener{
	
	public void paint(Graphics g){
		
		super.paint(g);
	}
	
	
	//MouseListener
	//鼠标点击
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//显示单击鼠标获取的位置
		System.out.println("The mouse is chlicked x="+e.getX()+" y="+e.getY());
	}

	//鼠标按下
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//鼠标松开
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//鼠标进入到MyPanel_3
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("The mouse is entering");
	}

	//鼠标离开MyPanel_3e
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("The mouse is exiting");
	}
	

	//KeyListener
	//键输入（没有具体字符的键不会被触发）
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//键按下
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//字符输出按下的键
		System.out.println(e.getKeyChar()+" The key is pressed");
	}

	//键松开
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	//MouseMotionListener
	//鼠标拖拽监听
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Drag and drop mouse.");
	}

	//鼠标移动监听
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("The current coordinates of the mouse pointer are x="+e.getX()+" y="+e.getY());
	}

	
	//WindowListener
	//窗口打开了
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//窗口正在关闭
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowClosing");
	}

	//窗口关闭了
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowClosed");
	}

	//图标化窗口时调用(窗口最小化至任务栏)
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowIconified");
	}

	//取消图标化窗口时调用(重新从任务栏点开)
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowDeiconified");
	}

	//窗口激活了(当前窗口(顶层窗口)变成它,包括重新从任务栏点开)
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowActivated");
	}

	//窗口未激活(当前窗口(顶层窗口)不是它,包括最小化至菜单栏)
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowDeactivated");
	}
}