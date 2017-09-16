


import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Font;
import java.awt.Image;
import java.awt.Rectangle;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.DefaultComboBoxModel;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JTextField;

public class CharacterSetSys implements ActionListener
{
	private JFrame frame;
	private Container contentPane;
	private MyIM p2pSys;
    //�û���
    private JLabel nameLabel;
    private JTextField nameField;
    
    private JLabel faceLabel;
    private JLabel nowfaceLabel;
    private JLabel nowfaceLabel2;
    
    private JButton saveButton;//
    private JComboBox facecheck;
    
    private DefaultComboBoxModel comboModel;
    
    private FileOperation fileoperation;
    
    String face = null;
    
    private FaceIconListItemRenderer faceiconlistitemrenderer = new FaceIconListItemRenderer(this.p2pSys);
    
    
    public CharacterSetSys(MyIM p2pSys) 
    {
    	this.p2pSys =p2pSys;
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame("����");
        contentPane=frame.getContentPane();
        //frame.setLocation(600,30);
        frame.setSize(310,310);
        frame.setResizable(false);
        frame.setLayout( new BorderLayout());
        contentPane.setLayout(null);//���ɵ�������
        //����ͼ��
        //ImageIcon Icon = new ImageIcon("Duke16.gif");
        //frame.setIconImage(Icon.getImage());
        //frame.setLayout(new BorderLayout()); 
        
        Toolkit toolkit=frame.getToolkit();
        Image topicon=toolkit.getImage(CharacterSetSys.class.getResource("SystemImage/page_white_cup.png"));
        frame.setIconImage(topicon);
        
        //��������Ϸ�
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//��ȡ������
        int h = frame.getSize().height;//��ȡ����߶�
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        frame.setLocation(x,y);//�������Ƶ���Ļ�м�
        frame.setVisible(true);
        
        face =  "face/1-1.gif";
        
        
        //�û���
        nameLabel=new JLabel("�û���");
        nameLabel.setBounds(10,20,60,30);
        contentPane.add(nameLabel);
        
        nameField=new JTextField("");
        nameField.setBounds(70,20 ,150,30);
        nameField.setText(p2pSys.myName);
        contentPane.add(nameField);
        
        
        //��ǰͷ��
        nowfaceLabel=new JLabel("��ǰͷ��");
        nowfaceLabel.setBounds(10,60,60,30);
        contentPane.add(nowfaceLabel);
        
        nowfaceLabel2=new JLabel();
        nowfaceLabel2.setBounds(70,60,150,30);
        nowfaceLabel2.setIcon(p2pSys.simplep2psys.faceLabel.getIcon());
        nowfaceLabel2.setText("   "+p2pSys.myface);
        contentPane.add(nowfaceLabel2);
        
        //ͷ��
        faceLabel=new JLabel("����ѡ��");
        faceLabel.setBounds(10,100,60,30);
        contentPane.add(faceLabel);
        
        
        facecheck = new JComboBox();
        facecheck.setRenderer(new FaceIconListItemRenderer(this.p2pSys));  //װ�����Զ���cellRenderer
        comboModel = new DefaultComboBoxModel();
        facecheck.setModel(comboModel);
        facecheck.setBounds(70,100,150,30);  
        readicon();
        facecheck.addItemListener
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
        contentPane.add(facecheck);
        
        saveButton=new JButton("����");
        saveButton.setBounds(210,235,80,30);
        contentPane.add(saveButton);
        saveButton.addActionListener(this);
        
        
       
    }

    private void readicon()
    {
      String s;
      
      try
      {
	      RandomAccessFile file = new RandomAccessFile("MyFace/face.ini","r");
	      long filepoint=0;
	      long length=file.length();
	      while(filepoint<length)
	      {
	       
	        s=file.readLine();
	        IconListItem item = new IconListItem(new ImageIcon("face/"+s),s);
	        comboModel.addElement(item);    // ΪList����Item
	        
	        filepoint=file.getFilePointer();
	      }
	      file.close();
      }
      catch(Exception e)
      {
        JOptionPane.showMessageDialog(null, e.getMessage());
      }


    }
    
	public void actionPerformed(ActionEvent e) 
	{
		// TODO Auto-generated method stub
		Object object=e.getSource() ; 
		 if(object.equals(saveButton))
        { 
			
			
			 String name = nameField.getText();
			 p2pSys.myName = name;
			 p2pSys.simplep2psys.faceLabel.setIcon(new ImageIcon(p2pSys.myface));
			 p2pSys.myface = face;
			 
			 String characterset = "<name>"+name+"</name>"+"<face>"+p2pSys.myface+"</face>";
			 fileoperation = new 	FileOperation();
			 fileoperation.savePerformed("CharacterSet/CharacterSet.txt",characterset);
			  
			 
			 p2pSys.simplep2psys.faceLabel.setIcon(new ImageIcon(p2pSys.myface));
			 
			 p2pSys.modeIp.clear() ;
			 p2pSys.ipTextField.setText("");
             String ip = p2pSys.getLocalIp() ;
             String string = p2pSys.xmlFormat.xmlForm("getIp","","",ip,"","   "+p2pSys.myName,p2pSys.myface,p2pSys.myemail) .trim() ;
             p2pSys.sender.sendMutiCast(string) ;
             
            
        }
			
	}
    
}
