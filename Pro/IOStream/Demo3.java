/*
 * FileOutputStream���ʹ��
 * 
 * �ڴ�	<---������(Reader(�ַ���),InputStream(�ֽ���))---	�ļ�
 * �ڴ�	---�����(Writer(�ַ���),OutputStream(�ֽ���))--->	�ļ�
 */
package IOStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File f = new File("d:/eclipse workspace/testIO/testIO_2.txt");
		//����ļ�·����������ᴴ�����ļ�
		
		//�ֽ������
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(f);
			
			String s="hello world\r\n";
			String s2="hello java";
			//����һ���ֽ�����
			//byte bytes[]=new byte[1024];
			//��Stringת����byte����
			
			fos.write(s.getBytes());
			fos.write(s2.getBytes());
		} catch (Exception e) {
			// TODO: handle exception
		}finally{
			try {
				fos.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
