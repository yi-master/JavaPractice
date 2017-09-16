/*
 * ����stu��ģ��
 * ���Զ�stu����в���
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

	// rowData������������ݣ�columnNames����ֶ�(����)
	Vector rowData, columnNames;

	JDBC_Query_mssqlserver jqm=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}


	public void init(String sql) {

		jqm=new JDBC_Query_mssqlserver();
		columnNames = new Vector();
		String[] names={"ID","Name","Sex" ,"Age","Native Place","Department"};
		// ��������
		jqm.setDatabaseColumn(columnNames,names);


		// ����һ����Ϣ	
		String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";		
		String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
		String username = "sa";
		String password = "yuyiQQhao0313";
		
		rowData = new Vector();//��������ݵ�����
		// 1.��������
		// 2.�������ݿ⣬��ȡsql�����ִ�з�ʽ
		jqm.DatabaseOperation(sqlDriver,url,username,password,sql,rowData);

	}

	public void ShowAll() {
		String sql = "select * from stu";
		this.init(sql);
	}

	// ͨ������sql������������ģ��
	public void ShowInit(String sql) {
		this.init(sql);
	}

	// ��ʼ������ģ�ͣ�stu��ģ�ͣ�
	public StuJTableModel() {
		this.ShowAll();
	}

	@Override
	// �õ����ж�����
	public int getRowCount() {
		// TODO Auto-generated method stub
		// rowData.size()��ʾ����
		return this.rowData.size();
	}

	@Override
	// �õ����ж�����
	public int getColumnCount() {
		// TODO Auto-generated method stub
		// columnNames.size()��ʾ����
		return this.columnNames.size();
	}

	@Override
	// �õ�ĳ��ĳ�е�����
	public Object getValueAt(int rowIndex, int columnIndex) {
		// TODO Auto-generated method stub
		// �ȵõ���row���ڵõ���column
		return ((Vector) this.rowData.get(rowIndex)).get(columnIndex);
	}

	@Override
	// ��ȡ����
	public String getColumnName(int column) {
		// TODO Auto-generated method stub
		// ���ص���String������Ҫǿ��ת��
		return (String) (this.columnNames.get(column));
	}

}
