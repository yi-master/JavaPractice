/*
 * �쳣����
 * 1.���(����)���쳣(java.lang.Exception)��
 * ������ȷ���ⲿ��������������������
 * �磺I/O���⣬�ڴ�ľ��������ж���
 * 2.�������쳣(java.lang.RuntimeException)��
 * ������bug
 * �磺����Խ�磬0��������
 * 3.����(java.lang.Error)
 */
/*
 * �쳣����ʽ
 * 1.try...catch
 * ��������ʱ�����쳣ʱ�������쳣�������жϳ��������׳��쳣��Ϣ
 * 2.finally
 * �����finally����try...catch...����finally��һ�㶼��õ�ִ��
 * �൱��һ�����ܵı���
 * ��ʹǰ���try�鷢���쳣������û�ж�Ӧ�쳣��catch�飬finally�齫����ִ��
 * finally�鲻�ᱻִ�е������
 * 1).finally���з������쳣
 * 2).���������߳�����
 * 3).��ǰ��Ĵ�����ʹ����System.exit();
 * 4).�ر�CPU
 * 0.try...finally
 */
package exception_Practice;

import java.io.*;
import java.net.*;

public class Exception_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// ����쳣
		FileReader fr=null;
		try {
			// ���ļ�
			fr = new FileReader("d:/eclipse workspace/test.txt");
			
			//�ڳ����쳣�ĵط�������ִֹ�д��룬Ȼ����뵽catch���
			//����ж��catch��䣬�����ƥ���쳣���Ǹ�catch���
			System.out.println("go on");
			
			// ����192.168.1.1 ip�Ķ˿ں�9999
			Socket s = new Socket("192.168.256.1", 8888);
		}
		// catch (FileNotFoundException e1) {
		// // TODO Auto-generated catch block
		// // ���쳣����Ϣ����������ų�bug
		// e.printStackTrace();
		// // ����
		// } catch (IOException e2) {
		// e2.printStackTrace();
		// }
		catch (Exception e) {
			// ���쳣����Ϣ����������ų�bug
			System.out.println("message: "+e.getMessage());
			e.printStackTrace();
		}finally{//������飬�����Ƿ����쳣������ִ��
			
			System.out.println("Enter finally block.");
			if(fr!=null){
				try {
					fr.close();
				} catch (IOException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
		}
		
		System.out.println("OK");
		
		// �����쳣

		// �����쳣
		// int a=4/0;

		// ����Խ��
		// int arr[]={2,3,4,5};
		// System.out.println(arr[99]);
	}

}
