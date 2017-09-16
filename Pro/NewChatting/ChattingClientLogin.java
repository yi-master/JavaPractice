/*
 * chatting客户端
 * 能够登录，实现基本的聊天功能（私聊，公聊）
 */

package NewChatting;

import javax.swing.*;
import javax.swing.event.PopupMenuEvent;
import javax.swing.event.PopupMenuListener;

import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Vector;
import java.awt.*;

public class ChattingClientLogin extends JFrame implements ActionListener {

	// 定义北部需要的组件
	private JLabel jl1;

	// 定义中部需要的组件
	// 有三个JPanel ，有一个选项卡管理窗口
	private JTabbedPane jtp;
	private JPanel jp2;
	private JLabel jp2_jl0, jp2_jl1, jp2_jl2;
	private JTextField jp2_jtf;

	// 定义南部需要的组件
	private JPanel jp1;
	private JButton jb;

	private Socket s;
	private Vector<String> names = new Vector();
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChattingClientLogin();
	}

	public ChattingClientLogin() {

		// 处理北部
		jl1 = new JLabel(new ImageIcon("D:/eclipse workspace/NetworkCourseDesign/src/chatting_picture/title.PNG"));
		this.add(jl1, "North");

		// 处理中部
		jp2 = new JPanel(new GridLayout(2, 2));

		jp2_jl0 = new JLabel("Welcome to use GG chatting", JLabel.RIGHT);
		jp2_jl1 = new JLabel("Please input you ID");
		jp2_jl2 = new JLabel("Chatting ID", JLabel.CENTER);
		jp2_jtf = new JTextField();

		// 按顺序把控件加入到jp2
		jp2.add(jp2_jl0);
		jp2.add(jp2_jl1);
		jp2.add(jp2_jl2);
		jp2.add(jp2_jtf);
		// 创建选项卡窗口
		jtp = new JTabbedPane();
		jtp.add("Chatting Login", jp2);
		this.add(jtp);

		// 处理南部
		jp1 = new JPanel();
		// 响应用户点击登录
		jb = new JButton(new ImageIcon("D:/eclipse workspace/NetworkCourseDesign/src/chatting_picture/login.PNG"));
		jb.addActionListener(this);

		jp1.add(jb);

		this.add(jp1, "South");

		this.setTitle("GG Chatting");
		this.setLocation(450, 235);
		this.setResizable(false);
		this.setSize(450, 285);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(
				new ImageIcon("D:/eclipse workspace/NetworkCourseDesign/src/chatting_picture/0.PNG").getImage());
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// 如果用户点击登录按钮
		if (e.getSource() == jb) {

			try {
				s = new Socket("127.0.0.1", 10000);

				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

				pw.println(jp2_jtf.getText());
				String string = br.readLine();

				if (jp2_jtf.getText().equals("")) {// 未输入用户名时
					JOptionPane.showMessageDialog(this, "Don't forget to type in your ID!");
				} else if (string.equals("STC_Name_repetition")) {// 输入用户名重复时
					JOptionPane.showMessageDialog(this, "Name repetition!");
				} else {
					// 实例化聊天窗口
					new ChattingSendView(jp2_jtf.getText());
					// 关闭登录界面
					this.dispose();
				}
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}

		}
	}

	class ChattingSendView extends JFrame implements ActionListener, WindowListener, PopupMenuListener {

		private JLabel jl;
		private JRadioButton jrb1, jrb2;
		private ButtonGroup bg;
		private JTextField jtf;
		private JTextArea jta;
		private JScrollPane jsp;
		private JButton jb;
		private JPanel jp, jp2;
		private JCheckBox checkbox;
		private JComboBox combobox,combobox2;
		private String bq[]={"(*^▽^*)","(^_−)☆","o(´^｀)o","o(╥﹏╥)o"};
		
		String ownerID;

		public ChattingSendView(String ownerID) {
			new ReceiveServerMessageThread().start();
			this.ownerID = ownerID;

			jl = new JLabel("You can select your online status ");
			jrb1 = new JRadioButton("offline");
			jrb1.addActionListener(this);
			jrb2 = new JRadioButton("online");
			jrb2.addActionListener(this);
			bg = new ButtonGroup();
			bg.add(jrb1);
			bg.add(jrb2);
			jp2 = new JPanel();

			jta = new JTextArea();
			jta.setText("You have successfully connected to the server!\n\r");
			jta.setEditable(false);
			jsp = new JScrollPane(jta);

			jtf = new JTextField(20);
			jb = new JButton("Send");
			jb.addActionListener(this);
			checkbox = new JCheckBox("Private chat");
			checkbox.addActionListener(this);
			combobox = new JComboBox(names);
			combobox.setEnabled(false);
			combobox.setPreferredSize(new Dimension(85, 25));
			combobox.addPopupMenuListener(this);// 向服务器获取登录的人的名单
			jp = new JPanel();
			jp.setBackground(Color.gray);

			combobox=new JComboBox(bq);
			
			this.addWindowListener(this);

			jp2.add(jl);
			jp2.add(jrb1);
			jp2.add(jrb2);

			jp.add(jtf);
			jp.add(jb);
			jp.add(combobox2);
			jp.add(checkbox);
			jp.add(combobox);

			this.add(jp2, "North");
			this.add(jsp, "Center");
			this.add(jp, "South");

			this.setIconImage(
					new ImageIcon("D:/eclipse workspace/NetworkCourseDesign/src/chatting_picture/0.PNG").getImage());
			this.setVisible(true);
			this.setTitle(ownerID);
			this.setSize(600, 300);
			this.setResizable(false);
			this.setLocation(600, 300);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if(e.getSource()==jrb1){//离线按钮按下时
				jtf.setEnabled(false);
				jb.setEnabled(false);
				checkbox.setEnabled(false);
				try{
					PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
					pw.println("CTS_public_offline");//向服务器发送离线信号
					pw.println(ownerID+" have been offline");// 把发送框内容发送给服务器
				}catch (Exception e3) {
					// TODO: handle exception
					e3.printStackTrace();
				}
			}
			if(e.getSource()==jrb2){//上线按钮按下时
				jtf.setEnabled(true);
				jb.setEnabled(true);
				checkbox.setEnabled(true);
				try{
					PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
					pw.println("CTS_public_online");//向服务器发送上线信号
					pw.println(ownerID+" is online");// 把发送框内容发送给服务器
				}catch (Exception e4) {
					// TODO: handle exception
					e4.printStackTrace();
				}
			}
			if (e.getSource() == checkbox) {
				if (checkbox.isSelected()) {
					combobox.setEnabled(true);
				} else
					combobox.setEnabled(false);
			}
			if (e.getSource() == jb) {
				// 当按下发送按钮时
				String string = jtf.getText();
				if (checkbox.isSelected()) {// 私聊框被勾中时（即Private chat私聊）
					
					try {

						PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
						pw.println("CTS_private_chat");// 向服务器发送私聊信号
						pw.println((String) combobox.getSelectedItem());// 把当前所选项（即私聊对象）发送给服务器
						pw.println(string);// 把发送框内容发送给服务器

						jtf.setText("");
						jta.append("You say to " + (String) combobox.getSelectedItem() + ":\n\r" + string + "\r\n");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {// 未被勾中时（即Public chat公聊）
					
					try {
						PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
						pw.println("CTS_public_chat");// 向服务器发送公聊信号
						pw.println(string);// 把发送框内容发送给服务器

						jtf.setText("");

					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		}

		// 接收服务器信息线程
		class ReceiveServerMessageThread extends Thread {

			public Socket getSocket() {
				return s;
			}

			public void run() {

				try {

					BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

					while (true) {
						// 接收服务器发来的消息
						String string = br.readLine();// 读取发来的第一行消息（即需要执行哪种信号回应）

						// 对服务器发来的信号做出各种回应
						if (string.equals("STC_Server_disconnect")) {// 当服务器关闭（Close server）按钮被触发的信号

							jta.setText("The server is closed!Please recover open server and client!");

							br.close();
							pw.close();
							s.close();

						} else if (string.equals("STC_Server_bulk")) {// 当服务器发送（Send）按钮被触发的信号

							String message = br.readLine();// 读取服务器发来的消息
							jta.append("Server say :" + message + "\n\r");// 显示服务器发送的消息

						} else if (string.equals("STC_public_chat")) {// 当服务器回发的第一行信号为公聊时

							// 读取后面服务器发送的几行内容
							String userName = br.readLine();// 读取发来的第二行（用户名）
							String message2 = br.readLine();// 读取发来的第三行（消息）

							jta.append(userName + " say :" + message2 + "\n\r");

						} else if (string.equals("STC_private_chat")) {// 当服务器回发的第一行信号为私聊时

							// 读取后面服务器发送的几行内容
							String userName = br.readLine();// 读取发来的第二行（用户名）
							String friend = br.readLine();// 读取发来的第三行（私聊对象）
							String message3 = br.readLine();// 读取发来的第四行（消息）

							// 显示文本在文本域（即聊天窗口）
							jta.append(userName + " say to " + friend + ": \n" + message3 + "\n\r");

						} else if (string.equals("STC_get_names")) {

							String strNameList = br.readLine();// 读取所有用户名
							String[] nameList = strNameList.split("\\|");

							// 更新用户名
							names.clear();// 清除所有用户名（即原来存在的用户名）
							for (int i = 0; i < nameList.length; i++) {// 把现有的用户名重新添加
								names.add(nameList[i]);
							}
						} else if (string.equals("STC_Client_disconnect")) {//当服务器回发第一行客户端断开连接的信号时

							String message = br.readLine();// 读取服务器发来的消息
							jta.append(message+"\n");//将消息显示到文本域中
							s.close();
						}else if(string.equals("STC_public_offline")){//当服务器回发第一行离线信号时
							String message =br.readLine();// 读取服务器发来的消息
							jta.append(message+"\n");//将消息显示到文本域中

						}else if(string.equals("STC_public_online")){//当服务器回发第一行上线信号时
							String message =br.readLine();// 读取服务器发来的消息
							jta.append(message+"\n");//将消息显示到文本域中
						}

					}

				} catch (Exception e) {
					// TODO: handle exception
					e.printStackTrace();
				}
			}
		}

		@Override
		public void windowOpened(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowClosing(WindowEvent e) {
			// TODO Auto-generated method stub
			try {

				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
				pw.println("CTS_Client_disconnect");// 向服务器发送客户端连接中断的信息
				pw.close();
				names.remove(ownerID);
				
			} catch (Exception e0) {
				// TODO: handle exception
				e0.printStackTrace();
			}
		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowIconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeiconified(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowActivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void windowDeactivated(WindowEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		// 更新combobox的内容（即更新用户名）
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			// TODO Auto-generated method stub
			try {
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
				pw.println("CTS_get_names");// 向服务器发送获取名字
			} catch (IOException e0) {
				e0.printStackTrace();
			}
		}

		@Override
		public void popupMenuWillBecomeInvisible(PopupMenuEvent e) {
			// TODO Auto-generated method stub

		}

		@Override
		public void popupMenuCanceled(PopupMenuEvent e) {
			// TODO Auto-generated method stub

		}
	}
}
