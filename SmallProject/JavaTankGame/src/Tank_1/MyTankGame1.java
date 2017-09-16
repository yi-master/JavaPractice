/*
 * ���ܣ�̹�˴�ս��Ϸ1.0
 * 1.����̹��(����(Graphics)ʵ��)
 */
package Tank_1;

import javax.swing.*;
import java.awt.*;

public class MyTankGame1 extends JFrame{

	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyTankGame1 mtg=new MyTankGame1();
	}

	//���캯��
	public MyTankGame1(){
		
		mp=new MyPanel();
		
		this.add(mp);
		this.setVisible(true);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

//�ҵ����
class MyPanel extends JPanel{
	
	//����һ���ҵ�̹��
	Hero hero=null;
	
	//���캯��
	public MyPanel(){
		hero=new Hero(100,100);//�ҵ�̹�˳�ʼ����Ϊ��100,100��
	}
	
	//��дpaint
	public void paint(Graphics g){
		super.paint(g);
		
		//���ñ�����ɫ(��ɫ)
		g.fillRect(0, 0, 400, 300);
		
		this.drawTank(hero.getX(), hero.getY(), g, 0, 1);
	}
	
	//����̹�ˣ������꣬�����꣬���ʣ��������ͣ�
	public void drawTank(int x, int y,Graphics g,int direct,int type){
		
		//�ж�̹������
		switch(type){
		case 0:
			//���û�����ɫ��������ǳ��ɫ̹�ˣ�
			g.setColor(Color.CYAN);
			break;
		case 1:
			//���û�����ɫ����������ɫ̹�ˣ�
			g.setColor(Color.yellow);
		}
		
		//�жϷ���
		switch(direct){
		case 0://����
				//�����ҵ�̹�ˣ���ʱ���װ��һ��������
				//1.����߾���
				g.fill3DRect(x, y, 5,30,false);
				//2.�����ұ߾���
				g.fill3DRect(x+15, y, 5,30,false);
				//3.�����м����
				g.fill3DRect(x+5, y+5, 10,20,false);
				//4.����Բ��
				g.fillOval(x+5, y+10, 10,10);
				//5.������
				g.drawLine(x+10, y+15,x+10, y);
		}	
	}
}

//̹����
class Tank{

	//̹�˵ĺ�����
	private int x=0;
	//̹�˵�������
	private int y=0;
	
	public int getX() {
		return x;
	}

	public void setX(int x) {
		this.x = x;
	}

	public int getY() {
		return y;
	}

	public void setY(int y) {
		this.y = y;
	}
	
	public Tank(int x,int y){
		this.x=x;
		this.y=y;
	}
}

//�ҵ�̹��
class Hero extends Tank{

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
}