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
	
	//����С��
	JPanel addgrouppane=new JPanel();
	private JLabel addgroupLabel;
	private JTextField addgroupField;
	private JButton addgroupButton;
	
//	����ѡ�
    JTabbedPane tabbedPane=new JTabbedPane();
	
	public MyFriendSys(MyIM p2pSys)
	{
		this.p2pSys =p2pSys;
		JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame("������С�� ����");
        contentPane=frame.getContentPane();
        //frame.setLocation(600,30);
        frame.setSize(300,300);
        frame.setResizable(false);
        frame.setLayout(null);
        contentPane.setLayout(null);//���ɵ�������
        //����ͼ��
        //ImageIcon Icon = new ImageIcon("Duke16.gif");
        //frame.setIconImage(Icon.getImage());
        //frame.setLayout(new BorderLayout()); 
        
        Toolkit toolkit=frame.getToolkit();
        Image topicon=toolkit.getImage("SystemImage/page_white_cup.png");
        frame.setIconImage(topicon);
        
        //��������Ϸ�
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//��ȡ������
        int h = frame.getSize().height;//��ȡ����߶�
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        frame.setLocation(x,y);//�������Ƶ���Ļ�м�
        frame.setVisible(true);
        
        //��Ӻ���
        addfriendemailLabel=new JLabel("����Email");
        addfriendemailLabel.setBounds(10,20,60,30);
       
        
        addfriendemailField=new JTextField("");
        addfriendemailField.setBounds(80,20 ,200,30);
        addfriendemailField.setText("");
       
        
        addfriendmarkLabel=new JLabel("��������");
        addfriendmarkLabel.setBounds(10,60,60,30);
        
        addfriendmarkField=new JTextField("");
        addfriendmarkField.setBounds(80,60 ,200,30);
        addfriendmarkField.setText("");
       
        Icon addfriendemailicon=new ImageIcon("friend/friend_add.png");
        
        addfriendButton=new JButton("��Ϊ����");
        addfriendButton.setBounds(30,180,90,30);
        addfriendButton.addActionListener(this);
        
        
        //ɾ������
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
        (  //�¼�����
        		
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
           
        delfriendButton=new JButton("ɾ������");
        delfriendButton.setBounds(310,60,90,30);
        delfriendButton.addActionListener(this);
        
        addfriendpane.setLayout(null);//����
        addfriendpane.add(addfriendemailLabel);
        addfriendpane.add(addfriendemailField);
        addfriendpane.add(addfriendmarkLabel);
        addfriendpane.add(addfriendmarkField);
        addfriendpane.add(addfriendButton);
        
        //����С��
        addgroupLabel=new JLabel("С����");
        addgroupLabel.setBounds(10,20,60,30);
       
        addgroupField=new JTextField("");
        addgroupField.setBounds(70,20 ,200,30);
        addgroupField.setText("");
        
        addgroupButton=new JButton("����С��");
        addgroupButton.setBounds(30,180,90,30);
        addgroupButton.addActionListener(this);
        
        addgrouppane.setLayout(null);//����
        addgrouppane.add(addgroupLabel);
        addgrouppane.add(addgroupField);
        addgrouppane.add(addgroupButton);
        
        
        
        //ѡ�
  	    tabbedPane.setTabPlacement(JTabbedPane.TOP);
  	    tabbedPane.setBounds(0 ,0 ,300,290);
  	    tabbedPane.setOpaque(true);
        
        
  	    //tabbedPane.addTab("��Ӻ���",addfriendemailicon,addfriendpane);
  	    tabbedPane.addTab("����С��",addfriendemailicon,addgrouppane);
  	    
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
   	                     "Email��д����,��������дEmail",
   	                     "Email��д����",
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
		        comboModel.addElement(s);    // ΪList����Item
		        
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
