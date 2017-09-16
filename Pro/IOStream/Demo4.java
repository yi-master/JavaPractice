/*
 * 文件字节流：图片copy
 * 
 * 内存	<---输入流(Reader(字符流),InputStream(字节流))---	文件
 * 内存	---输出流(Writer(字符流),OutputStream(字节流))--->	文件
 */
package IOStream;

import java.io.*;

public class Demo4 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//1.把图片读入内存，写入到某个文件
		//2.二进制文件只能用字节流完成
		
		//File f1=new File("D:/eclipse workspace/testIO/title.PNG");
		//输入流
		FileInputStream fis=null;
		//输出流
		FileOutputStream fos=null;
		
		try {
			//fis=new FileInputStream(f1);
			fis = new FileInputStream("D:/eclipse workspace/testIO/title.PNG");
			fos=new FileOutputStream("E:/NEW_title.PNG");//这个文件夹路径必须存在，文件名可更改
			byte bytes[]=new byte[512];
			int n=0;//记录实际读取到的字节数
			//循环读取
			while((n=fis.read(bytes))!=-1){
				//输出到指定文件
				fos.write(bytes);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}finally{
			try {
				//关闭打开的文件流
				fis.close();
				fos.close();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
		
		
	}

}
