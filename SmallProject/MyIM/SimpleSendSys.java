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
 
    JTextField fileField;//�ļ���ʾ��
    //���ļ�
    private JButton fileButton;
    private JFileChooser fc; 
    private JTextArea messageTextField;//��Ϣ������
    private JScrollPane scrollMsg;
    private JButton sendButton;//���Ͱ�ť
    private JButton closeButton;
    
    //�ı��༭
    private JComboBox fontBox,styleBox,sizeBox;
    //����
    private String fontNames[] = { "����", "�����п�", "����" };//��������
    //������
    private String styleNames[] = { "Bold", "Italic" };//����������
    private int style;//������
    //�ֺ�
    private String[] sizeString = new String[5];//�ֺ�����
    private int[] size = new int[5];//���ֺ������Ӧ���ֺ������������������ִ�С
    
    String sAddress;
    //html��ʽ��
    private HTMLFormat HTMLFormat = new HTMLFormat();
    
    public SimpleSendSys(MyIM p2pSys) 
    {
    	this.p2pSys=p2pSys;
    	JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame("������Ϣ");
        contentPane=frame.getContentPane();
        frame.setLocation(400,30);
        frame.setSize(310,310);
        contentPane.setLayout(null);//���ɵ�������
        frame.setResizable(false);
        //frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);//ȫ���˳� 
        
        //���巽����
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//��ȡ������
        int h = frame.getSize().height;//��ȡ����߶�
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        frame.setLocation(x,y);//�������Ƶ���Ļ�м�
        
//      ����ͼ��
        //ImageIcon Icon = new ImageIcon("Duke16.gif");
        //frame.setIconImage(Icon.getImage());
        
     
        
        //�ļ����
        fileLabel=new JLabel("���͸���:");
        fileLabel.setBounds(10,20,60,30);
        contentPane.add(fileLabel);
        
        fileField=new JTextField("");
        fileField.setBounds(80,20,130,30);
        contentPane.add(fileField);
        fileField.setEnabled(false);
        
        fileButton=new JButton("��");
        fileButton.setBounds(220,20,60,30);
        contentPane.add(fileButton);
        fileButton.addActionListener(this);
        
        //��Ϣ�༭��
        messageTextField=new JTextArea("");
        messageTextField.setForeground(Color.darkGray);
        messageTextField.setEditable(true);
        messageTextField.setLineWrap(true);
        scrollMsg = new JScrollPane(messageTextField);
        scrollMsg.setBounds(10 ,90,280,140);
        contentPane.add(scrollMsg);
        
        //���Ͱ�ť
        sendButton=new JButton("����");
        sendButton.setBounds(210,235,80,30);
        contentPane.add(sendButton);
        sendButton.addActionListener(this);
           
        //�رհ�ť
        closeButton=new JButton("�ر�");
        closeButton.setBounds(130,235,80,30);
        contentPane.add(closeButton);
        closeButton.addActionListener(this);
        
        //����ѡ��
        fontBox = new JComboBox(fontNames);
        fontBox.setBounds(10,70,70,20);
        fontBox.addItemListener
        (  //�¼�����
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
        
        //������  
        String style_name[] = {"����","��б","����","��б�Ӵ���"};//������
        styleBox = new JComboBox(style_name);
        styleBox.setBounds(80,70,70,20);
        styleBox.addItemListener
        (   //�¼�����
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
        
        //�ֺ�
//      ��ʼ�������С����
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
	        	 result = fc.showDialog(this.p2pSys,"�ļ�ѡ��"); 
	        	 File file = fc.getSelectedFile(); 
	        	 if(file!=null && result==JFileChooser.APPROVE_OPTION) 
	        	 { 
	        		 p2pSys.fileName = file.getAbsolutePath(); 
		        	 fileField.setText(p2pSys.fileName);
		        	 p2pSys.fileField.setText(p2pSys.fileName);
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
		
		if(object.equals(sendButton) )
        {
          
		    if(messageTextField.getText().equals("")&& fileField.getText().equals(""))
            {
            	JOptionPane.showMessageDialog(null,
                        "������Ҫ���͵���Ϣ�����ļ�",
                        "����ʧ��",
                        JOptionPane.INFORMATION_MESSAGE);	
            }                
            else
            {   
            	//��������Ϣ������������Ϣ��           	
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
            	   //�ļ���׼����transferfile()֮ǰ���ɼ���������Ϣ
            	   
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
                //fileButton.setText("���");
                this.frame.hide();
             
           }
        }
		if(object.equals(closeButton) )
        {
			this.frame.hide();
        }
	}
    
}
