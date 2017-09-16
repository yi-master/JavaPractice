import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.GridLayout;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.Calendar;

import javax.swing.BorderFactory;
import javax.swing.DefaultListModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTabbedPane;
import javax.swing.JToolBar;
import javax.swing.border.Border;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;




public class SimpleMyIM implements ActionListener,Runnable
{
	private JFrame frame;
    private Container contentPane;
      
	//DefaultListModel modeIp=new DefaultListModel();//IP列表控制,Swing组件
	
    private JButton styleButton;
    private JButton historyButton;
    private JButton onlineButton;
    private JButton manageButton;
    private JButton friendButton;
    private JButton searchButton;
    
    //create tabbedpane
    private JTabbedPane tabbedPane=new JTabbedPane();
    private JPanel friendpane=new JPanel();
    private JPanel grouppane=new JPanel();
    
    private JToolBar toolBar = new JToolBar();//创建工具条组件
    
    
    private JToolBar toolBarBottom = new JToolBar();
    private JToolBar toolBarBottom2 = new JToolBar();
    
    JLabel faceLabel= new JLabel();
    private Border
    faceLabelBorder = BorderFactory.createLineBorder(Color.GRAY,1);
    
    private JLabel dreamcodeLabel= new JLabel(" Dreamcode Home");
    private JLabel timeLabel= new JLabel();
    
    int year,month,date,hour,minute,second,week; 
    private static final long serialVersionUID = 1L; 
    
    private MyIM p2pSys;
   
    private String greeting; 
    
    CharacterSetSys characterset;
    
    MyFriendSys myfriendsys;
    
    private JPanel systabpane=new JPanel();
    
    public SimpleMyIM(MyIM p2pSys) 
    {
    	this.p2pSys =p2pSys;
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame("Send");
        contentPane=frame.getContentPane();
        //frame.setLocation(600,30);
        frame.setSize(200,565);
        frame.setLayout( new BorderLayout());
        contentPane.setLayout(null);//自由调整布局
        //窗体图标
        //ImageIcon Icon = new ImageIcon("Duke16.gif");
        //frame.setIconImage(Icon.getImage());
        //frame.setLayout(new BorderLayout()); 
        
        Toolkit toolkit=frame.getToolkit();
        Image topicon=toolkit.getImage(SimpleMyIM.class.getResource("SystemImage/page_white_cup.png"));
        frame.setIconImage(topicon);
        
        //窗体放右上方
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//获取窗体宽度
        int h = frame.getSize().height;//获取窗体高度
        int x = dim.width-w;
        int y = (dim.height-h)/5;
        frame.setLocation(x,y);//将窗体移到屏幕中间
     
        
        //连接按钮
       
        p2pSys.getIpButton.setBounds(0,0,70,20);
 
        //样式按钮
        styleButton=new JButton();
        styleButton.setBounds(160,0,30,30);
        Icon styleButtonicon=new ImageIcon(SimpleMyIM.class.getResource("SystemImage/styleButtonicon.png"));
        styleButton.setIcon(styleButtonicon);
        styleButton.addActionListener(this);//必须设置监听
        
//      样式按钮
        searchButton=new JButton("查询");
        searchButton.setBounds(60,0,60,30);
        Icon searchButtonicon=new ImageIcon(SimpleMyIM.class.getResource("SystemImage/historyButtonicon.png"));
        searchButton.setIcon(searchButtonicon);
        searchButton.addActionListener(this);//必须设置监听
        
        //历史按钮
        historyButton=new JButton("消息");
        historyButton.setBounds(0,0,60,30);
        Icon historyButtonicon=new ImageIcon(SimpleMyIM.class.getResource("SystemImage/styleButtonicon.png"));
        historyButton.setIcon(historyButtonicon);
        historyButton.addActionListener(this);//必须设置监听
           
        //在线用户
        manageButton=new JButton("设置");
        manageButton.setBounds(0,0,60,30);
        Icon manageButtonicon=new ImageIcon(SimpleMyIM.class.getResource("SystemImage/manageButtonicon.gif"));
        manageButton.setIcon(manageButtonicon);
        manageButton.addActionListener(this);//必须设置监听
        manageButton.setToolTipText("个性化设置");
//      好友
        friendButton=new JButton("好友");
        friendButton.setBounds(120,0,70,30);
        Icon friendButtonicon=new ImageIcon(SimpleMyIM.class.getResource("SystemImage/friendButtonicon.png"));
        friendButton.setIcon(friendButtonicon);
        friendButton.addActionListener(this);//必须设置监听
        
       
        
        
        //选项卡
        tabbedPane.setTabPlacement(JTabbedPane.BOTTOM);
       // tabbedPane.setBounds(0,60,200,400);
        
        friendpane.setLayout( null );
        friendpane.setLayout( new BorderLayout() );//布局
        friendpane.add(p2pSys.scrollIp);
        Icon friendpaneicon=new ImageIcon(SimpleMyIM.class.getResource("SystemImage/friendpaneicon.png"));
        tabbedPane.addTab("",friendpaneicon,friendpane); 
        
        grouppane.setLayout( null );
        grouppane.setLayout( new BorderLayout() );//布局
        grouppane.add(p2pSys.scrolltree);
        Icon grouppaneicon=new ImageIcon(SimpleMyIM.class.getResource("SystemImage/grouppaneicon.png"));
        tabbedPane.addTab("",grouppaneicon,grouppane); 
        
       // contentPane.add(tabbedPane);
        
        
        systabpane.setLayout( null );
        systabpane.setLayout( new BorderLayout() );//布局
        systabpane.add(tabbedPane);
        systabpane.setBounds(0,60,193,406);
        contentPane.add(systabpane);
        
        //添加头像   
        //faceLabel.setBorder(faceLabelBorder);
        faceLabel.setIcon(new ImageIcon(p2pSys.myface));
        faceLabel.setBounds(5,5,190,30);      
        contentPane.add(faceLabel); 
        
        //dreamcode
        dreamcodeLabel.setBounds(80,0,120,30);
        dreamcodeLabel.setForeground(Color.gray);
        
        dreamcodeLabel.addMouseListener(new MouseListener()
        {
	//        鼠标放在dreamcode上,必须实现全部方法
	       	 public void mousePressed(MouseEvent e)
	         {
	            	
	         }
	       	 public void mouseReleased(MouseEvent e)
	       	 {
	       		 
	       	 }
	       	 public void mouseEntered(MouseEvent e)
	       	 {
	       		 dreamcodeLabel.setForeground(Color.red);
	       	 }
	       	 public void mouseExited(MouseEvent e)
	       	 {
	       		 dreamcodeLabel.setForeground(Color.gray);
	       	 }
	       	 public void mouseClicked(MouseEvent e)
	       	 {
	       	    	
	       	 }

        });
       
        timeLabel.setText(p2pSys.myName);
        timeLabel.setBounds(70,0,160,20);
        timeLabel.setForeground(Color.BLACK);
        timeLabel.setBackground(Color.gray);
        new Thread(this).start(); 
       
      

        
        //工具栏
        toolBar.add(p2pSys.getIpButton);//工具组件添加按钮
        
        toolBar.add(timeLabel);
        toolBar.setLayout(null); 
        //toolBar.setBackground(Color.blue); 
        toolBar.setBounds(0,40,200,20); 
        toolBar.putClientProperty("JToolBar.isRollover",Boolean.TRUE);
        contentPane.add(toolBar); 

        //工具组件添加按钮
        
        toolBarBottom.add(manageButton);
        toolBarBottom.add(dreamcodeLabel);
        
        
        toolBarBottom.setLayout(null); 
        toolBarBottom.setBounds(0,501,200,30); 
        toolBarBottom.putClientProperty("JToolBar.isRollover",Boolean.TRUE);
        contentPane.add(toolBarBottom); 
        
        toolBarBottom2.add(friendButton);
        toolBarBottom2.add(searchButton);
        toolBarBottom2.add(historyButton);
        toolBarBottom2.setLayout(null); 
        toolBarBottom2.setBounds(0,468,200,30); 
        toolBarBottom2.putClientProperty("JToolBar.isRollover",Boolean.TRUE);
        contentPane.add(toolBarBottom2); 
        
        //show the window
        //frame.pack();
        frame.setVisible(true);
        frame.setResizable(false);
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//全部退出
        p2pSys.hide();
    }
    
    
    private void returnComponent()
    {
        //放回p2pSys.getIpButton
    	p2pSys.getContentPane().add(p2pSys.getIpButton);
		p2pSys.getIpButton.setBounds(385 ,220 ,80,30);
		
		//放回scrollIp
		p2pSys.getContentPane().add(p2pSys.scrollIp);
		p2pSys.scrollIp.setBounds(370 ,35 ,110,180);
    }
    
