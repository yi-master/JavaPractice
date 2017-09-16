/*
 * 服务器
 * 服务器端控制界面，可以启动服务器，关闭服务器
 * 可以查看在线用户，发送广播
 */

package NewChatting;

import javax.swing.*;

import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.net.*;
import java.util.Vector;

public class MyServerFrame extends JFrame implements ActionListener {

	private JPanel jp1, jp2;
	private JButton jb1, jb2, jb3;
	private JTextField jtf;
	private JTextArea jta;
	private JScrollPane jsp;
	private JLabel jl;
	private JComboBox combobox;

	ServerSocket ss;
	Vector<String> names = new Vector<String>();
	Vector<ReceiveClientMessageThread> threads = new Vector();
	ListenerThead lt;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		MyServerFrame msf = new MyServerFrame();
	}

	public MyServerFrame() {

		jp1 = new JPanel(new GridLayout(1, 2));
		jb1 = new JButton("Open Server");
		jb1.setEnabled(true);
		jb1.addActionListener(this);
		jb2 = new JButton("Close Server");
		jb2.setEnabled(false);
		jb2.addActionListener(this);

		jta = new JTextArea();
		jta.setEditable(false);
		jta.setEnabled(false);
		jsp = new JScrollPane(jta);

		jp2 = new JPanel();
		jtf = new JTextField(20);
		jtf.setEnabled(false);
		jb3 = new JButton("send");
		jb3.setEnabled(false);
		jb3.addActionListener(this);
		jl = new JLabel("在线者");
		combobox = new JComboBox(names);
		combobox.setEnabled(false);
		combobox.setPreferredSize(new Dimension(95, 25));

		jp1.add(jb1);
		jp1.add(jb2);

		jp2.add(jtf);
		jp2.add(jb3);
		jp2.add(jl);
		jp2.add(combobox);

		this.add(jp1, "North");
		this.add(jsp, "Center");
		this.add(jp2, "South");

		this.setSize(500, 300);
		this.setLocation(500, 200);
		this.setResizable(false);
		this.setVisible(true);
		this.setTitle("Server Manager");
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(
				new ImageIcon("D:/eclipse workspace/NetworkCourseDesign/src/chatting_picture/0.PNG").getImage());
	}

	// 接收客户端信息线程
	class ReceiveClientMessageThread extends Thread {

		private Socket s;
		private String name;

		ReceiveClientMessageThread(Socket s, String name) {
			this.s = s;
			this.name = name;
		}

		public Socket getSocket() {
			return s;
		}

		public void run() {

			try {

				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

				while (true) {
					// 接收客户端发来的消息
					String string = br.readLine();// 读取客户端发来的第一行消息（即需要执行哪种信号回应）
					StringBuilder sb = new StringBuilder();// 用来存储需要发送的用ID、消息等

					// 对客户端发来的信号做出各种回应
					if (string.equals("CTS_Client_disconnect")) {// 当客户端窗口关闭时，触发的信号

						threads.remove(this);
						names.remove(name);

						br.close();
						pw.close();
						s.close();

						jta.append(name + " disconnect\n\r");

					} else if (string.equals("CTS_public_chat")) {// 客户端发送按钮（send）被按下，未勾选私聊（checkbox）时，触发的信号
						String message = br.readLine();

						sb.append("STC_public_chat\n");// sb暂存向客户端发送的公聊信号
						// sb暂存用户名和消息
						sb.append(name + "\n");
						sb.append(message + "\n");

						// System.out.print(sb.toString());
						// 向客户端线程回发信号（发送sb中的三行内容（一行信号两行内容）至客户端）
						sendMessageToAll(sb.toString());

						jta.append(name + " say :" + message + "\r\n");

					} else if (string.equals("CTS_private_chat")) {// 客户端发送按钮（send）被按下，勾选私聊（checkbox）时，触发的信号

						// 读取客户端发来的消息
						String friend = br.readLine();
						String message = br.readLine();

						sb.append("STC_private_chat\n");// sb暂存向客户端发送的私聊信号
						// sb暂存用户名，私聊对象和消息
						sb.append(name + "\n");
						sb.append(friend + "\n");
						sb.append(message + "\n");
						// 向客户端线程回发信号（发送sb中的四行（一行信号三行内容）内容至客户端）
						sendMessageToPerson(sb.toString(), names.indexOf(friend));

						// 显示文本在文本域（即聊天窗口）
						jta.append(name + " say to " + friend + " : \n\r");
						jta.append(message + "\n\r");

					} else if (string.equals("CTS_get_names")) {// 当有用户登录时触发
						sb.append("STC_get_names\n");
						for (int i = 0; i < names.size(); i++) {

							if (i != 0) {
								sb.append("|");// 把每个ID用“|”隔开
							}
							sb.append(names.get(i));
						}
						sb.append("\n");
						sendMessageToPerson(sb.toString(), threads.indexOf(this));// 把用户名发送给客户端
					}

				}
			} catch (Exception e) {
				// TODO: handle exception
				e.printStackTrace();
			}
		}
	}

	class ListenerThead extends Thread {

		public void run() {

			try {
				ss = new ServerSocket(9999);
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			while (true) {
				Socket s = null;
				try {

					s = ss.accept();
					BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
					String name = br.readLine();

					// 判断重复用户名
					if (ReName(name)) {

						pw.println("STC_Name_repetition");// 发送用户名重复信号
						pw.close();
						br.close();
						s.close();
					} else {// 连接成功
						pw.println("connect OK");
						names.add(name);
						jta.append(name + " has successfully connected to the server!\n");
						ReceiveClientMessageThread rcmt = new ReceiveClientMessageThread(s, name);
						rcmt.start();
						threads.add(rcmt);
					}
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}

			}
		}
	}

	// 判断名称重复
	private boolean ReName(String name) {
		if (names.contains(name)) {
			return true;
		} else
			return false;
	}

	// 向所有人发送消息
	private void sendMessageToAll(String message) {
		for (int i = 0; i < threads.size(); i++) {
			PrintWriter pw;
			try {
				pw = new PrintWriter(((ReceiveClientMessageThread) (threads.get(i))).getSocket().getOutputStream(),
						true);
				pw.println(message);
			} catch (IOException e) {
				e.printStackTrace();
			}
		}
	}

	// 向私人对象发送消息
	private void sendMessageToPerson(String message, int i) {
		PrintWriter pw;
		try {
			pw = new PrintWriter(((ReceiveClientMessageThread) (threads.get(i))).getSocket().getOutputStream(), true);
			pw.println(message);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		if (e.getSource() == jb1) {
			// 当服务器（Open server）按钮被按下时，启动服务器的监听
			jta.setText("Server is listering...\n\r");

			// 启动线程实现服务器监听
			lt = new ListenerThead();
			lt.start();

			jb1.setEnabled(false);
			jb2.setEnabled(true);
			jb3.setEnabled(true);
			jta.setEnabled(true);
			jtf.setEnabled(true);
			combobox.setEnabled(true);
		}
		if (e.getSource() == jb2) {
			// 当服务器的关闭按钮（Close server）被按下时，服务器向客户发送一条断开的信息
			try {
				for (int i = 0; i < threads.size(); i++) {

					Socket s = ((ReceiveClientMessageThread) threads.get(i)).getSocket();
					PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
					BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					pw.println("STC_Server_disconnect");// 向客户端发送服务器关闭的信号
					pw.close();
					br.close();
					s.close();
				}
				ss.close();
				jta.setText("The server is closed!");
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}
			// 删除线程中所有元素
			threads.removeAllElements();
			// 删除所有用户名
			names.removeAllElements();

			jb1.setEnabled(true);
			jb2.setEnabled(false);
			jb3.setEnabled(false);
			jtf.setEnabled(false);
			jta.setEnabled(false);
		}
		if (e.getSource() == jb3) {
			// 当服务器发送按钮被按下时，向所有客户端发送消息
			String string = jtf.getText();
			jta.append("Server say :" + string + "\n\r");
			try {
				// 对每个客户端都发出服务器信息
				for (int i = 0; i < threads.size(); i++) {

					PrintWriter pw = new PrintWriter(
							((ReceiveClientMessageThread) (threads.get(i))).getSocket().getOutputStream(), true);
					pw.println("STC_Server_bulk");// 向客户端发送服务器群发信号
					pw.println(jtf.getText());// 向客户端发送服务器群发内容
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			jtf.setText("");
		}
	}

}
