/*
 * PreparedStatement实现curd操作
 * 1.PreparedStatement可以提高执行效率（因为它有预编译功能）
 * 2.PreparedStatement可以防止sql注入，但要使用"?"赋值方式
 * 
 * curd操作
 * 创建（Create）、更新（Update）、读取（Retrieve）和删除（Delete）操作
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
			// 1.加载驱动(把需要的驱动程序加入内存)
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// 2.得到连接
			ct = DriverManager.getConnection("jdbc:odbc:MS sqlserver", "sa", "yuyiQQhao0313");

			// 3.创建PreparedStatement
			ps = ct.prepareStatement("select * from dept where deptno=? and loc=?");
			//ps = ct.prepareStatement("select * from dept where deptno='10' and loc='dallas' or 1='1'");//这种直接赋值方式可以注入漏洞
			/* 给?赋值
			 * PreparedStatement对象.set语句中"?"前字段的数据类型(参数1，参数2)
			 * 参数1：表示第几个问号，参数2：表示问号的值
			 * 这种赋值方式可以防止注入漏洞，也可以直接写在SQL语句中，但无法防止注入漏洞
			 */
			ps.setInt(1, 10);
			ps.setString(2,"dallas" );
			rs = ps.executeQuery();

			// 4.执行
			//rs指向结果集的第一行的前一行(即字段行)
			//循环取出下一行，rs.next()表示指向下一行
			while (rs.next()) {
				//ResultSet对象.get第n列字段数据类型(n)
				int deptno = rs.getInt(1);//rs.getInt(1)中的1表示第一列，因为数据表的第一列为int型所以用getInt()
				String dname = rs.getString(2);
				String loc = rs.getString(3);
				System.out.println(deptno + "	" + dname + "	" + loc + "	\n");
			}

			//使用PreparedStatement添加一条记录
			ps=ct.prepareStatement("insert into dept values(?,?,?)");
			//给每个字段添加数据
			ps.setInt(1, 40);
			ps.setString(2, "保安部");
			ps.setString(3, "Beijing");
			
			//executeUpdate("SQL语句")可以执行添加、修改、删除操作,该函数返回值为int
			int i=ps.executeUpdate();
			
			if(i==1){
				System.out.println("添加成功");
			}else{
				System.out.println("添加失败");
			}
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {
			// 5.关闭资源（如果不关闭，连接数越来越多，达到最大连接数会报错）
			// 关闭顺序后创建的先关闭
			try {
				// 为了程序的健壮性判断ResultSet、PreparedStatement和Connection对象是否为空
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