    //时钟
    public void run() 
    { 
    while(true) 
    { 
    year = Calendar.getInstance().get(Calendar.YEAR); 
    month = Calendar.getInstance().get(Calendar.MONTH)+1; 
    date = Calendar.getInstance().get(Calendar.DATE); 
    week = Calendar.getInstance().get(Calendar.DAY_OF_WEEK)-1; 
    hour = Calendar.getInstance().get(Calendar.HOUR_OF_DAY); 
    minute = Calendar.getInstance().get(Calendar.MINUTE); 
    second = Calendar.getInstance().get(Calendar.SECOND); 
    
    timeLabel.setText("       "+"星期"+week+"  "+hour+":"+minute+":"+second);
    timeLabel.setForeground(Color.darkGray);
    
    if(hour <= 11)
    {
    	greeting = "早上好";
    }
    else if(hour > 11 && hour < 13)
    {
    	greeting = "中午好";
    }
    else if(hour >= 13 && hour < 18)
    {
    	greeting = "下午好";
    }
    else if(hour >= 18)
    {
    	greeting = "晚上好";
    }
    faceLabel.setText("  "+p2pSys.myName+"   "+greeting+"!");
   
    
    try 
    { 
    Thread.sleep(1000); 
    } 
    catch(Exception e) 
    { 
    e.printStackTrace(); 
    } 
    timeLabel.setText(""); 
    faceLabel.setText("");
   
    } 
    } 
    
	public void actionPerformed(ActionEvent e) 
	{	
		 Object object=e.getSource() ; 
		 if(object.equals(styleButton))
         { 
			 p2pSys.show();
			 
			 returnComponent();
			  
			 p2pSys.simplep2psys.frame.hide();			 
			 p2pSys.SimpleP2pSysShow = 0;
			
         }
		 //设置按钮
         if(object.equals(manageButton))
         { 
        	 characterset = new CharacterSetSys(this.p2pSys);
        	
         }
         
         if(object.equals(friendButton))
         { 
        	 myfriendsys = new MyFriendSys(this.p2pSys);
        	
         }
         
         
	}
	
	
	   

}
