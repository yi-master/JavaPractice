/*
 * ��java��ʹ��ddl��䣨create,drop,alter,backup,restore������
 * ִ��ddl�����execute()�������ú�������false���ʾ�����ɹ�
 */
package mssqlserver_JDBC;

import java.sql.*;

public class mssqlserver_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			//��������
			String SQLServerDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(SQLServerDriver);
			//�õ�����
			String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=query_Practice";
			String username="sa";
			String password="yuyiQQhao0313";
			
			ct=DriverManager.getConnection(url,username,password);
//			ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=query_Practice", "sa",
//					"yuyiQQhao0313");
			//�������ݿ�
			String sql="create database java_test";
			//ɾ�����ݿ�
			//String sql="drop database java_test";
			//�������ݱ�
			//String sql="create table table1(a int primary key,b varchar)";
			//ɾ�����ݱ�
			//String sql="drop table table1";
			//�������ݿ�
			//����ʹ�õ����ݱ��ݻ��Σ��
			//query_Practice������ݿ⴦�ڲ�����,���뱸�ݴ����ݿ��������url�е����ݿ�����(query_Practice)��Ϊ���������ݿ�����
			//String sql="backup database java_test to disk='e:/123.bak'";//�̷�����ҪСд
			//����ps
			ps=ct.prepareStatement(sql);
			//ִ��ddl�����execute()�������ú�������false���ʾ�����ɹ�
			//b=false���ʾ�����ɹ�
			boolean b=ps.execute();
			if(!b){
				System.out.println("true");
			}else{
				System.out.println("false");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
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
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
