/*
 * ���±�����
 */
package GUI_Practice;

import java.awt.*;
import javax.swing.*;

public class notepad_Demo_12 extends JFrame{

	//JMenu�������Ƕ��JMenu
	private JMenuBar jmb=null;//�˵���������������ÿ���˵���
	private JMenu menu[]=null;//�˵������ÿ���˵���������Է���Ӳ˵������
	private JMenuItem jmi[]=null;//�˵������(��ֱ�˵�)
	private JMenu menu_2=null;//�˵�����������˵���
	private JMenuItem file=null,project=null;//�˵������
	
	//�ı���
	private JTextArea jta=null;
	private JScrollPane jsp=null;
	
	//������
	//private JToolBar jtb=null;
	//private JButton jb[]=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		notepad_Demo_12 notepad=new notepad_Demo_12();
	}

	//���캯��
	public notepad_Demo_12(){
		
		//�������
		//jtb=new JToolBar();
		
		jmb=new JMenuBar();
		
		menu_2=new JMenu("New");
		file =new JMenuItem("File");
		project= new JMenuItem("Project");
		
		jmi=new JMenuItem[5];
		jmi[0]=new JMenuItem("Open");
		jmi[1]=new JMenuItem("Save");
		jmi[1].setMnemonic('S');//�������Ƿ���Alt+s���������˵���s��Сд���ƣ�
		jmi[2]=new JMenuItem("Save as");
		jmi[3]=new JMenuItem("Print");
		jmi[3].setMnemonic('P');//�������Ƿ���Alt+p���������˵���p��Сд���ƣ�
		jmi[4]=new JMenuItem("Exit");
		
		menu=new JMenu[5];
		menu[0]=new JMenu("File(F)");
		menu[0].setMnemonic('F');//�������Ƿ���Alt+f���������˵���f��Сд���ƣ�
		menu[1]=new JMenu("Edit(E)");
		menu[1].setMnemonic('E');//�������Ƿ���Alt+e���������˵���e��Сд���ƣ�
		menu[2]=new JMenu("Layout(L)");
		menu[2].setMnemonic('L');//�������Ƿ���Alt+l���������˵���l��Сд���ƣ�
		menu[3]=new JMenu("View(V)");
		menu[3].setMnemonic('V');//�������Ƿ���Alt+v���������˵���v��Сд���ƣ�
		menu[4]=new JMenu("Help(H)");
		menu[4].setMnemonic('H');//�������Ƿ���Alt+h���������˵���h��Сд���ƣ�
	
		jta=new JTextArea();
		//�ڹ������ؼ��Ϸ����ı���
		jsp=new JScrollPane(jta);
		//���ù��������Ǵ�ֱ��
		//jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//��ӹ�����
		this.add(jsp);
		
		//���ò���
		
		//������
		//��������ӵ���������
		//jtb.add();
		
		//���˵�����ӵ��˵���
		menu_2.add(file);
		menu_2.add(project);
		
		menu[0].add(menu_2);
		
		for(int i=0;i<jmi.length;i++){
			menu[0].add(jmi[i]);
			if(i==2){
				menu[0].addSeparator();//��ӷָ���
			}
		}
		
		//���ÿ���˵�
		for(int j=0;j<menu.length;j++){
			jmb.add(menu[j]);
		}
		
		//���˵�����ӵ�������
		this.setJMenuBar(jmb);
		
		//����������ӵ�������
		//this.add(jtb);
		
		this.setTitle("Notepad");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);//���ó�ʼλ��Ϊ��Ļ������
	}
	
}
