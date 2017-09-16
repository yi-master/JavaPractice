import java.awt.BorderLayout;
import java.awt.Component;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;

public class MyFriendSys implements ActionListener
{
	private JFrame frame;
	private Container contentPane;
	
	private JLabel addfriendemailLabel;
	private JLabel addfriendmarkLabel;
	private JTextField addfriendemailField;
	private JTextField addfriendmarkField;
	private JButton addfriendButton;
	
	private JLabel delfriendemailLabel;
	private JComboBox delfriendemailField;
	private JButton delfriendButton;
	private DefaultComboBoxModel comboModel;
	
	
	private FileOperation fileoperation;
	private MyIM p2pSys;
	
	JPanel addfriendpane=new JPanel();
	
	//创建小组
	JPanel addgrouppane=new JPanel();
	private JLabel addgroupLabel;
	private JTextField addgroupField;
	private JButton addgroupButton;
	
//	创建选项卡
    JTabbedPane tabbedPane=new JTabbedPane();
	
	public MyFriendSys(MyIM p2pSys)
	{
		this.p2pSys =p2pSys;
		JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame("好友与小组 管理");
        contentPane=frame.getContentPane();
        //frame.setLocation(600,30);
        frame.setSize(300,300);
        frame.setResizable(false);
        frame.setLayout(null);
        contentPane.setLayout(null);//自由调整布局
        //窗体图标
        //ImageIcon Icon = new ImageIcon("Duke16.gif");
        //frame.setIconImage(Icon.getImage());
        //frame.setLayout(new BorderLayout()); 
        
        Toolkit toolkit=frame.getToolkit();
        Image topicon=toolkit.getImage("SystemImage/page_white_cup.png");
        frame.setIconImage(topicon);
        
        //窗体放右上方
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//获取窗体宽度
        int h = frame.getSize().height;//获取窗体高度
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        frame.setLocation(x,y);//将窗体移到屏幕中间
        frame.setVisible(true);
        
        //添加好友
        addfriendemailLabel=new JLabel("好友Email");
        addfriendemailLabel.setBounds(10,20,60,30);
       
        
        addfriendemailField=new JTextField("");
        addfriendemailField.setBounds(80,20 ,200,30);
        addfriendemailField.setText("");
       
        
        addfriendmarkLabel=new JLabel("好友名称");
        addfriendmarkLabel.setBounds(10,60,60,30);
        
        addfriendmarkField=new JTextField("");
        addfriendmarkField.setBounds(80,60 ,200,30);
        addfriendmarkField.setText("");
       
        Icon addfriendemailicon=new ImageIcon("friend/friend_add.png");
        
        addfriendButton=new JButton("加为好友");
        addfriendButton.setBounds(30,180,90,30);
        addfriendButton.addActionListener(this);
        
        
        //删除好友
        delfriendemailLabel=new JLabel();
        Icon delfriendemailLabelicon=new ImageIcon("friend/friend_delete.png");
        delfriendemailLabel.setIcon(delfriendemailLabelicon);
        delfriendemailLabel.setBounds(10,60,80,30);
       
        
        delfriendemailField = new JComboBox();
        comboModel = new DefaultComboBoxModel();
        delfriendemailField.setModel(comboModel);
        delfriendemailField.setBounds(30,60,200,30);  
        readfriend();
        delfriendemailField.addItemListener
        (  //事件处理
        		
	         new ItemListener()
	         { 
		          public void itemStateChanged(ItemEvent event)
		          {    
			           if( event.getStateChange() == ItemEvent.SELECTED)
			           {		        	   		        	
			        	  
			           }
		          }
	         }
        );
           
        delfriendButton=new JButton("删除好友");
        delfriendButton.setBounds(310,60,90,30);
        delfriendButton.addActionListener(this);
        
        addfriendpane.setLayout(null);//布局
        addfriendpane.add(addfriendemailLabel);
        addfriendpane.add(addfriendemailField);
        addfriendpane.add(addfriendmarkLabel);
        addfriendpane.add(addfriendmarkField);
        addfriendpane.add(addfriendButton);
        
        //创建小组
        addgroupLabel=new JLabel("小组名");
        addgroupLabel.setBounds(10,20,60,30);
       
        addgroupField=new JTextField("");
        addgroupField.setBounds(70,20 ,200,30);
        addgroupField.setText("");
        
        addgroupButton=new JButton("创建小组");
        addgroupButton.setBounds(30,180,90,30);
        addgroupButton.addActionListener(this);
        
        addgrouppane.setLayout(null);//布局
        addgrouppane.add(addgroupLabel);
        addgrouppane.add(addgroupField);
        addgrouppane.add(addgroupButton);
        
        
        
        //选项卡
  	    tabbedPane.setTabPlacement(JTabbedPane.TOP);
  	    tabbedPane.setBounds(0 ,0 ,300,290);
  	    tabbedPane.setOpaque(true);
        
        
  	    //tabbedPane.addTab("添加好友",addfriendemailicon,addfriendpane);
  	    tabbedPane.addTab("创建小组",addfriendemailicon,addgrouppane);
  	    
  	    contentPane.add(tabbedPane);
  	    
        
	}
	
