
import javax.swing.*;
import javax.swing.event.ListSelectionListener;
import javax.swing.event.ListSelectionEvent;
import javax.swing.tree.DefaultMutableTreeNode;
import javax.swing.tree.DefaultTreeModel;
import javax.swing.tree.TreeNode;
import javax.swing.tree.TreePath;

import java.awt.event.*;
import java.awt.*;
import java.util.Hashtable;
import java.util.Vector;
import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.io.RandomAccessFile;
import java.net.InetAddress;
import java.net.ServerSocket;
import java.net.UnknownHostException;
import java.util.Date;



/*ActionListener用于接收操作事件的侦听器接口。
 * 对处理操作事件感兴趣的类可以实现此接口，
 * 而使用该类创建的对象可使用组件的 addActionListener 方法向该组件注册。
 * 在发生操作事件时，调用该对象的 actionPerformed 方法。
 * 侦听器的作用，使应用程序主动获取消息，并运行相关方法 */

public class MyIM extends JFrame  implements ActionListener
{

    /*Vector 类可以实现可增长的对象数组。
     * 与数组一样，它包含可以使用整数索引进行访问的组件。
     * 但是，Vector 的大小可以根据需要增大或缩小，以适应创建 Vector 后进行添加或移除项的操作。
     */
    //Vector ipItem=new Vector(10,10);//ip对象数组
 
    //Vector messageItem=new Vector(10,10);//消息对象数组 
    
    Hashtable ipItem = new Hashtable(10,(float) 0.8);//存放以已连接用户ip,判断用户是否已在列表中
    
    JButton sendButton;//发送按钮
    
    JButton getIpButton;//获得IP按钮
    
    String fileName;
    //JButton clearButton;
    
    //JButton downloadButton,nodownloadButton;
    
    //打开文件
    JButton fileButton;
    JFileChooser fc; 
    int receivefileover = 0;
    
    //代开历史纪录
    JButton historyButton;
    
    ServerSocket serverSkt;
    
    
    JTextArea messageTextField;//消息输入区
    
    JTextField fileField;//文件输入区
    
    JScrollPane scrollMessage,scrollMessage2,scrollMsg,scrollMessagefrom;//消息滚动
    
    JTextField ipTextField;//IP显示区

    DefaultListModel modeIp=new DefaultListModel();//IP列表控制,Swing组件
    
    DefaultListModel modeMsg=new DefaultListModel();//消息列表操作
    DefaultListModel modeMsg2=new DefaultListModel();//发送消息 列表操作
    DefaultListModel modeMsg3=new DefaultListModel();//收到消息 列表操作
    
    JList  ipList;//ip列表对象 
    JList  messageList,tomessageList,frommessageList;//消息列表对象
    
    /*输入输出口提示*/
    JLabel messageLabel;
    JLabel ipLabel;
    JLabel inputLabel;
    JLabel inputMessageLabel;
    JLabel fileLabel;

    JScrollPane scrollIp;

    XmlFormat xmlFormat = new XmlFormat();//格式化纯文本数据，将字符串能符合XML使用
    XmlParse xmlParse = new XmlParse();
    Sender sender = new Sender(MyIM.this);//声明Sender对象,发送多播消息
    ReceiveMutiCast receiveMutiCast = new ReceiveMutiCast(MyIM.this);//接收多播消息
    ReceiveP2p receiveP2p = new ReceiveP2p(MyIM.this);//接收点到点消息
    InfoQueue  infoQueue = new InfoQueue();//消息队列
    InfoResponse infoResponse = new InfoResponse(MyIM.this);
    FileTransfer fileTransfer = new FileTransfer(MyIM.this);
    FILEHashtable filehashtable = new FILEHashtable();
    FileOperation fileoperation = new FileOperation();
    
    IconListItem item ;//头像
    
    SimpleMyIM simplep2psys;
    int SimpleP2pSysShow = 0;
    
    String selectip;
    String receivemsg = "";//收到消息
    
    long clickTime = 0;//双击
    
    String myface = "face/14-3.gif";
    String myName = getLocalHostname();
   
    
    ImageIcon selecticon;
    
    //好友上限
    int friendnumber = 20;
    String myfriend[] = new String[friendnumber];
    
