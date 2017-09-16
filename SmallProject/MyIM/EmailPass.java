import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Container;
import java.awt.Dimension;
import java.awt.Image;
import java.awt.Toolkit;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import javax.swing.BorderFactory;
import javax.swing.JButton;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPasswordField;
import javax.swing.JScrollPane;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import javax.swing.border.Border;

public class EmailPass 
{
	private JFrame frame;
	private Container contentPane;
	
	private JLabel emailLabel;
	private JTextField emailField;
	
	private JLabel passwordLabel,dpasswordLabel;
	private JPasswordField passwordField,dpasswordField;
	
    private JButton sureButton,cancelButton;//
    private FileOperation fileoperation;
    
    
    private JTextArea messageTextField;//消息输入区
    
    private Border selectedBorder = BorderFactory.createLineBorder(Color.DARK_GRAY,1),
    emptyBorder = BorderFactory.createEmptyBorder(1,1,1,1);
	
	public EmailPass()
	{
        JFrame.setDefaultLookAndFeelDecorated(true);
        frame=new JFrame("通行证设置");
        contentPane=frame.getContentPane();
        //frame.setLocation(600,30);
        frame.setSize(300,300);
        frame.setResizable(false);
        frame.setLayout( new BorderLayout());
        contentPane.setLayout(null);//自由调整布局
        //窗体图标
        //ImageIcon Icon = new ImageIcon("Duke16.gif");
        //frame.setIconImage(Icon.getImage());
        //frame.setLayout(new BorderLayout()); 
        
        Toolkit toolkit=frame.getToolkit();
        Image topicon=toolkit.getImage("SystemImage/page_white_cup.png");
        frame.setIconImage(topicon);
        
        //窗体放右上方
        Dimension dim = Toolkit.getDefaultToolkit().getScreenSize();
        int w = frame.getSize().width;//获取窗体宽度
        int h = frame.getSize().height;//获取窗体高度
        int x = (dim.width-w)/2;
        int y = (dim.height-h)/3;
        frame.setLocation(x,y);//将窗体移到屏幕中间
        frame.setVisible(true);
        
        //email
        emailLabel=new JLabel("Email");
        emailLabel.setBounds(10,80,60,30);
        contentPane.add(emailLabel);
        
        emailField=new JTextField("");
        emailField.setBounds(70,80 ,200,30);
        contentPane.add(emailField);
        
        //password
        passwordLabel=new JLabel("密码");
        passwordLabel.setBounds(10,120,60,30);
        contentPane.add(passwordLabel);
        
        passwordField=new JPasswordField("");
        passwordField.setBounds(70,120 ,200,30);
        contentPane.add(passwordField);
        
        //password
        dpasswordLabel=new JLabel("密码确认");
        dpasswordLabel.setBounds(10,160,60,30);
        contentPane.add(dpasswordLabel);
        
        dpasswordField=new JPasswordField("");
        dpasswordField.setBounds(70,160 ,200,30);
        contentPane.add(dpasswordField);
        
        messageTextField=new JTextArea("");
        messageTextField.setForeground(Color.white);
        messageTextField.setBackground(Color.darkGray);
        messageTextField.setBounds(0,0,290,55);
        messageTextField.setText("        您的Email将唯一确定您在网络中的身份，正确填写唯一属于您的email" +
		"才能正常使用本软件,并防止他人冒充您的身份。");
        messageTextField.setEditable(false);
        messageTextField.setLineWrap(true); 
        contentPane.add(messageTextField);
        messageTextField.setBorder(selectedBorder);
        
        sureButton=new JButton("保存");
        sureButton.setBounds(50,220,80,30);
        contentPane.add(sureButton);
        sureButton.addActionListener( new ActionListener()
        {
              public void actionPerformed(ActionEvent e)
              {
            	 String email = emailField.getText();
            	 String password = passwordField.getText();
            	 boolean emailcheck = emailcheck(email);
            	 if(!emailcheck)
            	 {
            		 JOptionPane.showMessageDialog(null,
    	                     "Email填写错误,请重新填写Email",
    	                     "Email填写错误",
    	                     JOptionPane.INFORMATION_MESSAGE);	
            	 }
            	 else if(!password.equals(dpasswordField.getText()))
            	 {
            		 JOptionPane.showMessageDialog(null,
    	                     "密码填写错误,请重新填写密码",
    	                     "密码填写错误",
    	                     JOptionPane.INFORMATION_MESSAGE);	
            	 }
            	 else
            	 {
            		 String characterset = "<email>"+email+"</email>"+"<password>"+password+"</password>";
	     			 fileoperation = new 	FileOperation();
	     			 fileoperation.savePerformed("EmailPass/EmailPass.txt",characterset);
            	 }           	 
              }	
        }	
        );
        
        cancelButton=new JButton("重置");
        cancelButton.setBounds(160,220,80,30);
        contentPane.add(cancelButton);
        cancelButton.addActionListener( new ActionListener()
        {
              public void actionPerformed(ActionEvent e)
              {
            	  emailField.setText("");
            	  passwordField.setText("");
            	  dpasswordField.setText("");
              }	
        }	
        );
		
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
}
