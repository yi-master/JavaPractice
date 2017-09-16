/*
 * 小型学生管理系统
 * model 2 模式
 */
package SmallStuManageSys_mssqlserver_JDBC;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import java.util.*;

public class StuManagementSys extends JFrame implements ActionListener {

	// 定义界面控件
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

	// 构造函数
	public StuManagementSys() {

		// 面板上部
		jp1 = new JPanel();
		jl1 = new JLabel("Please input Keywords：");
		jtf = new JTextField(10);
		lookup = new JButton("Query");
		lookup.addActionListener(this);

		jp1.add(jl1);
		jp1.add(jtf);
		jp1.add(lookup);

		// 面板下部
		jp2 = new JPanel();
		add = new JButton("Add");
		add.addActionListener(this);
		revise = new JButton("Revise");
		revise.addActionListener(this);
		delete = new JButton("Delete");
		delete.addActionListener(this);
		showAll = new JButton("ShowAll");// 显示全部
		showAll.addActionListener(this);

		jp2.add(add);
		jp2.add(revise);
		jp2.add(delete);
		jp2.add(showAll);
		// 创建stu数据模型对象
		sjm = new StuJTableModel();

		// 把JTable对象实例化（stu数据模型放入JTable）
		jt = new JTable(sjm);

		// 把JTable对象放到滚动条面板中
		jsp = new JScrollPane(jt);

		this.add(jsp);
		this.add(jp1, "North");
		this.add(jp2, "South");

		this.setSize(800, 700);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}

	// 把对表的数据封装到StuJableModel中
	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == lookup) {// 查询

			this.QueryOperation();
		} else if (e.getSource() == add) {// 添加

			sjm = new StuJTableModel();
			new AddBox(this, true);

			sjm.ShowAll();
			jt.setModel(sjm);// 更新

		} else if (e.getSource() == revise) {// 修改
			
			sjm = new StuJTableModel();
			// getSelectedRow会返回用户点中行，返回值为int型，如果用户没有选中任何一行，则返回-1
			int rowNum = this.jt.getSelectedRow();
			if (rowNum == -1) {
				JOptionPane.showMessageDialog(this, "If you want to delete a row of data, select one line first!");
				return;
			} else {//显示修改对话框
				new ReviseBox(this, true, sjm,rowNum);
				sjm.ShowAll();
				jt.setModel(sjm);// 更新
			}
			
		} else if (e.getSource() == delete) {// 删除

			this.DeleteOperation();
		} else if (e.getSource() == showAll) {// 显示全部数据

			sjm = new StuJTableModel();
			sjm.ShowAll();
			jt.setModel(sjm);
		}
	}

	public void QueryOperation() {
		System.out.println("Query");

		// 获取文本框内容
		String content = this.jtf.getText().trim();// trim()可以过滤字符串两头为空的字符
		// SQL查询语句
		String sqlQuery = "select * from stu where stuID like'%" + content + "%' " + "or stuName like '%" + content
				+ "%' " + "or stuSex = '" + content + "' " + "or stuAge like '%" + content + "%' " + "or stuNP like '%"
				+ content + "%' " + "or stuDepartment like '%" + content + "%' ";
		// 构建新的数据模型类，并更新
		sjm = new StuJTableModel();
		sjm.ShowInit(sqlQuery);
		jt.setModel(sjm);// 更新
	}

	public void DeleteOperation() {
		System.out.println("Delete");

		// getSelectedRow会返回用户点中行，返回值为int型，如果用户没有选中任何一行，则返回-1
		int rowNum = this.jt.getSelectedRow();
		if (rowNum == -1) {
			JOptionPane.showMessageDialog(this, "If you want to delete a row of data, select one line first!");
			return;
		} else {

			// 获取文本框内容
			String content = this.jtf.getText().trim();// trim()可以过滤字符串两头为空的字符

			sjm = new StuJTableModel();

			String stuID = (String) sjm.getValueAt(rowNum, 0);// 获取某行0列(学生ID列)

			String sqlDelete = "delete from stu where stuID=?";

			// 定义用来操作数据的对象
			Connection ct = null;
			PreparedStatement ps = null;
			ResultSet rs = null;
			try {
				// 1.加载驱动
				String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
				Class.forName(sqlDriver);

				// 2.连接数据库，获取sql语句与执行方式
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

				// 5.关闭用来操作数据库的对象
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
