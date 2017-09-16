/*
 * ���ܣ�̹�˴�ս��Ϸ2.0
 * 1.����̹��(����(Graphics)ʵ��)
 * 2.�ҵ�̹�˿������������ƶ�(ͨ�����̿���,�����̼���(KeyListener)ʵ��)
 */
package Tank_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

//�ҵ����
class MyPanel extends JPanel implements KeyListener{
	
	//����һ���ҵ�̹��
	Hero hero=null;
	
	//������˵�̹��
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	int enemySize=3;
	
	//���캯��
	public MyPanel(){
		//�����ҵ�̹��
		hero=new Hero(100,100);//�ҵ�̹�˳�ʼ����Ϊ��100,100��
		//�����ҵ�̹������
		hero.setType(1);
		
		//��ʼ�����˵�̹��
		for(int i=0;i<enemySize;i++){
			
			//��������̹��
			EnemyTank et=new EnemyTank((i+1)*50, 0);
			//���õ���̹������
			et.setType(0);
			//���õ���̹�˳�ʼ����
			et.setDirect(2);
			//�ѵ���̹�˼��뵽������
			ets.add(et);
		}
	}
	
	//��дpaint
	public void paint(Graphics g){
		super.paint(g);
		
		//���ñ�����ɫ(��ɫ)
		g.fillRect(0, 0, 400, 300);
		
		//�����ҵ�̹��
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, hero.getType());
	
		//�������˵�̹��
		for(int i=0;i<ets.size();i++){
			this.drawTank(ets.get(i).getX(),ets.get(i).getY(), g, ets.get(i).getDirect(), ets.get(i).getType());
		}
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
				break;
		case 1://����
				//�����ҵ�̹�ˣ���ʱ���װ��һ��������
				//1.����߾���
				g.fill3DRect(x, y, 30,5,false);
				//2.�����ұ߾���
				g.fill3DRect(x, y+15, 30,5,false);
				//3.�����м����
				g.fill3DRect(x+5, y+5, 20,10,false);
				//4.����Բ��
				g.fillOval(x+10, y+5, 10,10);
				//5.������
				g.drawLine(x+15, y+10,x+30, y+10);
				break;
		case 2://����
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
				g.drawLine(x+10, y+15,x+10, y+30);
				break;
		case 3://����
			//�����ҵ�̹�ˣ���ʱ���װ��һ��������
			//1.����߾���
			g.fill3DRect(x, y, 30,5,false);
			//2.�����ұ߾���
			g.fill3DRect(x, y+15, 30,5,false);
			//3.�����м����
			g.fill3DRect(x+5, y+5, 20,10,false);
			//4.����Բ��
			g.fillOval(x+10, y+5, 10,10);
			//5.������
			g.drawLine(x+15, y+10,x, y+10);
			break;
		}	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//�������´���a��d�ң�s�£�w�ϣ�
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//�����ҵ�̹�˵ķ���
		if(e.getKeyCode()==KeyEvent.VK_W){//�����ƶ�
			
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_D){//�����ƶ�
			
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(e.getKeyCode()==KeyEvent.VK_S){//�����ƶ�
			
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_A){//�����ƶ�
			
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		
		//�ػ�Panel
		this.repaint();
	}

	@Override
	public void keyReleased(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}
}

public class MyTankGame2 extends JFrame{

	MyPanel mp=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		
		MyTankGame2 mtg=new MyTankGame2();
	}

	//���캯��
	public MyTankGame2(){
		
		mp=new MyPanel();
		
		//ע�����
		this.addKeyListener(mp);
		
		this.add(mp);
		
		//�����������
		this.setVisible(true);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}