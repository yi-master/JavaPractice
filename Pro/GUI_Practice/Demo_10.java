package GUI_Practice;

/*
 * QQ聊天
 */
import javax.swing.*;
import java.awt.*;

public class Demo_10 extends JFrame{

	JTextArea jta=null;
	JScrollPane jsp=null;
	JPanel jp1;
	JComboBox jcb=null;
	JTextField jtf=null;
	JButton jb=null;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_10 demo_10=new Demo_10();
	}
	
	public Demo_10(){
		//添加组件
		jta=new JTextArea();
		jsp=new JScrollPane(jta);//把多行文本框加入滚动条（当行数超过窗口时会显示滚动条）
		jp1=new JPanel();
		String []chatter={"奥巴马","特朗普"};
		jcb=new JComboBox(chatter);
		jtf =new JTextField(10);//定义文本框长度
		jb=new JButton("send");
		
		//设置布局
		
		//添加组件
		jp1.add(jcb);
		jp1.add(jtf);
		jp1.add(jb);
		
		//加入JFrame
		this.add(jsp);
		this.add(jp1,BorderLayout.SOUTH);
		
		//设置窗体属性
		this.setTitle("QQ");
		this.setIconImage((new ImageIcon("D:/eclipse workspace/JavaPractice/images/qq.jpg").getImage()));//设置QQ图标
		this.setVisible(true);
		this.setSize(300, 300);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
	}
	
}
