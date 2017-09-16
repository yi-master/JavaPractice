/*
 * chatting�ͻ���
 * �ܹ���¼��ʵ�ֻ��������칦�ܣ�˽�ģ����ģ�
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

	// ���山����Ҫ�����
	private JLabel jl1;

	// �����в���Ҫ�����
	// ������JPanel ����һ��ѡ�������
	private JTabbedPane jtp;
	private JPanel jp2;
	private JLabel jp2_jl0, jp2_jl1, jp2_jl2;
	private JTextField jp2_jtf;

	// �����ϲ���Ҫ�����
	private JPanel jp1;
	private JButton jb;

	private Socket s;
	private Vector<String> names = new Vector();

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new ChattingClientLogin();
	}

	public ChattingClientLogin() {

		// ������
		jl1 = new JLabel(new ImageIcon("D:/eclipse workspace/NetworkCourseDesign/src/chatting_picture/title.PNG"));
		this.add(jl1, "North");

		// �����в�
		jp2 = new JPanel(new GridLayout(2, 2));

		jp2_jl0 = new JLabel("Welcome to use GG chatting", JLabel.RIGHT);
		jp2_jl1 = new JLabel("Please input you ID");
		jp2_jl2 = new JLabel("Chatting ID", JLabel.CENTER);
		jp2_jtf = new JTextField();

		// ��˳��ѿؼ����뵽jp2
		jp2.add(jp2_jl0);
		jp2.add(jp2_jl1);
		jp2.add(jp2_jl2);
		jp2.add(jp2_jtf);
		// ����ѡ�����
		jtp = new JTabbedPane();
		jtp.add("Chatting Login", jp2);
		this.add(jtp);

		// �����ϲ�
		jp1 = new JPanel();
		// ��Ӧ�û������¼
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
		// ����û������¼��ť
		if (e.getSource() == jb) {

			try {
				s = new Socket("127.0.0.1", 9999);

				BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

				pw.println(jp2_jtf.getText());
				String string = br.readLine();

				if (jp2_jtf.getText().equals("")) {// δ�����û���ʱ
					JOptionPane.showMessageDialog(this, "Don't forget to type in your ID!");
				} else if (string.equals("STC_Name_repetition")) {// �����û����ظ�ʱ
					JOptionPane.showMessageDialog(this, "Name repetition!");
				} else {
					// ʵ�������촰��
					new ChattingSendView(jp2_jtf.getText());
					// �رյ�¼����
					this.dispose();
				}
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			}

		}
	}

	class ChattingSendView extends JFrame implements ActionListener, WindowListener, PopupMenuListener {

		private JTextField jtf;
		private JTextArea jta;
		private JScrollPane jsp;
		private JButton jb;
		private JPanel jp;
		private JCheckBox checkbox;
		private JComboBox combobox;

		String ownerID;

		public ChattingSendView(String ownerID) {
			new ReceiveServerMessageThread().start();
			this.ownerID = ownerID;

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
			combobox.addPopupMenuListener(this);// ���������ȡ��¼���˵�����

			jp = new JPanel();
			jp.setBackground(Color.gray);

			this.addWindowListener(this);

			jp.add(jtf);
			jp.add(jb);
			jp.add(checkbox);
			jp.add(combobox);

			this.add(jsp, "Center");
			this.add(jp, "South");

			this.setIconImage(
					new ImageIcon("D:/eclipse workspace/NetworkCourseDesign/src/chatting_picture/0.PNG").getImage());
			this.setVisible(true);
			this.setTitle(ownerID);
			this.setSize(500, 300);
			this.setResizable(false);
			this.setLocation(600, 300);
			this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		}

		@Override
		public void actionPerformed(ActionEvent e) {
			// TODO Auto-generated method stub
			if (e.getSource() == checkbox) {
				if (checkbox.isSelected()) {
					combobox.setEnabled(true);
				} else
					combobox.setEnabled(false);
			}
			if (e.getSource() == jb) {
				// �����·��Ͱ�ťʱ
				String string = jtf.getText();
				if (checkbox.isSelected()) {
					// ˽�Ŀ򱻹���ʱ����Private chat˽�ģ�
					try {

						PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
						pw.println("CTS_private_chat");// �����������˽���ź�
						pw.println((String) combobox.getSelectedItem());// �ѵ�ǰ��ѡ���˽�Ķ��󣩷��͸�������
						pw.println(string);// �ѷ��Ϳ����ݷ��͸�������

						jtf.setText("");
						jta.append("You say to " + (String) combobox.getSelectedItem() + ":\n\r" + string + "\r\n");

					} catch (Exception e1) {
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
				} else {
					// δ������ʱ����Public chat���ģ�
					try {
						PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
						pw.println("CTS_public_chat");// ����������͹����ź�
						pw.println(string);// �ѷ��Ϳ����ݷ��͸�������

						jtf.setText("");

					} catch (IOException e2) {
						e2.printStackTrace();
					}
				}
			}
		}

		// ���շ�������Ϣ�߳�
		class ReceiveServerMessageThread extends Thread {

			public Socket getSocket() {
				return s;
			}

			public void run() {

				try {

					BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					PrintWriter pw = new PrintWriter(s.getOutputStream(), true);

					while (true) {
						// ���շ�������������Ϣ
						String string = br.readLine();// ��ȡ�����ĵ�һ����Ϣ������Ҫִ�������źŻ�Ӧ��

						// �Է������������ź��������ֻ�Ӧ
						if (string.equals("STC_Server_disconnect")) {// ���������رգ�Close
																		// server����ť���������ź�

							jta.setText("The server is closed!");
							br.close();
							pw.close();
							s.close();

						} else if (string.equals("STC_Server_bulk")) {// �����������ͣ�Send����ť���������ź�

							String message = br.readLine();// ��ȡ��������������Ϣ
							jta.append("Server say :" + message + "\n\r");// ��ʾSTC_Server_bulk��Ϣ

						} else if (string.equals("STC_public_chat")) {// ���������ط��ĵ�һ���ź�Ϊ����ʱ

							// ��ȡ������������͵ļ�������
							String userName = br.readLine();// ��ȡ�����ĵڶ��У��û�����
							String message2 = br.readLine();// ��ȡ�����ĵ����У���Ϣ��

							jta.append(userName + " say :" + message2 + "\n\r");

						} else if (string.equals("STC_private_chat")) {// ���������ط��ĵ�һ���ź�Ϊ˽��ʱ

							// ��ȡ������������͵ļ�������
							String userName = br.readLine();// ��ȡ�����ĵڶ��У��û�����
							String friend = br.readLine();// ��ȡ�����ĵ����У�˽�Ķ���
							String message3 = br.readLine();// ��ȡ�����ĵ����У���Ϣ��

							// ��ʾ�ı����ı��򣨼����촰�ڣ�
							jta.append(userName + " say to " + friend + ": \n" + message3 + "\n\r");

						} else if (string.equals("STC_get_names")) {

							String strNameList = br.readLine();// ��ȡ�����û���
							String[] nameList = strNameList.split("\\|");

							// �����û���
							names.clear();// ��������û�������ԭ�����ڵ��û�����
							for (int i = 0; i < nameList.length; i++) {// �����е��û����������
								names.add(nameList[i]);
							}
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

		}

		@Override
		public void windowClosed(WindowEvent e) {
			// TODO Auto-generated method stub
			try {

				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
				pw.println("CTS_Client_disconnect");// ����������Ϳͻ��������жϵ���Ϣ
				pw.close();
				s.close();
				this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
			} catch (Exception e0) {
				// TODO: handle exception
				e0.printStackTrace();
			}
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
		// ����combobox�����ݣ��������û�����
		public void popupMenuWillBecomeVisible(PopupMenuEvent e) {
			// TODO Auto-generated method stub
			try {
				PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
				pw.println("CTS_get_names");// ������ͻ�ȡ����
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
