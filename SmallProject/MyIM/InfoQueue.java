
import java.util.Vector;

public class InfoQueue
{
	/*��Ϣ��������*/
    private Vector msgQueue=new Vector(10,10);

    public synchronized String getElement()
    {
    	/*firstElement����������ĵ�һ������
    	 *toString,���ַ�����ʽ����
    	 *trim��ȥ���ո񷵻��ַ���*/
        return msgQueue.firstElement() .toString() .trim() ;
    }

    public synchronized void addElement(String content)
    {
        /*�����Ϣ*/
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