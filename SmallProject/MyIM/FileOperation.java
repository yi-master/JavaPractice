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
                           //"�����µ���'����'��ť",
                           //"���óɹ�",
                           //JOptionPane.INFORMATION_MESSAGE);	


            } 


            catch (IOException e)
            {
            	JOptionPane.showMessageDialog(null,
                        "����������",
                        "����ʧ��",
                        JOptionPane.INFORMATION_MESSAGE);	
                  // display.setText("IOERROR: " + e.getMessage() + "\n");
                   e.printStackTrace();
            }
            


     }//savePerformed
	 
	 public void emailfriendsavePerformed(String fileName,String emailfriend) throws IOException
     {
		 RandomAccessFile rf=new RandomAccessFile(fileName,"rw"); 
		 //����һ����RandomAccessFile�Ķ��󣬲�ʵ���� 
		 rf.seek(rf.length());//��ָ���ƶ����ļ�ĩβ 
		 rf.writeBytes(emailfriend+"\n"); 
		 rf.close();//�ر��ļ��� 

     }

}
