/*
 * JTable的使用
 */
package GUI_Practice;

import javax.swing.*;
import java.util.*;

public class Demo_13 extends JFrame{

	//rowData存放行数据，columnNames存放字段（列名）
	Vector rowData,columnNames;
	JTable jt=null;
	JScrollPane jsp=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_13 demo_13=new Demo_13();
	}

	//构造函数
	public Demo_13(){
		
		columnNames=new Vector();
		//设置列名
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");
		
		//rowData可以存放多行
		rowData =new Vector();
		//设置每个字段信息
		Vector row=new Vector();
		row.add("100001");
		row.add("yasuo");
		row.add("man");
		row.add("300");
		row.add("Beijing");
		row.add("Shaolin");
		//添加一行信息
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
