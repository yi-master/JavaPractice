/*
 * jdbc-odbc桥方式操作SQL Server中的表（emp,dept）
 * jdk1.8以下的版本适用，jdk1.8以上的版本移除了sun.jdbc.odbc.*
 * 步骤：
 * 1.配置数据源
 * 控制面板->管理工具->ODBC数据源管理器->用户DSN->添加->选择你要添加的数据库
 * 创建数据源，输入一个新名称，服务器选这(local)或输入以.表示连接本地计算机
 * 下一步，选择数据库登录方式Windows NT验证不需要输入密码；
 * 数据库（使用的数据库类型，如：SQL Server）验证需要输入你数据库的用户名和密码
 * 下一步，更改默认数据库，需要用的数据表在哪个数据库选中即可
 * 下一步，根据自己的选择调整，最后按完成，测试数据源，测试成功，则数据源配置成功
 * 按配置可以修改之前的配置，但要按上述操作逐步完成
 * 2.在程序中连接数据源
 * 
 * curd操作
 * 创建（Create）、更新（Update）、读取（Retrieve）和删除（Delete）操作
 */
package mssqlserver_JDBC_ODBJ_bridge;

import java.sql.*;

public class mssqlserver_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		Connection ct = null;
		Statement sm = null;
		
		try {

			// 1.加载驱动(把需要的驱动程序加入内存)
			Class.forName("sun.jdbc.odbc.JdbcOdbcDriver");

			// 2.得到连接
			// 配置数据源选择的验证方式是数据库验证，需要输入用户名和密码
			// ct=DriverManager.getConnection("jdbc:odbc:数据源名称","数据库登录ID","数据库登录密码"
			// 配置数据源选择的验证方式是Windows NT 验证，则不需要用户名和密码，代码如下
			// ct=DriverManager.getConnection("jdbc:odbc:数据源名称")

			ct = DriverManager.getConnection("jdbc:odbc:MS sqlserver", "sa", "yuyiQQhao0313");

			// 3.创建Statement或者PreparedStatement
			/*
			 * Statement不会对SQL语句做任何处理，直接把SQL语句发送给数据库
			 * PreparedStatement效率会更高一点，减小数据库压力，并且适合批量的数据库操作（batch），还可以比较好地解决系统的本地化问题
			 * 更能有效的防止危险字符的注入，也就是SQL注入问题
			 */
			// Statement用来发送SQL语句到数据库
			sm = ct.createStatement();

			// 4.执行(可以用java操作数据库)

			// 添加一条数据到dept表
			// executeUpdate("SQL语句")可以执行添加、修改、删除操作,该函数返回值为int
			// int i = sm.executeUpdate("insert into dept
			// values('40','保安部','China')");
			// // i=0表示没有添加成功,i=1表示一条数据添加成功，i=2表示右两条数据添加成功，依次类推
			// if (i == 1) {
			//
			// System.out.println("添加成功");
			// } else {
			// System.out.println("添加失败");
			// }

			// 从dept表删除记录
//			int i2 = sm.executeUpdate("delete from dept where deptno=40");
//
//			if (i2 == 1) {
//
//				System.out.println("删除成功");
//			} else {
//				System.out.println("删除失败");
//			}
//			
//			// 更新(修改)deptno=40 loc 改为Beijing
//			int i3 = sm.executeUpdate("update dept set loc='Beijing' where deptno=40");
//
//			if (i3 == 1) {
//
//				System.out.println("更新成功");
//			} else {
//				System.out.println("更新失败");
//			}
			
			//查询，显示所有部门信息
			//ResultSet结果集(即返回表查询的结果)
			ResultSet rs=sm.executeQuery("select * from dept");
			//rs指向结果集的第一行的前一行(即字段行)
			//循环取出下一行，rs.next()表示指向下一行
			while(rs.next()){
				int deptno=rs.getInt(1);
				String dname=rs.getString(2);
				String loc=rs.getString(3);
				System.out.println(deptno+"	"+dname+"	"+loc+"	\n");
			}
			
//			//取出第一行的第一列
//			rs.next();
//			//rs.getInt(1)中的1表示第一列，因为数据表的第一列为int型所以用getInt()
//			
//			int a=rs.getInt(1);
//			System.out.println(a);
//			//取出第一行的第二列
//			//若要取出第二列则，为rs.getString(2)
//			String b=rs.getString(2);
//			System.out.println(b);
			
			
		} catch (Exception e) {
			// TODO: handle exception
			e.printStackTrace();
		} finally {

			// 5.关闭资源（如果不关闭，连接数越来越多，达到最大连接数会报错）
			// 关闭顺序后创建的先关闭
			try {
				// 为了程序的健壮性判断Statement和Connection对象是否为空
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
