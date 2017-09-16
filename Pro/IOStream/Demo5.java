/*
 * 
 * �ļ��ַ���
 * FileReader��̳�Reader�࣬����ʹ��Reader���е�read(char[] c)����
 * ���ַ��������顣��ĳ��������á����� I/O ��������ѵ�������ĩβǰ���˷���һֱ������
 * ��ȡ���ַ���������ѵ�������ĩβ���򷵻� -1 
 * �������ĵ�ʹ�ü��ɣ����������û����Ҫ�ķ����������ڸ�����ȥѰ�ң�
 * 
 * �ڴ�	<---������(Reader(�ַ���),InputStream(�ֽ���))---	�ļ�
 * �ڴ�	---�����(Writer(�ַ���),OutputStream(�ֽ���))--->	�ļ�
 */
package IOStream;

import java.io.*;

public class Demo5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//�ļ���ȡ�ַ���������������
		FileReader fr=null;
		//�ļ�д���ַ��������������
		FileWriter fw=null;
		
		try{
			//��������fr����
			fr=new FileReader("D:/eclipse workspace/testIO/testIO_1.txt");
			//�������fw����
			fw=new FileWriter("E:/123.txt");
			
			//���뵽�ڴ�
			char c[]=new char[1024];
			int n=0;//��¼ʵ�ʶ�ȡ���ַ���
			while((n=fr.read(c))!=-1){
//				String s=new String(c,0,n);
//				System.out.println(c);
				fw.write(c,0,n);
			}
			
		}catch (Exception e) {
			// TODO: handle exception
		}finally {

			try {
				fr.close();
				fw.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}

}
