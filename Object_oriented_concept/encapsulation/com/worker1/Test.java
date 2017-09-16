/*
 * 包的作用
 * 1.区分相同名字类
 * 2.当类很多时，可以很好的管理类
 * 3.控制访问范围
 * 
 * 打包命令一般放在文件开始处
 */
package com.worker1;

import java.util.*;

import com.worker2.*;

public class Test {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		HashMap hm=new HashMap();
		Cat cat1=new Cat();
		System.out.println(cat1.getName());
	}

}
class Dog{
	
}