/*
 * �ļ��ֽ�����ͼƬcopy
 * 
 * �ڴ�	<---������(Reader(�ַ���),InputStream(�ֽ���))---	�ļ�
 * �ڴ�	---�����(Writer(�ַ���),OutputStream(�ֽ���))--->	�ļ�
 */
package IOStream;

import java.io.*;

public class Demo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.��ͼƬ�����ڴ棬д�뵽ĳ���ļ�
		//2.�������ļ�ֻ�����ֽ������
		
		//File f1=new File("D:/eclipse workspace/testIO/title.PNG");
		//������
		FileInputStream fis=null;
		//�����
		FileOutputStream fos=null;
		
		try {
			//fis=new FileInputStream(f1);
			fis = new FileInputStream("D:/eclipse workspace/testIO/title.PNG");
			fos=new FileOutputStream("E:/NEW_title.PNG");//����ļ���·��������ڣ��ļ����ɸ���
			byte bytes[]=new byte[512];
			int n=0;//��¼ʵ�ʶ�ȡ�����ֽ���
			//ѭ����ȡ
			while((n=fis.read(bytes))!=-1){
				//�����ָ���ļ�
				fos.write(bytes);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//�رմ򿪵��ļ���
				fis.close();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
