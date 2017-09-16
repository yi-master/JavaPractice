import java.util.Vector;

/*HTML格式化*/
public class HTMLFormat 
{
	 private String HTMLString;
	
	 public HTMLFormat()
	 {
		
	 }
	 //字符串翻转
	 public String ChangeArray(String newstr)
	 {
			char[] changArray=newstr.toCharArray(); 
			char temp; 
			for(int i=0;i<changArray.length/2;i++) 
			{ 
			temp=changArray[i]; 
			changArray[i]=changArray[changArray.length-i-1]; 
			changArray[changArray.length-i-1]=temp; 
			} 
			newstr=new String(changArray); 
			return newstr;
			
	 }
	 
	 //在字符串中插入值
		public String brArray(String str)
		{
			String[] strsplitArray = str.split("",str.length()+1);
			String newstr = "";
	        int n=25;
	        String tab = "<br>";
	        tab = ChangeArray(tab);
	        
	        for(int i=0;i<=str.length();i++)
	        {
	       	
	       	 if(i==n)
	       	 {
	       		 newstr = tab+strsplitArray[i]+newstr;
	       		 n=n+25;
	       		 //System.err.println(n); 
	       	 }
	       	 else
	       	 {
	       		 newstr = strsplitArray[i]+newstr;
	       	 }
	       	
	       
	        }
	        newstr = ChangeArray(newstr);
	        return newstr;
			
		}
	 
	 public String HTMLForm(String  face,int size,String str)
	 {
		 String newstr = "";
		 newstr = brArray(str);
         
		HTMLString = 
			                    "<font" +
					               "  face="+
					               face+
					               "  size="+
					               size+
					               ">"+
					               newstr+
			                    "</font>"  
		               ;
		 return HTMLString;	 
	 }


}
