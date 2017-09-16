/*
 * 
 * 文件字符流
 * FileReader类继承Reader类，可以使用Reader类中的read(char[] c)方法
 * 将字符读入数组。在某个输入可用、发生 I/O 错误或者已到达流的末尾前，此方法一直阻塞。
 * 读取的字符数，如果已到达流的末尾，则返回 -1 
 * （帮助文档使用技巧，如果子类中没有需要的方法，可以在父类中去寻找）
 * 
 * 内存	<---输入流(Reader(字符流),InputStream(字节流))---	文件
 * 内存	---输出流(Writer(字符流),OutputStream(字节流))--->	文件
 */
package IOStream;

import java.io.*;

public class Demo5 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		//文件读取字符流对象（输入流）
		FileReader fr=null;
		//文件写入字符流对象（输出流）
		FileWriter fw=null;
		
		try{
			//创建输入fr对象
			fr=new FileReader("D:/eclipse workspace/testIO/testIO_1.txt");
			//创建输出fw对象
			fw=new FileWriter("E:/123.txt");
			
			//读入到内存
			char c[]=new char[1024];
			int n=0;//记录实际读取的字符数
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
