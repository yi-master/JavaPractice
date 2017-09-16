import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.File;

import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;

public class SimpleSendSys implements ActionListener
{
	JFrame frame;
    private Container contentPane;
    private MyIM p2pSys;
    
    private JLabel fileLabel;
 
    JTextField fileField;//文件显示区
    //打开文件
    private JButton fileButton;
    private JFileChooser fc; 
    private JTextArea messageTextField;//消息输入区
    private JScrollPane scrollMsg;
    private JButton sendButton;//发送按钮
    private JButton closeButton;
    
    //文本编辑
    private JComboBox fontBox,styleBox,sizeBox;
    //字体
    private String fontNames[] = { "宋体", "华文行楷", "隶书" };//字体数组
    //字体风格
    private String styleNames[] = { "Bold", "Italic" };//定义风格数组
    private int style;//字体风格
    //字号
    private String[] sizeString = new String[5];//字号数组
    private int[] size = new int[5];//与字号数组对应的字号整数，用于设置文字大小
    
    String sAddress;
    //html格式化
    private HTMLFormat HTMLFormat = new HTMLFormat();
    
    public SimpleSendSys(MyIM p2pSys) 
    {
    	this.p2pSys=p2pSys;
    	JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame("发送消息");
        contentPane=frame.getContentPane();
        frame.setLocation(400,30);
        frame.setSize(310,310);
        contentPane.setLayout(null);//自由调整布局
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//全部退出 
        
        //窗体方中央
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//获取窗体宽度
        int h = frame.getSize().height;//获取窗体高度
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        frame.setLocation(x,y);//将窗体移到屏幕中间
        
//      窗体图标
        //ImageIcon Icon = new ImageIcon("Duke16.gif");
        //frame.setIconImage(Icon.getImage());
        
     
        
        //文件相关
        fileLabel=new JLabel("发送附件:");
        fileLabel.setBounds(10,20,60,30);
        contentPane.add(fileLabel);
        
        fileField=new JTextField("");
        fileField.setBounds(80,20,130,30);
        contentPane.add(fileField);
        fileField.setEnabled(false);
        
        fileButton=new JButton("打开");
        fileButton.setBounds(220,20,60,30);
        contentPane.add(fileButton);
        fileButton.addActionListener(this);
        
        //消息编辑区
        messageTextField=new JTextArea("");
        messageTextField.setForeground(Color.darkGray);
        messageTextField.setEditable(true);
        messageTextField.setLineWrap(true);
        scrollMsg = new JScrollPane(messageTextField);
        scrollMsg.setBounds(10 ,90,280,140);
        contentPane.add(scrollMsg);
        
        //发送按钮
        sendButton=new JButton("发送");
        sendButton.setBounds(210,235,80,30);
        contentPane.add(sendButton);
        sendButton.addActionListener(this);
           
        //关闭按钮
        closeButton=new JButton("关闭");
        closeButton.setBounds(130,235,80,30);
        contentPane.add(closeButton);
        closeButton.addActionListener(this);
        
        //字体选择
        fontBox = new JComboBox(fontNames);
        fontBox.setBounds(10,70,70,20);
        fontBox.addItemListener
        (  //事件处理
	         new ItemListener()
	         {
	
		          public void itemStateChanged(ItemEvent event)
		          {
			           if( event.getStateChange() == ItemEvent.SELECTED)
			           {
			        	   messageTextField.setFont( new Font( fontNames[fontBox.getSelectedIndex()],
			        	   messageTextField.getFont().getStyle(), messageTextField.getFont().getSize() ) );	         
			           }
		          }
	         }
        );
        contentPane.add(fontBox);
        
        //字体风格  
        String style_name[] = {"常规","倾斜","粗体","倾斜加粗体"};//字体风格
        styleBox = new JComboBox(style_name);
        styleBox.setBounds(80,70,70,20);
        styleBox.addItemListener
        (   //事件处理
	         new ItemListener()
	         {
	
		          public void itemStateChanged(ItemEvent event)
		          {
			           if( event.getStateChange() == ItemEvent.SELECTED)
			           {
				            if(styleBox.getSelectedIndex()==0) style = Font.PLAIN;
				            if(styleBox.getSelectedIndex()==1) style = Font.ITALIC;
				            if(styleBox.getSelectedIndex()==2) style = Font.BOLD;
				            if(styleBox.getSelectedIndex()==3) style = Font.ITALIC+Font.BOLD;
				            messageTextField.setFont( new Font( messageTextField.getFont().getName(), 
				            style, messageTextField.getFont().getSize() ) );
			           }
		          }
	         }
        );
        contentPane.add(styleBox);
        
        //字号
//      初始化字体大小数组
        for(int i = 0 ; i<size.length;i++){
         sizeString[i] = "" + (i+1) * 5;
         size[i] = (i+1)*5;
        }
        
        sizeBox = new JComboBox(sizeString);
        sizeBox.setBounds(150,70,70,20);
        sizeBox.addItemListener
        (
	         new ItemListener()
	         {
	
		          public void itemStateChanged(ItemEvent event)
		          {
			           if( event.getStateChange() == ItemEvent.SELECTED)
			           {
			        	    messageTextField.setFont( new Font( messageTextField.getFont().getName(),
			        	    messageTextField.getFont().getStyle(), size[sizeBox.getSelectedIndex()] ) );
				  
			           }
		          }
	         }
        );
        contentPane.add(sizeBox);
        
        
    }
    


