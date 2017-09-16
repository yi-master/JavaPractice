import java.awt.Component;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.RandomAccessFile;

import javax.swing.JOptionPane;

public class FileOperation 
{
	public FileOperation()
	{
		
	}
	
	public String openPerformed(String fileName) throws IOException
    {
		 String line = null;
           try
           {
                  BufferedReader inStream = new BufferedReader(new FileReader(fileName));
                  
                  line = inStream.readLine();
                  inStream.close();
           }


           catch(FileNotFoundException e)
           {
                  e.printStackTrace();
           }
		   return line;


    } //openPerformed
	
	 public void savePerformed(String fileName,String CharacterSet)
     {
            try
            {

                   FileWriter outStream = new FileWriter(fileName);

                   outStream.write(CharacterSet);
                  
                   outStream.close();
                   
                   //JOptionPane.showMessageDialog(null,
                           //"请重新单击'启动'按钮",
                           //"设置成功",
                           //JOptionPane.INFORMATION_MESSAGE);	


            } 


            catch (IOException e)
            {
            	JOptionPane.showMessageDialog(null,
                        "请重新设置",
                        "设置失败",
                        JOptionPane.INFORMATION_MESSAGE);	
                  // display.setText("IOERROR: " + e.getMessage() + "\n");
                   e.printStackTrace();
            }
            


     }//savePerformed
	 
	 public void emailfriendsavePerformed(String fileName,String emailfriend) throws IOException
     {
		 RandomAccessFile rf=new RandomAccessFile(fileName,"rw"); 
		 //定义一个类RandomAccessFile的对象，并实例化 
		 rf.seek(rf.length());//将指针移动到文件末尾 
		 rf.writeBytes(emailfriend+"\n"); 
		 rf.close();//关闭文件流 

     }

}