    //myemail 唯一标实
    String myemail;
    
    //好友列表
    String myfriendemail[] = new String[friendnumber];
    
    //小组
    JTree tree;
    DefaultTreeModel treemodel;
    JScrollPane scrolltree;
    
    //右键菜单
    JPopupMenu popupMenu = new JPopupMenu();
    
    
    JMenu groupmenu = new JMenu("加入小组");
    
    //JMenuItem menuItem3 = new JMenuItem();
    int SelectedIndex = -1;
    
    //文件菜单
    public JMenu buildFileMenu() 
    { 
      
      //栏目
  	  JMenu fileMenu = new JMenu("文件");
  	  
  	  //子栏目
  	  JMenuItem exit=new JMenuItem("退出");  	   
  	  //theMenu.addSeparator();//分隔线
  	  
  	  //快捷键
  	  exit.setMnemonic('X');
  	  exit.setAccelerator( KeyStroke.getKeyStroke('X', java.awt.Event.CTRL_MASK, false) );
 	  
  	  //方法
  	  exit.addActionListener(new ActionListener() 
  	  {
  	     public void actionPerformed(ActionEvent e) 
  	     {
             System.exit(0);
	     }
  	   });

  	  fileMenu.add(exit);
  	  return fileMenu;
   	}//end of buildFileMenu()
    //风格菜单
    public JMenu buildstyleMenu() 
    { 
      
      //栏目
  	  JMenu styleMenu = new JMenu("风格");
  	  
  	  //子栏目
  	  JMenuItem stylesimplep2psys=new JMenuItem("简约风格");  	   
  	  //theMenu.addSeparator();//分隔线
  	  
  	  //快捷键
  	  stylesimplep2psys.setMnemonic('s');
  	  stylesimplep2psys.setAccelerator( KeyStroke.getKeyStroke('s', java.awt.Event.CTRL_MASK, false) );
 	  
  	  //方法
  	  stylesimplep2psys.addActionListener(new ActionListener() 
  	  {
  	     public void actionPerformed(ActionEvent e) 
  	     {
  	    	simplep2psys = new SimpleMyIM(MyIM.this);  
  	    	SimpleP2pSysShow = 1;
	     }
  	   });

  	  styleMenu.add(stylesimplep2psys);
  	  return styleMenu;
   	}//end of buildFileMenu()
    
    
    //创建选项卡
    JTabbedPane tabbedPane=new JTabbedPane();
    //选项卡项目
    JPanel messagepane=new JPanel();
    JPanel hmessagepane=new JPanel();

    //charactersetsys.java 是否为空
    boolean fileisnull;
    
