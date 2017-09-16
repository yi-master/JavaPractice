
import java.net.DatagramSocket;
import java.net.DatagramPacket;

//接收点到点消息
public class ReceiveP2p extends Thread
{
    private MyIM  p2pSys;
    byte[] buf = new byte[1000];
    DatagramSocket ds;
    DatagramPacket ip;
    XmlFormat xmlFormat=new XmlFormat();
    boolean isWork=true;

   public void stopWork(){
        isWork=false;

    }


    public ReceiveP2p(MyIM p2pSys){
        this.p2pSys=p2pSys;

    }

    public void run(){

        try{
            ds=new DatagramSocket(8888);
            ip=new DatagramPacket(buf,buf.length );
            while(isWork){
                ds.receive(ip);
                /*将信息加入队列*/
                p2pSys.infoQueue .addElement (new String(ip.getData()) );
               // System.out.println("In Receivepwp.java  "+p2pSys.infoQueue .getElement() );

            }
            ds.close() ;

        }
        catch(Exception e)
        {
            System.out.println("error in receivep2p.java"+e);           
        }
    }


}
