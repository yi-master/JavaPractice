/*
 * 功能：坦克大战游戏1.0
 * 1.画出坦克(画笔(Graphics)实现)
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

	//构造函数
	public MyTankGame1(){
		
		mp=new MyPanel();
		
		this.add(mp);
		this.setVisible(true);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
	}
}

//我的面板
class MyPanel extends JPanel{
	
	//定义一个我的坦克
	Hero hero=null;
	
	//构造函数
	public MyPanel(){
		hero=new Hero(100,100);//我的坦克初始坐标为（100,100）
	}
	
	//重写paint
	public void paint(Graphics g){
		super.paint(g);
		
		//设置背景颜色(黑色)
		g.fillRect(0, 0, 400, 300);
		
		this.drawTank(hero.getX(), hero.getY(), g, 0, 1);
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
		}	
	}
}

//坦克类
class Tank{

	//坦克的横坐标
	private int x=0;
	//坦克的纵坐标
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

//我的坦克
class Hero extends Tank{

	public Hero(int x, int y) {
		super(x, y);
		// TODO Auto-generated constructor stub
	}
	
}