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

	//构造函数，初始化数据模型
	public StuJTableModel(){
		this.ShowAllStu();
	}
	
	// 查询（其本质是初始化）
	public void QueryStu(String sql) {

		// 实例化SqlHelper对象，并加载驱动，连接数据库
		SqlHelper sh = new SqlHelper();
		
		columnNames = new Vector();
		String[] names = { "ID", "Name", "Sex", "Age", "Native Place", "Department" };
		// 设置列名(把列名放入到columnNames向量中)
		sh.setDatabaseColumn(columnNames, names);

		rowData = new Vector();// 存放行数据的向量
		//获取sql语句并执行
		sh.ExecuteQueryOperation(sql, rowData,columnNames);

	}
	
	//对学生表的操作（增，删，改）
	public boolean UpdateStu(String sql,String str[]){
		
		//创建SqlHelper(如果程序并发性不考虑，可以把SqlHelper做出static)
		SqlHelper sh=new SqlHelper();
		return sh.UpdatePerson(sql, str);
	}

	public void ShowAllStu() {
		String sql = "select * from stu";
		this.QueryStu(sql);
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
