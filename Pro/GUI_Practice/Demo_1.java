package GUI_Practice;

import javax.swing.*;
import java.awt.*;

//JFrame��һ�����������������������swing������ࣩ
public class Demo_1 extends JFrame{
//��Ҫ��swing����ڴ˶���
	JButton jb1=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		JFrame jf=new JFrame();
		Demo_1 demo_1=new Demo_1();
	}
	
	
	public Demo_1(){
					
				//����button��ť(JButtonҲ��������)
				JButton jb1=new JButton("This is a button");
				//���ô���
				this.setTitle("hello JFrame");
				//���ô�С
				this.setSize(200,200);
				//���JButton���
				this.add(jb1);
				//���ó�ʼλ��
				this.setLocation(100, 200);
				//���ڹر�ʱ����֤JFrame�˳���̨����
				this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
				//��ʾ
				this.setVisible(true);
	}
}
