import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.File;
import java.text.SimpleDateFormat;
import java.util.Date;

import javax.swing.DefaultListModel;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JEditorPane;
import javax.swing.JFileChooser;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.JTextPane;
import javax.swing.text.EditorKit;

public class SimpleReceiveSys implements ActionListener
{
	JFrame frame;
    private Container contentPane;
    private MyIM p2pSys;
    private JLabel inputLabel;
    JTextField ipTextField;//IP显示区
    
    private JScrollPane scrollMsg;
    private JButton sendButton;//发送按钮
    private JButton closeButton;
    JEditorPane receivemessage;
    //DefaultListModel modeMsg=new DefaultListModel();//消息列表操作
    SimpleSendSys simplesendsys;
    private ImageIcon Icon;
    
    public SimpleReceiveSys(MyIM p2pSys) 
    {
    	this.p2pSys=p2pSys;
    	JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame(p2pSys.infoResponse.hostname+"  对你说");
        contentPane=frame.getContentPane();
        frame.setLocation(400,30);
        frame.setSize(310,290);
        contentPane.setLayout(null);//自由调整布局
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//全部退出
        
//      窗体方中央
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//获取窗体宽度
        int h = frame.getSize().height;//获取窗体高度
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        frame.setLocation(x,y);//将窗体移到屏幕中间
        
//      窗体图标
        Icon = new ImageIcon(p2pSys.infoResponse.face);
        frame.setIconImage(Icon.getImage());
        
        //日期时间
        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm");     
        Date   currentTime_1   =   new   Date();     
        String   dateString   =   formatter.format(currentTime_1);     

        
        inputLabel = new JLabel("收到时间:  "+dateString);
        inputLabel.setBounds(10,10,300,30);  
        contentPane.add(inputLabel);      
           
        //显示消息
        receivemessage = new JEditorPane();
        final EditorKit kit = receivemessage.getEditorKitForContentType("text/html");
        receivemessage.setEditorKit(kit);
        receivemessage.setText(p2pSys.receivemsg );
        receivemessage.setEditable(false);
       
        scrollMsg = new JScrollPane(receivemessage); 
        scrollMsg.setBounds(10,40,280,160);
        contentPane.add(scrollMsg);
       
       
        
        //发送按钮
        sendButton=new JButton("回复");
        sendButton.setBounds(210,210,80,30);
        contentPane.add(sendButton);
        sendButton.addActionListener(this);
           
        //关闭按钮
        closeButton=new JButton("关闭");
        closeButton.setBounds(130,210,80,30);
        contentPane.add(closeButton);
        closeButton.addActionListener(this);
        
    }


	public void actionPerformed(ActionEvent e) 
	{
		Object object=e.getSource() ;
		if(object.equals(sendButton))
        {
			simplesendsys = new SimpleSendSys(this.p2pSys);
			//p2pSys.selectip = p2pSys.infoResponse.hostname+" || "+p2pSys.infoResponse.ip;
			simplesendsys.frame.setTitle("发送给  "+p2pSys.infoResponse.hostname);
			simplesendsys.sAddress = p2pSys.infoResponse.ip;
			simplesendsys.frame.setIconImage(Icon.getImage());
			simplesendsys.frame.show();
			this.frame.hide();
			
        }
		
		if(object.equals(closeButton) )
        {
			this.frame.hide();
        }
		
	}
    
}
