/*
 * PreparedStatementʵ��curd����
 * 1.PreparedStatement�������ִ��Ч�ʣ���Ϊ����Ԥ���빦�ܣ�
 * 2.PreparedStatement���Է�ֹsqlע�룬��Ҫʹ��"?"��ֵ��ʽ
 * 
 * curd����
 * ������Create�������£�Update������ȡ��Retrieve����ɾ����Delete������
 */
package mssqlserver_JDBC_ODBJ_bridge;

import java.sql.*;

public class mssqlserver_2 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection ct = null;
		PreparedStatement ps = null;
		ResultSet rs = null;

		try {
			// 1.��������(����Ҫ��������������ڴ�)
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// 2.�õ�����
			ct = DriverManager.getConnection("jdbc:odbc:MS sqlserver", "sa", "yuyiQQhao0313");

			// 3.����PreparedStatement
			ps = ct.prepareStatement("select * from dept where deptno=? and loc=?");
			//ps = ct.prepareStatement("select * from dept where deptno='10' and loc='dallas' or 1='1'");//����ֱ�Ӹ�ֵ��ʽ����ע��©��
			/* ��?��ֵ
			 * PreparedStatement����.set�����"?"ǰ�ֶε���������(����1������2)
			 * ����1����ʾ�ڼ����ʺţ�����2����ʾ�ʺŵ�ֵ
			 * ���ָ�ֵ��ʽ���Է�ֹע��©����Ҳ����ֱ��д��SQL����У����޷���ֹע��©��
			 */
			ps.setInt(1, 10);
			ps.setString(2,"dallas" );
			rs = ps.executeQuery();

			// 4.ִ��
			//rsָ�������ĵ�һ�е�ǰһ��(���ֶ���)
			//ѭ��ȡ����һ�У�rs.next()��ʾָ����һ��
			while (rs.next()) {
				//ResultSet����.get��n���ֶ���������(n)
				int deptno = rs.getInt(1);//rs.getInt(1)�е�1��ʾ��һ�У���Ϊ���ݱ�ĵ�һ��Ϊint��������getInt()
				String dname = rs.getString(2);
				String loc = rs.getString(3);
				System.out.println(deptno + "	" + dname + "	" + loc + "	\n");
			}

			//ʹ��PreparedStatement���һ����¼
			ps=ct.prepareStatement("insert into dept values(?,?,?)");
			//��ÿ���ֶ��������
			ps.setInt(1, 40);
			ps.setString(2, "������");
			ps.setString(3, "Beijing");
			
			//executeUpdate("SQL���")����ִ����ӡ��޸ġ�ɾ������,�ú�������ֵΪint
			int i=ps.executeUpdate();
			
			if(i==1){
				System.out.println("��ӳɹ�");
			}else{
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
				if(rs!=null){
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
