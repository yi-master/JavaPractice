package GUI_Practice;

/*
 * �������
 */
import javax.swing.*;
import java.awt.*;

public class Demo_9 extends JFrame{

	//�������
	JSplitPane jsp;//��ִ���
	JTextArea jta;
	JLabel jl1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_9 demo_9=new Demo_9();
	}

	
	public Demo_9(){
		//�������
		
		jta=new JTextArea(" ������� ������滭���� \n"
				+"���������(The Last Supper)��\n"
				+ "��������������а��ɶࡤ�������������\n"
				+ "�������������Ĵ�������Ʒ����������һ����\n"
				+ "�����е����\n"
				+ "�Ծ��֡���ŭ�����ɡ��ʰ׵���̬��\n"
				+ "�Լ����ơ��������Ϊ��\n"
				+ "���̻��þ�ϸ��΢��\n"
				+ "Ω��ΩФ��\n"
				+ "�ղ������������ʥ�����ǵ¶������޵�Ժ��\n");//JTextArea�����ı������
		
		//���ͼƬ��·����/��\\
		jl1=new JLabel(new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/GUI_Practice/finally_dinner.jpg"));
		
		//��ִ���
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jta,jl1);//ˮƽ��֣����Ų�֣�
		
		//���Ա仯
		jsp.setOneTouchExpandable(true);
		
		//���ò��ֹ�����(��Ϊ�Ѿ�����˴�����Բ��ò���)
		
		//������
		this.add(jsp);
		
		//���ô�������
		this.setTitle("The Last Supper");
		this.setSize(900,500);
		this.setLocation(200, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
