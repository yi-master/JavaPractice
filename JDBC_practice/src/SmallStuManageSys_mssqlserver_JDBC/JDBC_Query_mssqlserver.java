/*
 * 这个类用来实现数据库的各种操作
 */

package SmallStuManageSys_mssqlserver_JDBC;

import java.sql.*;
import java.util.*;

public class JDBC_Query_mssqlserver {

	// 定义用来操作数据的对象
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public JDBC_Query_mssqlserver() {

	}

	// 设置字段名
	public void setDatabaseColumn(Vector columnVector, String[] columnName) {
		for(int i=0;i<columnName.length;i++){
			columnVector.add(columnName[i]);
		}	
	}

	// 3.连接数据库，获取sql语句和操作方式
	public void DatabaseOperation(String sqlDriver,String url, String username, String password, String sql, Vector rowData) {
		try {
			Class.forName(sqlDriver);
			ct = DriverManager.getConnection(url, username, password);
			ps = ct.prepareStatement(sql);
			//if (sql.substring(0, 6).equals("select")) 
				// 如果sql语句以select开头则执行查询操作
				this.ExecuteQueryOperation(rowData);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// 关闭
			this.Close();
		}

	}

	// 4.1执行查询
	public void ExecuteQueryOperation(Vector rowData) {

		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				// rowData可以存放多行
				Vector row = new Vector();
				// 设置每个字段信息
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				
				// 添加一行信息
				rowData.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

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
