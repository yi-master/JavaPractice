/*
 * ���������ʵ�����ݿ�ĸ��ֲ���
 */

package SmallStuManageSys_mssqlserver_JDBC;

import java.sql.*;
import java.util.*;

public class SqlHelper {

	// ���������������ݵĶ���
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	// ������������
	String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
	String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
	String username = "sa";
	String password = "yuyiQQhao0313";
	
	//���캯�����������������͵õ�����
	public SqlHelper(){
		
		try {
			Class.forName(sqlDriver);//��������
			ct = DriverManager.getConnection(url, username, password);//�õ�����
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

	// �����ֶ���
	public void setDatabaseColumn(Vector columnVector, String[] columnName) {
		for (int i = 0; i < columnName.length; i++) {
			columnVector.add(columnName[i]);
		}
	}

	// ִ�в�ѯ(��?ע��)
	public ResultSet ExecuteQueryOperation(String sql,Vector rowData,Vector columnVector) {

		try {
			
			ps = ct.prepareStatement(sql);
			rs = ps.executeQuery();
			
			while (rs.next()) {
				// rowData���Դ�Ŷ���
				Vector row = new Vector();
				// ����ÿ���ֶ���Ϣ
				for(int i=0;i<columnVector.size();i++){
					row.add(rs.getString(i+1));
				}
//				row.add(rs.getString(1));
//				row.add(rs.getString(2));
//				row.add(rs.getString(3));
//				row.add(rs.getInt(4));
//				row.add(rs.getString(5));
//				row.add(rs.getString(6));

				// ���һ����Ϣ
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

	// ����ѧ����Ϣ������ɾ���ģ�
	public boolean UpdatePerson(String sql, String str[]) {
		boolean b = true;

		try {

			// 3.����ps
			ps = ct.prepareStatement(sql);
			// ��ps��?��ֵ
			for (int i = 0; i < str.length; i++) {
				ps.setString(i + 1, str[i]);
			}
			// 4.ִ�в���
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
