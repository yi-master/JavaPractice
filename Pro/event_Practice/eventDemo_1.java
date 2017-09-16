/*
 * 1.Java����ί�л��ƴ����¼�
 * 2.Java�е��¼��Ƿ���ģ����細���¼�������¼��������¼���
 * 3.Java�е�һ����Ҫ����ĳ���¼��������ʵ����Ӧ���¼������ӿ�
 * 4.�¼������ӿ��ж��֣���Բ�ͬ���ʵ�ֲ�ͬ�����ӿڣ����������¼���Ҫʵ��MouseListener
 * 5.ʵ�ּ����ӿڵ��ࣨ�¼�������/�ߣ��У���Ҫ��д��������
 * ��ʵ��ActionListener�ӿڣ���Ҫ��дactionPerformed(ActionEvent e)����
 * 6.���¼�Դ����Ҫע���¼������ࡣ�����¼���������ղ����¼�Դ�������¼�
 */
package event_Practice;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
/*
 * ���ܣ��¼��������
 */

import javax.swing.JPanel;

public class eventDemo_1 extends JFrame implements ActionListener{

	JPanel mp = null;
	JButton jb1 = null;
	JButton jb2 = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		eventDemo_1 eD1 = new eventDemo_1();
	}

	public eventDemo_1() {

		mp = new JPanel();
		jb1 = new JButton("black");
		jb2 = new JButton("red");

		this.add(jb1, BorderLayout.NORTH);
		//mp.setBackground(Color.BLACK);//����Ĭ�ϱ���ɫ
		this.add(mp);
		this.add(jb2, BorderLayout.SOUTH);
		
		Cat myCat1=new Cat();
		
		//ע�����(��JFrame��myCat1����)
		//��������ܼ���
		//�¼�Դ��˭�����¼�������jb1��jb2���¼�������˭֪���¼�������������myCat1
		jb1.addActionListener(this);
		jb2.addActionListener(this);
		jb1.addActionListener(myCat1);//jb1�����¼�����myCat1֪��
		jb2.addActionListener(myCat1);
		
		//ָ��action����
		jb1.setActionCommand("black");
		jb2.setActionCommand("red");
		
		this.setSize(200, 150);
		this.setLocationRelativeTo(null);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}

	//���¼�����ķ�����ActionEvent e���¼�����
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		//�ж����ĸ���ť�����
		if(e.getActionCommand().equals("black")){
			System.out.println("The black button is clicked");
			mp.setBackground(Color.BLACK);
		}else if(e.getActionCommand().equals("red")){
			System.out.println("The red button is clicked");
			mp.setBackground(Color.RED);
		}else{
			System.out.println("I don't know");
		}
	}
}

//�κ�һ���ֻ࣬Ҫ����ʵ������Ӧ�Ľӿ�(implements ActionListener)���Ϳ���ȥ����ĳ���¼�Դ
class Cat implements ActionListener{

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("black")){
			System.out.println("The cat knows you pressed the red button");
		}else if(e.getActionCommand().equals("red")){
			System.out.println("The cat knows you pressed the red button");
		}else{
			System.out.println("I don't know");
		}
	}
	
}

/*
 * 1.һ����Ҫʵ�ּ����Ĳ���
 * a.ʵ����Ӧ�Ľӿڣ�keyListener,MouseListener,ActionListener,WindowListener��
 * b.�ѽӿڵĴ�����������Ҫ���±�д��Override��
 * c.���¼�Դע�����
 * d.�¼������ǿ��¼�����
 */
