/*
 * 这个类用来实现数据库的各种操作
 */

package SmallStuManageSys_mssqlserver_JDBC;

import java.sql.*;
import java.util.*;

public class SqlHelper {

	// 定义用来操作数据的对象
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// 定义数据驱动
	String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
	String username = "sa";
	String password = "yuyiQQhao0313";
	
	//构造函数，用来创建驱动和得到连接
	public SqlHelper(){
		
		try {
			Class.forName(sqlDriver);//创建驱动
			ct = DriverManager.getConnection(url, username, password);//得到连接
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// 设置字段名
	public void setDatabaseColumn(Vector columnVector, String[] columnName) {
		for (int i = 0; i < columnName.length; i++) {
			columnVector.add(columnName[i]);
		}
	}

	// 执行查询(无?注入)
	public ResultSet ExecuteQueryOperation(String sql,Vector rowData,Vector columnVector) {

		try {
			
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				// rowData可以存放多行
				Vector row = new Vector();
				// 设置每个字段信息
				for(int i=0;i<columnVector.size();i++){
					row.add(rs.getString(i+1));
				}
//				row.add(rs.getString(1));
//				row.add(rs.getString(2));
//				row.add(rs.getString(3));
//				row.add(rs.getInt(4));
//				row.add(rs.getString(5));
//				row.add(rs.getString(6));

				// 添加一行信息
				rowData.add(row);
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}finally{
			this.Close();
		}
		
		return rs;
	}

	// 更新学生信息（增，删，改）
	public boolean UpdatePerson(String sql, String str[]) {
		boolean b = true;

		try {

			// 3.创建ps
			ps = ct.prepareStatement(sql);
			// 给ps的?赋值
			for (int i = 0; i < str.length; i++) {
				ps.setString(i + 1, str[i]);
			}
			// 4.执行操作
			if (ps.executeUpdate() != 1) {
				b = false;
			}

		} catch (Exception e) {
			// TODO: handle exception
			b = false;
			e.printStackTrace();
		} finally {
			this.Close();
		}

		return b;
	}

	public void Close() {
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
}
