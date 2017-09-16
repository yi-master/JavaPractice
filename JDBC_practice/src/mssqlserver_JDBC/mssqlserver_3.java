/*
 * 从数据库中取出学生信息
 */
package mssqlserver_JDBC;

import javax.swing.*;

import com.microsoft.sqlserver.jdbc.SQLServerDriver;

import java.util.*;
import java.sql.*;
import java.awt.event.*;

public class mssqlserver_3 extends JFrame {

	// rowData用来存放行数据，columnNames存放字段(列名)
	Vector rowData, columnNames;
	JTable jt = null;
	JScrollPane jsp = null;

	// 定义用来操作数据的对象
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		mssqlserver_3 mssql_3=new mssqlserver_3();
	}

	// 构造函数
	public mssqlserver_3() {
		columnNames = new Vector();
		// 设置列名
		columnNames.add("学号");
		columnNames.add("姓名");
		columnNames.add("性别");
		columnNames.add("年龄");
		columnNames.add("籍贯");
		columnNames.add("系别");

		// rowData可以存放多行
		rowData = new Vector();
		// 设置一行信息
		try {
			// 1.加载驱动
			String mssqlserver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(mssqlserver);
			//com.microsoft.sqlserver.jdbc.SQLServerDriver mssqlserver=new SQLServerDriver();//这种方式也可以
			
			// 2.连接数据库
			String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
			String username = "sa";
			String password = "yuyiQQhao0313";
			ct = DriverManager.getConnection(url,username,password);

			// 3.创建sql语句
			String sql = "select * from stu";
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();

			// 4.执行
			while (rs.next()) {
				// rowData可以存放多行
				Vector row = new Vector();
				// 设置每个字段信息
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getInt(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				// 添加一行信息
				rowData.add(row);
			}

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
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
		}
		
		//把JTable对象实例化（把行列放到JTable中）
		jt = new JTable(rowData, columnNames);
		//把JTable对象放到滚动条面板中
		jsp = new JScrollPane(jt);
		//把滚动条面板放到主窗口上
		this.add(jsp);

		this.setSize(400, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
		this.setLocationRelativeTo(null);

	}
}
