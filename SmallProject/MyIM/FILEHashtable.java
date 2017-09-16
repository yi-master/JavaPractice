import java.util.Hashtable;

public class FILEHashtable 
{
	private Hashtable fileItem = new Hashtable(10,(float) 0.8);
	private Hashtable refileItem = new Hashtable(10,(float) 0.8);
	
	public synchronized String getHashfileItem(String ip)
    {
		
		return (String) fileItem.get(ip);	
    }
	public synchronized String getHashrefileItem(String ip)
    {
		
		return (String) refileItem.get(ip);	
    }
	
	public synchronized void addHashfileItem(String ip,String file)
    {
        /*添加信息*/
		fileItem.put(ip,file);
    }
	public synchronized void addHashrefileItem(String ip,String file)
    {
        /*添加信息*/
		refileItem.put(ip,file);
    }
	
    public synchronized void delHashfileItem(String ip)
    {
    	fileItem.remove(ip);
    }
    public synchronized void delHashrefileItem(String ip)
    {
    	refileItem.remove(ip);
    }
	

}
