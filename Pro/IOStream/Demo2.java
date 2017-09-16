/*
 * FileInputStream类的使用
 * FileInputStream的read(byte[] b)方法参考帮助文档
 * 从此输入流中将最多 b.length 个字节的数据读入一个 byte 数组中。
 * 在某些输入可用之前，此方法将阻塞。
 * b - 存储读取数据的缓冲区。
 * 读入缓冲区的字节总数，如果因为已经到达文件末尾而没有更多的数据，则返回 -1。
/*
/* 
* 内存	<---输入流(Reader(字符流),InputStream(字节流))---	文件
* 内存	---输出流(Writer(字符流),OutputStream(字节流))--->	文件
*/
package IOStream;

import java.io.*;

public class Demo2 {

	public static void main(String[] args) {

		// 得到一个文件对象f指向(->)d:/eclipse workspace/test.txt
		File f = new File("d:/eclipse workspace/testIO/testIO_1.txt");
		FileInputStream fis = null;
		// File没有读写能力，使用InputStream
		try {
			fis = new FileInputStream(f);

			// 定义一个字节数组，相当于缓存
			byte bytes[] = new byte[8];// 文件大小1024B
			int n = 0;// 得到实际读取到的字节数

			// 循环读取
			while ((n = fis.read(bytes)) != -1) {
				/* 这个循环的执行过程如下
				 * 第一次循环读取bytes大小的字节数（read(bytes)）
				 * 1.如果文本内容小于bytes容量，则一次性输出
				 * 2.如果文本内容大于bytes容量，则按bytes的最大容量（最大字节数）输出文本
				 * 3.如果文本中的内容已全部读取，则返回-1
				 * 4.如果未读取完文本中的内容则在判断1、2、3
				 * 如：
				 * 设bytes容量为8个字节：	byte bytes[] = new byte[8];
				 * 设文本内容有10个字节（一个汉字两个字节）：	
				 * 输出(println会换行)：	一二三四
										五六七八
										九十
				 * 
				 */
				
				// 把字节转成String
				String s = new String(bytes, 0, n);
				System.out.println(s);
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} finally {
			// 关闭文件流(一般在这关闭)
			try {
				fis.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
		}
	}
}
