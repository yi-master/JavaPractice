package GUI_Practice;

/*
 * QQ����
 */
import javax.swing.*;
import java.awt.*;

public class Demo_10 extends JFrame{

	JTextArea jta=null;
	JScrollPane jsp=null;
	JPanel jp1;
	JComboBox jcb=null;
	JTextField jtf=null;
	JButton jb=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_10 demo_10=new Demo_10();
	}
	
	public Demo_10(){
		//������
		jta=new JTextArea();
		jsp=new JScrollPane(jta);//�Ѷ����ı���������������������������ʱ����ʾ��������
		jp1=new JPanel();
		String []chatter={"�°���","������"};
		jcb=new JComboBox(chatter);
		jtf =new JTextField(10);//�����ı��򳤶�
		jb=new JButton("send");
		
		//���ò���
		
		//������
		jp1.add(jcb);
		jp1.add(jtf);
		jp1.add(jb);
		
		//����JFrame
		this.add(jsp);
		this.add(jp1,BorderLayout.SOUTH);
		
		//���ô�������
		this.setTitle("QQ");
		this.setIconImage((new ImageIcon("D:/eclipse workspace/JavaPractice/images/qq.jpg").getImage()));//����QQͼ��
		this.setVisible(true);
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
