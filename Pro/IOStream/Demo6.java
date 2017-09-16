/*
 * 缓冲字符流
 * 处理字符快效率高
 * BufferedReader读取一行数据时用
 * BufferedWriter写入一行数据时用
 */
package IOStream;

import java.io.*;

public class Demo6 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		BufferedReader br=null;
		BufferedWriter bw=null;
		
		try {
			//1.先创建FileReader类
			//2.再创建BufferReader类
			FileReader fr=new FileReader("D:/eclipse workspace/testIO/testIO_1.txt");
			br = new BufferedReader(fr);
			//创建FileWriter对象
			FileWriter fw=new FileWriter("E:/123.txt");
			//创建BufferedWriter对象
			bw=new BufferedWriter(fw);
			//循环读取
			String s="";
			while((s=br.readLine())!=null){
				//System.out.println(s);
				//输出到磁盘
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
