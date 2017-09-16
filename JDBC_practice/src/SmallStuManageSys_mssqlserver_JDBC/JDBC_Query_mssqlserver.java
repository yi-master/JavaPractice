/*
 * ���������ʵ�����ݿ�ĸ��ֲ���
 */

package SmallStuManageSys_mssqlserver_JDBC;

import java.sql.*;
import java.util.*;

public class JDBC_Query_mssqlserver {

	// ���������������ݵĶ���
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public JDBC_Query_mssqlserver() {

	}

	// �����ֶ���
	public void setDatabaseColumn(Vector columnVector, String[] columnName) {
		for(int i=0;i<columnName.length;i++){
			columnVector.add(columnName[i]);
		}	
	}

	// 3.�������ݿ⣬��ȡsql���Ͳ�����ʽ
	public void DatabaseOperation(String sqlDriver,String url, String username, String password, String sql, Vector rowData) {
		try {
			Class.forName(sqlDriver);
			ct = DriverManager.getConnection(url, username, password);
			ps = ct.prepareStatement(sql);
			//if (sql.substring(0, 6).equals("select")) 
				// ���sql�����select��ͷ��ִ�в�ѯ����
				this.ExecuteQueryOperation(rowData);
			
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {// �ر�
			this.Close();
		}

	}

	// 4.1ִ�в�ѯ
	public void ExecuteQueryOperation(Vector rowData) {

		try {
			rs = ps.executeQuery();
			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector row = new Vector();
				// ����ÿ���ֶ���Ϣ
				row.add(rs.getString(1));
				row.add(rs.getString(2));
				row.add(rs.getString(3));
				row.add(rs.getString(4));
				row.add(rs.getString(5));
				row.add(rs.getString(6));
				
				// ���һ����Ϣ
				rowData.add(row);
			}
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		}

	}

	public void Close() {
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
}
