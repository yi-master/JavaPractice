
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



/*ActionListener���ڽ��ղ����¼����������ӿڡ�
 * �Դ�������¼�����Ȥ�������ʵ�ִ˽ӿڣ�
 * ��ʹ�ø��ഴ���Ķ����ʹ������� addActionListener ����������ע�ᡣ
 * �ڷ��������¼�ʱ�����øö���� actionPerformed ������
 * �����������ã�ʹӦ�ó���������ȡ��Ϣ����������ط��� */

public class MyIM extends JFrame  implements ActionListener
{

    /*Vector �����ʵ�ֿ������Ķ������顣
     * ������һ��������������ʹ�������������з��ʵ������
     * ���ǣ�Vector �Ĵ�С���Ը�����Ҫ�������С������Ӧ���� Vector �������ӻ��Ƴ���Ĳ�����
     */
    //Vector ipItem=new Vector(10,10);//ip��������
 
    //Vector messageItem=new Vector(10,10);//��Ϣ�������� 
    
    Hashtable ipItem = new Hashtable(10,(float) 0.8);//������������û�ip,�ж��û��Ƿ������б���
    
    JButton sendButton;//���Ͱ�ť
    
    JButton getIpButton;//���IP��ť
    
    String fileName;
    //JButton clearButton;
    
    //JButton downloadButton,nodownloadButton;
    
    //���ļ�
    JButton fileButton;
    JFileChooser fc; 
    int receivefileover = 0;
    
    //������ʷ��¼
    JButton historyButton;
    
    ServerSocket serverSkt;
    
    
    JTextArea messageTextField;//��Ϣ������
    
    JTextField fileField;//�ļ�������
    
    JScrollPane scrollMessage,scrollMessage2,scrollMsg,scrollMessagefrom;//��Ϣ����
    
    JTextField ipTextField;//IP��ʾ��

    DefaultListModel modeIp=new DefaultListModel();//IP�б����,Swing���
    
    DefaultListModel modeMsg=new DefaultListModel();//��Ϣ�б����
    DefaultListModel modeMsg2=new DefaultListModel();//������Ϣ �б����
    DefaultListModel modeMsg3=new DefaultListModel();//�յ���Ϣ �б����
    
    JList  ipList;//ip�б���� 
    JList  messageList,tomessageList,frommessageList;//��Ϣ�б����
    
    /*�����������ʾ*/
    JLabel messageLabel;
    JLabel ipLabel;
    JLabel inputLabel;
    JLabel inputMessageLabel;
    JLabel fileLabel;

    JScrollPane scrollIp;

    XmlFormat xmlFormat = new XmlFormat();//��ʽ�����ı����ݣ����ַ����ܷ���XMLʹ��
    XmlParse xmlParse = new XmlParse();
    Sender sender = new Sender(MyIM.this);//����Sender����,���Ͷಥ��Ϣ
    ReceiveMutiCast receiveMutiCast = new ReceiveMutiCast(MyIM.this);//���նಥ��Ϣ
    ReceiveP2p receiveP2p = new ReceiveP2p(MyIM.this);//���յ㵽����Ϣ
    InfoQueue  infoQueue = new InfoQueue();//��Ϣ����
    InfoResponse infoResponse = new InfoResponse(MyIM.this);
    FileTransfer fileTransfer = new FileTransfer(MyIM.this);
    FILEHashtable filehashtable = new FILEHashtable();
    FileOperation fileoperation = new FileOperation();
    
    IconListItem item ;//ͷ��
    
    SimpleMyIM simplep2psys;
    int SimpleP2pSysShow = 0;
    
    String selectip;
    String receivemsg = "";//�յ���Ϣ
    
    long clickTime = 0;//˫��
    
    String myface = "face/14-3.gif";
    String myName = getLocalHostname();
   
    
    ImageIcon selecticon;
    
    //��������
    int friendnumber = 20;
    String myfriend[] = new String[friendnumber];
    
    //myemail Ψһ��ʵ
    String myemail;
    
    //�����б�
    String myfriendemail[] = new String[friendnumber];
    
    //С��
    JTree tree;
    DefaultTreeModel treemodel;
    JScrollPane scrolltree;
    
    //�Ҽ��˵�
    JPopupMenu popupMenu = new JPopupMenu();
    
    
    JMenu groupmenu = new JMenu("����С��");
    
    //JMenuItem menuItem3 = new JMenuItem();
    int SelectedIndex = -1;
    
