import java.net.*;

/*接收多播消息*/
public class ReceiveMutiCast extends Thread
{
    private MyIM p2pSys;
    private boolean isWork=true;

    public ReceiveMutiCast(MyIM p2pSys)
    {
        this.p2pSys=p2pSys;
    }
    public void stopWork(){
           isWork=false;

       }

   public synchronized void run()
   {
        try
        {
        	/*用来发送或接收多播数据包的Socket*/
            MulticastSocket multicastSocket = new MulticastSocket(6789);
            
            
            InetAddress inetAddress = InetAddress.getByName("234.5.6.7");
            
            /*加入多播组*/
            multicastSocket.joinGroup(inetAddress);

            while(isWork)
            {
                byte [] b = new byte [1000];
                
                /*DatagramSocket代表传送或接收数据报的Socket*/
                DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
                
                /*接收多播消息*/
                multicastSocket.receive(datagramPacket);
                
                /*队列操作*/
                String mutiCastMsg = new String(b);  
                
                /*返回制定字符串的子链（0-mutiCastMsg.lastIndexOf('>') +1）范围之内的数据*/
                mutiCastMsg=mutiCastMsg.substring(0,mutiCastMsg.lastIndexOf('>') +1);   
                
                //System.out.println(mutiCastMsg.length() );
                //System.out.println("----========="+mutiCastMsg+"receivemuticast.java"); 
                
                /*返回一个去掉空格的字符串的拷贝*/
                mutiCastMsg=mutiCastMsg.trim() ;  
                
                /*将拷贝添加到队列*/
                p2pSys.infoQueue .addElement (mutiCastMsg);
                    
             }

        }
        catch(Exception e)
        {
            System.out.println("error in receivemuticast  "+e) ;

        }
    }

    public static void main(String[] args)
    {

    }

}
