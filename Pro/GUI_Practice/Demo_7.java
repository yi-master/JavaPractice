package GUI_Practice;
/*
 * ��ѡ��(JCheckBox)�͵�ѡ��ť(JRadioButton)
 */

import javax.swing.*;
import java.awt.*;

public class Demo_7 extends JFrame{
	
	//�������
	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2;
	JButton jb1,jb2,jb3;
	JCheckBox jcb1,jcb2,jcb3;
	JRadioButton jrb1,jrb2;
	ButtonGroup bg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_7 demo_7=new Demo_7();
	}

	//���캯��
	public Demo_7(){
		//�������
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jl1=new JLabel("��ϲ�����˶�");
		jl2=new JLabel("����Ա�");
		
		jb1=new JButton("ע���û�");
		jb2=new JButton("ȡ��ע��");
		
		jcb1=new JCheckBox("����");
		jcb2=new JCheckBox("����");
		jcb3=new JCheckBox("����");
		
		jrb1=new JRadioButton("��");
		jrb2=new JRadioButton("Ů");
		
		//�ѵ�ѡ��ť(JRadioButton)jrb1��jrb2���뵽һ��ButtonGroup
		//ButtonGroup���뵥ѡ��ť(JRadioButton)���ʹ��
		//JPanel��ӻ�����ӵ�ѡ��ť(JRadioButton)����jrb1��jrb2
		bg=new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		
		//���ò��ֹ�����
		this.setLayout(new GridLayout(3, 1));//����һ�����񲼾�
		
		//������
		//��һ��
		jp1.add(jl1);
		jp1.add(jcb1);
		jp1.add(jcb2);
		jp1.add(jcb3);
		
		//�ڶ���
		jp2.add(jl2);
		jp2.add(jrb1);//�ѵ�ѡ��ť(JRadioButton)��ӵ�JPanel
		jp2.add(jrb2);
		
		//������
		jp3.add(jb1);
		jp3.add(jb2);
		
		//��������JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		//���ô�������
		this.setSize(300,150);
		this.setLocation(150,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
