/*
 * 在java中使用ddl语句（create,drop,alter,backup,restore……）
 * 执行ddl语句用execute()函数，该函数返回false则表示操作成功
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
			//加载驱动
			String SQLServerDriver="com.microsoft.sqlserver.jdbc.SQLServerDriver";
			Class.forName(SQLServerDriver);
			//得到连接
			String url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=query_Practice";
			String username="sa";
			String password="yuyiQQhao0313";
			
			ct=DriverManager.getConnection(url,username,password);
//			ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=query_Practice", "sa",
//					"yuyiQQhao0313");
			//创建数据库
			String sql="create database java_test";
			//删除数据库
			//String sql="drop database java_test";
			//创建数据表
			//String sql="create table table1(a int primary key,b varchar)";
			//删除数据表
			//String sql="drop table table1";
			//备份数据库
			//正在使用的数据备份会很危险
			//query_Practice这个数据库处于操作中,若想备份此数据库则把上面url中的数据库名字(query_Practice)改为其他的数据库名字
			//String sql="backup database java_test to disk='e:/123.bak'";//盘符名称要小写
			//创建ps
			ps=ct.prepareStatement(sql);
			//执行ddl语句用execute()函数，该函数返回false则表示操作成功
			//b=false则表示创建成功
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
