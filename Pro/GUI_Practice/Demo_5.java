package GUI_Practice;

/*
 *���ֲ��ֵ�ʹ��
 *JPanel���
 */

import javax.swing.*;
import java.awt.*;

public class Demo_5 extends JFrame {

	//�������
	JPanel jp1,jp2;
	JButton jb[]=new JButton[6];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_5 demo_5=new Demo_5();
	}
	
	//���캯��
	public Demo_5(){
		//�������
		//JPanl����Ĭ����FlowLayout
		jp1=new JPanel();
		jp2=new JPanel();
		
		jb[0]=new JButton("����");
		jb[1]=new JButton("�ŷ�");
		jb[2]=new JButton("����");
		jb[3]=new JButton("��");
		jb[4]=new JButton("����");
		jb[5]=new JButton("κ��");
		
		//���ò��ֹ�����
		
		//���JPanel
		jp1.add(jb[0]);
		jp1.add(jb[1]);
		jp2.add(jb[2]);
		jp2.add(jb[3]);
		jp2.add(jb[4]);
		
		//��Panel����JFrame
		this.add(jp1,BorderLayout.NORTH);
		this.add(jb[5],BorderLayout.CENTER);
		this.add(jp2, BorderLayout.SOUTH);
		
		
		this.setTitle("�಼��");
		this.setSize(300, 300);
		this.setLocation(100,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

}
