
import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.InetAddress;
import java.net.DatagramSocket;
import java.net.DatagramPacket;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

import javax.swing.ImageIcon;
import javax.swing.JEditorPane;

/*  信息响应  */

public class InfoResponse extends Thread
{
    private MyIM p2pSys;
    private String msg="";
    private boolean isWork=true;
    private XmlFormat xmlFormat=new XmlFormat();
    private XmlParse xmlParse=new XmlParse();
    String ip;
    String hostname;
    String face;
    FileDownload filedownload;
    private SimpleReceiveSys simplereceivesys;
    int NO = 0;
    
    public InfoResponse(MyIM p2pSys)
    {
        this.p2pSys =p2pSys;
    }
    public void stopWork()
    {
           isWork=false;

    }

    /*synchronized关键字就可以解决多线程共享数据同步问题
     *addIpList:添加IP到列表中 */
    private synchronized void addIpList(IconListItem item,String ip,String friendemail)
    {
    	int i;

        if(p2pSys.modeIp .isEmpty() )
        {
            p2pSys.modeIp .addElement(item);
            p2pSys.myfriend[NO] = ip;
            
            System.out.println("\n uuuuuu i="+NO);
        	System.out.println("\n uuuuuu p2pSys.myfriend="+p2pSys.myfriend[NO]);
        	System.out.println("\n uuuuuu p2pSys.ipItem="+p2pSys.ipItem.get(ip));
        	
        	NO = NO+1;
        	p2pSys.ipItem.put(ip,ip);
        }
        else 
		{
            for(i=0;i<p2pSys.modeIp .size();i++ )
			{

                if(p2pSys.myfriend[i].equals(ip) )
                    break;
			}
            if(i>=p2pSys.modeIp .size() )
			{
            	p2pSys.modeIp .addElement(item);
                p2pSys.myfriend[NO] = ip;
                
                System.out.println("\n uuuuuu i="+NO);
            	System.out.println("\n uuuuuu p2pSys.myfriend="+p2pSys.myfriend[NO]);
            	System.out.println("\n uuuuuu p2pSys.ipItem="+p2pSys.ipItem.get(ip));
            	
            	NO = NO+1;
            	p2pSys.ipItem.put(ip,ip);
            }
        }
        
    }

    private synchronized boolean myfriendcheck(String friendemail)
    {
    	int nfriend = 0;
    	boolean ismyfriend = false;
    	while(!p2pSys.myfriendemail[nfriend].equals(""))
    	{
    		if(p2pSys.myfriendemail[nfriend].equals(friendemail))
    		{
    			ismyfriend = true;
    			break;
    		}
    		System.out.println(p2pSys.myfriendemail[nfriend] + "  myfriendemail"+nfriend);
    		nfriend = nfriend + 1;
    	}
    	System.out.println(ismyfriend);
		return ismyfriend;
    }
    
    private synchronized void getIp()
    {
        try{
        	 /*toString,以字符串方式返回
        	  *trim，去掉空格返回字符串
        	  *解析XML文件中<ip>标签*/        
             String ip=xmlParse .parse(msg,"ip").trim() ;
             String hostname=xmlParse .parse(msg,"hostname");
             String face = xmlParse .parse(msg,"face").trim() ;
             String friendemail = null;
             friendemail = xmlParse .parse(msg,"email").trim() ;
             System.out.println(ip + " cccc ip");
            
             /*获得IP地址*/
             InetAddress inetAddress=InetAddress.getByName(ip);
             System.out.println("MY IP "+ip);
             /*将字节流作为数组以指定的缓冲区偏移量从指定的列偏移读入到缓冲区中。*/
             String sendString="";//inetAddress.toString() +"  is online  !!!!";
             
            
             /*addIpList:添加IP到列表中*/
             p2pSys.item = new IconListItem(new ImageIcon(face),hostname);     
             addIpList(p2pSys.item,ip,friendemail);
             
             
             /*DatagramSocket代表传送或接收数据报的Socket*/
             DatagramSocket  ds=new DatagramSocket();
            
             /*获得本地地址,/192.168.50.31*/
             String sAddress=InetAddress.getLocalHost() .toString() ;
             
             /*截取从/后第一个数字开始的全部地址*/
             sAddress=sAddress.substring(sAddress.indexOf('/')+1 );
             
             String myhostname =  "   "+p2pSys.myName ;
              
             /*格式化sendString信息，xmlFormat通用方法ip，msg格式化*/
             sendString=xmlFormat.xmlForm("p2p",sendString,"",sAddress ,"8888",myhostname,p2pSys.myface,p2pSys.myemail);
             
            /*将字节流作为数组以指定的缓冲区偏移量从指定的列偏移读入到缓冲区中。*/
             byte[] buf = sendString.getBytes();
             
             /*DatagramPacket代表数据包*/
             DatagramPacket dp = new DatagramPacket(buf, buf.length,inetAddress, 8888);
             
             ds.send(dp);
             
             ds.close();
             
        }
        catch(Exception e)
        {
             System.out.println("error in inforesponse.java "+e);
        }
   }

