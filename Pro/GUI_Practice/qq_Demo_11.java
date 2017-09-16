package GUI_Practice;

/*
 * QQ登录界面
 */
import javax.swing.*;
import java.awt.*;

public class qq_Demo_11 extends JFrame{

	private static final int Font = 0;

	//北部区域
	JLabel jl1;
	
	//南北区域
	JButton jb1,jb2,jb3;
	JPanel jp1;
	
	//中部区域
	JTabbedPane jtp;//选项卡窗口
	JPanel jp2,jp3,jp4;
	
	JLabel jl2,jl3,jl4,jl5;
	JTextField jtf;//号码文本框
	JPasswordField jpf;//密码框
	JButton jb4;//清空账号
	JCheckBox jcb1,jcb2;//隐身登录，记住密码
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		new qq_Demo_11();
	}
	public qq_Demo_11(){
		//创建组件
		jl2 =new JLabel("QQ号码",JLabel.CENTER);
		jl3 =new JLabel("QQ密码",JLabel.CENTER);
		jl4 =new JLabel("忘记密码",JLabel.CENTER);
		
		jl4.setFont(new Font("宋体", Font, 16));//设置字体样式
		jl4.setForeground(Color.blue);//蓝色
		jl5=new JLabel("QQ");
		jl5.setForeground(Color.BLUE);//粗体蓝色
		jl5.setCursor(Cursor.getPredefinedCursor(Cursor.HAND_CURSOR));//设置鼠标
		
		jtf=new JTextField();//输入号码框
		jpf=new JPasswordField();//输入密码框
		
		//图片按钮
		jb4=new JButton(new ImageIcon("D:/eclipse workspace/JavaPractice/images/denglu.jpg"));
		jb4.setToolTipText("登录");//当鼠标移动到这图片时会显示“登录”两个字
		
		//复选框
		jcb1=new JCheckBox("隐身登录");
		jcb2=new JCheckBox("记住密码");
		
		//北部区域
		jl1=new JLabel(new ImageIcon("D:/eclipse workspace/JavaPractice/images/qq_2011.jpg"));
		
		//南部区域
		jp1 =new JPanel();
		jb1 =new JButton("登录");
		jb2=new JButton("取消");
		jb3=new JButton("向导");
		
		//中部区域
		jtp=new JTabbedPane();
		jp2=new JPanel();
		jp3=new JPanel();
		jp3.setBackground(Color.RED);//给面板设置颜色
		jp4 =new JPanel();
		jp4.setBackground(new Color(0, 0, 255));
		
		//将面板添加到选项卡窗格上
		jtp.add("QQ号码", jp2);
		jtp.add("手机号码",jp3);
		jtp.add("电子邮箱",jp4);
		
		//设置布局
		jp2.setLayout(new GridLayout(3, 3));//把中部分为三行三列的表格
		
		//添加组件
		//南部
		jp1.add(jb1);
		jp1.add(jb2);
		jp1.add(jb3);
		
		//中部，选项卡窗口第一个面板（JPanel）
		//第一行
		jp2.add(jl2);
		jp2.add(jtf);
		jp2.add(jb4);
		//第二行
		jp2.add(jl3);
		jp2.add(jpf);
		jp2.add(jl4);
		//第三行
		jp2.add(jcb1,"Center");
		jp2.add(jcb2,"Center");
		jp2.add(jl5,"Center");
		
		//添加组件至JFrame
		this.add(jp1,BorderLayout.SOUTH);//添加按钮至南部
		this.add(jl1,BorderLayout.NORTH);//添加JLabel（图片）至北部
		this.add(jtp,BorderLayout.CENTER);//选项卡窗口添加至中部
		
		//设置窗口属性
		this.setVisible(true);
		this.setSize(500, 400);
		this.setLocationRelativeTo(null); //初始位置在屏幕正中间
		this.setTitle("QQ");
		this.setIconImage(new ImageIcon("D:/eclipse workspace/JavaPractice/images/qq.jpg").getImage());
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
}
