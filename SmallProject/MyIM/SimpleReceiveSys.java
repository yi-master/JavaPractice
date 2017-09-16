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
    JTextField ipTextField;//IP��ʾ��
    
    private JScrollPane scrollMsg;
    private JButton sendButton;//���Ͱ�ť
    private JButton closeButton;
    JEditorPane receivemessage;
    //DefaultListModel modeMsg=new DefaultListModel();//��Ϣ�б����
    SimpleSendSys simplesendsys;
    private ImageIcon Icon;
    
    public SimpleReceiveSys(MyIM p2pSys) 
    {
    	this.p2pSys=p2pSys;
    	JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame(p2pSys.infoResponse.hostname+"  ����˵");
        contentPane=frame.getContentPane();
        frame.setLocation(400,30);
        frame.setSize(310,290);
        contentPane.setLayout(null);//���ɵ�������
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ȫ���˳�
        
//      ���巽����
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//��ȡ������
        int h = frame.getSize().height;//��ȡ����߶�
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        frame.setLocation(x,y);//�������Ƶ���Ļ�м�
        
//      ����ͼ��
        Icon = new ImageIcon(p2pSys.infoResponse.face);
        frame.setIconImage(Icon.getImage());
        
        //����ʱ��
        SimpleDateFormat   formatter   =   new   SimpleDateFormat   ("yyyy-MM-dd HH:mm");     
        Date   currentTime_1   =   new   Date();     
        String   dateString   =   formatter.format(currentTime_1);     

        
        inputLabel = new JLabel("�յ�ʱ��:  "+dateString);
        inputLabel.setBounds(10,10,300,30);  
        contentPane.add(inputLabel);      
           
        //��ʾ��Ϣ
        receivemessage = new JEditorPane();
        final EditorKit kit = receivemessage.getEditorKitForContentType("text/html");
        receivemessage.setEditorKit(kit);
        receivemessage.setText(p2pSys.receivemsg );
        receivemessage.setEditable(false);
       
        scrollMsg = new JScrollPane(receivemessage); 
        scrollMsg.setBounds(10,40,280,160);
        contentPane.add(scrollMsg);
       
       
        
        //���Ͱ�ť
        sendButton=new JButton("�ظ�");
        sendButton.setBounds(210,210,80,30);
        contentPane.add(sendButton);
        sendButton.addActionListener(this);
           
        //�رհ�ť
        closeButton=new JButton("�ر�");
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
			simplesendsys.frame.setTitle("���͸�  "+p2pSys.infoResponse.hostname);
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
