package GUI_Practice;

/*
 * 最后的晚餐
 */
import javax.swing.*;
import java.awt.*;

public class Demo_9 extends JFrame{

	//定义组件
	JSplitPane jsp;//拆分窗格
	JTextArea jta;
	JLabel jl1;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_9 demo_9=new Demo_9();
	}

	
	public Demo_9(){
		//创建组件
		
		jta=new JTextArea(" 最后的晚餐 （达·芬奇画作） \n"
				+"《最后的晚餐(The Last Supper)》\n"
				+ "是意大利艺术家列奥纳多·达·芬奇所创作，\n"
				+ "是所有以这个题材创作的作品中最著名的一幅。\n"
				+ "画面中的人物，\n"
				+ "以惊恐、愤怒、怀疑、剖白等神态，\n"
				+ "以及手势、眼神和行为，\n"
				+ "都刻画得精细入微，\n"
				+ "惟妙惟肖。\n"
				+ "收藏于意大利米兰圣玛利亚德尔格契修道院。\n");//JTextArea多行文本框组件
		
		//添加图片，路径用/或\\
		jl1=new JLabel(new ImageIcon("D:/eclipse workspace/JavaPractice/Pro/GUI_Practice/finally_dinner.jpg"));
		
		//拆分窗格
		jsp=new JSplitPane(JSplitPane.HORIZONTAL_SPLIT,jta,jl1);//水平拆分（竖着拆分）
		
		//可以变化
		jsp.setOneTouchExpandable(true);
		
		//设置布局管理器(因为已经拆分了窗格可以不用布局)
		
		//添加组件
		this.add(jsp);
		
		//设置窗口属性
		this.setTitle("The Last Supper");
		this.setSize(900,500);
		this.setLocation(200, 200);
		this.setVisible(true);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
