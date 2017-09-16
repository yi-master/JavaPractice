package GUI_Practice;
/*
 * 复选框(JCheckBox)和单选按钮(JRadioButton)
 */

import javax.swing.*;
import java.awt.*;

public class Demo_7 extends JFrame{
	
	//定义组件
	JPanel jp1,jp2,jp3;
	JLabel jl1,jl2;
	JButton jb1,jb2,jb3;
	JCheckBox jcb1,jcb2,jcb3;
	JRadioButton jrb1,jrb2;
	ButtonGroup bg;
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Demo_7 demo_7=new Demo_7();
	}

	//构造函数
	public Demo_7(){
		//创建组件
		jp1=new JPanel();
		jp2=new JPanel();
		jp3=new JPanel();
		
		jl1=new JLabel("你喜欢的运动");
		jl2=new JLabel("你的性别");
		
		jb1=new JButton("注册用户");
		jb2=new JButton("取消注册");
		
		jcb1=new JCheckBox("足球");
		jcb2=new JCheckBox("篮球");
		jcb3=new JCheckBox("网球");
		
		jrb1=new JRadioButton("男");
		jrb2=new JRadioButton("女");
		
		//把单选按钮(JRadioButton)jrb1，jrb2放入到一个ButtonGroup
		//ButtonGroup常与单选按钮(JRadioButton)组合使用
		//JPanel添加还是添加单选按钮(JRadioButton)对象jrb1，jrb2
		bg=new ButtonGroup();
		bg.add(jrb1);
		bg.add(jrb2);
		
		//设置布局管理器
		this.setLayout(new GridLayout(3, 1));//三行一列网格布局
		
		//添加组件
		//第一行
		jp1.add(jl1);
		jp1.add(jcb1);
		jp1.add(jcb2);
		jp1.add(jcb3);
		
		//第二行
		jp2.add(jl2);
		jp2.add(jrb1);//把单选按钮(JRadioButton)添加到JPanel
		jp2.add(jrb2);
		
		//第三行
		jp3.add(jb1);
		jp3.add(jb2);
		
		//添加组件到JFrame
		this.add(jp1);
		this.add(jp2);
		this.add(jp3);
		
		//设置窗体属性
		this.setSize(300,150);
		this.setLocation(150,200);
		this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		this.setVisible(true);
	}
}
