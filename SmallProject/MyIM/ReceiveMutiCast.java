import java.net.*;

/*���նಥ��Ϣ*/
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
        	/*�������ͻ���նಥ���ݰ���Socket*/
            MulticastSocket multicastSocket = new MulticastSocket(6789);
            
            
            InetAddress inetAddress = InetAddress.getByName("234.5.6.7");
            
            /*����ಥ��*/
            multicastSocket.joinGroup(inetAddress);

            while(isWork)
            {
                byte [] b = new byte [1000];
                
                /*DatagramSocket�����ͻ�������ݱ���Socket*/
                DatagramPacket datagramPacket = new DatagramPacket(b, b.length);
                
                /*���նಥ��Ϣ*/
                multicastSocket.receive(datagramPacket);
                
                /*���в���*/
                String mutiCastMsg = new String(b);  
                
                /*�����ƶ��ַ�����������0-mutiCastMsg.lastIndexOf('>') +1����Χ֮�ڵ�����*/
                mutiCastMsg=mutiCastMsg.substring(0,mutiCastMsg.lastIndexOf('>') +1);   
                
                //System.out.println(mutiCastMsg.length() );
                //System.out.println("----========="+mutiCastMsg+"receivemuticast.java"); 
                
                /*����һ��ȥ���ո���ַ����Ŀ���*/
                mutiCastMsg=mutiCastMsg.trim() ;  
                
                /*��������ӵ�����*/
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
