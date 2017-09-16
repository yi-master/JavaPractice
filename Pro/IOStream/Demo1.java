/*
 * Java分为两种流
 * 1.字节流：可以用于读写二进制文件及任何类型文件 byte
 * 2.文件流：可以用于读写文本文件，不能操作二进制文件
 * 
 * 内存	<---输入流(Reader(字符流),InputStream(字节流))---	文件
 * 内存	---输出流(Writer(字符流),OutputStream(字节流))--->	文件
 * 
 * 以内存为参照，如果数据向内存流动则是输入流
 * 如果数据离开内存则为输出流
 */
package IOStream;

import java.io.*;

public class Demo1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
//		//创建一个文件对象
//		File f=new File("d:/eclipse workspace/test.txt");
//		//得到文件路径
//		System.out.println("path: "+f.getAbsolutePath());
//		//得到文件大小
//		System.out.println("size: "+f.length());
//		//文件是否可读
//		System.out.println("readable: "+f.canRead());
	
		//创建文件夹
		File folder=new File("d:/eclipse workspace/testIO");
		if(folder.isDirectory()){//判断文件夹是目录
			//是目录(该文件夹存在)
			System.out.println("The folder already exists.");
		}else{
			//创建文件夹
			folder.mkdir();
		}
		
		//创建文件
		File f=new File("d:/eclipse workspace/testIO/testIO_1.txt");
		if(!f.exists()){//判断文件是否存在
			//如果文件不存在
			
			try {
				//创建文件
				f.createNewFile();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}else{
			System.out.println("This file exists. Please change the file path again");
		}
		
		//列出一个文件夹下的所有文件及其路径
		File f2=new File("d:/eclipse workspace/testIO");
		if(f2.isDirectory()){
			File lists[]=f2.listFiles();
			for(int i=0;i<lists.length;i++){
				System.out.println("name: "+lists[i].getName()+"\npath: "+lists[i].getPath());
			}
		}
	}

}
