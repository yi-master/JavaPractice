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

	//���캯��
	public eventDemo_3(){
		
		mp=new MyPanel_3();
		
		this.add(mp);
		
		//ע�����
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
 * 1.��MyPanel_3֪����갴�µ���Ϣ������֪�������λ�ã���(x,y)
 * 2.��MyPanel_3֪���ĸ���������
 * 3.��MyPanel_3֪������ƶ�����ק(MouseMotionListener)
 * 4.��MyPanel_3֪�����ڵı仯(��󻯣���С�����ر�)
 */

//һ�������ʵ�ֶ�������ӿ�
class MyPanel_3 extends JPanel implements WindowListener,MouseListener,MouseMotionListener,KeyListener{
	
	public void paint(Graphics g){
		
		super.paint(g);
	}
	
	
	//MouseListener
	//�����
	public void mouseClicked(MouseEvent e) {
		// TODO Auto-generated method stub
		//��ʾ��������ȡ��λ��
		System.out.println("The mouse is chlicked x="+e.getX()+" y="+e.getY());
	}

	//��갴��
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//����ɿ�
	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	//�����뵽MyPanel_3
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("The mouse is entering");
	}

	//����뿪MyPanel_3e
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("The mouse is exiting");
	}
	

	//KeyListener
	//�����루û�о����ַ��ļ����ᱻ������
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	//������
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		
		//�ַ�������µļ�
		System.out.println(e.getKeyChar()+" The key is pressed");
	}

	//���ɿ�
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
	

	//MouseMotionListener
	//�����ק����
	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		System.out.println("Drag and drop mouse.");
	}

	//����ƶ�����
	public void mouseMoved(MouseEvent e) {
		// TODO Auto-generated method stub
		
		System.out.println("The current coordinates of the mouse pointer are x="+e.getX()+" y="+e.getY());
	}

	
	//WindowListener
	//���ڴ���
	public void windowOpened(WindowEvent e) {
		// TODO Auto-generated method stub
		
	}

	//�������ڹر�
	public void windowClosing(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowClosing");
	}

	//���ڹر���
	public void windowClosed(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowClosed");
	}

	//ͼ�껯����ʱ����(������С����������)
	public void windowIconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowIconified");
	}

	//ȡ��ͼ�껯����ʱ����(���´��������㿪)
	public void windowDeiconified(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowDeiconified");
	}

	//���ڼ�����(��ǰ����(���㴰��)�����,�������´��������㿪)
	public void windowActivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowActivated");
	}

	//����δ����(��ǰ����(���㴰��)������,������С�����˵���)
	public void windowDeactivated(WindowEvent e) {
		// TODO Auto-generated method stub
		System.out.println("windowDeactivated");
	}
}