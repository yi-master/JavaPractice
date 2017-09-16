package GUI_Practice;

/*
 * JLabel、JTextField、JPasswordField
 */
import javax.swing.*;
import java.awt.*;

public class Demo_6 extends JFrame{

	//定义组件
	JPanel jp1,jp2,jp3;
	JLabel jlb1,jlb2;
	JButton jb1,jb2;
	JTextField jtf1;
	JPasswordField jpf1;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_6 demo_6=new Demo_6();
	}

	//构造函数
	public Demo_6(){
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jlb1=new JLabel("用户名");
		jlb2=new JLabel("密    码");
		
		jb1=new JButton("登录");
		jb2=new JButton("取消");
		
		jtf1=new JTextField(10);
		jpf1=new JPasswordField(10);
		
		//设置JFrame布局
		this.setLayout(new GridLayout(3, 1));
		
		//加入各个组件
		//第一行
		jp1.add(jlb1);
		jp1.add(jtf1);
		
		//第二行
		jp2.add(jlb2);
		jp2.add(jpf1);
		
		//第三行
		jp3.add(jb1);
		jp3.add(jb2);
		
		//加入到JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		this.setTitle("practice");
		this.setVisible(true);
		this.setSize(300, 150);
		this.setLocation(200,100);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
