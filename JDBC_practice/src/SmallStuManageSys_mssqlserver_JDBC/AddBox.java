/*
JDialog(模式对话框)
public Dialog(Frame owner,boolean model)构造一个初始无形的 Dialog与指定的所有者 Frame和模态和一个空的标题。 
参数 
owner - 对话框的所有者或 null如果此对话框没有所有者 
model - 指定对话框是否显示用户输入到其他顶级窗口。 
如果false ，对话框是MODELESS ; 如果true ，模态类型属性设置为APPLICATION_model
异常 
IllegalArgumentException - 如果 owner的 GraphicsConfiguration不是从屏幕设备 
HeadlessException - 当时 GraphicsEnvironment.isHeadless()返回 true 

 */
package SmallStuManageSys_mssqlserver_JDBC;

import java.awt.*;
import java.awt.event.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import javax.swing.*;

public class AddBox extends JDialog implements ActionListener {

	JButton add, cancel;
	JLabel[] jl;
	JTextField[] jtf;
	JPanel jp1, jp2;

	// 定义用来操作数据的对象
	Connection ct = null;
	PreparedStatement ps = null;
	ResultSet rs = null;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddBox cpb = new AddBox(new JFrame(), true);
	}

	// owner 它的父窗口
	// title 窗口名
	// model 指定的模式窗口，还有非模式窗口
	public AddBox(JFrame owner, boolean model) {

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

		add = new JButton("Add");
		add.setActionCommand("add");
		add.addActionListener(this);
		cancel = new JButton("Cancel");
		cancel.setActionCommand("cancel");
		cancel.addActionListener(this);

		jp1 = new JPanel(new GridLayout(6, 2));
		jp2 = new JPanel();

		for (int i = 0; i < jl.length; i++) {

			jp1.add(jl[i]);
			jp1.add(jtf[i]);
		}

		jp2.add(add);
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
		if (e.getActionCommand().equals("add")) {

			String sqlDriver = "com.microsoft.sqlserver.jdbc.SQLServerDriver";
			String url = "jdbc:sqlserver://127.0.0.1:1433;databaseName=Java_Practice";
			String username = "sa";
			String password = "yuyiQQhao0313";

			String sqlAdd = "insert into stu values(?,?,?,?,?,?)";
			try {
				Class.forName(sqlDriver);
				ct = DriverManager.getConnection(url, username, password);
				ps = ct.prepareStatement(sqlAdd);
				//jtf[2].setText("男");
				for (int j = 0; j < jtf.length; j++) {
					
					if(jtf[j].getText().equals("")){
						JOptionPane.showMessageDialog(this, "Each field cannot be empty!");
						return;
					}else{
						if(j==2&&jtf[2].getText()!="女"){
							jtf[j].setText("男");
						}else if(jtf[2].getText()=="女"){
							jtf[j].setText("女");
						}
						ps.setString(j + 1, jtf[j].getText());
					}
				}
				
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
			
		} else if (e.getActionCommand().equals("cancel")) {
			this.dispose();
		}
	}
}
