/*
JDialog(ģʽ�Ի���)
public Dialog(Frame owner,boolean modal)����һ����ʼ���ε� Dialog��ָ���������� Frame��ģ̬��һ���յı��⡣ 
���� 
owner - �Ի���������߻� null����˶Ի���û�������� 
modal - ָ���Ի����Ƿ���ʾ�û����뵽�����������ڡ� 
���false ���Ի�����MODELESS ; ���true ��ģ̬������������ΪAPPLICATION_MODAL
�쳣 
IllegalArgumentException - ��� owner�� GraphicsConfiguration���Ǵ���Ļ�豸 
HeadlessException - ��ʱ GraphicsEnvironment.isHeadless()���� true 

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

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		AddBox cpb = new AddBox(new JFrame(), true);
	}

	// owner ���ĸ�����
	// title ������
	// modal ָ����ģʽ���ڣ����з�ģʽ����
	public AddBox(JFrame owner, boolean modal) {

		super(owner, modal);// ������ø��෽��,���ܴﵽģʽ����Ч��

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
		this.setVisible(true);// ����������
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getActionCommand().equals("add")) {
				
				StuJTableModel temp=new StuJTableModel();
				String sql="insert into stu values(?,?,?,?,?,?)";
				String str[] = new String[jtf.length];
				for(int i=0;i<jtf.length;i++){
					str[i]=jtf[i].getText();
				}
				if(!temp.UpdateStu(sql, str)){
					
					JOptionPane.showMessageDialog(this, "Add failed");
				}
				this.dispose();
			
		} else if (e.getActionCommand().equals("cancel")) {
			this.dispose();
		}
	}
}
