package GUI_Practice;

/*
 * QQ��¼����
 */
import javax.swing.*;
import java.awt.*;

public class qq_Demo_11 extends JFrame{

	private static final int Font = 0;

	//��������
	JLabel jl1;
	
	//�ϱ�����
	JButton jb1,jb2,jb3;
	JPanel jp1;
	
	//�в�����
	JTabbedPane jtp;//ѡ�����
	JPanel jp2,jp3,jp4;
	
	JLabel jl2,jl3,jl4,jl5;
	JTextField jtf;//�����ı���
	JPasswordField jpf;//�����
	JButton jb4;//����˺�
	JCheckBox jcb1,jcb2;//�����¼����ס����
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new qq_Demo_11();
	}
	public qq_Demo_11(){
		//�������
		jl2 =new JLabel("QQ����",JLabel.CENTER);
		jl3 =new JLabel("QQ����",JLabel.CENTER);
		jl4 =new JLabel("��������",JLabel.CENTER);
		
		jl4.setFont(new Font("����", Font, 16));//����������ʽ
		jl4.setForeground(Color.blue);//��ɫ
		jl5=new JLabel("QQ");
		jl5.setForeground(Color.BLUE);//������ɫ
		jl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//�������
		
		jtf=new JTextField();//��������
		jpf=new JPasswordField();//���������
		
		//ͼƬ��ť
		jb4=new JButton(new ImageIcon("D:/eclipse workspace/JavaPractice/images/denglu.jpg"));
		jb4.setToolTipText("��¼");//������ƶ�����ͼƬʱ����ʾ����¼��������
		
		//��ѡ��
		jcb1=new JCheckBox("�����¼");
		jcb2=new JCheckBox("��ס����");
		
		//��������
		jl1=new JLabel(new ImageIcon("D:/eclipse workspace/JavaPractice/images/qq_2011.jpg"));
		
		//�ϲ�����
		jp1 =new JPanel();
		jb1 =new JButton("��¼");
		jb2=new JButton("ȡ��");
		jb3=new JButton("��");
		
		//�в�����
		jtp=new JTabbedPane();
		jp2=new JPanel();
		jp3=new JPanel();
		jp3.setBackground(Color.RED);//�����������ɫ
		jp4 =new JPanel();
		jp4.setBackground(new Color(0, 0, 255));
		
		//�������ӵ�ѡ�������
		jtp.add("QQ����", jp2);
		jtp.add("�ֻ�����",jp3);
		jtp.add("��������",jp4);
		
		//���ò���
		jp2.setLayout(new GridLayout(3, 3));//���в���Ϊ�������еı��
		
		//������
		//�ϲ�
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		
		//�в���ѡ����ڵ�һ����壨JPanel��
		//��һ��
		jp2.add(jl2);
		jp2.add(jtf);
		jp2.add(jb4);
		//�ڶ���
		jp2.add(jl3);
		jp2.add(jpf);
		jp2.add(jl4);
		//������
		jp2.add(jcb1,"Center");
		jp2.add(jcb2,"Center");
		jp2.add(jl5,"Center");
		
		//��������JFrame
		this.add(jp1,BorderLayout.SOUTH);//��Ӱ�ť���ϲ�
		this.add(jl1,BorderLayout.NORTH);//���JLabel��ͼƬ��������
		this.add(jtp,BorderLayout.CENTER);//ѡ�����������в�
		
		//���ô�������
		this.setVisible(true);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null); //��ʼλ������Ļ���м�
		this.setTitle("QQ");
		this.setIconImage(new ImageIcon("D:/eclipse workspace/JavaPractice/images/qq.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