	public void actionPerformed(ActionEvent e) 
	{
		Object object=e.getSource() ; 
		
		 if(object.equals(addfriendButton))
        { 
			    String addfriendmail = addfriendemailField.getText();
				String addfriendmark = addfriendmarkField.getText();
			    boolean emailcheck = emailcheck(addfriendmail);
			    if(!emailcheck)
           	 	{
           		 JOptionPane.showMessageDialog(null,
   	                     "Email填写错误,请重新填写Email",
   	                     "Email填写错误",
   	                     JOptionPane.INFORMATION_MESSAGE);	
           	 	}
			    else
			    {
			    	String addfriendline = "<email>"+addfriendmail+"</email>"+"<name>"+addfriendmark+"</name>";
			    	fileoperation = new 	FileOperation();
					try 
					{
						fileoperation.emailfriendsavePerformed("MyFriend/MyFriend",addfriendline);
					} 
					catch (IOException e1) 
					{
						// TODO Auto-generated catch block
						e1.printStackTrace();
					}
			    }
			    
          }
		 
		 if(object.equals(addgroupButton))
		 {
			 String addgroupname = addgroupField.getText();
			 String addgroupline = "<groupname>"+addgroupname+"</groupname>";
			 fileoperation = new 	FileOperation();
		     
			 System.out.println("oooooo  " +addgroupline);
			
			 try 
			{
					fileoperation.emailfriendsavePerformed("MyGroup/MyGroup.txt",addgroupline);
			} 
			catch (IOException e1) 
			{
					// TODO Auto-generated catch block
					e1.printStackTrace();
			}
			 
		 }
		 
		
	}
	
	private boolean emailcheck(String email)
	{
		 String check = "^([a-z0-9A-Z]+[-\\.]?)+[a-z0-9A-Z]@([a-z0-9A-Z]+(-[a-z0-9A-Z]+)?\\.)+[a-zA-Z]{2,}$";
		 Pattern regex = Pattern.compile(check);
		 Matcher matcher = regex.matcher(email);
		 boolean isMatched = matcher.matches();
		 if(isMatched)
		 {
			 System.out.println("it's a email");
		 }
		 else
		 {
			 System.out.println("it's not a email");
		 } 

		
		return isMatched;
	}
	
	private void readfriend()
    {
      String s;
      
      try
      {
	      RandomAccessFile file = new RandomAccessFile("MyFriend/MyFriend","r");
	      long filepoint=0;
	      long length=file.length();
	      while(filepoint<length)
	      {
	       
		        s=file.readLine(); 
		        comboModel.addElement(s);    // 为List增加Item
		        
		        filepoint=file.getFilePointer();
	      }
	      file.close();
      }
      catch(Exception e)
      {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }


    }
}
