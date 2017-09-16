/*
 * 建立stu表模型
 * 可以对stu表进行操作
 */
package SmallStuManageSys_mssqlserver_JDBC;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Vector;

import javax.swing.JOptionPane;
import javax.swing.table.*;

public class StuJTableModel extends AbstractTableModel {

	// rowData用来存放行数据，columnNames存放字段(列名)
	Vector rowData, columnNames;

	JDBC_Query_mssqlserver jqm=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public void init(String sql) {

		jqm=new JDBC_Query_mssqlserver();
		columnNames = new Vector();
		String[] names={"ID","Name","Sex" ,"Age","Native Place","Department"};
		// 设置列名
		jqm.setDatabaseColumn(columnNames,names);


		// 设置一行信息	
		String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";		
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
		String username = "sa";
		String password = "yuyiQQhao0313";
		
		rowData = new Vector();//存放行数据的向量
		// 1.加载驱动
		// 2.连接数据库，获取sql语句与执行方式
		jqm.DatabaseOperation(sqlDriver,url,username,password,sql,rowData);

	}

	public void ShowAll() {
		String sql = "select * from stu";
		this.init(sql);
	}

	// 通过传递sql语句来获得数据模型
	public void ShowInit(String sql) {
		this.init(sql);
	}

	// 初始化数据模型（stu表模型）
	public StuJTableModel() {
		this.ShowAll();
	}

	@Override
	// 得到共有多少列
	public int getRowCount() {
		// TODO Auto-generated method stub
		// rowData.size()表示列数
		return this.rowData.size();
	}

	@Override
	// 得到共有多少行
	public int getColumnCount() {
		// TODO Auto-generated method stub
		// columnNames.size()表示行数
		return this.columnNames.size();
	}

	@Override
	// 得到某行某列的数据
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		// 先得到行row，在得到列column
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}

	@Override
	// 获取列名
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		// 返回的是String类所以要强制转换
		return (String) (this.columnNames.get(column));
	}

}
