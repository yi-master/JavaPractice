package GUI_Practice;
/*
 * �߽粼�֣�
 * 
 * 1.�̳�JFrame
 * 2.��������Ҫ�����
 * 3.�������
 * 4.������
 * 5.������������
 * 6.��ʾ����
 */
import javax.swing.*;
import java.awt.*;

public class Demo_2 extends JFrame{

	//�������
	JButton jb[]=new JButton[6];//jb[0-5]
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_2 demo_2=new Demo_2();
	}
	
	public Demo_2(){
	//�������
	jb[1]=new JButton("�в�");
	jb[2]=new JButton("����");
	jb[3]=new JButton("����");
	jb[4]=new JButton("�ϲ�");
	jb[5]=new JButton("����");
	
	//��Ӹ������
	this.add(jb[1],BorderLayout.CENTER);
	this.add(jb[2],BorderLayout.NORTH);
	this.add(jb[3],BorderLayout.EAST);
	this.add(jb[4],BorderLayout.SOUTH);
	this.add(jb[5],BorderLayout.WEST);
	
	//���ô�������
	this.setTitle("�߽粼�ְ���");
	this.setSize(300,200);
	this.setLocation(200,200);
	this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	
	//��ʾ����
	this.setVisible(true);
	}
}
