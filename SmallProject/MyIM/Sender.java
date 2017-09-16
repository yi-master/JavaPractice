
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

        	/*InetAddress����IP��ַ*/
            InetAddress ia=InetAddress.getByName(destinationIp);
            
            /*DatagramSocket�����ͻ�������ݱ���Socket*/
            DatagramSocket  ds=new DatagramSocket();
            
            /*���ֽ�����Ϊ������ָ���Ļ�����ƫ������ָ������ƫ�ƶ��뵽�������С�*/
            byte[] buf = sendString.getBytes() ;
            
            /*DatagramPacket�������ݰ�*/
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
    
    /*���Ͷಥ��Ϣ*/
    public  void sendMutiCast(String s)
    {
            try
            {
	            byte[] b=s.getBytes() ;
	            
	            /*getByNameȷ��������ַ*/
	            InetAddress inetAddress = InetAddress.getByName("234.5.6.7");
	            
	            DatagramPacket datagramPacket = new DatagramPacket(b, b.length, inetAddress, 6789);
	            
	            /*�������Ͷಥ���ݰ���Socket*/
	            MulticastSocket multicastSocket = new MulticastSocket();
	            
	            /*���Ͷಥ����*/
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