   /*p2p信息相应*/
    public synchronized void p2p()
    {
    	p2pSys.modeMsg.clear();
    	/*解析出ip地址*/
        ip= xmlParse.parse(msg,"ip");
        
        /* 截取从/后第一个数字开始的全部地址 */
        ip=ip.substring(ip.indexOf('/')+1 )  ;
        hostname=xmlParse .parse(msg,"hostname");
        face = xmlParse .parse(msg,"face").trim() ;
        String friendemail = xmlParse .parse(msg,"email").trim() ;
        
        //p2pSys.ipItem.put(ip,ip);
        
        p2pSys.item = new IconListItem(new ImageIcon(face),hostname);   
        /*添加到地址列表*/
        addIpList(p2pSys.item,ip,friendemail);
        System.out.println(ip + " cccc ip");
        
        /*解析出msg*/
        String content=xmlParse.parse(msg,"content");
        content = content.toString();
        String localip = p2pSys.getLocalIp() ;
        p2pSys.receivemsg = content;
        
        /*在消息区添加msg信息*/
        if(!content.equals("sure")&&!content.equals("fileok"))
        {		
        	/*在消息区添加from:ip信息*/
        	p2pSys.modeMsg .addElement("From:  "+ip);
        	p2pSys.modeMsg.addElement(content);
        	
        	p2pSys.modeMsg3.addElement("From:  "+ip);
        	p2pSys.modeMsg3.addElement(content);
        	
        	if(p2pSys.SimpleP2pSysShow == 1&&!content.equals(""))
        	{
        		simplereceivesys = new SimpleReceiveSys(p2pSys);
        		simplereceivesys.frame.show();
        	}
        	
        }
        else if(content.equals("sure"))
        {
        	
        	p2pSys.fileTransfer.transferfile();
        	
        	//p2pSys.fileButton.setEnabled(true);
        	//p2pSys.sendButton.setEnabled(true);
        	
        	String string=xmlFormat.xmlForm("p2p","fileok","",localip,"8888","",p2pSys.myface,p2pSys.myemail);
        	p2pSys.sender.sendP2p(string,ip);
        	System.out.println(ip);
        }
        else if(content.equals("fileok"))
        {
        	p2pSys.fileTransfer.receivefile();
        }
        
        
        /*解析出file*/
        String file=xmlParse.parse(msg,"file");
        
        if(!file.equals(""))
        {
        	filedownload=new FileDownload(ip,file,p2pSys);
        	p2pSys.filehashtable.addHashrefileItem(ip,file);
        	//p2pSys.refileItem.addElement(file);
        	filedownload.show();
        	p2pSys.modeMsg.addElement("file:  "+file); 
        	p2pSys.modeMsg3.addElement("file:  "+file); 
        }
        
    }
    
    
    /*退出Send*/
    private synchronized void exitSys()
    {
    	/*解析出ip地址*/
        String ip=xmlParse.parse(msg,"ip");
         
        ip=ip.substring(ip.indexOf('/')+1 )  ;
        
        /*解析出msg*/
        String content=xmlParse.parse(msg,"content") ;
        
        /*在消息区添加 Note: ip content信息*/
        p2pSys.modeMsg .addElement("Note:   "+ip+"   "+content);

        //System.out.println("in exitSys  "+content);

        int i;
        
        for(i=0;i<p2pSys.modeIp .size();i++ )
        {
                if(p2pSys.modeIp .getElementAt(i).toString().trim() .equals(ip) )
                {
                    p2pSys.modeIp .remove(i);
                }
        }
        if(p2pSys.ipTextField .getText() .toString() .trim() .equals(ip) )
        {
           p2pSys.ipTextField .setText("");
        }
    }



    public  synchronized void run()
    {
        while(isWork)
        {
            try
            {
                sleep(100);
            }
            catch(Exception e)
            {
                System.out.println("error"+e);
            }

            if(!p2pSys.infoQueue .isEmpty() )
            {
                msg=p2pSys.infoQueue .getElement() .trim() ;
                p2pSys.infoQueue .delElement() ;
                String parseResult=xmlParse .parse(msg,"type").trim() ;
                if(parseResult.equals("getIp") )
                {
                    getIp();

                    continue;
                }

                if(parseResult.equals("exitSys") )
                {
                    exitSys();
                    continue;
                }
                if(parseResult.equals("p2p"))
                {
                	
                    p2p();
                    continue;

                }

            }
        }
    }   
    
}
