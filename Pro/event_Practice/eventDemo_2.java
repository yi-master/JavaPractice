package event_Practice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * 功能：加深对事件处理机制的理解
 * 1.通过上下左右键来控制小球移动的位置
 */
public class eventDemo_2 extends JFrame{

	MyPanel_2 mp = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		eventDemo_2 eD2=new eventDemo_2();
	}

	//构造函数
	public eventDemo_2(){
		
		mp=new MyPanel_2();
		mp.setBackground(Color.GRAY);
		//mp加入到JFrame
		this.add(mp);
		
		//键盘监听(KeyListener)
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

// 定义自己的面板
class MyPanel_2 extends JPanel implements KeyListener{

	int x=10,y=10;
	public void paint(Graphics g) {

		super.paint(g);
		
		g.setColor(Color.cyan);
		g.fillOval(x, y, 10, 10);
	}

	//键输入（没有具体字符的键不会被触发），键的一个值被输出
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//键被按下
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//e.getKeyCode()获取按下的键,(char)放前面获取具体键值,没有(char)则输出ASCII码
		System.out.println("(char)e.getKeyCode() pressed: "+(char)e.getKeyCode());
		//字符输出按下的键
		System.out.println("getKeyChar() pressed: "+e.getKeyChar());
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			//KeyEvent.VK_DOWN表示下键
			//按下下键显示down
			//System.out.println("down");
			
			//y+的值越大下落速度越快，如：y+=10
			y++;//改变小球y轴坐标，即按下下键时，小球下移
		}else if(e.getKeyCode()==KeyEvent.VK_UP){
			
			//y-的值越大上升速度越快，如：y-=10
			y--;//改变小球y轴坐标，即按下上键时，小球上移
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			
			//x-的值越大左移速度越快，如：x-=10
			x--;//改变小球x轴坐标，即按下左键时，小球左移
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			
			//x+的值越大右移速度越快，如：x+=10
			x++;//改变小球x轴坐标，即按下右键时，小球右移
		}
		//调用repaint()函数，来重新绘制界面（更新界面）
		this.repaint();
	}

	//键被松开(释放)
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
