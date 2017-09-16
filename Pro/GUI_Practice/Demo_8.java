package GUI_Practice;
/*
 * 下拉菜单（JComboBox）/列表框（JList）/滚动条（JScrollPane）
 */

import javax.swing.*;
import java.awt.*;

public class Demo_8 extends JFrame {

	JPanel jp1, jp2;
	JLabel jl1, jl2;
	JComboBox jcb1;
	JList jlist;
	JScrollPane jsp;

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_8 demo_8 = new Demo_8();
	}

	// 构造函数
	public Demo_8() {
		jp1 = new JPanel();
		jp2 = new JPanel();

		jl1 = new JLabel("你的籍贯");
		jl2 = new JLabel("旅游地点");

		String[] jg = { "北京", "上海", "天津", "广州" };
		jcb1 = new JComboBox(jg);

		String[] place = { "长城", "故宫", "天安门", "王府井" };
		jlist = new JList(place);
		jlist.setVisibleRowCount(3);// 设置你希望显示多少个选项
		jsp = new JScrollPane(jlist);
		

		// 设置布局
		this.setLayout(new GridLayout(3, 1));// 三行一列网格布局

		// 添加组件
		// 第一行
		jp1.add(jl1);
		jp1.add(jcb1);

		// 第二行
		jp2.add(jl2);
		jp2.add(jsp);

		// 将面板添加至窗口(JFrame)
		this.add(jp1);
		this.add(jp2);

		// 设置窗口属性
		this.setSize(300, 300);
		this.setLocation(200, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
