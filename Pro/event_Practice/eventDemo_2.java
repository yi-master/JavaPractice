package event_Practice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;

/*
 * ���ܣ�������¼�������Ƶ����
 * 1.ͨ���������Ҽ�������С���ƶ���λ��
 */
public class eventDemo_2 extends JFrame{

	MyPanel_2 mp = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		eventDemo_2 eD2=new eventDemo_2();
	}

	//���캯��
	public eventDemo_2(){
		
		mp=new MyPanel_2();
		mp.setBackground(Color.GRAY);
		//mp���뵽JFrame
		this.add(mp);
		
		//���̼���(KeyListener)
		this.addKeyListener(mp);
		
		this.setSize(400,300);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}

// �����Լ������
class MyPanel_2 extends JPanel implements KeyListener{

	int x=10,y=10;
	public void paint(Graphics g) {

		super.paint(g);
		
		g.setColor(Color.cyan);
		g.fillOval(x, y, 10, 10);
	}

	//�����루û�о����ַ��ļ����ᱻ������������һ��ֵ�����
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//��������
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//e.getKeyCode()��ȡ���µļ�,(char)��ǰ���ȡ�����ֵ,û��(char)�����ASCII��
		System.out.println("(char)e.getKeyCode() pressed: "+(char)e.getKeyCode());
		//�ַ�������µļ�
		System.out.println("getKeyChar() pressed: "+e.getKeyChar());
		
		if(e.getKeyCode()==KeyEvent.VK_DOWN){
			//KeyEvent.VK_DOWN��ʾ�¼�
			//�����¼���ʾdown
			//System.out.println("down");
			
			//y+��ֵԽ�������ٶ�Խ�죬�磺y+=10
			y++;//�ı�С��y�����꣬�������¼�ʱ��С������
		}else if(e.getKeyCode()==KeyEvent.VK_UP){
			
			//y-��ֵԽ�������ٶ�Խ�죬�磺y-=10
			y--;//�ı�С��y�����꣬�������ϼ�ʱ��С������
		}else if(e.getKeyCode()==KeyEvent.VK_LEFT){
			
			//x-��ֵԽ�������ٶ�Խ�죬�磺x-=10
			x--;//�ı�С��x�����꣬���������ʱ��С������
		}else if(e.getKeyCode()==KeyEvent.VK_RIGHT){
			
			//x+��ֵԽ�������ٶ�Խ�죬�磺x+=10
			x++;//�ı�С��x�����꣬�������Ҽ�ʱ��С������
		}
		//����repaint()�����������»��ƽ��棨���½��棩
		this.repaint();
	}

	//�����ɿ�(�ͷ�)
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}
