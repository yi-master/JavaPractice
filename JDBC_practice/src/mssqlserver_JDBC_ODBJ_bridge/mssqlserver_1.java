/*
 * jdbc-odbc�ŷ�ʽ����SQL Server�еı�emp,dept��
 * jdk1.8���µİ汾���ã�jdk1.8���ϵİ汾�Ƴ���sun.jdbc.odbc.*
 * ���裺
 * 1.��������Դ
 * �������->������->ODBC����Դ������->�û�DSN->���->ѡ����Ҫ��ӵ����ݿ�
 * ��������Դ������һ�������ƣ�������ѡ��(local)��������.��ʾ���ӱ��ؼ����
 * ��һ����ѡ�����ݿ��¼��ʽWindows NT��֤����Ҫ�������룻
 * ���ݿ⣨ʹ�õ����ݿ����ͣ��磺SQL Server����֤��Ҫ���������ݿ���û���������
 * ��һ��������Ĭ�����ݿ⣬��Ҫ�õ����ݱ����ĸ����ݿ�ѡ�м���
 * ��һ���������Լ���ѡ������������ɣ���������Դ�����Գɹ���������Դ���óɹ�
 * �����ÿ����޸�֮ǰ�����ã���Ҫ���������������
 * 2.�ڳ�������������Դ
 * 
 * curd����
 * ������Create�������£�Update������ȡ��Retrieve����ɾ����Delete������
 */
package mssqlserver_JDBC_ODBJ_bridge;

import java.sql.*;

public class mssqlserver_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection ct = null;
		Statement sm = null;
		
		try {

			// 1.��������(����Ҫ��������������ڴ�)
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// 2.�õ�����
			// ��������Դѡ�����֤��ʽ�����ݿ���֤����Ҫ�����û���������
			// ct=DriverManager.getConnection("jdbc:odbc:����Դ����","���ݿ��¼ID","���ݿ��¼����"
			// ��������Դѡ�����֤��ʽ��Windows NT ��֤������Ҫ�û��������룬��������
			// ct=DriverManager.getConnection("jdbc:odbc:����Դ����")

			ct = DriverManager.getConnection("jdbc:odbc:MS sqlserver", "sa", "yuyiQQhao0313");

			// 3.����Statement����PreparedStatement
			/*
			 * Statement�����SQL������κδ���ֱ�Ӱ�SQL��䷢�͸����ݿ�
			 * PreparedStatementЧ�ʻ����һ�㣬��С���ݿ�ѹ���������ʺ����������ݿ������batch���������ԱȽϺõؽ��ϵͳ�ı��ػ�����
			 * ������Ч�ķ�ֹΣ���ַ���ע�룬Ҳ����SQLע������
			 */
			// Statement��������SQL��䵽���ݿ�
			sm = ct.createStatement();

			// 4.ִ��(������java�������ݿ�)

			// ���һ�����ݵ�dept��
			// executeUpdate("SQL���")����ִ����ӡ��޸ġ�ɾ������,�ú�������ֵΪint
			// int i = sm.executeUpdate("insert into dept
			// values('40','������','China')");
			// // i=0��ʾû����ӳɹ�,i=1��ʾһ��������ӳɹ���i=2��ʾ������������ӳɹ�����������
			// if (i == 1) {
			//
			// System.out.println("��ӳɹ�");
			// } else {
			// System.out.println("���ʧ��");
			// }

			// ��dept��ɾ����¼
//			int i2 = sm.executeUpdate("delete from dept where deptno=40");
//
//			if (i2 == 1) {
//
//				System.out.println("ɾ���ɹ�");
//			} else {
//				System.out.println("ɾ��ʧ��");
//			}
//			
//			// ����(�޸�)deptno=40 loc ��ΪBeijing
//			int i3 = sm.executeUpdate("update dept set loc='Beijing' where deptno=40");
//
//			if (i3 == 1) {
//
//				System.out.println("���³ɹ�");
//			} else {
//				System.out.println("����ʧ��");
//			}
			
			//��ѯ����ʾ���в�����Ϣ
			//ResultSet�����(�����ر��ѯ�Ľ��)
			ResultSet rs=sm.executeQuery("select * from dept");
			//rsָ�������ĵ�һ�е�ǰһ��(���ֶ���)
			//ѭ��ȡ����һ�У�rs.next()��ʾָ����һ��
			while(rs.next()){
				int deptno=rs.getInt(1);
				String dname=rs.getString(2);
				String loc=rs.getString(3);
				System.out.println(deptno+"	"+dname+"	"+loc+"	\n");
			}
			
//			//ȡ����һ�еĵ�һ��
//			rs.next();
//			//rs.getInt(1)�е�1��ʾ��һ�У���Ϊ���ݱ�ĵ�һ��Ϊint��������getInt()
//			
//			int a=rs.getInt(1);
//			System.out.println(a);
//			//ȡ����һ�еĵڶ���
//			//��Ҫȡ���ڶ�����Ϊrs.getString(2)
//			String b=rs.getString(2);
//			System.out.println(b);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			// 5.�ر���Դ��������رգ�������Խ��Խ�࣬�ﵽ����������ᱨ��
			// �ر�˳��󴴽����ȹر�
			try {
				// Ϊ�˳���Ľ�׳���ж�Statement��Connection�����Ƿ�Ϊ��
				if (sm != null) {
					sm.close();
				}
				if (ct != null) {
					ct.close();
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		System.exit(0);
	}

}
