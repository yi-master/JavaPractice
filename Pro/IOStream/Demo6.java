/*
 * �����ַ���
 * �����ַ���Ч�ʸ�
 * BufferedReader��ȡһ������ʱ��
 * BufferedWriterд��һ������ʱ��
 */
package IOStream;

import java.io.*;

public class Demo6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br=null;
		BufferedWriter bw=null;
		
		try {
			//1.�ȴ���FileReader��
			//2.�ٴ���BufferReader��
			FileReader fr=new FileReader("D:/eclipse workspace/testIO/testIO_1.txt");
			br = new BufferedReader(fr);
			//����FileWriter����
			FileWriter fw=new FileWriter("E:/123.txt");
			//����BufferedWriter����
			bw=new BufferedWriter(fw);
			//ѭ����ȡ
			String s="";
			while((s=br.readLine())!=null){
				//System.out.println(s);
				//���������
				bw.write(s+"\r\n");
			}
		} catch (Exception e) {
			// TODO: handle exception
		}finally {
			try {
				br.close();
				bw.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
	}

}
