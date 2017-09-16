/*
 * FileInputStream���ʹ��
 * FileInputStream��read(byte[] b)�����ο������ĵ�
 * �Ӵ��������н���� b.length ���ֽڵ����ݶ���һ�� byte �����С�
 * ��ĳЩ�������֮ǰ���˷�����������
 * b - �洢��ȡ���ݵĻ�������
 * ���뻺�������ֽ������������Ϊ�Ѿ������ļ�ĩβ��û�и�������ݣ��򷵻� -1��
/*
/* 
* �ڴ�	<---������(Reader(�ַ���),InputStream(�ֽ���))---	�ļ�
* �ڴ�	---�����(Writer(�ַ���),OutputStream(�ֽ���))--->	�ļ�
*/
package IOStream;

import java.io.*;

public class Demo2 {

	public static void main(String[] args) {

		// �õ�һ���ļ�����fָ��(->)d:/eclipse workspace/test.txt
		File f = new File("d:/eclipse workspace/testIO/testIO_1.txt");
		FileInputStream fis = null;
		// Fileû�ж�д������ʹ��InputStream
		try {
			fis = new FileInputStream(f);

			// ����һ���ֽ����飬�൱�ڻ���
			byte bytes[] = new byte[8];// �ļ���С1024B
			int n = 0;// �õ�ʵ�ʶ�ȡ�����ֽ���

			// ѭ����ȡ
			while ((n = fis.read(bytes)) != -1) {
				/* ���ѭ����ִ�й�������
				 * ��һ��ѭ����ȡbytes��С���ֽ�����read(bytes)��
				 * 1.����ı�����С��bytes��������һ�������
				 * 2.����ı����ݴ���bytes��������bytes���������������ֽ���������ı�
				 * 3.����ı��е�������ȫ����ȡ���򷵻�-1
				 * 4.���δ��ȡ���ı��е����������ж�1��2��3
				 * �磺
				 * ��bytes����Ϊ8���ֽڣ�	byte bytes[] = new byte[8];
				 * ���ı�������10���ֽڣ�һ�����������ֽڣ���	
				 * ���(println�ỻ��)��	һ������
										�����߰�
										��ʮ
				 * 
				 */
				
				// ���ֽ�ת��String
				String s = new String(bytes, 0, n);
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// �ر��ļ���(һ������ر�)
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
