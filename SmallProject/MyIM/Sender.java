
import java.net.*;


public class Sender
{

    private MyIM p2pSys;    
    
    public Sender(MyIM p2pSys)
    
    {
        this.p2pSys=p2pSys;       
    }
    
    public  void sendP2p(String sendString,String destinationIp)
    {
        try
        {

        	/*InetAddress代表IP地址*/
            InetAddress ia=InetAddress.getByName(destinationIp);
            
            /*DatagramSocket代表传送或接收数据报的Socket*/
            DatagramSocket  ds=new DatagramSocket();
            
            /*将字节流作为数组以指定的缓冲区偏移量从指定的列偏移读入到缓冲区中。*/
            byte[] buf = sendString.getBytes() ;
            
            /*DatagramPacket代表数据包*/
            DatagramPacket dp = new DatagramPacket(buf, buf.length, ia, 8888);

            
             

           // System.out.println("in sender.java"+new String(buf));
            ds.send(dp);
            ds.close();


        }
        catch(Exception e)
        {
            System.out.println("error in Sender.java"+e);

        }
    }
    
    /*发送多播消息*/
    public  void sendMutiCast(String s)
    {
            try
            {
	            byte[] b=s.getBytes() ;
	            
	            /*getByName确定主机地址*/
	            InetAddress inetAddress = InetAddress.getByName("234.5.6.7");
	            
	            DatagramPacket datagramPacket = new DatagramPacket(b, b.length, inetAddress, 6789);
	            
	            /*用来发送多播数据包的Socket*/
	            MulticastSocket multicastSocket = new MulticastSocket();
	            
	            /*发送多播数据*/
	            multicastSocket.send(datagramPacket);
	            //    System.out.println(s+"  in Sender.java sendMutiCast()");
	
	            multicastSocket.close() ;
            }
            catch(Exception e)
            {
                p2pSys.modeMsg.addElement("Note:  getting ip is failed Please click getIp button again");
                System.out.println("error in Sender.java's sendMutiCast"+e);
            }

    }

    public static void main(String[] args){

    }
}