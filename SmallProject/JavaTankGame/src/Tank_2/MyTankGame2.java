/*
 * 功能：坦克大战游戏2.0
 * 1.画出坦克(画笔(Graphics)实现)
 * 2.我的坦克可以上下左右移动(通过键盘控制,即键盘监听(KeyListener)实现)
 */
package Tank_2;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Vector;

//我的面板
class MyPanel extends JPanel implements KeyListener{
	
	//定义一个我的坦克
	Hero hero=null;
	
	//定义敌人的坦克
	Vector<EnemyTank> ets=new Vector<EnemyTank>();
	
	int enemySize=3;
	
	//构造函数
	public MyPanel(){
		//创建我的坦克
		hero=new Hero(100,100);//我的坦克初始坐标为（100,100）
		//设置我的坦克类型
		hero.setType(1);
		
		//初始化敌人的坦克
		for(int i=0;i<enemySize;i++){
			
			//创建敌人坦克
			EnemyTank et=new EnemyTank((i+1)*50, 0);
			//设置敌人坦克类型
			et.setType(0);
			//设置敌人坦克初始方向
			et.setDirect(2);
			//把敌人坦克加入到向量中
			ets.add(et);
		}
	}
	
	//重写paint
	public void paint(Graphics g){
		super.paint(g);
		
		//设置背景颜色(黑色)
		g.fillRect(0, 0, 400, 300);
		
		//画出我的坦克
		this.drawTank(hero.getX(), hero.getY(), g, this.hero.direct, hero.getType());
	
		//画出敌人的坦克
		for(int i=0;i<ets.size();i++){
			this.drawTank(ets.get(i).getX(),ets.get(i).getY(), g, ets.get(i).getDirect(), ets.get(i).getType());
		}
	}
	
	//画出坦克（横坐标，纵坐标，画笔，方向，类型）
	public void drawTank(int x, int y,Graphics g,int direct,int type){
		
		//判断坦克类型
		switch(type){
		case 0:
			//设置画笔颜色（即画出浅蓝色坦克）
			g.setColor(Color.CYAN);
			break;
		case 1:
			//设置画笔颜色（即画出黄色坦克）
			g.setColor(Color.yellow);
		}
		
		//判断方向
		switch(direct){
		case 0://向上
				//画出我的坦克（到时候封装成一个函数）
				//1.画左边矩形
				g.fill3DRect(x, y, 5,30,false);
				//2.画出右边矩形
				g.fill3DRect(x+15, y, 5,30,false);
				//3.画出中间矩形
				g.fill3DRect(x+5, y+5, 10,20,false);
				//4.画出圆形
				g.fillOval(x+5, y+10, 10,10);
				//5.画出线
				g.drawLine(x+10, y+15,x+10, y);
				break;
		case 1://向右
				//画出我的坦克（到时候封装成一个函数）
				//1.画左边矩形
				g.fill3DRect(x, y, 30,5,false);
				//2.画出右边矩形
				g.fill3DRect(x, y+15, 30,5,false);
				//3.画出中间矩形
				g.fill3DRect(x+5, y+5, 20,10,false);
				//4.画出圆形
				g.fillOval(x+10, y+5, 10,10);
				//5.画出线
				g.drawLine(x+15, y+10,x+30, y+10);
				break;
		case 2://向下
				//画出我的坦克（到时候封装成一个函数）
				//1.画左边矩形
				g.fill3DRect(x, y, 5,30,false);
				//2.画出右边矩形
				g.fill3DRect(x+15, y, 5,30,false);
				//3.画出中间矩形
				g.fill3DRect(x+5, y+5, 10,20,false);
				//4.画出圆形
				g.fillOval(x+5, y+10, 10,10);
				//5.画出线
				g.drawLine(x+10, y+15,x+10, y+30);
				break;
		case 3://向左
			//画出我的坦克（到时候封装成一个函数）
			//1.画左边矩形
			g.fill3DRect(x, y, 30,5,false);
			//2.画出右边矩形
			g.fill3DRect(x, y+15, 30,5,false);
			//3.画出中间矩形
			g.fill3DRect(x+5, y+5, 20,10,false);
			//4.画出圆形
			g.fillOval(x+10, y+5, 10,10);
			//5.画出线
			g.drawLine(x+15, y+10,x, y+10);
			break;
		}	
	}

	@Override
	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	@Override
	//键被按下处理（a左，d右，s下，w上）
	public void keyPressed(KeyEvent e) {
		// TODO Auto-generated method stub
		//设置我的坦克的方向
		if(e.getKeyCode()==KeyEvent.VK_W){//向上移动
			
			this.hero.setDirect(0);
			this.hero.moveUp();
		}else if(e.getKeyCode()==KeyEvent.VK_D){//向右移动
			
			this.hero.setDirect(1);
			this.hero.moveRight();
		}else if(e.getKeyCode()==KeyEvent.VK_S){//向下移动
			
			this.hero.setDirect(2);
			this.hero.moveDown();
		}else if(e.getKeyCode()==KeyEvent.VK_A){//向上移动
			
			this.hero.setDirect(3);
			this.hero.moveLeft();
		}
		
		//重绘Panel
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

	//构造函数
	public MyTankGame2(){
		
		mp=new MyPanel();
		
		//注册监听
		this.addKeyListener(mp);
		
		this.add(mp);
		
		//设置面板属性
		this.setVisible(true);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setLocationRelativeTo(null);
	}
}