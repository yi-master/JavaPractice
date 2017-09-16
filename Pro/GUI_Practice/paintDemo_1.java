package GUI_Practice;

import java.awt.*;
import javax.swing.*;
/*
 * 功能：Java绘图原理
 */

public class paintDemo_1 extends JFrame {

	MyPanel mp = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		paintDemo_1 Tdemo_1 = new paintDemo_1();

	}

	public paintDemo_1() {
		mp = new MyPanel();
		this.add(mp);
		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}

// 定义一个MyPanel(我自己的面板，是用于绘图和现实绘图的区域)
class MyPanel extends JPanel {
	// 覆盖JPanel的paint方法
	// Graphices是绘图的重要类，你可以把它理解成一只画笔
	public void paint(Graphics g) {
		// 1.调用父类函数完成初始化
		super.paint(g);// 这句话最好别少
		// System.out.println("paint被调用");//paint最大化最小化都会被调用
		// //画一个圆
		// g.drawOval(10, 10, 30, 30);
		// //画直线
		// g.drawLine(10, 10, 40, 40);
		// //画矩形边框
		// g.drawRect(10, 20, 30, 40);
		// //填充矩形
		// g.setColor(Color.blue);
		// g.fillRect(50,50 , 30, 40);
		// g.setColor(Color.red);
		// g.fillRect(100,100 , 30, 40);

		// //在面板上画图片
		// Image im=Toolkit.getDefaultToolkit().getImage
		// (Panel.class.getResource("D:/eclipse
		// workspace/JavaPractice/Pro/GUI_Practice/finally_dinner.jpg"));
		// //实现g.drawImage(img, x, y, width, height, observer)
		// //width,height和原图比例最好一致
		// g.drawImage(im, 10,10, 200,100,this);

		// //画出字
		// g.setColor(Color.RED);//颜色
		// g.setFont(new
		// Font("华文彩云",Font.BOLD,50));//字体样式及其大小（如果找不到设置的字体样式，则默认为宋体）
		// g.drawString("Chinese", 100, 100);

		// 画弧线
		g.drawArc(100, 100, 120, 400, 50, 100);
	}
}