	public void actionPerformed(ActionEvent e) 
	{
		Object object=e.getSource() ;
		if(object.equals(fileButton))
        {
       	 if(fileField.getText().equals(""))
       	 {
       			 
	        	 int result;
	        	 fc = new JFileChooser("C:\\temp\\"); 
	        	 result = fc.showDialog(this.p2pSys,"文件选择"); 
	        	 File file = fc.getSelectedFile(); 
	        	 if(file!=null && result==JFileChooser.APPROVE_OPTION) 
	        	 { 
	        		 p2pSys.fileName = file.getAbsolutePath(); 
		        	 fileField.setText(p2pSys.fileName);
		        	 p2pSys.fileField.setText(p2pSys.fileName);
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
		
		if(object.equals(sendButton) )
        {
          
		    if(messageTextField.getText().equals("")&& fileField.getText().equals(""))
            {
            	JOptionPane.showMessageDialog(null,
                        "请输入要发送的消息或发送文件",
                        "发送失败",
                        JOptionPane.INFORMATION_MESSAGE);	
            }                
            else
            {   
            	//首先在消息区增加两条消息，           	
            	p2pSys.modeMsg2.addElement("To :  "+p2pSys.ipList.getSelectedValue() .toString()+"    ("+java.util.Calendar.getInstance().getTime()+")");                
            	p2pSys.modeMsg2.addElement(messageTextField.getText()); 
            	
            	//sAddress=p2pSys.selectip.substring(p2pSys.selectip.indexOf('|')+3 );;
                String ip=p2pSys.getLocalIp() ;
                String hostname=p2pSys.myName ;
                String str=messageTextField.getText() .toString();
                String file=fileField.getText().toString().trim() ;
          
            	
                if(!fileField.getText().equals(""))
                {              	              	             	   
                	p2pSys.modeMsg2.addElement("file:  "+fileField.getText());
            	   //文件在准备好transferfile()之前不可继续发送消息
            	   
            	   //fileButton.setEnabled(false);
            	   //sendButton.setEnabled(false);
                	p2pSys.filehashtable.addHashfileItem(sAddress, file);
                	//p2pSys.fileItem.addElement(p2pSys.fileName);
                }
                
                String face = fontNames[fontBox.getSelectedIndex()];
                int hsize = size[sizeBox.getSelectedIndex()];
            
                str =HTMLFormat.HTMLForm(face,hsize,str);// "<font  face="+fontNames[fontBox.getSelectedIndex()]+"  size="+size[sizeBox.getSelectedIndex()]+">"+str+"</font>";
                String string=p2pSys.xmlFormat.xmlForm("p2p",str,file,ip,"8888",hostname,p2pSys.myface,p2pSys.myemail)  ;
                //System.out.println(string+sAddress); 
                p2pSys.sender.sendP2p (string,sAddress);
                
                System.out.println(sAddress); 
                
                //fileTransfer.transferfile();
                messageTextField.setText("");               
                //fileButton.setText("清除");
                this.frame.hide();
             
           }
        }
		if(object.equals(closeButton) )
        {
			this.frame.hide();
        }
	}
    
}
