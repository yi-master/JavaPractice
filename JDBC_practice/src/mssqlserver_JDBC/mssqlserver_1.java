/*
 * jdbc方式操作数据库:不需要配置数据源
 * 1.引入java.sql.*
 * 2.引入com.microsoft.sqlserver.jdbc.SQLServerDriver的jar包
 * 如果没有这个包需要在微软官网下载:
 * https://www.microsoft.com/zh-cn/search/result.aspx?q=jdbc&form=MSHOME
 * 引入下载包的步骤
 * 1)选中需要引入该包的Project
 * 2)右键选择Properties(快捷键Alt+Enter)
 * 3)选择Java Build Path
 * 4)点击Add External JARs
 * 5)选择需要引入的包的位置
 * 6)OK
 * 
 * PreparedStatement实现curd操作
 * 1.PreparedStatement可以提高执行效率（因为它有预编译功能）
 * 2.PreparedStatement可以防止sql注入，但要使用"?"赋值方式
 * 
 * curd操作
 * 创建（Create）、更新（Update）、读取（Retrieve）和删除（Delete）操作
 *
 * 取值是按编号，则需要一一对应；如果是按照字段列名取值，则可以顺序颠倒，比较灵活
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
			// 1.加载驱动
			Class.forName("com.microsoft.sqlserver.jdbc.SQLServerDriver");
			// 2.得到连接
			/*
			 * 127.0.0.1表示你要连接的数据库ip 
			 * 1433表示SQL Server的默认端口
			 * query_Practice表示你要操作的数据库
			 * sa表示数据库用户名
			 * yuyiQQhao0313表示数据密码
			 */
			ct = DriverManager.getConnection("jdbc:sqlserver://127.0.0.1:1433;DatabaseName=query_Practice", "sa",
					"yuyiQQhao0313");
			// String
			// url="jdbc:sqlserver://127.0.0.1:1433;DatabaseName=query_Practice";
			// String username="sa";
			// String password="yuyiQQhao0313";
			//
			// ct=DriverManager.getConnection(url,username,password);

			// // 3.创建SQL语句和PreparedStatement对象
			// String sql = "select * from emp,dept where dept.deptno=emp.deptno";
			// ps = ct.prepareStatement(sql);
			//
			// // 4.执行（添加、删除、修改是用executeUpdate(),如果查询使用executeQuery）
			// rs = ps.executeQuery();
			//
			// // 循环取出，雇员名字，薪水，部门编号，部门名称
			// while (rs.next()) {
			// // 注意SQL语句和编号之间关系
			// String ename = rs.getString(2);//或String ename =
			// rs.getString("ename");
			// float sal = rs.getFloat(6);//float sal = rs.getFloat("sal");
			// int deptno = rs.getInt(8);//int deptno = rs.getInt("deptno");
			// String dname=rs.getString("dname");
			// System.out.println(ename + " " + sal + " " + deptno +" "+ dname +
			// "\n");
			//
			// }

			/*
			 * 如果改为ps = ct.prepareStatement("select ename,sal,deptno from emp");
			 * 则循环取出部分应改为 ： 
			 * String ename = rs.getString(1); 
			 * float sal = rs.getFloat(2); 
			 * int deptno = rs.getInt(3);
			 */

			// 添加、删除、修改
			// 添加(删除和修改操作只需要改变SQL语句即可)
			String sql = "insert into dept values(?,?,?)";
			ps = ct.prepareStatement(sql);
			/*
			 * 给?赋值 PreparedStatement对象.set语句中"?"前字段的数据类型(参数1，参数2)
			 * 参数1：表示第几个问号，参数2：表示问号的值 这种赋值方式可以防止注入漏洞，也可以直接写在SQL语句中，但无法防止注入漏洞
			 */
			ps.setInt(1, 100);
			ps.setString(2, "财务部");
			ps.setString(3, "Beijing");
			// 执行
			int i = ps.executeUpdate();

			if (i == 1) {
				System.out.println("添加成功");
			} else {
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
				//如果为空，说明已经关闭
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
