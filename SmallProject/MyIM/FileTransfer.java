import java.io.BufferedInputStream;
import java.io.BufferedOutputStream;
import java.io.BufferedReader;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.UnknownHostException;

public class FileTransfer 
{
	private MyIM p2pSys;
	private FileDownload filedownload;


	 public FileTransfer(MyIM p2pSys)
	 {
	        this.p2pSys =p2pSys;
	 }
	 
	 public void transferfile()
	 {
		 BufferedReader user; 
	     BufferedReader serverInfo; 
	     Socket skt; 
	     BufferedInputStream fileReader; 
	     String remoteHost; 
	     int port; 
	     String fileName; 
	     PrintStream printStream; 
	
	     try 
	     { 
	         user = new BufferedReader(new InputStreamReader(System.in)); 
	         //System.out.println("简易档案传送v0.1版"); 
	
	         //System.out.print("远端主机: "); 
	         remoteHost = p2pSys.ipTextField.getText().trim(); 
	         //System.out.print("远端主机连接端口: "); 
	         port = Integer.parseInt("8888"); 
	         //System.out.print("要传送的文件: ");
	        
	         //文件准备数组
	         fileName = p2pSys.filehashtable.getHashfileItem(p2pSys.infoResponse.ip);
	         //p2pSys.filehashtable.delHashfileItem(p2pSys.infoResponse.ip);
	         //fileName = p2pSys.fileItem.firstElement().toString().trim();
	         //p2pSys.fileItem.remove(0);
	         
	         System.out.println("尝试与 " + remoteHost + " 连接...."); 
	         skt = new Socket(remoteHost, port); 
	
	         System.out.println("连接成功！尝试传送文件...."); 
	         serverInfo = new BufferedReader( 
	                        new InputStreamReader(skt.getInputStream())); 
	         printStream = new PrintStream(skt.getOutputStream()); 
	         printStream.println(fileName); 	         
	
	         System.out.print("OK! 传送文件...."); 
	         fileReader = new BufferedInputStream( 
	                          new FileInputStream(fileName)); 
	
	         int readFile; 
	         while((readFile = fileReader.read()) != -1) { 
	              //System.out.print("."); 
	              printStream.write(readFile); 
	         } 
	
	         fileReader.close(); 
	         printStream.flush(); 
	         skt.close(); 
	         System.out.println("\n文件传送文件！"); 
	     } 
	     catch(UnknownHostException e) { 
	         System.out.println(e.toString()); 
	     } 
	     catch(IOException e) { 
	         System.out.println(e.toString()); 
	     } 
	 } 
	 
	 public void receivefile()
	 {
	    	BufferedReader user; 
	        int port; 
	        ServerSocket serverSkt; 
	        Socket clientSkt; 
	        BufferedReader fileInfo; 
	        BufferedInputStream fileReader; 
	        BufferedOutputStream fileWriter; 
	        String fileName; 
	        PrintStream printStream; 
	      
	        try 
	        { 
	        	

	                System.out.println("倾听中...."); 
	                clientSkt = p2pSys.serverSkt.accept(); 
	                printStream = new PrintStream(clientSkt.getOutputStream()); 

	                fileInfo = new BufferedReader(new InputStreamReader(clientSkt.getInputStream())); 
	                
	                fileName = p2pSys.filehashtable.getHashfileItem(p2pSys.infoResponse.ip);
	                //fileName = p2pSys.refileItem.firstElement().toString().trim(); 
	  	                
	                System.out.println("与" +  clientSkt.getInetAddress() + "建立连接"); 

	                
	                System.out.print("\nOK! 接收文件 " + fileName); 
	                fileReader = new BufferedInputStream(clientSkt.getInputStream());	                    
	                fileWriter = new BufferedOutputStream(new FileOutputStream(fileName+"1")); 
	                int readin; 
	                while((readin = fileReader.read()) != -1) 
	                { 
	                        //System.out.print("."); 
	                    fileWriter.write(readin); 
	                } 

	                fileReader.close(); 
	                fileWriter.flush(); 
	                clientSkt.close(); 
	                p2pSys.modeMsg.addElement("\n文件接收完毕！");
	                p2pSys.modeMsg3.addElement("\n文件接收完毕！");
	                System.out.println("\n文件接收完毕！");
	                
	                p2pSys.infoResponse.filedownload.hide();
	                System.out.println(p2pSys.receivefileover);
	                p2pSys.filehashtable.delHashrefileItem(p2pSys.infoResponse.filedownload.fromLabel.getText().toString().trim());
	                //p2pSys.refileItem.remove(0);
	              
	             
	        } 
	        catch(IOException e) 
	        { 
	            System.out.println(e.toString()); 
	        } 
	    }
}
