/*
 * JTable��ʹ��
 */
package GUI_Practice;

import javax.swing.*;
import java.util.*;

public class Demo_13 extends JFrame{

	//rowData��������ݣ�columnNames����ֶΣ�������
	Vector rowData,columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_13 demo_13=new Demo_13();
	}

	//���캯��
	public Demo_13(){
		
		columnNames=new Vector();
		//��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");
		
		//rowData���Դ�Ŷ���
		rowData =new Vector();
		//����ÿ���ֶ���Ϣ
		Vector row=new Vector();
		row.add("100001");
		row.add("yasuo");
		row.add("man");
		row.add("300");
		row.add("Beijing");
		row.add("Shaolin");
		//���һ����Ϣ
		rowData.add(row);
		
		jt=new JTable(rowData,columnNames);
		
		jsp=new JScrollPane(jt);
		
		this.add(jsp);
		
		this.setSize(400,300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
		
	}
	
}
