package GUI_Practice;
/*
 * �����˵���JComboBox��/�б��JList��/��������JScrollPane��
 */

import javax.swing.*;
import java.awt.*;

public class Demo_8 extends JFrame {

	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JComboBox jcb1;
	JList jlist;
	JScrollPane jsp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_8 demo_8 = new Demo_8();
	}

	// ���캯��
	public Demo_8() {
		jp1 = new JPanel();
		jp2 = new JPanel();

		jl1 = new JLabel("��ļ���");
		jl2 = new JLabel("���εص�");

		String[] jg = { "����", "�Ϻ�", "���", "����" };
		jcb1 = new JComboBox(jg);

		String[] place = { "����", "�ʹ�", "�찲��", "������" };
		jlist = new JList(place);
		jlist.setVisibleRowCount(3);// ������ϣ����ʾ���ٸ�ѡ��
		jsp = new JScrollPane(jlist);
		

		// ���ò���
		this.setLayout(new GridLayout(3, 1));// ����һ�����񲼾�

		// ������
		// ��һ��
		jp1.add(jl1);
		jp1.add(jcb1);

		// �ڶ���
		jp2.add(jl2);
		jp2.add(jsp);

		// ��������������(JFrame)
		this.add(jp1);
		this.add(jp2);

		// ���ô�������
		this.setSize(300, 300);
		this.setLocation(200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
