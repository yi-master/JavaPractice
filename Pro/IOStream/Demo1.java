/*
 * Java��Ϊ������
 * 1.�ֽ������������ڶ�д�������ļ����κ������ļ� byte
 * 2.�ļ������������ڶ�д�ı��ļ������ܲ����������ļ�
 * 
 * �ڴ�	<---������(Reader(�ַ���),InputStream(�ֽ���))---	�ļ�
 * �ڴ�	---�����(Writer(�ַ���),OutputStream(�ֽ���))--->	�ļ�
 * 
 * ���ڴ�Ϊ���գ�����������ڴ���������������
 * ��������뿪�ڴ���Ϊ�����
 */
package IOStream;

import java.io.*;

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		//����һ���ļ�����
//		File f=new File("d:/eclipse workspace/test.txt");
//		//�õ��ļ�·��
//		System.out.println("path: "+f.getAbsolutePath());
//		//�õ��ļ���С
//		System.out.println("size: "+f.length());
//		//�ļ��Ƿ�ɶ�
//		System.out.println("readable: "+f.canRead());
	
		//�����ļ���
		File folder=new File("d:/eclipse workspace/testIO");
		if(folder.isDirectory()){//�ж��ļ�����Ŀ¼
			//��Ŀ¼(���ļ��д���)
			System.out.println("The folder already exists.");
		}else{
			//�����ļ���
			folder.mkdir();
		}
		
		//�����ļ�
		File f=new File("d:/eclipse workspace/testIO/testIO_1.txt");
		if(!f.exists()){//�ж��ļ��Ƿ����
			//����ļ�������
			
			try {
				//�����ļ�
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("This file exists. Please change the file path again");
		}
		
		//�г�һ���ļ����µ������ļ�����·��
		File f2=new File("d:/eclipse workspace/testIO");
		if(f2.isDirectory()){
			File lists[]=f2.listFiles();
			for(int i=0;i<lists.length;i++){
				System.out.println("name: "+lists[i].getName()+"\npath: "+lists[i].getPath());
			}
		}
	}

}
