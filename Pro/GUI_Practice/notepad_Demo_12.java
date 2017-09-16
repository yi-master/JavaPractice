/*
 * 记事本界面
 */
package GUI_Practice;

import java.awt.*;
import javax.swing.*;

public class notepad_Demo_12 extends JFrame{

	//JMenu里面可以嵌套JMenu
	private JMenuBar jmb=null;//菜单条组件（用来存放每个菜单）
	private JMenu menu[]=null;//菜单组件（每个菜单组件都可以放添加菜单组件）
	private JMenuItem jmi[]=null;//菜单项组件(垂直菜单)
	private JMenu menu_2=null;//菜单组件（二级菜单）
	private JMenuItem file=null,project=null;//菜单项组件
	
	//文本域
	private JTextArea jta=null;
	private JScrollPane jsp=null;
	
	//工具条
	//private JToolBar jtb=null;
	//private JButton jb[]=null;
	
	public static void main(String[] args) {
		// TODO Auto-generated method stub
		notepad_Demo_12 notepad=new notepad_Demo_12();
	}

	//构造函数
	public notepad_Demo_12(){
		
		//创建组件
		//jtb=new JToolBar();
		
		jmb=new JMenuBar();
		
		menu_2=new JMenu("New");
		file =new JMenuItem("File");
		project= new JMenuItem("Project");
		
		jmi=new JMenuItem[5];
		jmi[0]=new JMenuItem("Open");
		jmi[1]=new JMenuItem("Save");
		jmi[1].setMnemonic('S');//设置助记符（Alt+s弹出下拉菜单，s大小写不计）
		jmi[2]=new JMenuItem("Save as");
		jmi[3]=new JMenuItem("Print");
		jmi[3].setMnemonic('P');//设置助记符（Alt+p弹出下拉菜单，p大小写不计）
		jmi[4]=new JMenuItem("Exit");
		
		menu=new JMenu[5];
		menu[0]=new JMenu("File(F)");
		menu[0].setMnemonic('F');//设置助记符（Alt+f弹出下拉菜单，f大小写不计）
		menu[1]=new JMenu("Edit(E)");
		menu[1].setMnemonic('E');//设置助记符（Alt+e弹出下拉菜单，e大小写不计）
		menu[2]=new JMenu("Layout(L)");
		menu[2].setMnemonic('L');//设置助记符（Alt+l弹出下拉菜单，l大小写不计）
		menu[3]=new JMenu("View(V)");
		menu[3].setMnemonic('V');//设置助记符（Alt+v弹出下拉菜单，v大小写不计）
		menu[4]=new JMenu("Help(H)");
		menu[4].setMnemonic('H');//设置助记符（Alt+h弹出下拉菜单，h大小写不计）
	
		jta=new JTextArea();
		//在滚动条控件上放入文本域
		jsp=new JScrollPane(jta);
		//设置滚动条总是垂直的
		//jsp.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_ALWAYS);
		//添加滚动条
		this.add(jsp);
		
		//设置布局
		
		//添加组件
		//将按键添加到工具条上
		//jtb.add();
		
		//将菜单项添加到菜单上
		menu_2.add(file);
		menu_2.add(project);
		
		menu[0].add(menu_2);
		
		for(int i=0;i<jmi.length;i++){
			menu[0].add(jmi[i]);
			if(i==2){
				menu[0].addSeparator();//添加分割线
			}
		}
		
		//添加每个菜单
		for(int j=0;j<menu.length;j++){
			jmb.add(menu[j]);
		}
		
		//将菜单条添加到窗体上
		this.setJMenuBar(jmb);
		
		//将工具条添加到窗体上
		//this.add(jtb);
		
		this.setTitle("Notepad");
		this.setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
		this.setVisible(true);
		this.setSize(600, 500);
		this.setLocationRelativeTo(null);//设置初始位置为屏幕正中心
	}
	
}
