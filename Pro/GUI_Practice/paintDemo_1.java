package GUI_Practice;

import java.awt.*;
import javax.swing.*;
/*
 * ���ܣ�Java��ͼԭ��
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

// ����һ��MyPanel(���Լ�����壬�����ڻ�ͼ����ʵ��ͼ������)
class MyPanel extends JPanel {
	// ����JPanel��paint����
	// Graphices�ǻ�ͼ����Ҫ�࣬����԰�������һֻ����
	public void paint(Graphics g) {
		// 1.���ø��ຯ����ɳ�ʼ��
		super.paint(g);// ��仰��ñ���
		// System.out.println("paint������");//paint�����С�����ᱻ����
		// //��һ��Բ
		// g.drawOval(10, 10, 30, 30);
		// //��ֱ��
		// g.drawLine(10, 10, 40, 40);
		// //�����α߿�
		// g.drawRect(10, 20, 30, 40);
		// //������
		// g.setColor(Color.blue);
		// g.fillRect(50,50 , 30, 40);
		// g.setColor(Color.red);
		// g.fillRect(100,100 , 30, 40);

		// //������ϻ�ͼƬ
		// Image im=Toolkit.getDefaultToolkit().getImage
		// (Panel.class.getResource("D:/eclipse
		// workspace/JavaPractice/Pro/GUI_Practice/finally_dinner.jpg"));
		// //ʵ��g.drawImage(img, x, y, width, height, observer)
		// //width,height��ԭͼ�������һ��
		// g.drawImage(im, 10,10, 200,100,this);

		// //������
		// g.setColor(Color.RED);//��ɫ
		// g.setFont(new
		// Font("���Ĳ���",Font.BOLD,50));//������ʽ�����С������Ҳ������õ�������ʽ����Ĭ��Ϊ���壩
		// g.drawString("Chinese", 100, 100);

		// ������
		g.drawArc(100, 100, 120, 400, 50, 100);
	}
}