    public MyIM()
    {
    	 Thread t=new Thread(new StartLogo());
    	    t.start();
    	    try 
    	    {
    	     t.join();
    	    }
    	    catch (InterruptedException ex) 
    	    {
    	    
    	    }
    	
    	
       JFrame.setDefaultLookAndFeelDecorated(true);
      
      
       
     //用户接口

        //主框架
        setTitle("MyIM");
        getContentPane().setLayout(null);
        setSize(500,620);
        setResizable(false);
        setLocation(200,100);  

        
        //显示历史
        frommessageList= new JList(modeMsg3);
        scrollMessagefrom = new JScrollPane(frommessageList);

        
        //----------其他按钮或文本框-----------
        getIpButton=new JButton("启动");
        getIpButton.setBounds(385 ,220 ,80,30);
        Icon getIpButtonicon=new ImageIcon(MyIM.class.getResource("SystemImage/getIpButtonicon.gif"));
        getIpButton.setIcon(getIpButtonicon);
        getContentPane().add(getIpButton);
        getIpButton.addActionListener(this);
        

        inputLabel=new JLabel("发送对象:");
        inputLabel.setBounds(10 ,270 ,170,30);
        getContentPane().add(inputLabel);

        //编辑消息 或 已发送消息历史
        inputMessageLabel=new JLabel("");
        inputMessageLabel.setBounds(10 ,330 ,100,30);
        getContentPane().add(inputMessageLabel);
        inputMessageLabel.setText("编辑消息");
        //inputMessageLabel.hide();

        ipTextField=new JTextField("");
        ipTextField.setBounds(80 ,270 ,275,30);
        ipTextField.setForeground(Color.red );
        getContentPane().add(ipTextField);
        ipTextField.setEnabled(false);

        //消息编辑区
        messageTextField=new JTextArea("");
        messageTextField.setForeground(Color.darkGray);
        messageTextField.setEditable(true);
        scrollMsg = new JScrollPane(messageTextField);
        scrollMsg.setBounds(10 ,360 ,470,140);
        getContentPane().add(scrollMsg) ;
        
        //发送按钮
        sendButton=new JButton("发送");
        sendButton.setBounds(310,510,80,30);
        getContentPane().add(sendButton);
        sendButton.addActionListener(this);
             
        
        //显示历史信息 to.....
        tomessageList = new JList(modeMsg2);
        scrollMessage2 = new JScrollPane(tomessageList);
        scrollMessage2.setBounds(10 ,360 ,470,140);
        getContentPane().add(scrollMessage2);
        scrollMessage2.hide();
      
        
        //显示历史
        historyButton=new JButton("显示记录");
        historyButton.setBounds(390,510,90,30);
        getContentPane().add(historyButton);
        historyButton.addActionListener(this);
        historyButton.setEnabled(true);
        
        //
        fileLabel=new JLabel("文件路径:");
        fileLabel.setBounds(10 ,300 ,80,30);
        getContentPane().add(fileLabel);
        
        //
        fileField=new JTextField("");
        fileField.setBounds(80 ,300 ,275,30);
        getContentPane().add(fileField);
        fileField.setEnabled(false);
        
        //
        fileButton=new JButton("打开");
        fileButton.setBounds(385 ,300 ,80,30);
        getContentPane().add(fileButton);
        fileButton.addActionListener(this);
             
        
        //Menu菜单
        JMenuBar MBar = new JMenuBar(); 
  	    JMenu filemenu = buildFileMenu();
  	    JMenu stylemenu = buildstyleMenu();
  	    MBar.add(filemenu);
  	    MBar.add(stylemenu);
  	  
  	    setJMenuBar(MBar);
  	    
  	  //显示msg的列表
        messageList= new JList(modeMsg);
        scrollMessage = new JScrollPane(messageList);
  	    
  	    //选项卡
  	    tabbedPane.setTabPlacement(JTabbedPane.TOP);
  	    tabbedPane.setBounds(10 ,10 ,350,240);
  	    //messagelist的选项卡
  	    messagepane.setLayout( new BorderLayout() );//布局
  	    messagepane.add(scrollMessage);
  	    tabbedPane.addTab("最新消息",messagepane);
  	    getContentPane().add(tabbedPane);    
  	    //frommessageList的选项卡
  	    hmessagepane.setLayout( new BorderLayout() );//布局
  	    hmessagepane.add(scrollMessagefrom);
	    tabbedPane.addTab("全部消息",hmessagepane);
	    getContentPane().add(tabbedPane);  
	    
	    //头像
	    try 
	    {
			fileisnull = fileIsNull("CharacterSet/CharacterSet.txt","CharacterSet.txt");
		}
	    catch (IOException e2) 
	    {
			// TODO Auto-generated catch block
			e2.printStackTrace();
		}
	    
	    if(fileisnull)
	    {
	    	String characterset = "<name>"+myName+"</name>"+"<face>"+myface+"</face>";
			fileoperation = new 	FileOperation();
			fileoperation.savePerformed("CharacterSet/CharacterSet.txt",characterset);
	    }
	    
	    
	    try 
	    {
			String line = fileoperation.openPerformed("CharacterSet/CharacterSet.txt");
			String nameline = xmlParse .parse(line,"name").trim() ;
			String faceline = xmlParse .parse(line,"face").trim() ;
			
			String emailpassline = fileoperation.openPerformed("EmailPass/EmailPass");
			String emailline = xmlParse .parse(emailpassline,"email").trim() ;
			
			if(!nameline.equals("")&&!faceline.equals(""))
			{
				myName = nameline;
				myface = faceline;
			}
			if(!emailline.equals(""))
			{
				myemail = emailline;
				System.out.println(myemail+"  myemail");
			}
			
			
		} 
	    catch (IOException e1) 
	    {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
	    
	    try
	      {
		      String myfriendline = "";
		      RandomAccessFile file = new RandomAccessFile("MyFriend/MyFriend","r");
		      long filepoint=0;
		      long length=file.length();
		      int i = 0;
		      while(filepoint<length)
		      {  
		    	  myfriendline = file.readLine(); 
		    	  String myfriendemailline = xmlParse .parse(myfriendline,"email").trim() ;
		    	  myfriendemail[i] = myfriendemailline;
		    	  System.out.println(myfriendemail[i] + "  myfriendemailnnn"+i);
		    	  i = i + 1;
		      }
		      file.close();
	      }
	      catch(Exception e)
	      {
	    	  System.out.println(e.getMessage());
	    	 
	      }
	      
	    //右键菜单  
	    initGroupmenu();
	    popupMenu.add(groupmenu);//在iplist处添加
	    
	    //getContentPane().add(popupMenu);
	     
	    //小组树
	    
        TreeNode rootnote = initGroup();
        treemodel = new DefaultTreeModel(rootnote);
        tree = new JTree(treemodel);
        tree.setEditable(true);
        
        scrolltree = new JScrollPane(tree);
        
	    
	    //IP Label
        ipLabel=new JLabel("IP 列表:");
        ipLabel.setBounds(370 ,10 ,80,20);
        getContentPane().add(ipLabel);      

        //一个IP列表
        ipList=new JList(modeIp); 
        scrollIp=new JScrollPane(ipList);
        scrollIp.setBounds(370 ,35 ,110,180);
        ipList.setCellRenderer(new IconListItemRenderer(MyIM.this)); 
        getContentPane().add(scrollIp);
        
        ipList.addMouseListener(new  MouseAdapter()  
        {  
        	public void mouseClicked(MouseEvent e) 
        	{  
        		        if(e.getClickCount() == 2)  
        		        //the  thing  you  want  to  do 
        		        {
        		        	if(SimpleP2pSysShow != 1)
        	            	 {   	 
        	            		 
        	            		 ipTextField.setText(ipList.getSelectedValue() .toString() );
        	            	 }
        	            	 else
        	            		 
        	            	 {   
        	            		 SimpleSendSys simplesendsys = new SimpleSendSys(MyIM.this);
        	                	 simplesendsys.frame.show();
        	                	 simplesendsys.frame.setTitle("发送给  "+selectip.trim());
        	                	 simplesendsys.frame.setIconImage(selecticon.getImage());
        	                	 simplesendsys.sAddress = myfriend[ipList.getSelectedIndex()];
        	                	 //selectip = selectip.substring(selectip.indexOf('|')+3 );
        	                	 System.out.print(" \n getSelectedIndex   "+ipList.getSelectedIndex()); 
        	            		 ipTextField.setText(selectip);       	                 
        	            		 System.out.print("\ndddddddd: "+myfriend[ipList.getSelectedIndex()]); 
        	            	 }
        		        }
        		       
        	    }
        	
        	public void mousePressed(MouseEvent e) 
        	{
        		int mods=e.getModifiers();
        		ipList.setComponentPopupMenu(popupMenu);
        		if((mods&InputEvent.BUTTON3_MASK)!=0)
        		{
        			
        			SelectedIndex = ipList.getSelectedIndex();
        		}
        	}
        	      
        	
         });
      
        
         //-------------启动三个线程--------------
         receiveMutiCast.start() ;
         receiveP2p.start() ;
         infoResponse.start() ;
        
         
        //
        addWindowListener(new 
        WindowAdapter()
        {
            public void windowClosing(WindowEvent e)
            {
            	
                String string=xmlFormat.xmlForm("exitSys","go away","",getLocalIp(),"",myName,"","") .trim() ;
                sender.sendMutiCast(string);
                receiveMutiCast.stopWork() ;
                receiveP2p.stopWork() ;
                infoResponse.stopWork() ;
                //fileTransfer.stop();
                System.exit(0);

            }
        }
        );
    }

    //发送消息
    public  void actionPerformed(ActionEvent e)
    {	   	
    	//fileTransfer.receivefile();
        Object object=e.getSource() ;
        int i = 0 ;
        int ipyes = 0 ;
        
        //单击发送按钮
        if(object.equals(sendButton) )
        {
          
        	for(i=0;i<modeIp .size();i++ )
            {
               if(ipTextField.getText().equals(modeIp.getElementAt(i)))
               {
            	   ipyes = 1;
            	   break;
               }
      
            }  
        	
        	if(ipTextField.getText().equals("")||ipyes != 1)
            {
            	JOptionPane.showMessageDialog(null,
                        "请正确选择接收者IP地址",
                        "发送失败",
                        JOptionPane.INFORMATION_MESSAGE);	
            } 	
		    else if(messageTextField.getText().equals("")&& fileField.getText().equals(""))
            {
            	JOptionPane.showMessageDialog(null,
                        "请输入要发送的消息或发送文件",
                        "发送失败",
                        JOptionPane.INFORMATION_MESSAGE);	
            }                
            else
            {   
            	//首先在消息区增加两条消息，           	
                modeMsg2.addElement("To :  "+ipTextField.getText()+"    ("+java.util.Calendar.getInstance().getTime()+")");                
                modeMsg2.addElement(messageTextField.getText());
                
                String sAddress=ipTextField.getText() .trim() ;
                String ip=getLocalIp() ;
                String str=messageTextField.getText() .toString().trim() ;
                String file=fileField.getText().toString().trim() ;
                
                
                String string=xmlFormat.xmlForm("p2p",str,file,ip,"8888",myName,myface,myemail)  ;

                if(!fileField.getText().equals(""))
                {              	              	             	   
            	   modeMsg2.addElement("file:  "+fileField.getText());
            	   //文件在准备好transferfile()之前不可继续发送消息
            	   filehashtable.addHashfileItem(sAddress,file);
            	   //fileItem.addElement(fileName);
            	   //fileButton.setEnabled(false);
            	   //sendButton.setEnabled(false);
                }
                            
               
                sender.sendP2p (string,sAddress);
                //fileTransfer.transferfile();
                messageTextField.setText("");               
                //fileButton.setText("清除");
                
           }
        	
        }
        
         if(object.equals(getIpButton))
         {
             modeIp.clear() ;
             ipTextField.setText("");
             String ip = getLocalIp() ;
			
			
			 
             
             String string = xmlFormat.xmlForm("getIp","","",ip,"","   "+myName,myface,myemail) .trim() ;
             sender.sendMutiCast(string) ;
        }
         
         //if(object.equals(clearButton))
         //{
        	// modeMsg.clear(); 
         //}
         
         if(object.equals(fileButton))
         {
        	 if(fileField.getText().equals(""))
        	 {
        			 
	        	 int result;
	        	 fc = new JFileChooser("C:\\temp\\"); 
	        	 result = fc.showOpenDialog(this); 
	        	 File file = fc.getSelectedFile(); 
	        	 if(file!=null && result==JFileChooser.APPROVE_OPTION) 
	        	 { 
		        	 fileName = file.getAbsolutePath(); 
		        	 fileField.setText(fileName);
		        	 //System.out.println("You chose to open this file: " +fileName); 
		        	 fileButton.setText("清除");
		        	 
	        	 }  
	        	
        	 }
        	 else
        	 {
        		
        		 fileField.setText("");	
        		 fileButton.setText("打开");
        	 }
        	 
         }
         //if(object.equals(downloadButton))
         //{
        	 //BufferedReader user; 
             //int port; 
              
             //user = new BufferedReader(new InputStreamReader(System.in)); 
             //port = Integer.parseInt("8888"); 
             //System.out.println("将接收文件于端口: " + port);
             //String ip = getLocalIp() ;
             //String sAddress;
             
             //sAddress = modeMsg.getElementAt(modeMsg.size()-3).toString().trim();
             //sAddress=sAddress.substring(sAddress.indexOf(':')+1 ).toString().trim();
             
             //System.out.println(newMsg);
             
             //try 
             //{
     			//serverSkt = new ServerSocket(port);
     		 //} 
             //catch (IOException e1) 
             //{	
             	//System.out.println(e1.toString());
     		 //} 
             
             //String string=xmlFormat.xmlForm("p2p","sure","",ip,"8888")  ;
             //sender.sendP2p (string,sAddress);
             //downloadButton.setEnabled(false);             
         //}
         if(object.equals(historyButton))
         {
        	 if(historyButton.getLabel()=="显示记录" )
        	 {
	        	 inputMessageLabel.setText("发送记录:");
	        	 scrollMsg.hide();
	        	 scrollMessage2.show();
	        	 historyButton.setText("隐藏记录");
        	 }
        	 else
        	 {
        		 inputMessageLabel.setText("编辑消息:");
        		 scrollMsg.show();
	        	 scrollMessage2.hide();
	        	 historyButton.setText("显示记录");
        	 }
         }
    }
    

    public String  getLocalIp()
    {
        String ip="";
        
        try
        {
            InetAddress inetAddress=InetAddress.getLocalHost() ;
            ip=inetAddress.toString() ;
            
            ip=ip.substring(ip.indexOf('/')+1 ) ;

        }
        catch(Exception e)
        {
            System.out.println("in getLocalIp() "+e);
        }
        return ip;

    }
    
    public String  getLocalHostname()
    {
      
        String hostname="";
        
        try 
        {
			hostname = InetAddress.getLocalHost().getHostName().toString();
		} 
        catch (UnknownHostException e1) 
        {
			// TODO 自动生成 catch 块
			e1.printStackTrace();
		}
            
        return hostname;

    }
    
//  具体实现按钮双击的功能的方法//已停用
    public boolean checkClickTime() 
    {
    	
	    long nowTime = (new Date()).getTime();
	    if ( (nowTime - clickTime) < 300) 
	    {
		    clickTime = nowTime;
		    return true;
	    }
	    clickTime = nowTime;
	    return false;

    }

    public TreeNode initGroup() 
    {
    	 final DefaultMutableTreeNode root = new DefaultMutableTreeNode(myName+" 的小组");
    	 String s;
         
         try
         {
   	      RandomAccessFile file = new RandomAccessFile("MyGroup/MyGroup.txt","r");
   	      long filepoint=0;
   	      long length=file.length();
   	      while(filepoint<length)
   	      {
   	    	  
   		        s=file.readLine(); 
   		    
   		        s=xmlParse.parse(s,"groupname").trim();
   		        
   		        root.add(new DefaultMutableTreeNode(s));
   		        
   		        filepoint=file.getFilePointer();
   	      
   	      }
   	      file.close();
         }
         catch(Exception e)
         {
           JOptionPane.showMessageDialog(null, e.getMessage());
         }
         
    	 
    	 return root;
    }
    
    public void initGroupmenu()
    {
    	DefaultMutableTreeNode root = new DefaultMutableTreeNode(myName+" 的小组");
   	    String s;
   	    
        try
        {
  	      RandomAccessFile file = new RandomAccessFile("MyGroup/MyGroup.txt","r");
  	      long filepoint=0;
  	      long length=file.length();
  	      while(filepoint<length)
  	      {
  	    	  
  		        s=file.readLine(); 
  		    
  		        s=xmlParse.parse(s,"groupname").trim();
  		            	   
  		        groupmenu.add(new JMenuItem(s));
  		       
  		         System.out.println("oooooo  " +s); 
  	              
  	      }
  	      file.close();
        }
        catch(Exception e)
        {
          //JOptionPane.showMessageDialog(null, e.getMessage());
        }
    }
    
    public boolean fileIsNull(String filePath,String fileName) throws IOException 
    { 
	    boolean result = false; 
	    FileReader fr = new FileReader(filePath+fileName); 
	    
	    if(fr.read() == -1) 
	    { 
		    result = true; 
		    System.out.println(fileName+" 文件中没有数据!"); 
	    } 
	    else 
	    { 
	    	System.out.println(fileName+" 文件中有数据!"); 
	    } 
	    fr.close(); 
	    
	    return result; 
    } 

    
    public static void main(String[] args)
    {       
    	//EmailPassSys emailpass = new EmailPassSys();  
    	MyIM myIM=new MyIM();
    	myIM.simplep2psys = new SimpleMyIM(myIM);  
    	myIM.SimpleP2pSysShow = 1;
    	//myIM.hide();
        //myIM.show();
        
    }
    
}

