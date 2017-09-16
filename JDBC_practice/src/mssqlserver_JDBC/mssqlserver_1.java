/*
 * jdbc��ʽ�������ݿ�:����Ҫ��������Դ
 * 1.����java.sql.*
 * 2.����com.microsoft.sqlserver.jdbc.SQLServerDriver��jar��
 * ���û���������Ҫ��΢���������:
 * https://www.microsoft.com/zh-cn/search/result.aspx?q=jdbc&form=MSHOME
 * �������ذ��Ĳ���
 * 1)ѡ����Ҫ����ð���Project
 * 2)�Ҽ�ѡ��Properties(��ݼ�Alt+Enter)
 * 3)ѡ��Java Build Path
 * 4)���Add External JARs
 * 5)ѡ����Ҫ����İ���λ��
 * 6)OK
 * 
 * PreparedStatementʵ��curd����
 * 1.PreparedStatement�������ִ��Ч�ʣ���Ϊ����Ԥ���빦�ܣ�
 * 2.PreparedStatement���Է�ֹsqlע�룬��Ҫʹ��"?"��ֵ��ʽ
 * 
 * curd����
 * ������Create�������£�Update������ȡ��Retrieve����ɾ����Delete������
 *
 * ȡֵ�ǰ���ţ�����Ҫһһ��Ӧ������ǰ����ֶ�����ȡֵ�������˳��ߵ����Ƚ����
 */
package mssqlserver_JDBC;

import java.sql.*;

public class mssqlserver_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1.��������
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 2.�õ�����
			/*
			 * 127.0.0.1��ʾ��Ҫ���ӵ����ݿ�ip 
			 * 1433��ʾSQL Server��Ĭ�϶˿�
			 * query_Practice��ʾ��Ҫ���������ݿ�
			 * sa��ʾ���ݿ��û���
			 * yuyiQQhao0313��ʾ��������
			 */
			ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=query_Practice", "sa",
					"yuyiQQhao0313");
			// String
			// url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=query_Practice";
			// String username="sa";
			// String password="yuyiQQhao0313";
			//
			// ct=DriverManager.getConnection(url,username,password);

			// // 3.����SQL����PreparedStatement����
			// String sql = "select * from emp,dept where dept.deptno=emp.deptno";
			// ps = ct.prepareStatement(sql);
			//
			// // 4.ִ�У���ӡ�ɾ�����޸�����executeUpdate(),�����ѯʹ��executeQuery��
			// rs = ps.executeQuery();
			//
			// // ѭ��ȡ������Ա���֣�нˮ�����ű�ţ���������
			// while (rs.next()) {
			// // ע��SQL���ͱ��֮���ϵ
			// String ename = rs.getString(2);//��String ename =
			// rs.getString("ename");
			// float sal = rs.getFloat(6);//float sal = rs.getFloat("sal");
			// int deptno = rs.getInt(8);//int deptno = rs.getInt("deptno");
			// String dname=rs.getString("dname");
			// System.out.println(ename + " " + sal + " " + deptno +" "+ dname +
			// "\n");
			//
			// }

			/*
			 * �����Ϊps = ct.prepareStatement("select ename,sal,deptno from emp");
			 * ��ѭ��ȡ������Ӧ��Ϊ �� 
			 * String ename = rs.getString(1); 
			 * float sal = rs.getFloat(2); 
			 * int deptno = rs.getInt(3);
			 */

			// ��ӡ�ɾ�����޸�
			// ���(ɾ�����޸Ĳ���ֻ��Ҫ�ı�SQL��伴��)
			String sql = "insert into dept values(?,?,?)";
			ps = ct.prepareStatement(sql);
			/*
			 * ��?��ֵ PreparedStatement����.set�����"?"ǰ�ֶε���������(����1������2)
			 * ����1����ʾ�ڼ����ʺţ�����2����ʾ�ʺŵ�ֵ ���ָ�ֵ��ʽ���Է�ֹע��©����Ҳ����ֱ��д��SQL����У����޷���ֹע��©��
			 */
			ps.setInt(1, 100);
			ps.setString(2, "����");
			ps.setString(3, "Beijing");
			// ִ��
			int i = ps.executeUpdate();

			if (i == 1) {
				System.out.println("��ӳɹ�");
			} else {
				System.out.println("���ʧ��");
			}

		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 5.�ر���Դ��������رգ�������Խ��Խ�࣬�ﵽ����������ᱨ��
			// �ر�˳��󴴽����ȹر�
			try {
				// Ϊ�˳���Ľ�׳���ж�ResultSet��PreparedStatement��Connection�����Ƿ�Ϊ��
				//���Ϊ�գ�˵���Ѿ��ر�
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
