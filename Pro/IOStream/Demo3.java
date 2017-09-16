/*
 * FileOutputStream类的使用
 * 
 * 内存	<---输入流(Reader(字符流),InputStream(字节流))---	文件
 * 内存	---输出流(Writer(字符流),OutputStream(字节流))--->	文件
 */
package IOStream;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class Demo3 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub

		File f = new File("d:/eclipse workspace/testIO/testIO_2.txt");
		//如果文件路径不存在则会创建该文件
		
		//字节输出流
		FileOutputStream fos=null;
		try {
			fos=new FileOutputStream(f);
			
			String s="hello world\r\n";
			String s2="hello java";
			//定义一个字节数组
			//byte bytes[]=new byte[1024];
			//把String转换成byte数组
			
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
