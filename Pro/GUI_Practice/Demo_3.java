package GUI_Practice;
/*
 * ��ʽ���֣�
 * ���ܸı������С
 * ���Ĭ�Ͼ��ж���
 * 
 * ���裺
 * 1.�̳�JFrame
 * 2.��������Ҫ�����
 * 3.�������
 * 4.������
 * 5.���ò��ֹ�����
 * 6.������������
 * 7.��ʾ����
 */

import javax.swing.*;
import java.awt.*;;

public class Demo_3 extends JFrame{

	//������Ҫ�����
	JButton jb[]=new JButton[6];
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_3 demo_3=new Demo_3();
	}
	
	public Demo_3(){
		
		//�������
		jb[0]=new JButton("����");
		jb[1]=new JButton("�ŷ�");
		jb[2]=new JButton("����");
		jb[3]=new JButton("��");
		jb[4]=new JButton("����");
		jb[5]=new JButton("κ��");
		
		//������
		this.add(jb[0]);
		this.add(jb[1]);
		this.add(jb[2]);
		this.add(jb[3]);
		this.add(jb[4]);
		this.add(jb[5]);
		
		//���ò��ֹ�����
		this.setLayout(new FlowLayout(FlowLayout.CENTER));//���ж���
		
		//���ô�������
		this.setTitle("��ʽ���ְ���");
		this.setSize(350,200);
		this.setLocation(200,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setResizable(false);//��ֹ�ı䴰���С
		//��ʾ����
		this.setVisible(true);
	}

}
