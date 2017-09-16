import java.awt.Color;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.ServerSocket;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JProgressBar;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.SwingUtilities;


public class FileDownload extends JFrame implements ActionListener
{

	private JButton downloadButton,nodownloadButton;//��ť
    JLabel  fromLabel;
    private JLabel filelable,tfromLabel,tfilelable,tmsgLabel;
    private JTextArea dmessageField;//�ܾ�����������
    private JScrollPane scrolldMessage;
    private MyIM p2pSys;
    //������
    private final JProgressBar progressBar;
   
    
    
    public FileDownload(String from,String file,MyIM p2pSys)
    {
    	this.p2pSys=p2pSys; 
    	
    	//�����
        setTitle("��������");
        getContentPane().setLayout(null);
        setSize(250,270);
        setLocation(500,260);
        setResizable(false);
        setForeground(Color.red);
        
        tfromLabel=new JLabel("����  :");
        tfromLabel.setBounds(10 ,10 ,40,20);
        getContentPane().add(tfromLabel);
        
        tfilelable=new JLabel("�ļ�  :");
        tfilelable.setBounds(10 ,30 ,40,20);
        getContentPane().add(tfilelable);
        
        tmsgLabel=new JLabel("�ظ�  :");
        tmsgLabel.setBounds(10 ,50 ,90,20);
        getContentPane().add(tmsgLabel);
        
        fromLabel=new JLabel(from);
        fromLabel.setBounds(50 ,10 ,250,20);
        getContentPane().add(fromLabel);
        
        filelable=new JLabel(file);
        filelable.setBounds(50 ,30 ,250,20);
        getContentPane().add(filelable);
        
        dmessageField=new JTextArea("");
        dmessageField.setForeground(Color.darkGray);
        dmessageField.setEditable(true);
        scrolldMessage = new JScrollPane(dmessageField);
        scrolldMessage.setBounds(10 ,70 ,220,100);
        getContentPane().add(scrolldMessage) ;
        scrolldMessage.setVisible(false);
        
        downloadButton=new JButton("���ظ���");
        downloadButton.setBounds(20 ,180 ,90,30);
        //downloadButton.setForeground(Color.red);
        downloadButton.addActionListener(this);
        getContentPane().add(downloadButton);
        //downloadButton.setEnabled(false);
        
        nodownloadButton=new JButton("�ܾ�����");
        nodownloadButton.setBounds(130 ,180 ,90,30);
        //nodownloadButton.setForeground(Color.red);
        nodownloadButton.addActionListener(this);
        getContentPane().add(nodownloadButton);
        //downloadButton.setEnabled(false);
        
        //������
        
        progressBar = new JProgressBar();
        progressBar.setIndeterminate(true); 
        progressBar.setBounds(10 ,120 ,220,10);
        getContentPane().add(progressBar) ;
        progressBar.setVisible(true);
        
       
             
    }
      
    public void Listen()
    {
    	BufferedReader user; 
        int port; 
         
        user = new BufferedReader(new InputStreamReader(System.in)); 
        port = Integer.parseInt("8888"); 
        System.out.println("�������ļ��ڶ˿�: " + port);
        
        try 
        {
       	 p2pSys.serverSkt = new ServerSocket(port);
		 } 
        catch (IOException e1) 
        {	
        	System.out.println(e1.toString());
		 } 
        
    }
    
	public void actionPerformed(ActionEvent e) 
	{
		 Object object=e.getSource() ;
		 if(object.equals(downloadButton))
         {
			 Listen();
             String ip = p2pSys.getLocalIp() ;
             String sAddress;
             
             sAddress = fromLabel.getText().toString().trim();                       
             //System.out.println(newMsg);
             
             //scrolldMessage.setVisible(false);
            // progressBar.setVisible(true);
       
             
             String string=p2pSys.xmlFormat.xmlForm("p2p","sure","",ip,"8888","","","")  ;       
             p2pSys.sender.sendP2p (string,sAddress);
             
             String msgstring=p2pSys.xmlFormat.xmlForm("p2p",dmessageField.getText().toString().trim(),"",ip,"8888","","",""); 
             p2pSys.sender.sendP2p (msgstring,sAddress);
             
             //boolean indeterminate = progressBar.isIndeterminate(); 
             //progressBar.setIndeterminate(!indeterminate);
             System.out.println(p2pSys.receivefileover);
             if(p2pSys.receivefileover == 1)
             {    
            	 this.hide();
             }
            // downloadButton.setEnabled(false);             
         }
		
	}
}
	
	
	