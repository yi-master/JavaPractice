/*
 * С��ѧ������ϵͳ
 * model 2 ģʽ
 */
package SmallStuManageSys_mssqlserver_JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class StuManagementSys extends JFrame implements ActionListener {

	// �������ؼ�
	JPanel jp1, jp2;
	JLabel jl1;
	JButton lookup, add, revise, delete, showAll;
	JTable jt;
	JScrollPane jsp;
	JTextField jtf;

	StuJTableModel sjm = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		StuManagementSys sms = new StuManagementSys();
	}

	// ���캯��
	public StuManagementSys() {

		// ����ϲ�
		jp1 = new JPanel();
		jl1 = new JLabel("Please input Keywords��");
		jtf = new JTextField(10);
		lookup = new JButton("Query");
		lookup.addActionListener(this);

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(lookup);

		// ����²�
		jp2 = new JPanel();
		add = new JButton("Add");
		add.addActionListener(this);
		revise = new JButton("Revise");
		revise.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		showAll = new JButton("ShowAll");// ��ʾȫ��
		showAll.addActionListener(this);

		jp2.add(add);
		jp2.add(revise);
		jp2.add(delete);
		jp2.add(showAll);
		// ����stu����ģ�Ͷ���
		sjm = new StuJTableModel();

		// ��JTable����ʵ������stu����ģ�ͷ���JTable��
		jt = new JTable(sjm);

		// ��JTable����ŵ������������
		jsp = new JScrollPane(jt);

		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");

		this.setSize(800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	// �ѶԱ�����ݷ�װ��StuJableModel��
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == lookup) {// ��ѯ

			this.QueryOperation();
		} else if (e.getSource() == add) {// ���

			sjm = new StuJTableModel();
			new AddBox(this, true);

			sjm.ShowAll();
			jt.setModel(sjm);// ����

		} else if (e.getSource() == revise) {// �޸�
			
			sjm = new StuJTableModel();
			// getSelectedRow�᷵���û������У�����ֵΪint�ͣ�����û�û��ѡ���κ�һ�У��򷵻�-1
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "If you want to delete a row of data, select one line first!");
				return;
			} else {//��ʾ�޸ĶԻ���
				new ReviseBox(this, true, sjm,rowNum);
				sjm.ShowAll();
				jt.setModel(sjm);// ����
			}
			
		} else if (e.getSource() == delete) {// ɾ��

			this.DeleteOperation();
		} else if (e.getSource() == showAll) {// ��ʾȫ������

			sjm = new StuJTableModel();
			sjm.ShowAll();
			jt.setModel(sjm);
		}
	}

	public void QueryOperation() {
		System.out.println("Query");

		// ��ȡ�ı�������
		String content = this.jtf.getText().trim();// trim()���Թ����ַ�����ͷΪ�յ��ַ�
		// SQL��ѯ���
		String sqlQuery = "select * from stu where stuID like'%" + content + "%' " + "or stuName like '%" + content
				+ "%' " + "or stuSex = '" + content + "' " + "or stuAge like '%" + content + "%' " + "or stuNP like '%"
				+ content + "%' " + "or stuDepartment like '%" + content + "%' ";
		// �����µ�����ģ���࣬������
		sjm = new StuJTableModel();
		sjm.ShowInit(sqlQuery);
		jt.setModel(sjm);// ����
	}

	public void DeleteOperation() {
		System.out.println("Delete");

		// getSelectedRow�᷵���û������У�����ֵΪint�ͣ�����û�û��ѡ���κ�һ�У��򷵻�-1
		int rowNum = this.jt.getSelectedRow();
		if (rowNum == -1) {
			JOptionPane.showMessageDialog(this, "If you want to delete a row of data, select one line first!");
			return;
		} else {

			// ��ȡ�ı�������
			String content = this.jtf.getText().trim();// trim()���Թ����ַ�����ͷΪ�յ��ַ�

			sjm = new StuJTableModel();

			String stuID = (String) sjm.getValueAt(rowNum, 0);// ��ȡĳ��0��(ѧ��ID��)

			String sqlDelete = "delete from stu where stuID=?";

			// ���������������ݵĶ���
			Connection ct = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				// 1.��������
				String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				Class.forName(sqlDriver);

				// 2.�������ݿ⣬��ȡsql�����ִ�з�ʽ
				String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
				String username = "sa";
				String password = "yuyiQQhao0313";
				ct = DriverManager.getConnection(url, username, password);
				ps = ct.prepareStatement(sqlDelete);
				ps.setString(1, stuID);
				int k = ps.executeUpdate();
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
				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
			sjm.ShowAll();
			jt.setModel(sjm);
		}
	}

}
