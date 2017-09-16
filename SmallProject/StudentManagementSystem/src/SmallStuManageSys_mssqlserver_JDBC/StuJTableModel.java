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

	//���캯������ʼ������ģ��
	public StuJTableModel(){
		this.ShowAllStu();
	}
	
	// ��ѯ���䱾���ǳ�ʼ����
	public void QueryStu(String sql) {

		// ʵ����SqlHelper���󣬲������������������ݿ�
		SqlHelper sh = new SqlHelper();
		
		columnNames = new Vector();
		String[] names = { "ID", "Name", "Sex", "Age", "Native Place", "Department" };
		// ��������(���������뵽columnNames������)
		sh.setDatabaseColumn(columnNames, names);

		rowData = new Vector();// ��������ݵ�����
		//��ȡsql��䲢ִ��
		sh.ExecuteQueryOperation(sql, rowData,columnNames);

	}
	
	//��ѧ����Ĳ���������ɾ���ģ�
	public boolean UpdateStu(String sql,String str[]){
		
		//����SqlHelper(������򲢷��Բ����ǣ����԰�SqlHelper����static)
		SqlHelper sh=new SqlHelper();
		return sh.UpdatePerson(sql, str);
	}

	public void ShowAllStu() {
		String sql = "select * from stu";
		this.QueryStu(sql);
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
