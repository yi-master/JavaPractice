/*
 * ������
 * �������˿��ƽ��棬�����������������رշ�����
 * ���Բ鿴�����û������͹㲥
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
		jl = new JLabel("������");
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

	// ���տͻ�����Ϣ�߳�
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
					// ���տͻ��˷�������Ϣ
					String string = br.readLine();// ��ȡ�ͻ��˷����ĵ�һ����Ϣ������Ҫִ�������źŻ�Ӧ��
					StringBuilder sb = new StringBuilder();// �����洢��Ҫ���͵���ID����Ϣ��

					// �Կͻ��˷������ź��������ֻ�Ӧ
					if (string.equals("CTS_Client_disconnect")) {// ���ͻ��˴��ڹر�ʱ���������ź�

						threads.remove(this);
						names.remove(name);

						br.close();
						pw.close();
						s.close();

						jta.append(name + " disconnect\n\r");

					} else if (string.equals("CTS_public_chat")) {// �ͻ��˷��Ͱ�ť��send�������£�δ��ѡ˽�ģ�checkbox��ʱ���������ź�
						String message = br.readLine();

						sb.append("STC_public_chat\n");// sb�ݴ���ͻ��˷��͵Ĺ����ź�
						// sb�ݴ��û�������Ϣ
						sb.append(name + "\n");
						sb.append(message + "\n");

						// System.out.print(sb.toString());
						// ��ͻ����̻߳ط��źţ�����sb�е��������ݣ�һ���ź��������ݣ����ͻ��ˣ�
						sendMessageToAll(sb.toString());

						jta.append(name + " say :" + message + "\r\n");

					} else if (string.equals("CTS_private_chat")) {// �ͻ��˷��Ͱ�ť��send�������£���ѡ˽�ģ�checkbox��ʱ���������ź�

						// ��ȡ�ͻ��˷�������Ϣ
						String friend = br.readLine();
						String message = br.readLine();

						sb.append("STC_private_chat\n");// sb�ݴ���ͻ��˷��͵�˽���ź�
						// sb�ݴ��û�����˽�Ķ������Ϣ
						sb.append(name + "\n");
						sb.append(friend + "\n");
						sb.append(message + "\n");
						// ��ͻ����̻߳ط��źţ�����sb�е����У�һ���ź��������ݣ��������ͻ��ˣ�
						sendMessageToPerson(sb.toString(), names.indexOf(friend));

						// ��ʾ�ı����ı��򣨼����촰�ڣ�
						jta.append(name + " say to " + friend + " : \n\r");
						jta.append(message + "\n\r");

					} else if (string.equals("CTS_get_names")) {// �����û���¼ʱ����
						sb.append("STC_get_names\n");
						for (int i = 0; i < names.size(); i++) {

							if (i != 0) {
								sb.append("|");// ��ÿ��ID�á�|������
							}
							sb.append(names.get(i));
						}
						sb.append("\n");
						sendMessageToPerson(sb.toString(), threads.indexOf(this));// ���û������͸��ͻ���
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

					// �ж��ظ��û���
					if (ReName(name)) {

						pw.println("STC_Name_repetition");// �����û����ظ��ź�
						pw.close();
						br.close();
						s.close();
					} else {// ���ӳɹ�
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

	// �ж������ظ�
	private boolean ReName(String name) {
		if (names.contains(name)) {
			return true;
		} else
			return false;
	}

	// �������˷�����Ϣ
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

	// ��˽�˶�������Ϣ
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
			// ����������Open server����ť������ʱ�������������ļ���
			jta.setText("Server is listering...\n\r");

			// �����߳�ʵ�ַ���������
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
			// ���������Ĺرհ�ť��Close server��������ʱ����������ͻ�����һ���Ͽ�����Ϣ
			try {
				for (int i = 0; i < threads.size(); i++) {

					Socket s = ((ReceiveClientMessageThread) threads.get(i)).getSocket();
					PrintWriter pw = new PrintWriter(s.getOutputStream(), true);
					BufferedReader br = new BufferedReader(new InputStreamReader(s.getInputStream()));
					pw.println("STC_Server_disconnect");// ��ͻ��˷��ͷ������رյ��ź�
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
			// ɾ���߳�������Ԫ��
			threads.removeAllElements();
			// ɾ�������û���
			names.removeAllElements();

			jb1.setEnabled(true);
			jb2.setEnabled(false);
			jb3.setEnabled(false);
			jtf.setEnabled(false);
			jta.setEnabled(false);
		}
		if (e.getSource() == jb3) {
			// �����������Ͱ�ť������ʱ�������пͻ��˷�����Ϣ
			String string = jtf.getText();
			jta.append("Server say :" + string + "\n\r");
			try {
				// ��ÿ���ͻ��˶�������������Ϣ
				for (int i = 0; i < threads.size(); i++) {

					PrintWriter pw = new PrintWriter(
							((ReceiveClientMessageThread) (threads.get(i))).getSocket().getOutputStream(), true);
					pw.println("STC_Server_bulk");// ��ͻ��˷��ͷ�����Ⱥ���ź�
					pw.println(jtf.getText());// ��ͻ��˷��ͷ�����Ⱥ������
				}
			} catch (Exception e2) {
				// TODO: handle exception
				e2.printStackTrace();
			}
			jtf.setText("");
		}
	}

}
