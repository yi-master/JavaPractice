package SmallStuManageSys_mssqlserver_JDBC;

import java.awt.*;
import java.awt.event.*;
import java.sql.*;
import javax.swing.*;


public class ReviseBox extends JDialog implements ActionListener{

	JButton update, cancel;
	JLabel[] jl;
	JTextField[] jtf;
	JPanel jp1, jp2;

	// 定义用来操作数据的对象
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}

	// owner 它的父窗口
	// title 窗口名
	// model 指定的模式窗口，还有非模式窗口
	public ReviseBox(JFrame owner, boolean model,StuJTableModel sjm,int rowNums) {

		super(owner, model);// 必须调用父类方法,才能达到模式窗口效果

		jl = new JLabel[6];
		jl[0] = new JLabel("ID");
		jl[1] = new JLabel("Name");
		jl[2] = new JLabel("Sex");
		jl[3] = new JLabel("Age");
		jl[4] = new JLabel("Nnative Nlace");
		jl[5] = new JLabel("Department");

		jtf = new JTextField[6];
		for (int i = 0; i < jtf.length; i++) {
			jtf[i] = new JTextField(20);
		}
		
		for(int j=0;j<jtf.length;j++){
			jtf[j].setText(sjm.getValueAt(rowNums, j).toString());
		}
		
//		jtf[0].setText((String)sjm.getValueAt(rowNums, 0));
//		jtf[1].setText((String)sjm.getValueAt(rowNums, 1));
//		jtf[2].setText((String)sjm.getValueAt(rowNums, 2));
//		jtf[3].setText(sjm.getValueAt(rowNums, 3).toString());
//		jtf[4].setText((String)sjm.getValueAt(rowNums, 4));
//		jtf[5].setText((String)sjm.getValueAt(rowNums, 5));

		jtf[0].setEditable(false);
		
		update = new JButton("Update");
		update.setActionCommand("update");
		update.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);

		jp1 = new JPanel(new GridLayout(6, 2));
		jp2 = new JPanel();

		for (int i = 0; i < jl.length; i++) {

			jp1.add(jl[i]);
			jp1.add(jtf[i]);
		}

		jp2.add(update);
		jp2.add(cancel);

		this.add(jp1);
		this.add(jp2, BorderLayout.SOUTH);

		this.setSize(250, 250);
		this.setLocationRelativeTo(null);
		this.setTitle("Message");
		this.setVisible(true);// 必须放在最后
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if(e.getActionCommand().equals("update")){
			String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
			String username = "sa";
			String password = "yuyiQQhao0313";

			String sqlUpdate = "update stu set stuName=?,stuSex=?,stuAge=?,stuNP=?,stuDepartment=? where stuID=?";
			
			
			try {
				Class.forName(sqlDriver);
				ct = DriverManager.getConnection(url, username, password);
				ps = ct.prepareStatement(sqlUpdate);
				
				//给?赋值
				for(int i=1;i<jtf.length;i++){
					
					ps.setString(i,jtf[i].getText());
				}
//				ps.setString(1,jtf[1].getText());
//				ps.setString(2,jtf[2].getText());
//				ps.setString(3,jtf[3].getText());
//				ps.setString(4,jtf[4].getText());
//				ps.setString(5,jtf[5].getText());
				ps.setString(6,jtf[0].getText());
				
				int k = ps.executeUpdate();
				this.dispose();
				
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
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
				} catch (Exception e1) {
					// TODO: handle exception
					e1.printStackTrace();
				}
			}
		}else if(e.getActionCommand().equals("cancel")){
			this.dispose();
		}
	}

}