    //�ļ��˵�
    public JMenu buildFileMenu() 
    { 
      
      //��Ŀ
  	  JMenu fileMenu = new JMenu("�ļ�");
  	  
  	  //����Ŀ
  	  JMenuItem exit=new JMenuItem("�˳�");  	   
  	  //theMenu.addSeparator();//�ָ���
  	  
  	  //��ݼ�
  	  exit.setMnemonic('X');
  	  exit.setAccelerator( KeyStroke.getKeyStroke('X', java.awt.Event.CTRL_MASK, false) );
 	  
  	  //����
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
    //���˵�
    public JMenu buildstyleMenu() 
    { 
      
      //��Ŀ
  	  JMenu styleMenu = new JMenu("���");
  	  
  	  //����Ŀ
  	  JMenuItem stylesimplep2psys=new JMenuItem("��Լ���");  	   
  	  //theMenu.addSeparator();//�ָ���
  	  
  	  //��ݼ�
  	  stylesimplep2psys.setMnemonic('s');
  	  stylesimplep2psys.setAccelerator( KeyStroke.getKeyStroke('s', java.awt.Event.CTRL_MASK, false) );
 	  
  	  //����
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
    
    
    //����ѡ�
    JTabbedPane tabbedPane=new JTabbedPane();
    //ѡ���Ŀ
    JPanel messagepane=new JPanel();
    JPanel hmessagepane=new JPanel();

    //charactersetsys.java �Ƿ�Ϊ��
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
      
      
       
     //�û��ӿ�

        //�����
        setTitle("MyIM");
        getContentPane().setLayout(null);
        setSize(500,620);
        setResizable(false);
        setLocation(200,100);  

        
        //��ʾ��ʷ
        frommessageList= new JList(modeMsg3);
        scrollMessagefrom = new JScrollPane(frommessageList);

        
        //----------������ť���ı���-----------
        getIpButton=new JButton("����");
        getIpButton.setBounds(385 ,220 ,80,30);
        Icon getIpButtonicon=new ImageIcon(MyIM.class.getResource("SystemImage/getIpButtonicon.gif"));
        getIpButton.setIcon(getIpButtonicon);
        getContentPane().add(getIpButton);
        getIpButton.addActionListener(this);
        

        inputLabel=new JLabel("���Ͷ���:");
        inputLabel.setBounds(10 ,270 ,170,30);
        getContentPane().add(inputLabel);

        //�༭��Ϣ �� �ѷ�����Ϣ��ʷ
        inputMessageLabel=new JLabel("");
        inputMessageLabel.setBounds(10 ,330 ,100,30);
        getContentPane().add(inputMessageLabel);
        inputMessageLabel.setText("�༭��Ϣ");
        //inputMessageLabel.hide();

        ipTextField=new JTextField("");
        ipTextField.setBounds(80 ,270 ,275,30);
        ipTextField.setForeground(Color.red );
        getContentPane().add(ipTextField);
        ipTextField.setEnabled(false);

        //��Ϣ�༭��
        messageTextField=new JTextArea("");
        messageTextField.setForeground(Color.darkGray);
        messageTextField.setEditable(true);
        scrollMsg = new JScrollPane(messageTextField);
        scrollMsg.setBounds(10 ,360 ,470,140);
        getContentPane().add(scrollMsg) ;
        
        //���Ͱ�ť
        sendButton=new JButton("����");
        sendButton.setBounds(310,510,80,30);
        getContentPane().add(sendButton);
        sendButton.addActionListener(this);
             
        
        //��ʾ��ʷ��Ϣ to.....
        tomessageList = new JList(modeMsg2);
        scrollMessage2 = new JScrollPane(tomessageList);
        scrollMessage2.setBounds(10 ,360 ,470,140);
        getContentPane().add(scrollMessage2);
        scrollMessage2.hide();
      
        
        //��ʾ��ʷ
        historyButton=new JButton("��ʾ��¼");
        historyButton.setBounds(390,510,90,30);
        getContentPane().add(historyButton);
        historyButton.addActionListener(this);
        historyButton.setEnabled(true);
        
        //
        fileLabel=new JLabel("�ļ�·��:");
        fileLabel.setBounds(10 ,300 ,80,30);
        getContentPane().add(fileLabel);
        
        //
        fileField=new JTextField("");
        fileField.setBounds(80 ,300 ,275,30);
        getContentPane().add(fileField);
        fileField.setEnabled(false);
        
        //
        fileButton=new JButton("��");
        fileButton.setBounds(385 ,300 ,80,30);
        getContentPane().add(fileButton);
        fileButton.addActionListener(this);
             
        
        //Menu�˵�
        JMenuBar MBar = new JMenuBar(); 
  	    JMenu filemenu = buildFileMenu();
  	    JMenu stylemenu = buildstyleMenu();
  	    MBar.add(filemenu);
  	    MBar.add(stylemenu);
  	  
  	    setJMenuBar(MBar);
  	    
  	  //��ʾmsg���б�
        messageList= new JList(modeMsg);
        scrollMessage = new JScrollPane(messageList);
  	    
  	    //ѡ�
  	    tabbedPane.setTabPlacement(JTabbedPane.TOP);
  	    tabbedPane.setBounds(10 ,10 ,350,240);
  	    //messagelist��ѡ�
  	    messagepane.setLayout( new BorderLayout() );//����
  	    messagepane.add(scrollMessage);
  	    tabbedPane.addTab("������Ϣ",messagepane);
  	    getContentPane().add(tabbedPane);    
  	    //frommessageList��ѡ�
  	    hmessagepane.setLayout( new BorderLayout() );//����
  	    hmessagepane.add(scrollMessagefrom);
	    tabbedPane.addTab("ȫ����Ϣ",hmessagepane);
	    getContentPane().add(tabbedPane);  
	    
	    //ͷ��
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
	      
	    //�Ҽ��˵�  
	    initGroupmenu();
	    popupMenu.add(groupmenu);//��iplist�����
	    
	    //getContentPane().add(popupMenu);
	     
	    //С����
	    
        TreeNode rootnote = initGroup();
        treemodel = new DefaultTreeModel(rootnote);
        tree = new JTree(treemodel);
        tree.setEditable(true);
        
        scrolltree = new JScrollPane(tree);
        
	    
	    //IP Label
        ipLabel=new JLabel("IP �б�:");
        ipLabel.setBounds(370 ,10 ,80,20);
        getContentPane().add(ipLabel);      

        //һ��IP�б�
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
        	                	 simplesendsys.frame.setTitle("���͸�  "+selectip.trim());
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
      
        
         //-------------���������߳�--------------
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

    //������Ϣ
    public  void actionPerformed(ActionEvent e)
    {	   	
    	//fileTransfer.receivefile();
        Object object=e.getSource() ;
        int i = 0 ;
        int ipyes = 0 ;
        
        //�������Ͱ�ť
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
                        "����ȷѡ�������IP��ַ",
                        "����ʧ��",
                        JOptionPane.INFORMATION_MESSAGE);	
            } 	
		    else if(messageTextField.getText().equals("")&& fileField.getText().equals(""))
            {
            	JOptionPane.showMessageDialog(null,
                        "������Ҫ���͵���Ϣ�����ļ�",
                        "����ʧ��",
                        JOptionPane.INFORMATION_MESSAGE);	
            }                
            else
            {   
            	//��������Ϣ������������Ϣ��           	
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
            	   //�ļ���׼����transferfile()֮ǰ���ɼ���������Ϣ
            	   filehashtable.addHashfileItem(sAddress,file);
            	   //fileItem.addElement(fileName);
            	   //fileButton.setEnabled(false);
            	   //sendButton.setEnabled(false);
                }
                            
               
                sender.sendP2p (string,sAddress);
                //fileTransfer.transferfile();
                messageTextField.setText("");               
                //fileButton.setText("���");
                
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
		        	 fileButton.setText("���");
		        	 
	        	 }  
	        	
        	 }
        	 else
        	 {
        		
        		 fileField.setText("");	
        		 fileButton.setText("��");
        	 }
        	 
         }
         //if(object.equals(downloadButton))
         //{
        	 //BufferedReader user; 
             //int port; 
              
             //user = new BufferedReader(new InputStreamReader(System.in)); 
             //port = Integer.parseInt("8888"); 
             //System.out.println("�������ļ��ڶ˿�: " + port);
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
        	 if(historyButton.getLabel()=="��ʾ��¼" )
        	 {
	        	 inputMessageLabel.setText("���ͼ�¼:");
	        	 scrollMsg.hide();
	        	 scrollMessage2.show();
	        	 historyButton.setText("���ؼ�¼");
        	 }
        	 else
        	 {
        		 inputMessageLabel.setText("�༭��Ϣ:");
        		 scrollMsg.show();
	        	 scrollMessage2.hide();
	        	 historyButton.setText("��ʾ��¼");
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
			// TODO �Զ����� catch ��
			e1.printStackTrace();
		}
            
        return hostname;

    }
    
//  ����ʵ�ְ�ť˫���Ĺ��ܵķ���//��ͣ��
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
    	 final DefaultMutableTreeNode root = new DefaultMutableTreeNode(myName+" ��С��");
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
    	DefaultMutableTreeNode root = new DefaultMutableTreeNode(myName+" ��С��");
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
		    System.out.println(fileName+" �ļ���û������!"); 
	    } 
	    else 
	    { 
	    	System.out.println(fileName+" �ļ���������!"); 
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

