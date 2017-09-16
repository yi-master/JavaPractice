/*
 * 记事本
 * 保存功能未完成
 * 另存为功能不能换行的问题为解决
 */
package IOStream;

import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.awt.image.ImageObserver;
import java.awt.image.ImageProducer;
import java.io.*;

public class NotePad extends JFrame implements ActionListener {

	// 定义组件
	JTextArea jta = null;// 文本域
	JScrollPane jsp = null;// 滚动条
	JMenuBar jmb = null;// 菜单条
	JMenu jm[] = null;// 菜单
	JMenuItem jmi[] = null;// 菜单子项

	int menuSize = 5;// 菜单个数
	int menuItemSize = 3;// 菜单子项个数

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		NotePad np = new NotePad();
	}

	// 构造函数
	public NotePad() {

		// 创建组件
		jta = new JTextArea();
		jsp = new JScrollPane(jta);
		jmb = new JMenuBar();
		jm = new JMenu[menuSize];
		jmi = new JMenuItem[menuItemSize];

		jm[0] = new JMenu("文件");
		// 设置助记符(Alt+F,弹出下拉菜单)
		jm[0].setMnemonic('F');

		jmi[0] = new JMenuItem("Open", new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/IOStream/open.png"));
		jmi[1] = new JMenuItem("Save", new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/IOStream/save.png"));
		jmi[2] = new JMenuItem("Save As", new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/IOStream/save as.png"));
		// 注册监听
		for (int listener = 0; listener < jmi.length; listener++) {
			jmi[listener].addActionListener(this);
		}

		jmi[0].setActionCommand("open");
		jmi[1].setActionCommand("save");
		jmi[2].setActionCommand("save as");

		// 放入组件
		this.add(jsp);

		this.setJMenuBar(jmb);

		// 把JMenu对象放入jmb
		jmb.add(jm[0]);

		// 把JMenuItem对象放入jm[0]
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
		// 判断哪个菜单被选中(两种方法均可)
		// if(e.getSource()==jmi[0]){
		// System.out.println("open");
		// }
		//这种方式适用于控件和actionPerformed(ActionEvent e)函数不在同一个类中
		if (e.getActionCommand().equals("open")) {// 打开文件
			// System.out.println("open");

			// 文件选择组件
			JFileChooser jfc_o = new JFileChooser();
			// 设置标题
			jfc_o.setDialogTitle("Please select a file...");
			// 默认打开文件方式
			jfc_o.showOpenDialog(null);
			// 显示
			jfc_o.setVisible(true);

			// 得到用户选择的文件路径
			String filename = jfc_o.getSelectedFile().getAbsolutePath();
			// System.out.println(filename);

			FileReader fr = null;
			BufferedReader br = null;

			try {
				fr = new FileReader(filename);
				br = new BufferedReader(fr);

				// 从文件中读取信息并显示到文本域（jta）
				String s = "";
				String allContent = "";
				while ((s = br.readLine()) != null) {
					/*
					 * 循环读取过程 每次读取一行文本内容赋值给字符串s 再把字符串s的内容赋给字符串allContent
					 * 并加上\r\n使其换行
					 */
					allContent += s + "\r\n";
				}
				// 放置到jta
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
		} else if (e.getActionCommand().equals("save")) {// 保存文件

		} else if (e.getActionCommand().equals("save as")) {// 文件另存为
			// 显示保持对话框
			JFileChooser jfc_s = new JFileChooser();
			// 设置标题
			jfc_s.setDialogTitle("Save As");
			// 默认保存文件方式
			jfc_s.showSaveDialog(null);
			// 显示
			jfc_s.setVisible(true);

			// 得到用户希望保存文件的路径
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
//				// 文本域写入				
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
