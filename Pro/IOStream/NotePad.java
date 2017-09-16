/*
 * ���±�
 * ���湦��δ���
 * ���Ϊ���ܲ��ܻ��е�����Ϊ���
 */
package IOStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;

public class NotePad extends JFrame implements ActionListener {

	// �������
	JTextArea jta = null;// �ı���
	JScrollPane jsp = null;// ������
	JMenuBar jmb = null;// �˵���
	JMenu jm[] = null;// �˵�
	JMenuItem jmi[] = null;// �˵�����

	int menuSize = 5;// �˵�����
	int menuItemSize = 3;// �˵��������

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NotePad np = new NotePad();
	}

	// ���캯��
	public NotePad() {

		// �������
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jmb = new JMenuBar();
		jm = new JMenu[menuSize];
		jmi = new JMenuItem[menuItemSize];

		jm[0] = new JMenu("�ļ�");
		// �������Ƿ�(Alt+F,���������˵�)
		jm[0].setMnemonic('F');

		jmi[0] = new JMenuItem("Open", new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/IOStream/open.png"));
		jmi[1] = new JMenuItem("Save", new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/IOStream/save.png"));
		jmi[2] = new JMenuItem("Save As", new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/IOStream/save as.png"));
		// ע�����
		for (int listener = 0; listener < jmi.length; listener++) {
			jmi[listener].addActionListener(this);
		}

		jmi[0].setActionCommand("open");
		jmi[1].setActionCommand("save");
		jmi[2].setActionCommand("save as");

		// �������
		this.add(jsp);

		this.setJMenuBar(jmb);

		// ��JMenu�������jmb
		jmb.add(jm[0]);

		// ��JMenuItem�������jm[0]
		for (int j = 0; j < jmi.length; j++) {
			jm[0].add(jmi[j]);
		}

		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setIconImage(new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/IOStream/page_white_cup.png").getImage());
		this.setTitle("NotePad");
		this.setSize(400, 300);
		this.setVisible(true);
		this.setLocationRelativeTo(null);
	}

	@Override
	//
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
		// �ж��ĸ��˵���ѡ��(���ַ�������)
		// if(e.getSource()==jmi[0]){
		// System.out.println("open");
		// }
		//���ַ�ʽ�����ڿؼ���actionPerformed(ActionEvent e)��������ͬһ������
		if (e.getActionCommand().equals("open")) {// ���ļ�
			// System.out.println("open");

			// �ļ�ѡ�����
			JFileChooser jfc_o = new JFileChooser();
			// ���ñ���
			jfc_o.setDialogTitle("Please select a file...");
			// Ĭ�ϴ��ļ���ʽ
			jfc_o.showOpenDialog(null);
			// ��ʾ
			jfc_o.setVisible(true);

			// �õ��û�ѡ����ļ�·��
			String filename = jfc_o.getSelectedFile().getAbsolutePath();
			// System.out.println(filename);

			FileReader fr = null;
			BufferedReader br = null;

			try {
				fr = new FileReader(filename);
				br = new BufferedReader(fr);

				// ���ļ��ж�ȡ��Ϣ����ʾ���ı���jta��
				String s = "";
				String allContent = "";
				while ((s = br.readLine()) != null) {
					/*
					 * ѭ����ȡ���� ÿ�ζ�ȡһ���ı����ݸ�ֵ���ַ���s �ٰ��ַ���s�����ݸ����ַ���allContent
					 * ������\r\nʹ�任��
					 */
					allContent += s + "\r\n";
				}
				// ���õ�jta
				jta.setText(allContent);

			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			} finally {

				try {
					br.close();
					fr.close();
				} catch (Exception e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		} else if (e.getActionCommand().equals("save")) {// �����ļ�

		} else if (e.getActionCommand().equals("save as")) {// �ļ����Ϊ
			// ��ʾ���ֶԻ���
			JFileChooser jfc_s = new JFileChooser();
			// ���ñ���
			jfc_s.setDialogTitle("Save As");
			// Ĭ�ϱ����ļ���ʽ
			jfc_s.showSaveDialog(null);
			// ��ʾ
			jfc_s.setVisible(true);

			// �õ��û�ϣ�������ļ���·��
			String filename = jfc_s.getSelectedFile().getAbsolutePath();
			// System.out.println(filename);

			FileWriter fw = null;
			BufferedWriter bw = null;
			// BufferedReader br = null;

			try {
				fw = new FileWriter(filename);
				bw = new BufferedWriter(fw);
				// br = new BufferedReader(new FileReader(jta.getText()));
				String x=jta.getText();

				bw.write(x);
//				String n[]=x.split("\n");
//				System.out.println(n);
//				for(int i=0;i<n.length;i++){
//				System.out.println(n);
//				// �ı���д��				
//				bw.write(n+"\r\n");
//				}
			} catch (Exception e1) {
				// TODO: handle exception
				e1.printStackTrace();
			} finally {
				try {
					bw.close();
					fw.close();
				} catch (IOException e2) {
					// TODO Auto-generated catch block
					e2.printStackTrace();
				}
			}
		}

	}

}
