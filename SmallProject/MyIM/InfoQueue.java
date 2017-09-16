
import java.util.Vector;

public class InfoQueue
{
	/*消息队列数组*/
    private Vector msgQueue=new Vector(10,10);

    public synchronized String getElement()
    {
    	/*firstElement，返回数组的第一个数据
    	 *toString,以字符串方式返回
    	 *trim，去掉空格返回字符串*/
        return msgQueue.firstElement() .toString() .trim() ;
    }

    public synchronized void addElement(String content)
    {
        /*添加信息*/
        msgQueue.addElement(content);
    }

    public synchronized void delElement()
    {
        msgQueue.remove(0);

    }

    public synchronized boolean isEmpty()
    {
        if(msgQueue.isEmpty() )
            return true;
        else
            return  false;
    }
}