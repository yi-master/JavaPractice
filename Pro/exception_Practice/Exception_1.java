/*
 * 异常分类
 * 1.检查(编译)性异常(java.lang.Exception)：
 * 程序正确，外部环境条件不满足引发。
 * 如：I/O问题，内存耗尽，机器中毒。
 * 2.运行期异常(java.lang.RuntimeException)：
 * 程序有bug
 * 如：数组越界，0被整除。
 * 3.错误(java.lang.Error)
 */
/*
 * 异常处理方式
 * 1.try...catch
 * 程序运行时产生异常时，将从异常发生点中断程序并向外抛出异常信息
 * 2.finally
 * 如果把finally块置try...catch...语句后，finally块一般都会得到执行
 * 相当于一个万能的保险
 * 即使前面的try块发生异常，而又没有对应异常的catch块，finally块将马上执行
 * finally块不会被执行的情况：
 * 1).finally块中发生了异常
 * 2).程序所在线程死亡
 * 3).在前面的代码中使用了System.exit();
 * 4).关闭CPU
 * 0.try...finally
 */
package exception_Practice;

import java.io.*;
import java.net.*;

public class Exception_1 {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		// 检查异常
		FileReader fr=null;
		try {
			// 打开文件
			fr = new FileReader("d:/eclipse workspace/test.txt");
			
			//在出现异常的地方，就终止执行代码，然后进入到catch语句
			//如果有多个catch语句，则进入匹配异常的那个catch语句
			System.out.println("go on");
			
			// 连接192.168.1.1 ip的端口号9999
			Socket s = new Socket("192.168.256.1", 8888);
		}
		// catch (FileNotFoundException e1) {
		// // TODO Auto-generated catch block
		// // 把异常的信息输出，利于排除bug
		// e.printStackTrace();
		// // 处理
		// } catch (IOException e2) {
		// e2.printStackTrace();
		// }
		catch (Exception e) {
			// 把异常的信息输出，利于排除bug
			System.out.println("message: "+e.getMessage());
			e.printStackTrace();
		}finally{//这个语句块，无论是否有异常，都会执行
			
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
		
		// 运行异常

		// 算术异常
		// int a=4/0;

		// 数组越界
		// int arr[]={2,3,4,5};
		// System.out.println(arr[99]);
	}

}
