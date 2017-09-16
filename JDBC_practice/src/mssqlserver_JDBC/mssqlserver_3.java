/*
 * �����ݿ���ȡ��ѧ����Ϣ
 */
package mssqlserver_JDBC;

import javax.swing.*;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.util.*;
import java.sql.*;
import java.awt.event.*;

public class mssqlserver_3 extends JFrame {

	// rowData������������ݣ�columnNames����ֶ�(����)
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;

	// ���������������ݵĶ���
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mssqlserver_3 mssql_3=new mssqlserver_3();
	}

	// ���캯��
	public mssqlserver_3() {
		columnNames = new Vector();
		// ��������
		columnNames.add("ѧ��");
		columnNames.add("����");
		columnNames.add("�Ա�");
		columnNames.add("����");
		columnNames.add("����");
		columnNames.add("ϵ��");

		// rowData���Դ�Ŷ���
		rowData = new Vector();
		// ����һ����Ϣ
		try {
			// 1.��������
			String mssqlserver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(mssqlserver);
			//com.microsoft.sqlserver.jdbc.SQLServerDriver mssqlserver=new SQLServerDriver();//���ַ�ʽҲ����
			
			// 2.�������ݿ�
			String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
			String username = "sa";
			String password = "yuyiQQhao0313";
			ct = DriverManager.getConnection(url,username,password);

			// 3.����sql���
			String sql = "select * from stu";
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();

			// 4.ִ��
			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector row = new Vector();
				// ����ÿ���ֶ���Ϣ
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getInt(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				// ���һ����Ϣ
				rowData.add(row);
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 5.�ر������������ݿ�Ķ���
			try {
				if (rs != null) {
					rs.close();
				}
				if (ps != null) {
					ps.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		//��JTable����ʵ�����������зŵ�JTable�У�
		jt = new JTable(rowData, columnNames);
		//��JTable����ŵ������������
		jsp = new JScrollPane(jt);
		//�ѹ��������ŵ���������
		this.add(jsp);

		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
}
